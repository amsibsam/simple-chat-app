package android.rahardyan.simplechatapp.ui.topiclist.presenter;

import android.content.Context;
import android.rahardyan.simplechatapp.R;
import android.rahardyan.simplechatapp.base.BasePresenter;
import android.rahardyan.simplechatapp.model.CommentDetail;
import android.rahardyan.simplechatapp.model.IssueList;
import android.rahardyan.simplechatapp.model.TopicList;
import android.support.annotation.NonNull;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by rahardyan on 03/06/17.
 */

public class TopicPresenter extends BasePresenter implements TopicContract.UserActionListener{
    @NonNull
    private TopicContract.View mTopicView;

    public TopicPresenter(@NonNull Context context, @NonNull TopicContract.View mTopicView) {
        super(context);
        this.mTopicView = mTopicView;
    }

    @Override
    public void loadTopic() {
        mTopicView.onShowLoading();
        networkManager.getTopicList(1).enqueue(new Callback<TopicList>() {
            @Override
            public void onResponse(Call<TopicList> call, Response<TopicList> response) {
                mTopicView.onDismissLoading();
                if (response.isSuccessful()) {
                    for (IssueList commentDetail : response.body().getIssueList()) {
                        Realm realm = Realm.getDefaultInstance();
                        realm.beginTransaction();
                        realm.copyToRealmOrUpdate(commentDetail);
                        realm.commitTransaction();
                    }

                    mTopicView.onSuccessLoadTopic(response.body().getIssueList());
                } else {
                    mTopicView.onFailedLoadTopic(context.getResources().getString(R.string.failed_loading_data));
                }
            }

            @Override
            public void onFailure(Call<TopicList> call, Throwable t) {
                mTopicView.onDismissLoading();
                mTopicView.onFailedLoadTopic(context.getResources().getString(R.string.failed_loading_data) + " " + t.toString());
            }
        });
    }

    @Override
    public void loadMoreTopic(int page) {
        networkManager.getTopicList(page).enqueue(new Callback<TopicList>() {
            @Override
            public void onResponse(Call<TopicList> call, Response<TopicList> response) {
                if (response.isSuccessful()) {
                    for (IssueList commentDetail : response.body().getIssueList()) {
                        Realm realm = Realm.getDefaultInstance();
                        realm.beginTransaction();
                        realm.copyToRealmOrUpdate(commentDetail);
                        realm.commitTransaction();
                    }
                    mTopicView.onSuccessLoadTopic(response.body().getIssueList());
                } else {
                    mTopicView.onFailedLoadTopic(context.getResources().getString(R.string.failed_loading_data));
                }
            }

            @Override
            public void onFailure(Call<TopicList> call, Throwable t) {
                mTopicView.onFailedLoadTopic(context.getResources().getString(R.string.failed_loading_data) + " " + t.toString());
            }
        });
    }

    @Override
    public void loadTopicFromDb() {
        RealmResults<IssueList> issueLists = Realm.getDefaultInstance().where(IssueList.class).findAll();
        RealmList<IssueList> issueListRealmList = new RealmList<>();
        issueListRealmList.addAll(issueLists);
        mTopicView.onSuccessLoadTopic(issueListRealmList);
    }
}
