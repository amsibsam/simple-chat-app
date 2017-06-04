package android.rahardyan.simplechatapp.ui.topiclist;

import android.content.Intent;
import android.rahardyan.simplechatapp.R;
import android.rahardyan.simplechatapp.base.BaseActivity;
import android.rahardyan.simplechatapp.model.IssueList;
import android.rahardyan.simplechatapp.model.TopicList;
import android.rahardyan.simplechatapp.network.WebSocketService;
import android.rahardyan.simplechatapp.ui.chat.ChatActivity;
import android.rahardyan.simplechatapp.ui.topiclist.adapter.TopicAdapter;
import android.rahardyan.simplechatapp.ui.topiclist.presenter.TopicContract;
import android.rahardyan.simplechatapp.ui.topiclist.presenter.TopicPresenter;
import android.os.Bundle;
import android.rahardyan.simplechatapp.util.ConnectivityUtils;
import android.rahardyan.simplechatapp.util.Constants;
import android.rahardyan.simplechatapp.util.EndlessRecyclerViewScrollListener;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import io.realm.Realm;
import io.realm.RealmList;


public class TopicListActivity extends BaseActivity implements TopicContract.View {
    private static final String TAG = TopicListActivity.class.getSimpleName();
    private TopicPresenter topicPresenter;
    private RecyclerView recyclerViewTopic;
    private TopicAdapter topicAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topicPresenter = new TopicPresenter(this, this);
        startService(new Intent(TopicListActivity.this, WebSocketService.class));
        setUpToolbar(false, getResources().getString(R.string.app_name));
        initView();
        initRecyclerView();

        if (ConnectivityUtils.isConnectedToNetwork(this)) {
            topicPresenter.loadTopic();
        } else {
            topicPresenter.loadTopicFromDb();
        }
    }

    private void initRecyclerView() {
        topicAdapter = new TopicAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewTopic.setLayoutManager(linearLayoutManager);
        recyclerViewTopic.setHasFixedSize(true);
        recyclerViewTopic.setAdapter(topicAdapter);
        recyclerViewTopic.setOnScrollListener(new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                topicPresenter.loadMoreTopic(page);
            }
        });

        topicAdapter.setOnItemClickListener(new TopicAdapter.OnItemClickListener() {
            @Override
            public void onClick(String topicName, String issueId) {
                startActivity(ChatActivity.generateIntent(TopicListActivity.this, topicName, issueId));
            }
        });
    }

    private void initView() {
        recyclerViewTopic = (RecyclerView) findViewById(R.id.recycler_topic);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_topic_list;
    }

    @Override
    public void onSuccessLoadTopic(RealmList<IssueList> topicList) {
        topicAdapter.setData(topicList);
    }

    @Override
    public void onLoadMoreTopic(RealmList<IssueList> topicList) {
        topicAdapter.addData(topicList);
    }

    @Override
    public void onFailedLoadTopic(String message) {
        onRequestFailed(message);
    }

    @Override
    public void onShowLoading() {
        showProgressDialog();
    }

    @Override
    public void onDismissLoading() {
        dismissProgressDialog();
    }
}
