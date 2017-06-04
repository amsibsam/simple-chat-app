package android.rahardyan.simplechatapp.network;

import android.content.Context;
import android.net.Uri;
import android.rahardyan.simplechatapp.model.Comment;
import android.rahardyan.simplechatapp.model.CommentRequestBody;
import android.rahardyan.simplechatapp.model.TopicList;
import android.rahardyan.simplechatapp.util.FileUtil;

import com.google.gson.JsonElement;

import java.io.File;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created by rahardyan on 31/05/17.
 */

public class NetworkManager {
    private Context context;
    private NetworkService networkService;
    public NetworkManager(Context context, String baseUrl, boolean isDebug) {
        this.context = context;
        this.networkService = NetworkFactory.createNetwork(baseUrl, isDebug)
                .create(NetworkService.class);
    }

    public Call<TopicList> getTopicList(int page) {
        return networkService.getTopicList(page, 10, "TO DO");
    }

    public Call<JsonElement> getTopic() {
        return networkService.getTopic("bean17249_6");
    }

    public Call<Comment> getComments(String issueId, int startPage) {
        return networkService.getComment(issueId, startPage, 15);
    }

    public Call<JsonElement> postComment(String issueId, CommentRequestBody commentRequestBody) {
        return networkService.postComment(issueId, commentRequestBody);
    }

    public Call<JsonElement> uploadFile(String issueId, File file){
        Map<String, RequestBody> partMap = new HashMap<>();
        partMap.put(String.format("raw_file\"; filename=\"%s\"", file.getName()),
                RequestBody.create(MediaType.parse(URLConnection.guessContentTypeFromName(file.getName())), file));

        RequestBody fileRequestBody = RequestBody.create(MediaType.parse("image/*"), file);

        MultipartBody.Part body =
                MultipartBody.Part.createFormData("upload", file.getName(), fileRequestBody);

        RequestBody requestBodyDesc = RequestBody.create(
                MediaType.parse("text/plain"), "test upload");
        return networkService.uploadFile(issueId, UUID.randomUUID().toString(), requestBodyDesc, body);
    }

    public Call<ResponseBody> downloadFile(String issueId, String attachmentId) {
        return networkService.downloadFile(issueId, attachmentId);
    }
}
