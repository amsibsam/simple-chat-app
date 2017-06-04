package android.rahardyan.simplechatapp.ui.topiclist.presenter;

import android.rahardyan.simplechatapp.model.TopicList;

/**
 * Created by rahardyan on 03/06/17.
 */

public interface TopicContract {
    interface View {
        void onSuccessLoadTopic(TopicList topicList);

        void onLoadMoreTopic(TopicList topicList);

        void onFailedLoadTopic(String message);

        void onShowLoading();

        void onDismissLoading();
    }

    interface UserActionListener {
        void loadTopic();

        void loadMoreTopic(int page);
    }
}
