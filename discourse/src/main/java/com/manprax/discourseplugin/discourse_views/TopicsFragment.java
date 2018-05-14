package com.manprax.discourseplugin.discourse_views;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.manprax.discourseplugin.R;
import com.manprax.discourseplugin.discourse_views.adapter.TopicRecyclerAdapter;
import com.manprax.discourseplugin.model.ResponseError;
import com.manprax.discourseplugin.model.category.Category;
import com.manprax.discourseplugin.model.topic.Topic;
import com.manprax.discourseplugin.model.topic.TopicResponse;
import com.manprax.discourseplugin.net.DiscourseCallback;
import com.manprax.discourseplugin.net.DiscourseRepository;
import com.manprax.discourseplugin.net.category.CategoryAPI;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * Created by Mukesh on 29-11-2017.
 */

public class TopicsFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, RecyclerItemClickListener<Topic> {
    public static final String TAG = "TopicsFragment";
    public static final String CATEGORY_EXTRA = "Discourse.Category";
    RecyclerView mRecyclerView;
    SwipeRefreshLayout mSwipeRefreshLayout;
    ProgressBar mProgressIndicator;
    TextView mEmptyTextView;
    FloatingActionButton mNewTopicFab;
    CategoryAPI categoryAPI;
    private Category mCategory;
    private TopicRecyclerAdapter mAdapter;
    private OnTopicSelectedListener mListener;
    private EndlessRecyclerViewScrollListener mScrollListener;
    private FilterBottomFragment mFilterFragment;
    private String mFilter = "";
    private int mFilterType;

    public static TopicsFragment newInstance(Category category) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(CATEGORY_EXTRA, category);
        TopicsFragment fragment = new TopicsFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
            mCategory = getArguments().getParcelable(CATEGORY_EXTRA);
        setHasOptionsMenu(true);
        categoryAPI= DiscourseRepository.get().getCategoryApi();
        mFilterFragment = FilterBottomFragment.newInstance();
        mFilterFragment.setOnFilterSelectionListener(new FilterBottomFragment.OnFilterSelectionListener() {
            @Override
            public void onFilterSelected(String filter, int type) {
                if (mScrollListener != null && mAdapter != null) {
                    mProgressIndicator.setVisibility(View.VISIBLE);
                    mAdapter.reset();
                    mScrollListener.resetState();
                    getTopicDataByFilter(mCategory.getId(), filter, type, 0);
                    mFilter = filter;
                    mFilterType = type;
                }
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.discourse_topic_filter_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.filter) {
            mFilterFragment.show(getChildFragmentManager(), "Filter");
        }
        return super.onOptionsItemSelected(item);
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
        mEmptyTextView= (TextView) view.findViewById(R.id.empty_state_text);
        mNewTopicFab= (FloatingActionButton) view.findViewById(R.id.new_topic_fab);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(llm);
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new TopicRecyclerAdapter(getActivity(), this);
        mRecyclerView.setAdapter(mAdapter);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mProgressIndicator.setVisibility(View.VISIBLE);
        getTopicData(mCategory.getId(), 0);
        mScrollListener = new EndlessRecyclerViewScrollListener(llm) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                mAdapter.setLoading(true);
                if (TextUtils.isEmpty(mFilter))
                    getTopicData(mCategory.getId(), page);
                else
                    getTopicDataByFilter(mCategory.getId(), mFilter, mFilterType, page);
            }
        };
        mRecyclerView.addOnScrollListener(mScrollListener);
        mNewTopicFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //create new topic here
                //title,category,raw
                if (mListener != null) {
                    mListener.onNewTopicClick();
                }
            }
        });
    }

    protected void hideLoading() {
        mSwipeRefreshLayout.setRefreshing(false);
        mProgressIndicator.setVisibility(View.GONE);
        mAdapter.setLoading(false);//this hide recycler bottom progress
    }

    protected void canToggleEmptyState() {
        mEmptyTextView.setVisibility(mAdapter.getItemCount() > 0 ? View.GONE : View.VISIBLE);
    }

    private void toast(String msg) {
        Toast.makeText(getActivity(), "" + msg, Toast.LENGTH_LONG).show();
    }

    private void getTopicData(int categoryId, int page) {
        categoryAPI.getTopicList(categoryId, page)
                .enqueue(new DiscourseCallback<TopicResponse>() {
                    @Override
                    protected void onResponse(@NonNull TopicResponse responseBody) {
                        hideLoading();
                        mAdapter.refreshTopicList(new ArrayList<Topic>(Arrays.asList(responseBody.getTopic_list().getTopics())));
                        canToggleEmptyState();
                    }

                    @Override
                    protected void onFailure(ResponseError responseError, @NonNull Throwable error) {
                        super.onFailure(responseError, error);
                        hideLoading();
                        canToggleEmptyState();
                        error.printStackTrace();
                    }
                });
    }

    private void getTopicDataByFilter(int categoryId, String filter, int type, int page) {
        categoryAPI.getTopicListByFilter(categoryId, filter, type, page)
                .enqueue(new DiscourseCallback<TopicResponse>() {
                    @Override
                    protected void onResponse(@NonNull TopicResponse responseBody) {
                        hideLoading();
                        mAdapter.refreshTopicList(new ArrayList<Topic>(Arrays.asList(responseBody.getTopic_list().getTopics())));
                        canToggleEmptyState();
                    }

                    @Override
                    protected void onFailure(ResponseError responseError, @NonNull Throwable error) {
                        super.onFailure(responseError, error);
                        hideLoading();
                        canToggleEmptyState();
                        error.printStackTrace();
                    }

                });
    }

    @Override
    public void onRefresh() {
        //reset here and fetch fresh data
        mScrollListener.resetState();
        mAdapter.reset();
        getTopicData(mCategory.getId(), 0);
    }

    @Override
    public void onItemClick(View view, Topic item) {
        if (mListener != null) {
            mListener.onTopicSelection(item);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (OnTopicSelectedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnTopicSelectedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (mListener != null)
            mListener = null;
        if (mScrollListener != null)
            mScrollListener = null;
    }

    public interface OnTopicSelectedListener {
        void onTopicSelection(Topic topic);

        void onNewTopicClick();
    }
}
