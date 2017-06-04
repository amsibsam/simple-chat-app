package android.rahardyan.simplechatapp.ui.chat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.rahardyan.simplechatapp.base.BaseActivity;
import android.rahardyan.simplechatapp.model.Comment;
import android.os.Bundle;
import android.rahardyan.simplechatapp.R;
import android.rahardyan.simplechatapp.model.CommentRequestBody;
import android.rahardyan.simplechatapp.ui.chat.adapter.ChatAdapter;
import android.rahardyan.simplechatapp.ui.chat.presenter.ChatContract;
import android.rahardyan.simplechatapp.ui.chat.presenter.ChatPresenter;
import android.rahardyan.simplechatapp.util.Constants;
import android.rahardyan.simplechatapp.util.EndlessRecyclerViewScrollListener;
import android.rahardyan.simplechatapp.util.FileUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class ChatActivity extends BaseActivity implements ChatContract.View{
    private static final int PICK_FILE_REQUEST = 1;
    private static final String TAG = ChatActivity.class.getSimpleName();
    private String issueId;
    private ChatPresenter chatPresenter;
    private ChatAdapter chatAdapter;
    private RecyclerView recyclerViewComment;
    private ImageView btnSendComment, btnAttach;
    private EditText etComment;
    private String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};

    public static Intent generateIntent(Context context, String issueId) {
        Intent intent = new Intent(context, ChatActivity.class);
        intent.putExtra(Constants.ISSUE_ID_KEY, issueId);

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getIntentData();
        chatPresenter = new ChatPresenter(this, this);
        chatPresenter.loadComment(issueId);
        requestPermission(permissions);
        initView();
        initRecyclerView();
        setUpToolbar(true, "New Arrival");
    }

    private void getIntentData() {
        issueId = getIntent().getStringExtra(Constants.ISSUE_ID_KEY);
    }

    private void initView() {
        recyclerViewComment = (RecyclerView) findViewById(R.id.recycler_comment);
        btnSendComment = (ImageView) findViewById(R.id.btn_send);
        btnAttach = (ImageView) findViewById(R.id.btn_attach);
        etComment = (EditText) findViewById(R.id.et_comment);

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
                chatPresenter.uploadFile(issueId, file);
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
    }

    @Override
    public void onSuccessLoadComment(Comment comment) {
        chatAdapter.setData(comment.getData());
    }

    @Override
    public void onFailedLoadComment(String message) {
        onRequestFailed(message);
    }

    @Override
    public void onReceivedComment() {

    }

    @Override
    public void onSendComment(Comment.CommentDetail commentDetail) {
        chatAdapter.addComment(commentDetail);
        recyclerViewComment.smoothScrollToPosition(0);
    }

    @Override
    public void onSuccessSendComment(Comment.CommentDetail commentDetail) {
        chatPresenter.loadComment(issueId);
        chatAdapter.removeTempComment(commentDetail);
    }

    @Override
    public void onFailedSendComment(Comment.CommentDetail commentDetail) {

    }

    @Override
    public void onLoadMoreComment(Comment comment) {
        chatAdapter.loadMoreComment(comment.getData());
    }

    @Override
    public void onSuccessUploadFile() {
        chatPresenter.loadComment(issueId);
    }

    @Override
    public void onSuccessDownloadFile(File file) {
        String mime = FileUtil.getMimeTypeOfFile(file.getPath());

        Intent intent = new Intent();
        intent.setAction(android.content.Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file), mime);
        startActivityForResult(intent, 10);
    }

    @Override
    public void onFailedUploadFile() {
        showToast(getString(R.string.failed_upload));
    }

    @Override
    public void onFaileDownloadFile(String message) {
        showToast(message);
    }
}
