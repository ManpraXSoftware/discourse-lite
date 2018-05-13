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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.manprax.discourseplugin.R;
import com.manprax.discourseplugin.discourse_views.adapter.CategoryRecyclerAdapter;
import com.manprax.discourseplugin.model.ResponseError;
import com.manprax.discourseplugin.model.category.Category;
import com.manprax.discourseplugin.model.category.CategoryResponse;
import com.manprax.discourseplugin.net.DiscourseCallback;
import com.manprax.discourseplugin.net.DiscourseRepository;
import com.manprax.discourseplugin.net.category.CategoryAPI;

import java.util.List;


/**
 * Created by mukesh on 22/3/18.
 */

public class CategoriesFragment extends Fragment implements RecyclerItemClickListener<Category>, SwipeRefreshLayout.OnRefreshListener {
    public static final String TAG = "CategoriesFragment";
    RecyclerView mRecyclerView;
    SwipeRefreshLayout mSwipeRefreshLayout;
    ProgressBar mProgressIndicator;
    TextView mEmptyTextView;
    CategoryAPI categoryAPI;
    private CategoryRecyclerAdapter mAdapter;
    private OnCategorySelectedListener mListener;

    public static CategoriesFragment newInstance() {
        CategoriesFragment fragment = new CategoriesFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        categoryAPI= DiscourseRepository.get().getCategoryApi();
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
        view.findViewById(R.id.new_topic_fab).setVisibility(View.GONE);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new CategoryRecyclerAdapter(getActivity(), this);
        mRecyclerView.setAdapter(mAdapter);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mProgressIndicator.setVisibility(View.VISIBLE);
        getCategoryData();
    }

    protected void hideLoading() {
        mSwipeRefreshLayout.setRefreshing(false);
        mProgressIndicator.setVisibility(View.GONE);
    }

    protected void canToggleEmptyState() {
        mEmptyTextView.setVisibility(mAdapter.getItemCount() > 0 ? View.GONE : View.VISIBLE);
    }

    private void getCategoryData() {
        categoryAPI.getCategories()
        .enqueue(new DiscourseCallback<CategoryResponse>() {
            @Override
            protected void onResponse(@NonNull CategoryResponse responseBody) {
                hideLoading();
                if (responseBody.getCategory_list() != null) {
                    updateCategoryList(responseBody.getCategory_list().getCategories());
                } else {
                    //may show a message here
                }
                canToggleEmptyState();
            }

            @Override
            protected void onFailure(ResponseError responseError, @NonNull Throwable error) {
                super.onFailure(responseError, error);
                hideLoading();
                error.printStackTrace();
                canToggleEmptyState();
            }
        });
    }

    private void updateCategoryList(List<Category> categoryList) {
        mAdapter.refreshCategories(categoryList);
    }

    @Override
    public void onItemClick(View view,Category item) {
       //Category item selected notify to activity to show topics
        if (mListener!=null)
            mListener.onCategorySelection(item);
    }

    @Override
    public void onRefresh() {
        //force refresh here
        getCategoryData();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (OnCategorySelectedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnCategorySelectedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (mListener!=null)
            mListener=null;
    }

    public interface OnCategorySelectedListener{
        void onCategorySelection(Category category);
    }
}
