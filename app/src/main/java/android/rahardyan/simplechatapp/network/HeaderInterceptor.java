package android.rahardyan.simplechatapp.network;

import android.rahardyan.simplechatapp.BuildConfig;
import android.text.TextUtils;

import java.io.IOException;
import java.util.Locale;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by rahardyan on 03/06/17.
 */

public class HeaderInterceptor implements Interceptor {
    private final String TOKEN = "token";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();

        HttpUrl url = originalRequest.url();
        Request.Builder newRequestBuilder = originalRequest.newBuilder();
        newRequestBuilder.removeHeader(TOKEN).addHeader(TOKEN, BuildConfig.USER_TOKEN);
        newRequestBuilder.url(url);

        return chain.proceed(newRequestBuilder.build());
    }
}
