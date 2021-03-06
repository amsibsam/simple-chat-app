package android.rahardyan.simplechatapp.ui.chat.presenter;

import android.content.Context;
import android.net.Uri;
import android.rahardyan.simplechatapp.R;
import android.rahardyan.simplechatapp.base.BasePresenter;
import android.rahardyan.simplechatapp.model.Comment;
import android.rahardyan.simplechatapp.model.CommentDetail;
import android.rahardyan.simplechatapp.model.CommentRequestBody;
import android.rahardyan.simplechatapp.util.FileUtil;
import android.support.annotation.NonNull;

import com.google.gson.JsonElement;

import java.io.File;
import java.io.IOException;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by rahardyan on 03/06/17.
 */

public class ChatPresenter extends BasePresenter implements ChatContract.UserActionListener {
    private int loadingCount = 0;
    @NonNull
    private ChatContract.View mChatView;

    public ChatPresenter(@NonNull Context context, @NonNull ChatContract.View mChatView) {
        super(context);
        this.mChatView = mChatView;
    }


    @Override
    public void sendComment(String issueId, CommentRequestBody commentRequestBody) {
        final CommentDetail tempComment = new CommentDetail();
        tempComment.setMessage(commentRequestBody.getComment());
        tempComment.setWrittenTime("0");
        tempComment.setCreatedName(context.getResources().getString(R.string.user_name));
        tempComment.setField("");
        mChatView.onSendComment(tempComment);

        networkManager.postComment(issueId, commentRequestBody).enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                if (response.isSuccessful()) {
                    mChatView.onSuccessSendComment(tempComment);
                } else {
                    mChatView.onFailedSendComment(tempComment);
                }
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                mChatView.onFailedSendComment(tempComment);
            }
        });
    }

    @Override
    public void uploadFile(String issueId, File file, Uri fileUri) {
        mChatView.onUploading();
        networkManager.uploadFile(issueId, file, fileUri).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                mChatView.onDismissLoading();
                if (response.isSuccessful()) {
                    mChatView.onSuccessUploadFile();
                } else {
                    mChatView.onFailedUploadFile();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                mChatView.onDismissLoading();
                mChatView.onFailedUploadFile();
            }
        });
    }

    @Override
    public void loadCommentFromServer(String issueId) {
        loadingCount++;
        if (loadingCount < 2) {
            mChatView.onShowLoading();
        }
        networkManager.getComments(issueId, 0).enqueue(new Callback<Comment>() {
            @Override
            public void onResponse(Call<Comment> call, Response<Comment> response) {
                mChatView.onDismissLoading();
                if (response.isSuccessful()) {
                    for (CommentDetail commentDetail : response.body().getData()) {
                        Realm realm = Realm.getDefaultInstance();
                        realm.beginTransaction();
                        realm.copyToRealmOrUpdate(commentDetail);
                        realm.commitTransaction();
                    }
                    mChatView.onSuccessLoadComment(response.body().getData());
                } else {
                    mChatView.onFailedLoadComment(context.getString(R.string.failed_loading_data));
                }
            }

            @Override
            public void onFailure(Call<Comment> call, Throwable t) {
                mChatView.onDismissLoading();
                mChatView.onFailedLoadComment(context.getString(R.string.failed_loading_data));
            }
        });
    }

    @Override
    public void loadCommentFromDb(String issueId) {
        RealmResults<CommentDetail> commentDetails = Realm.getDefaultInstance().where(CommentDetail.class).equalTo("mIssueId", issueId).findAll();
        RealmList<CommentDetail> commentDetailRealmList = new RealmList<>();
        commentDetailRealmList.addAll(commentDetails);
        mChatView.onSuccessLoadComment(commentDetailRealmList);
    }

    @Override
    public void loadMoreComment(String issueId, int page) {
        mChatView.loadingMoreComment();
        networkManager.getComments(issueId, page).enqueue(new Callback<Comment>() {
            @Override
            public void onResponse(Call<Comment> call, Response<Comment> response) {
                mChatView.onDismissLoading();
                if (response.isSuccessful()) {
                    for (CommentDetail commentDetail : response.body().getData()) {
                        Realm realm = Realm.getDefaultInstance();
                        realm.beginTransaction();
                        realm.copyToRealmOrUpdate(commentDetail);
                        realm.commitTransaction();
                    }
                    RealmList<CommentDetail> commentDetailRealmList = new RealmList<CommentDetail>();
                    commentDetailRealmList.addAll(response.body().getData());
                    mChatView.onLoadMoreComment(commentDetailRealmList);
                } else {
                    mChatView.onFailedLoadComment(context.getString(R.string.failed_loading_data));
                }
            }

            @Override
            public void onFailure(Call<Comment> call, Throwable t) {
                mChatView.onDismissLoading();
                mChatView.onFailedLoadComment(context.getString(R.string.failed_loading_data));
            }
        });
    }

    @Override
    public void downloadFile(String issueId, final String fileId) {
        mChatView.onDownloading();
        networkManager.downloadFile(issueId, fileId)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        mChatView.onDismissLoading();
                        if (response.isSuccessful()) {
                            try {
                                File file = FileUtil.from(context, response.body().byteStream(), fileId);
                                mChatView.onSuccessDownloadFile(file);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            mChatView.onFailedDownloadFile(context.getString(R.string.failed_download));
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        mChatView.onDismissLoading();
                        mChatView.onFailedDownloadFile(context.getString(R.string.failed_download));
                    }
                });
    }
}
