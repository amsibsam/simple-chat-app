package android.rahardyan.simplechatapp.ui.topiclist.adapter;

import android.rahardyan.simplechatapp.R;
import android.rahardyan.simplechatapp.model.TopicList;
import android.rahardyan.simplechatapp.ui.topiclist.presenter.TopicContract;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rahardyan on 03/06/17.
 */

public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.TopicViewHolder>{
    private List<TopicList.IssueList> issueListList = new ArrayList<>();
    private OnItemClickListener mOnItemClickListener;

    public void setData(List<TopicList.IssueList> issueLists) {
        this.issueListList = issueLists;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public void addData(List<TopicList.IssueList> moreIssueLists) {
        if (moreIssueLists.size() != 0) {
            int startIndex = this.issueListList.size();
            this.issueListList.addAll(startIndex, moreIssueLists);
            notifyItemRangeChanged(startIndex, this.issueListList.size());
        }
    }


    @Override
    public TopicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_topic, parent, false);
        return new TopicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TopicViewHolder holder, int position) {
        final String topicName = issueListList.get(position).getTitle();
        final String issueId = issueListList.get(position).getId();
        holder.tvTopicName.setText(topicName);

        holder.cardTopic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null)
                    mOnItemClickListener.onClick(topicName, issueId);
            }
        });
    }

    @Override
    public int getItemCount() {
        return issueListList.size();
    }

    public class TopicViewHolder extends RecyclerView.ViewHolder {
        TextView tvTopicName;
        CardView cardTopic;

        public TopicViewHolder(View itemView) {
            super(itemView);
            tvTopicName = (TextView) itemView.findViewById(R.id.tv_topic_name);
            cardTopic = (CardView) itemView.findViewById(R.id.card_topic);
        }
    }

    public interface OnItemClickListener {
        void onClick(String topicName, String issueId);
    }
}
