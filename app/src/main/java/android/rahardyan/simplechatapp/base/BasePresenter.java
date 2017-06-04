package android.rahardyan.simplechatapp.base;

import android.content.Context;
import android.rahardyan.simplechatapp.BuildConfig;
import android.rahardyan.simplechatapp.network.NetworkManager;

/**
 * Created by rahardyan on 31/05/17.
 */

public class BasePresenter {
    protected final NetworkManager networkManager;
    protected Context context;

    public BasePresenter(Context context) {
        this.context = context;
        this.networkManager = new NetworkManager(context, BuildConfig.REST_API_URL, BuildConfig.DEBUG);
    }
}
