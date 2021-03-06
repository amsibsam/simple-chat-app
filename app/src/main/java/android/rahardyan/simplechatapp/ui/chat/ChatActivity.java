package android.rahardyan.simplechatapp.ui.chat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.rahardyan.simplechatapp.base.BaseActivity;
import android.rahardyan.simplechatapp.model.Comment;
import android.os.Bundle;
import android.rahardyan.simplechatapp.R;
import android.rahardyan.simplechatapp.model.CommentDetail;
import android.rahardyan.simplechatapp.model.CommentRequestBody;
import android.rahardyan.simplechatapp.model.WebSocketMessage;
import android.rahardyan.simplechatapp.ui.chat.adapter.ChatAdapter;
import android.rahardyan.simplechatapp.ui.chat.presenter.ChatContract;
import android.rahardyan.simplechatapp.ui.chat.presenter.ChatPresenter;
import android.rahardyan.simplechatapp.util.ConnectivityUtils;
import android.rahardyan.simplechatapp.util.Constants;
import android.rahardyan.simplechatapp.util.EndlessRecyclerViewScrollListener;
import android.rahardyan.simplechatapp.util.FileUtil;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

import static android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION;
import static android.content.Intent.FLAG_GRANT_WRITE_URI_PERMISSION;

public class ChatActivity extends BaseActivity implements ChatContract.View{
    private static final int PICK_FILE_REQUEST = 1;
    private static final String TAG = ChatActivity.class.getSimpleName();
    private String issueId;
    private String topicName;
    private ChatPresenter chatPresenter;
    private ChatAdapter chatAdapter;
    private RecyclerView recyclerViewComment;
    private LinearLayout uploadLoading, moreCommentLoading;
    private TextView tvLoading;
    private ImageView btnSendComment, btnAttach;
    private EditText etComment;
    private String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};

    public static Intent generateIntent(Context context, String topicName, String issueId) {
        Intent intent = new Intent(context, ChatActivity.class);
        intent.putExtra(Constants.ISSUE_ID_KEY, issueId);
        intent.putExtra(Constants.TOPIC_NAME_KEY, topicName);

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        getIntentData();
        chatPresenter = new ChatPresenter(this, this);
        requestPermission(permissions);
        initView();
        initRecyclerView();
        setUpToolbar(true, topicName);

        if (ConnectivityUtils.isConnectedToNetwork(this)) {
            chatPresenter.loadCommentFromServer(issueId);
        } else {
            chatPresenter.loadCommentFromDb(issueId);
        }
    }

    private void getIntentData() {
        issueId = getIntent().getStringExtra(Constants.ISSUE_ID_KEY);
        topicName = getIntent().getStringExtra(Constants.TOPIC_NAME_KEY);
    }

    private void initView() {
        recyclerViewComment = (RecyclerView) findViewById(R.id.recycler_comment);
        btnSendComment = (ImageView) findViewById(R.id.btn_send);
        btnAttach = (ImageView) findViewById(R.id.btn_attach);
        etComment = (EditText) findViewById(R.id.et_comment);
        uploadLoading = (LinearLayout) findViewById(R.id.loading_upload);
        moreCommentLoading = (LinearLayout) findViewById(R.id.loadmore_loading);
        tvLoading = (TextView) findViewById(R.id.tv_loading);

        btnAttach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");
                startActivityForResult(intent, PICK_FILE_REQUEST);
            }
        });

        btnSendComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chatPresenter.sendComment(issueId, buildComment());
                etComment.setText("");
            }
        });
    }

    private CommentRequestBody buildComment() {
        UUID uuid = UUID.randomUUID();

        CommentRequestBody commentRequestBody = new CommentRequestBody();
        commentRequestBody.setComment(etComment.getText().toString());
        commentRequestBody.setUuid(uuid.toString());

        return commentRequestBody;
    }

    private void initRecyclerView() {
        chatAdapter = new ChatAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true);

        recyclerViewComment.setLayoutManager(linearLayoutManager);
        recyclerViewComment.setHasFixedSize(true);
        recyclerViewComment.setAdapter(chatAdapter);
        recyclerViewComment.setOnScrollListener(new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                chatPresenter.loadMoreComment(issueId, page * 15);
                Log.d(TAG, "onLoadMore: page "+page);
            }
        });

        chatAdapter.setOnDownloadClickListener(new ChatAdapter.OnAttachmentDownloadClickListener() {
            @Override
            public void onClick(String attachFileId) {
                chatPresenter.downloadFile(issueId, attachFileId);
            }
        });
    }

    private void openDownloadedFile(File file) {
        String mime = FileUtil.getMimeTypeOfFile(file.getPath());
        Intent intent = new Intent();
        intent.setAction(android.content.Intent.ACTION_VIEW);
        intent.setFlags(FLAG_GRANT_READ_URI_PERMISSION | FLAG_GRANT_WRITE_URI_PERMISSION);
        Uri fileUri = FileProvider.getUriForFile(this, this.getApplicationContext().getPackageName() + ".provider", file);;
        intent.setDataAndType(fileUri, mime);
        startActivity(intent);
    }

    private void showUploadLoading() {
        tvLoading.setText(getResources().getString(R.string.uploading));
        uploadLoading.setVisibility(View.VISIBLE);
    }

    private void showDownloadLoading() {
        tvLoading.setText(getResources().getString(R.string.downloading));
        uploadLoading.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_FILE_REQUEST && resultCode == RESULT_OK) {
            if (data == null) {
                showToast("Failed to open file!");
                return;
            }
            try {
                File file = FileUtil.from(this, data.getData());
                chatPresenter.uploadFile(issueId, file, data.getData());
            } catch (IOException e) {
                showToast("Failed to read file data!");
                e.printStackTrace();
            }  catch (NullPointerException e) {
                showToast("Failed to read picture data!");
                e.printStackTrace();
            }
        }
    }

    @Override
    public int getLayout() {
        return R.layout.activity_chat;
    }

    @Override
    public void onShowLoading() {
        showProgressDialog();
    }

    @Override
    public void onDismissLoading() {
        dismissProgressDialog();
        uploadLoading.setVisibility(View.GONE);
    }

    @Override
    public void onSuccessLoadComment(RealmList<CommentDetail> commentDetails) {
        RealmResults<CommentDetail> commentDetails2 = Realm.getDefaultInstance().where(CommentDetail.class).findAll();
        RealmList<CommentDetail> commentDetailRealmList = new RealmList<>();
        commentDetailRealmList.addAll(commentDetails2);
        for (CommentDetail commentDetail : commentDetails2) {
            Log.d(TAG, "onSuccessLoadComment: comment message "+commentDetail.getMessage());
        }
        chatAdapter.setData(commentDetails);
    }

    @Override
    public void onFailedLoadComment(String message) {
        onRequestFailed(message);
    }

    @Override
    public void onReceivedComment() {

    }

    @Override
    public void onSendComment(CommentDetail commentDetail) {
        chatAdapter.addComment(commentDetail);
        recyclerViewComment.smoothScrollToPosition(0);
    }

    @Override
    public void onSuccessSendComment(CommentDetail commentDetail) {
        chatPresenter.loadCommentFromServer(issueId);
        chatAdapter.removeTempComment(commentDetail);
    }

    @Override
    public void onFailedSendComment(CommentDetail commentDetail) {

    }

    @Override
    public void onLoadMoreComment(RealmList<CommentDetail> commentDetails) {
        moreCommentLoading.setVisibility(View.GONE);
        chatAdapter.loadMoreComment(commentDetails);
    }

    @Override
    public void onSuccessUploadFile() {
        chatPresenter.loadCommentFromServer(issueId);
    }

    @Override
    public void onSuccessDownloadFile(File file) {
        openDownloadedFile(file);
    }

    @Override
    public void onFailedUploadFile() {
        showToast(getString(R.string.failed_upload));
    }

    @Override
    public void onFailedDownloadFile(String message) {
        showToast(message);
    }

    @Override
    public void onUploading() {
        showUploadLoading();
    }

    @Override
    public void onDownloading() {
        showDownloadLoading();
    }

    @Override
    public void loadingMoreComment() {
        moreCommentLoading.setVisibility(View.VISIBLE);
    }

    @Subscribe
    public void onWebSocketMessage(WebSocketMessage webSocketMessage) {
        chatPresenter.loadCommentFromServer(issueId);
    }

}
