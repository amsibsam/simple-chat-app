package android.rahardyan.simplechatapp.ui.chat.presenter;

import android.net.Uri;
import android.rahardyan.simplechatapp.model.Comment;
import android.rahardyan.simplechatapp.model.CommentRequestBody;

import java.io.File;

/**
 * Created by rahardyan on 03/06/17.
 */

public interface ChatContract {
    interface View {
        void onShowLoading();

        void onDismissLoading();

        void onSuccessLoadComment(Comment comment);

        void onFailedLoadComment(String message);

        void onReceivedComment();

        void onSendComment(Comment.CommentDetail commentDetail);

        void onSuccessSendComment(Comment.CommentDetail commentDetail);

        void onFailedSendComment(Comment.CommentDetail commentDetail);

        void onLoadMoreComment(Comment comment);

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

        void loadComment(String issueId);

        void loadMoreComment(String issueId, int page);

        void downloadFile(String issueId, String fileId);
    }

}
