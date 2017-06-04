package android.rahardyan.simplechatapp.network;

import android.rahardyan.simplechatapp.model.Comment;
import android.rahardyan.simplechatapp.model.CommentRequestBody;
import android.rahardyan.simplechatapp.model.TopicList;

import com.google.gson.JsonElement;


import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by rahardyan on 31/05/17.
 */

public interface NetworkService {
    @GET("/api/issues")
    Call<TopicList> getTopicList(@Query("page") int page,
                                 @Query("pageSize") int pageSize,
                                 @Query("status") String status);

    @GET("/api/issues/{issueId}")
    Call<JsonElement> getTopic(@Path("issueId") String issueid);

    @GET("/api/issues/{issueId}/comments")
    Call<Comment> getComment(@Path("issueId") String issueId,
                             @Query("startNumber") int startNumber,
                             @Query("pageSize") int pageSize);

    @POST("/api/issues/{issueId}/comments")
    Call<JsonElement> postComment(@Path("issueId") String issueId,
                                  @Body CommentRequestBody comment);

    @Multipart
    @POST("/api/issues/{issueId}/upload")
    Call<Void> uploadFile(@Path("issueId") String issueId,
                                 @Query("uuid") String uuid,
                                 @Part MultipartBody.Part file);


    @POST("/api/issues/{issueId}/upload")
    Call<JsonElement> uploadFile2(@Path("issueId") String issueId,
                                 @Query("uuid") String uuid,
                                 @Part("description") RequestBody requestBody,
                                 @Part MultipartBody.Part file);

    @GET("/api/issues/{issueId}/attachments/{attachmentId}")
    Call<ResponseBody> downloadFile(@Path("issueId") String issueId,
                                    @Path("attachmentId") String attachmentId);
}
