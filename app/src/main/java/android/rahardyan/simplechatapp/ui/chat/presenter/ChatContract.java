package android.rahardyan.simplechatapp.ui.chat.presenter;

import android.net.Uri;
import android.rahardyan.simplechatapp.model.Comment;
import android.rahardyan.simplechatapp.model.CommentDetail;
import android.rahardyan.simplechatapp.model.CommentRequestBody;

import java.io.File;
import java.util.List;

import io.realm.RealmList;

/**
 * Created by rahardyan on 03/06/17.
 */

public interface ChatContract {
    interface View {
        void onShowLoading();

        void onDismissLoading();

        void onSuccessLoadComment(RealmList<CommentDetail> comment);

        void onFailedLoadComment(String message);

        void onReceivedComment();

        void onSendComment(CommentDetail commentDetail);

        void onSuccessSendComment(CommentDetail commentDetail);

        void onFailedSendComment(CommentDetail commentDetail);

        void onLoadMoreComment(RealmList<CommentDetail> comment);

        void onFailedUploadFile();

        void onSuccessUploadFile();

        void onSuccessDownloadFile(File file);

        void onFailedDownloadFile(String message);

        void onUploading();

        void onDownloading();

        void loadingMoreComment();
    }

    interface UserActionListener {
        void sendComment(String issueId, CommentRequestBody comment);

        void uploadFile(String issueId, File file, Uri fileUri);

        void loadCommentFromServer(String issueId);

        void loadCommentFromDb(String issueId);

        void loadMoreComment(String issueId, int page);

        void downloadFile(String issueId, String fileId);
    }

}
