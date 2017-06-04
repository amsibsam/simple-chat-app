package android.rahardyan.simplechatapp.ui.topiclist.presenter;

import android.rahardyan.simplechatapp.model.IssueList;
import android.rahardyan.simplechatapp.model.TopicList;

import io.realm.RealmList;

/**
 * Created by rahardyan on 03/06/17.
 */

public interface TopicContract {
    interface View {
        void onSuccessLoadTopic(RealmList<IssueList> topicList);

        void onLoadMoreTopic(RealmList<IssueList> topicList);

        void onFailedLoadTopic(String message);

        void onShowLoading();

        void onDismissLoading();
    }

    interface UserActionListener {
        void loadTopic();

        void loadMoreTopic(int page);

        void loadTopicFromDb();
    }
}
