package com.manprax.discourseplugin.discourse_views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.manprax.discourseplugin.R;
import com.manprax.discourseplugin.discourse_views.adapter.PostRecyclerAdapter;
import com.manprax.discourseplugin.model.PostActionType;
import com.manprax.discourseplugin.model.ResponseError;
import com.manprax.discourseplugin.model.post.Post;
import com.manprax.discourseplugin.model.topic.Topic;
import com.manprax.discourseplugin.net.DiscourseCallback;
import com.manprax.discourseplugin.net.DiscourseRepository;
import com.manprax.discourseplugin.net.post.PostAPI;
import com.manprax.discourseplugin.net.topic.TopicAPI;

import java.util.Arrays;


/**
 * Created by Mukesh on 04-12-2017.
 */

public class DiscoursePostFragment extends Fragment {
    public static final String  TAG = "DiscoursePostFragment";
    public static String ARG_TOPIC_ID = "ARG_TOPIC_ID";
    RecyclerView mRecyclerView;
    SwipeRefreshLayout mSwipeRefreshLayout;
    FloatingActionButton mWritePostFab;
    private ProgressBar mProgressIndicator;
    TopicAPI topicApi;
    PostAPI postApi;
    PostRecyclerAdapter mPostRecyclerAdapter;
    private OnNewPostFabClickListener newPostFabListener;
    private int topicId;
    private int mSelectedPosition;

    private RecyclerItemClickListener<Post> mRecyclerItemClickListener = new RecyclerItemClickListener<Post>() {
        @Override
        public void onItemClick(View view, Post post) {
            mSelectedPosition = (int) view.getTag();
            int i = view.getId();
            if (i == R.id.like_button) {
                if (post.isPostLiked() && post.canUndoLike()) doPostUnlike(post.getId());
                else if (!post.isPostLiked())
                    doPostActions(post.getId(), PostActionType.LIKE, false);

            } else if (i == R.id.share_link_button) {
                sharePost(post.prepareShareLink(DiscourseRepository.getConfig().getBasUrl(), DiscourseRepository.getConfig().getUserName()));

            } else if (i == R.id.bookmark_button) {
                if (!post.isBookmarked())
                    doPostActions(post.getId(), PostActionType.BOOKMARK, false);

            } else if (i == R.id.reply_button) {
            }
            else if (i == R.id.reply_layout) {
                mRecyclerView.smoothScrollToPosition(mPostRecyclerAdapter.getPostPositionByPostNumber(post.getReply_to_post_number()));
            }
        }
    };

    public static DiscoursePostFragment newInstance(int id) {
        DiscoursePostFragment dPostFrag = new DiscoursePostFragment();
        Bundle b = new Bundle();
        b.putInt(ARG_TOPIC_ID, id);
        dPostFrag.setArguments(b);
        return dPostFrag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            topicId = getArguments().getInt(ARG_TOPIC_ID);
        }
        topicApi=DiscourseRepository.get().getTopicApi();
        postApi= DiscourseRepository.get().getPostApi();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.common_fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView= (RecyclerView) view.findViewById(R.id.discourse_recycler_view);
        mSwipeRefreshLayout= (SwipeRefreshLayout) view.findViewById(R.id.discourse_swipe_layout);
        mProgressIndicator= (ProgressBar) view.findViewById(R.id.progress_indicator);
//        mEmptyTextView= (TextView) view.findViewById(R.id.empty_state_text);
        mProgressIndicator.setVisibility(View.GONE);
        mWritePostFab= (FloatingActionButton) view.findViewById(R.id.new_topic_fab);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setHasFixedSize(true);
        mPostRecyclerAdapter = new PostRecyclerAdapter(getActivity(), mRecyclerItemClickListener);
        mRecyclerView.setAdapter(mPostRecyclerAdapter);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getSingleTopic(topicId);
            }
        });
        getSingleTopic(topicId);
        mWritePostFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (newPostFabListener != null) {
                    newPostFabListener.onNewPostFabClick();
                }
            }
        });
    }
private void sharePost(String url){
    Intent sendIntent = new Intent();
    sendIntent.setAction(Intent.ACTION_SEND);
    sendIntent.putExtra(Intent.EXTRA_TEXT, url);
    sendIntent.setType("text/plain");
    startActivity(Intent.createChooser(sendIntent, "Share with"));
}
    private void doPostActions(int postId,PostActionType actionType,boolean isFlaged) {
        postApi.postActions(postId, actionType.getValue(), isFlaged)
                .enqueue(new DiscourseCallback<Post>() {
                    @Override
                    protected void onResponse(@NonNull Post responseBody) {
                           if (mPostRecyclerAdapter!=null){
                               mPostRecyclerAdapter.updateAt(mSelectedPosition,responseBody);
                           }
                    }
                });
    }
    private void doPostUnlike(int postId) {
        postApi.unlikePost(postId)
                .enqueue(new DiscourseCallback<Post>() {
                    @Override
                    protected void onResponse(@NonNull Post responseBody) {
                        if (mPostRecyclerAdapter!=null){
                            mPostRecyclerAdapter.updateAt(mSelectedPosition,responseBody);
                        }
                    }
                });
    }

    private void getSingleTopic(int id) {
        mSwipeRefreshLayout.setRefreshing(true);
        topicApi.getTopicById(id)
                .enqueue(new DiscourseCallback<Topic>() {
                    @Override
                    protected void onResponse(@NonNull Topic responseBody) {
                        mSwipeRefreshLayout.setRefreshing(false);
                        mPostRecyclerAdapter.refreshPostList(Arrays.asList(responseBody.getPost_stream().getPosts()));
                    }

                    @Override
                    protected void onFailure(ResponseError responseError, @NonNull Throwable error) {
                        super.onFailure(responseError, error);
                        mSwipeRefreshLayout.setRefreshing(false);
                        error.printStackTrace();
                    }
                });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            newPostFabListener = (OnNewPostFabClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnNewPostFabClickListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (newPostFabListener != null)
            newPostFabListener = null;
    }

    public interface OnNewPostFabClickListener {
        void onNewPostFabClick();
    }
}
