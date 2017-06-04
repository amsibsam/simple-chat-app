package android.rahardyan.simplechatapp;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by rahardyan on 04/06/17.
 */

public class SimpleChatApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .schemaVersion(0)
                .name("CHAT_DB")
                .build();
        Realm.getInstance(realmConfiguration);
        Realm.setDefaultConfiguration(realmConfiguration);
    }
}
