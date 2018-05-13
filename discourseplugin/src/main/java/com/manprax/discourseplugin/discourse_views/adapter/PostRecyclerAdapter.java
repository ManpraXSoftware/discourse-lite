package com.manprax.discourseplugin.discourse_views.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.manprax.discourseplugin.R;
import com.manprax.discourseplugin.discourse_views.RecyclerItemClickListener;
import com.manprax.discourseplugin.domain.DateUtil;
import com.manprax.discourseplugin.model.post.Post;
import com.manprax.discourseplugin.net.DiscourseRepository;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Mukesh on 06-12-2017.
 */

public class PostRecyclerAdapter extends RecyclerView.Adapter<PostRecyclerAdapter.PostHolder> {
    private static final int AVATAR_SIZE = 240;
    private List<Post> postList;
    private Context mContext;
    private RecyclerItemClickListener<Post> mRecyclerItemClickListener;
    private long now;
    private boolean closed=false;

    public PostRecyclerAdapter(Context context, RecyclerItemClickListener<Post> mRecyclerItemClickListener) {
        this.mContext = context;
        this.now = System.currentTimeMillis();
        this.mRecyclerItemClickListener = mRecyclerItemClickListener;
    }

    @Override
    public PostHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PostHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.discourse_post_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(PostHolder holder, int position) {
        holder.bind(postList.get(position), mContext);
    }

    public void refreshPostList(List<Post> list) {
        this.now = System.currentTimeMillis();
        this.postList = list;
        notifyDataSetChanged();
    }
    public Post getPostByPostNumber(int postNumber) {
        for (Post post : postList) {
            if (post.getPost_number() == postNumber)
                return post;
        }
        return null;
    }

    public int getPostPositionByPostNumber(int postNumber) {
        int i = 0;
        for (Post post : postList) {
            if (post.getPost_number() == postNumber)
                return i;
            i++;
        }
        return -1;
    }
    public void setClosed(boolean b) {
        this.closed=b;
    }
    @Override
    public int getItemCount() {
        return postList == null ? 0 : postList.size();
    }

    public void updateAt(int selectedPosition, Post post) {
        if (selectedPosition>=0&&selectedPosition<getItemCount()) {
            this.postList.set(selectedPosition, post);
            notifyItemChanged(selectedPosition);
        }
    }

    class PostHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView posterNameText, posterUserNameText, timeText, likeCountText, replyName;
        CircleImageView posterPic, replyPic;
        WebView postWebView;
        ImageButton likeButton, bookmarkButton, shareLinkButton;
        ImageView codeImage;
        Button replyButton;
        View actionLayout, codeLayout, replyLayout;
        TextView actionCodeText;

        public PostHolder(View itemView) {
            super(itemView);
            posterNameText = (TextView) itemView.findViewById(R.id.poster_name_text);
            posterUserNameText = (TextView) itemView.findViewById(R.id.username_text);
            replyPic = (CircleImageView) itemView.findViewById(R.id.reply_avatar);
            replyName = (TextView) itemView.findViewById(R.id.reply_user_name_text);
            actionLayout = itemView.findViewById(R.id.action_layout);
            codeLayout = itemView.findViewById(R.id.code_layout);
            replyLayout = itemView.findViewById(R.id.reply_layout);
            actionCodeText = (TextView) itemView.findViewById(R.id.action_code_text);
            timeText = (TextView) itemView.findViewById(R.id.time_text);
            likeCountText = (TextView) itemView.findViewById(R.id.like_count_text);
            posterPic = (CircleImageView) itemView.findViewById(R.id.poster_pic);
            postWebView = (WebView) itemView.findViewById(R.id.post_expert_web_view);
            likeButton = (ImageButton) itemView.findViewById(R.id.like_button);
            codeImage = (ImageView) itemView.findViewById(R.id.action_code_image);
            bookmarkButton = (ImageButton) itemView.findViewById(R.id.bookmark_button);
            replyButton = (Button) itemView.findViewById(R.id.reply_button);
            shareLinkButton = (ImageButton) itemView.findViewById(R.id.share_link_button);
            likeButton.setOnClickListener(this);
            bookmarkButton.setOnClickListener(this);
            replyButton.setOnClickListener(this);
            shareLinkButton.setOnClickListener(this);
            replyLayout.setOnClickListener(this);
        }

        public void bind(Post post, Context context) {
            replyButton.setVisibility(closed?View.GONE:View.VISIBLE);
            posterNameText.setText(post.getDisplay_username());
            timeText.setText(DateUtils.getRelativeTimeSpanString(DateUtil.convertToDate(post.getCreated_at()).getTime(), now, DateUtils.DAY_IN_MILLIS));
            Glide.with(context).load(post.getAvatarUrl(AVATAR_SIZE,DiscourseRepository.getConfig().getBasUrl())).into(posterPic);
            if (!TextUtils.isEmpty(post.getAction_code())) {   //post is either archived or close or locked
                actionLayout.setVisibility(View.GONE);
                replyLayout.setVisibility(View.GONE);
                codeLayout.setVisibility(View.VISIBLE);
                postWebView.setVisibility(View.GONE);
                bindCode(post.getAction_code());
            } else {
                replyLayout.setVisibility(View.GONE);
                actionLayout.setVisibility(View.VISIBLE);
                postWebView.setVisibility(View.VISIBLE);
                codeLayout.setVisibility(View.GONE);
                likeCountText.setText(post.getLikeCount());
                likeButton.setVisibility(post.isMyPost(DiscourseRepository.getConfig().getUserName()) ? View.GONE : View.VISIBLE);
                likeButton.setImageResource(post.isPostLiked() ? R.drawable.ic_favorite_pink_500_24dp : R.drawable.ic_favorite_grey_500_24dp);
                //   bookmarkButton.setImageResource(post.isBookmarked()?R.drawable.ic_bookmark_blue_700_24dp:R.drawable.ic_bookmark_grey_500_24dp);
                //postText.setText(Html.fromHtml(post.getCooked()));
                postWebView.loadData(post.getCooked(), "text/html", "utf-8");
            }
                if (post.getReply_to_post_number() > 0&&post.getReply_to_user()!=null) {
                    replyLayout.setVisibility(View.VISIBLE);
                    replyName.setText(post.getReply_to_user().getUsername());
                    Glide.with(context).load(String.valueOf(DiscourseRepository.getConfig().getBasUrl() + post.getReply_to_user().getAvatar_template().replace("{size}", "" + AVATAR_SIZE))).into(replyPic);
                } else {
                    replyLayout.setVisibility(View.GONE);
                }
        }
        private void bindCode(String action_code) {
            if (action_code.contains("close")) {
                actionCodeText.setText("Topic closed");
                codeImage.setImageResource(R.drawable.ic_lock_grey_500_24dp);
            } else if (action_code.contains("archived")) {
                actionCodeText.setText("Topic archived");
                codeImage.setImageResource(R.drawable.ic_archive_grey_500_24dp);
            }else{
                actionCodeText.setText(action_code);
            }
        }

        @Override
        public void onClick(View view) {
            if (mRecyclerItemClickListener!=null) {
                view.setTag(getAdapterPosition());
                mRecyclerItemClickListener.onItemClick(view, postList.get(getAdapterPosition()));
            }
        }
    }
}
