package com.manprax.discourseplugin.discourse_views.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.manprax.discourseplugin.R;
import com.manprax.discourseplugin.discourse_views.RecyclerItemClickListener;
import com.manprax.discourseplugin.domain.DateUtil;
import com.manprax.discourseplugin.domain.TextUtil;
import com.manprax.discourseplugin.model.topic.Topic;

import java.util.List;

/**
 * Created by Prateek on 30-11-2017.
 */

public class TopicRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private boolean isLoading = true;
    private List<Topic> mTopicList;
    private long now;
    private RecyclerItemClickListener<Topic> mListener;


    public TopicRecyclerAdapter(Context context, RecyclerItemClickListener<Topic> listener) {
        this.mContext = context;
        this.mListener = listener;
        this.now = System.currentTimeMillis();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == R.layout.recycler_bottom_progress_item_layout)
            return new LoadingViewHolder(LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false));
        else if (viewType == R.layout.discourse_topic_item_layout)
            return new TopicHolder(LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false));
        else
            return null;
    }

    @Override
    public int getItemViewType(int position) {
        return (position != 0 && position == getItemCount() - 1) ? R.layout.recycler_bottom_progress_item_layout : R.layout.discourse_topic_item_layout;
    }

    public void setLoading(boolean isLoading) {
        this.now = System.currentTimeMillis();
        this.isLoading = isLoading;
        notifyItemChanged(getItemCount() - 1);
    }

    public void reset() {
        this.mTopicList = null;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof LoadingViewHolder) {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
        } else if (holder instanceof TopicHolder) {
            TopicHolder topicHolder = (TopicHolder) holder;
            topicHolder.bind(mTopicList.get(position));
        }
    }

    public void refreshTopicList(List<Topic> topicList) {
        this.now = System.currentTimeMillis();
        if (this.mTopicList != null) {
            int startIndex = getItemCount();
            int itemCount = topicList.size();
            this.mTopicList.addAll(topicList);
            notifyItemRangeInserted(startIndex - 1, itemCount);
        } else {
            this.mTopicList = topicList;
            notifyDataSetChanged();
        }
    }

    @Override
    public int getItemCount() {
        return mTopicList == null ? 0 : mTopicList.size();
    }


    class LoadingViewHolder extends RecyclerView.ViewHolder {
        ProgressBar progressBar;

        public LoadingViewHolder(View itemView) {
            super(itemView);
            progressBar = (ProgressBar) itemView.findViewById(R.id.loading_indicator);
        }
    }

    class TopicHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView topicNameText, seenText, lastPostedText, postCountText, replyCountText;
        ImageView icon;

        public TopicHolder(View itemView) {
            super(itemView);
            icon = (ImageView) itemView.findViewById(R.id.topic_icon);
            topicNameText = (TextView) itemView.findViewById(R.id.topic_name_text);
            seenText = (TextView) itemView.findViewById(R.id.seen_text);
            lastPostedText = (TextView) itemView.findViewById(R.id.last_posted_text);
            postCountText = (TextView) itemView.findViewById(R.id.post_count_text);
            replyCountText = (TextView) itemView.findViewById(R.id.reply_count_text);
            itemView.setOnClickListener(this);
        }

        public void bind(Topic topic) {
            if (topic.isClosed() || topic.isArchived()) {
                icon.setVisibility(View.VISIBLE);
                icon.setImageResource(R.drawable.ic_lock_grey_500_24dp);
            } else {
                icon.setVisibility(View.GONE);
            }
            topicNameText.setText(Html.fromHtml(topic.getTitle()));
            seenText.setText(TextUtil.format(topic.getViews()));
            seenText.setTypeface(Typeface.DEFAULT_BOLD);
            postCountText.setText(String.valueOf("Post: " + TextUtil.format(topic.getPosts_count())));
            replyCountText.setText(String.valueOf("Reply: " + TextUtil.format(topic.getReply_count())));
            if (!android.text.TextUtils.isEmpty(topic.getLast_posted_at()))
                lastPostedText.setText(DateUtils.getRelativeTimeSpanString(DateUtil.convertToDate(topic.getLast_posted_at()).getTime(), now, DateUtils.DAY_IN_MILLIS));
            else
                lastPostedText.setText("N/A");
        }

        @Override
        public void onClick(View view) {
            mListener.onItemClick(view, mTopicList.get(getAdapterPosition()));
        }
    }
}
