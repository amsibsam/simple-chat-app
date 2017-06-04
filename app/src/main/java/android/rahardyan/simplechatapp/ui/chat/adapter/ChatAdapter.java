package android.rahardyan.simplechatapp.ui.chat.adapter;

import android.content.Context;
import android.rahardyan.simplechatapp.R;
import android.rahardyan.simplechatapp.model.Comment;
import android.rahardyan.simplechatapp.util.DateUtil;
import android.rahardyan.simplechatapp.util.RoundedImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by rahardyan on 03/06/17.
 */

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    private OnAttachmentDownloadClickListener onAttachmentDownloadClickListener;
    private List<Comment.CommentDetail> comments = new ArrayList<>();
    private static final int MY_COMMENT = 0;
    private static final int ANOTHER_COMMENT = 1;

    public ChatAdapter(Context context) {
        this.context = context;
    }

    public void setOnDownloadClickListener(OnAttachmentDownloadClickListener onDownloadClickListener) {
        this.onAttachmentDownloadClickListener = onDownloadClickListener;
    }

    public void setData(List<Comment.CommentDetail> comments) {
        this.comments = comments;
        notifyDataSetChanged();
    }

    public void addComment(Comment.CommentDetail commentDetail) {
        comments.add(0, commentDetail);
        notifyItemInserted(0);
        notifyItemRangeChanged(0, comments.size());
    }

    public void loadMoreComment(List<Comment.CommentDetail> comments) {
        if (!comments.isEmpty()) {
            int startIndex = this.comments.size();
            this.comments.addAll(startIndex, comments);
            notifyDataSetChanged();
        }
    }

    public void removeTempComment(Comment.CommentDetail commentDetail) {
        comments.remove(commentDetail);
        notifyItemRemoved(comments.size()-1);
        notifyItemRangeChanged(comments.size()-1, comments.size());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ANOTHER_COMMENT) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment_text, parent, false);
            return new AnotherCommentViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment_text_me, parent, false);
            return new MyCommentViewHolder(view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        String sender = comments.get(position).getCreatedName();

        return sender.toLowerCase().equals("joy") ? MY_COMMENT : ANOTHER_COMMENT;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        String comment = comments.get(position).getMessage();
        String chatCreatedTime = DateUtil.getDateWithCustomFormat("dd MM yyyy", comments.get(position).getWrittenTime());
        String sender = comments.get(position).getCreatedName();
        String senderAvatar = comments.get(position).getCreatorAvatarImageUrl();
        final String fileId = comments.get(position).getAttachFileId();
        boolean isAttachment = comments.get(position).getField().toLowerCase().equals("attachment");
        switch (getItemViewType(position)) {
            case ANOTHER_COMMENT:
                AnotherCommentViewHolder anotherCommentViewHolder = (AnotherCommentViewHolder) holder;
                anotherCommentViewHolder.tvComments.setText(comment);
                anotherCommentViewHolder.tvCommentsWrittenTime.setText(chatCreatedTime);
                anotherCommentViewHolder.tvSender.setText(sender);
                Picasso.with(context).load(senderAvatar).into(anotherCommentViewHolder.ivAvatar);
                anotherCommentViewHolder.btnDownload.setVisibility(isAttachment ? View.VISIBLE : View.GONE);
                anotherCommentViewHolder.btnDownload.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (onAttachmentDownloadClickListener != null)
                            onAttachmentDownloadClickListener.onClick(fileId);
                    }
                });
                break;
            case MY_COMMENT:
                MyCommentViewHolder myCommentViewHolder = (MyCommentViewHolder) holder;
                myCommentViewHolder.tvComments.setText(comment);
                myCommentViewHolder.tvCommentsWrittenTime.setText(chatCreatedTime);
                myCommentViewHolder.btnDownload.setVisibility(isAttachment ? View.VISIBLE : View.GONE);
                myCommentViewHolder.btnDownload.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (onAttachmentDownloadClickListener != null)
                            onAttachmentDownloadClickListener.onClick(fileId);
                    }
                });
                break;
        }

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public class MyCommentViewHolder extends RecyclerView.ViewHolder {
        TextView tvComments, tvCommentsWrittenTime;
        ImageView btnDownload;

        public MyCommentViewHolder(View itemView) {
            super(itemView);
            tvComments = (TextView) itemView.findViewById(R.id.tv_comment);
            tvCommentsWrittenTime = (TextView) itemView.findViewById(R.id.tv_time);
            btnDownload = (ImageView) itemView.findViewById(R.id.btn_download);
        }
    }

    public class AnotherCommentViewHolder extends RecyclerView.ViewHolder {
        TextView tvComments, tvCommentsWrittenTime, tvSender;
        RoundedImageView ivAvatar;
        ImageView btnDownload;

        public AnotherCommentViewHolder(View itemView) {
            super(itemView);
            tvComments = (TextView) itemView.findViewById(R.id.tv_comment);
            tvCommentsWrittenTime = (TextView) itemView.findViewById(R.id.tv_time);
            tvSender = (TextView) itemView.findViewById(R.id.tv_sender_name);
            ivAvatar = (RoundedImageView) itemView.findViewById(R.id.iv_sender_avatar);
            btnDownload = (ImageView) itemView.findViewById(R.id.btn_download);
        }
    }

    public interface OnAttachmentDownloadClickListener {
        void onClick(String attachFileId);
    }
}
