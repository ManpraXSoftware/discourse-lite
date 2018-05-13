package com.manprax.discourseplugin.discourse_views;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.TextView;

import com.manprax.discourseplugin.R;
import com.manprax.discourseplugin.domain.DiscourseHelper;
import com.manprax.discourseplugin.model.ResponseError;
import com.manprax.discourseplugin.model.category.Category;
import com.manprax.discourseplugin.model.category.CategoryResponse;
import com.manprax.discourseplugin.model.tags.Tag;
import com.manprax.discourseplugin.model.tags.TagsResponse;
import com.manprax.discourseplugin.net.DiscourseCallback;
import com.manprax.discourseplugin.net.DiscourseRepository;
import com.manprax.discourseplugin.net.category.CategoryAPI;
import com.manprax.discourseplugin.net.tags.TagAPI;

import java.util.List;

/**
 * Created by mukesh on 3/4/18.
 */

public class FilterBottomFragment extends BottomSheetDialogFragment implements View.OnClickListener {
    public static final int FILTER_LIST_TYPE = 34;
    public static final int FILTER_TAGS_TYPE = 36;
    public static final int FILTER_CATEGORY_TYPE = 38;
    public static final String SELECTED_FILTER = "FilterBottomFragment.SelectedFilter";
    public static final String SELECTED_FILTER_TYPE = "FilterBottomFragment.SelectedFilterType";
    CategoryAPI categoryAPI;
    TagAPI tagAPI;
    private RecyclerView mTagsRecycler;//, mCategoriesRecycler;
    private CheckedTextView mTopText, mNewText, mUnreadText, mLatestText;
    private TextView  mCloseText,tag_title;
    private GridRecyclerAdapter<Category> mCategoriesAdapter;
    private GridRecyclerAdapter<Tag> mTagsAdapter;
    private OnFilterSelectionListener mFilterListener;
    private String mFilter = "";
    private int mFilterType;
    private BottomSheetBehavior.BottomSheetCallback mBottomSheetBehaviorCallback = new BottomSheetBehavior.BottomSheetCallback() {

        @Override
        public void onStateChanged(@NonNull View bottomSheet, int newState) {
            if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                dismiss();
            }
        }

        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {
        }
    };

    public static FilterBottomFragment newInstance() {
        Bundle args = new Bundle();
        FilterBottomFragment fragment = new FilterBottomFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            mFilter = savedInstanceState.getString(SELECTED_FILTER);
            mFilterType = savedInstanceState.getInt(SELECTED_FILTER_TYPE);
        }
        categoryAPI= DiscourseRepository.get().getCategoryApi();
        tagAPI= DiscourseRepository.get().getTagsApi();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (!TextUtils.isEmpty(mFilter)){
            outState.putString(SELECTED_FILTER,mFilter);
            outState.putInt(SELECTED_FILTER_TYPE,mFilterType);
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    public void setupDialog(Dialog dialog, int style) {
        View contentView = View.inflate(getContext(), R.layout.discourse_filter_bottom_sheet_layout, null);
        mTagsRecycler = (RecyclerView) contentView.findViewById(R.id.tags_recycler);
        mCloseText = (TextView) contentView.findViewById(R.id.close_filter);
        tag_title = (TextView) contentView.findViewById(R.id.tag_title);
        mLatestText = (CheckedTextView) contentView.findViewById(R.id.latest_filter_text);
        mNewText = (CheckedTextView) contentView.findViewById(R.id.new_filter_text);
        mUnreadText = (CheckedTextView) contentView.findViewById(R.id.unread_filter_text);
        mTopText = (CheckedTextView) contentView.findViewById(R.id.top_filter_text);
//        mCategoriesRecycler = (RecyclerView) contentView.findViewById(R.id.categories_recycler);
        mTagsRecycler.setLayoutManager(new GridLayoutManager(getActivity(), 1, GridLayoutManager.HORIZONTAL, false));
        mTagsAdapter = new GridRecyclerAdapter<Tag>(getActivity());
        mTagsRecycler.setAdapter(mTagsAdapter);
        //
//        mCategoriesRecycler.setLayoutManager(new GridLayoutManager(getActivity(), 1, GridLayoutManager.HORIZONTAL, false));
//        mCategoriesAdapter = new GridRecyclerAdapter<Category>(getActivity());
//        mCategoriesRecycler.setAdapter(mCategoriesAdapter);

//        getCategories();
        getTags();
        dialog.setContentView(contentView);
        CoordinatorLayout.LayoutParams layoutParams =
                (CoordinatorLayout.LayoutParams) ((View) contentView.getParent()).getLayoutParams();
        CoordinatorLayout.Behavior behavior = layoutParams.getBehavior();
        if (behavior != null && behavior instanceof BottomSheetBehavior) {
            ((BottomSheetBehavior) behavior).setBottomSheetCallback(mBottomSheetBehaviorCallback);
        }
        mCloseText.setOnClickListener(this);
        mLatestText.setOnClickListener(this);
        mUnreadText.setOnClickListener(this);
        mNewText.setOnClickListener(this);
        mTopText.setOnClickListener(this);
        clearAllSelection();
        setSelection(mFilter,mFilterType);
    }

    private void clearAllSelection() {
        mLatestText.setChecked(false);
        mTopText.setChecked(false);
        mNewText.setChecked(false);
        mUnreadText.setChecked(false);
        mTagsAdapter.setSelection(-1);
    }

    private void setSelection(String filter,int type) {
      //  if (type==FILTER_LIST_TYPE) {
            switch (filter) {
                case "latest":
                    mLatestText.setChecked(true);
                    break;
                case "top":
                    mTopText.setChecked(true);
                    break;
                case "new":
                    mNewText.setChecked(true);
                    break;
                case "unread":
                    mUnreadText.setChecked(true);
                    break;
            }
//        }else if(type==FILTER_TAGS_TYPE){
//
//            //mTagsRecycler.scrollToPosition(pos);
//        }
    }

    private void getTags() {
        tagAPI.getAllTags()
        .enqueue(new DiscourseCallback<TagsResponse>() {
            @Override
            protected void onResponse(@NonNull TagsResponse responseBody) {
                if (mTagsAdapter != null) {
                    mTagsAdapter.refreshList(responseBody.getTags());
                    int pos=mTagsAdapter.getPositionOf(mFilter);
                    mTagsAdapter.setSelection(pos);
                }else{
                    hideTagsView();
                }
            }

            @Override
            protected void onFailure(ResponseError responseError, @NonNull Throwable error) {
                super.onFailure(responseError, error);
                hideTagsView();
            }

        });
    }

    private void hideTagsView() {
        mTagsRecycler.setVisibility(View.GONE);
        tag_title.setVisibility(View.GONE);

    }

    private void getCategories() {
         categoryAPI.getCategories()
            .enqueue(new DiscourseCallback<CategoryResponse>() {
            @Override
            protected void onResponse(@NonNull CategoryResponse responseBody) {
                if (responseBody.getCategory_list() != null) {
                    if (mCategoriesAdapter != null)
                        mCategoriesAdapter.refreshList(responseBody.getCategory_list().getCategories());
                } else {
                    //may show a message here
                }
            }
        });
    }

    public void setOnFilterSelectionListener(OnFilterSelectionListener listener) {
        this.mFilterListener = listener;
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.close_filter) {
            dismiss();
        } else if (i == R.id.latest_filter_text) {
            doFilter("latest", FILTER_LIST_TYPE);

        } else if (i == R.id.unread_filter_text) {
            doFilter("unread", FILTER_LIST_TYPE);

        } else if (i == R.id.new_filter_text) {
            doFilter("new", FILTER_LIST_TYPE);

        } else if (i == R.id.top_filter_text) {
            doFilter("top", FILTER_LIST_TYPE);

        }
    }

    private void doFilter(String filter, int type) {
        if (mFilterListener != null) {
            mFilter=filter;
            mFilterType=type;
            mFilterListener.onFilterSelected(filter, type);
            dismiss();
        }
    }

    public interface OnFilterSelectionListener {
        void onFilterSelected(String filter, int type);
    }

    class GridRecyclerAdapter<T> extends RecyclerView.Adapter<GridRecyclerAdapter<T>.GridHolder> {
        int type;
        int selectedPosition;
        List<T> mList;

        public GridRecyclerAdapter(Context context) {
        }

        @Override
        public GridHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new GridHolder(new FancyTextView(parent.getContext()));
        }


        @Override
        public void onBindViewHolder(GridHolder holder, int position) {
            holder.bind(mList.get(position),position==selectedPosition);
        }

        @Override
        public int getItemCount() {
            return mList == null ? 0 : mList.size();
        }

        public void refreshList(List<T> items) {
            this.mList = items;
            notifyDataSetChanged();
        }

        public void setSelection(int i) {
            this.selectedPosition=i;
            notifyItemChanged(i);
        }

        public int getPositionOf(String filter) {
            for (int i=0;i<getItemCount();i++){
                T t=mList.get(i);
                if (t instanceof Tag){
                    if (((Tag) t).getText().equals(filter)) return i;
                }
            }
            return -1;
        }

        class GridHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            FancyTextView fancyTextView;

            public GridHolder(View itemView) {
                super(itemView);
                fancyTextView = (FancyTextView) itemView;
                fancyTextView.setOnClickListener(this);
            }

            public void bind(T t,boolean isSelected) {
                if (t instanceof Tag) {
                    fancyTextView.setSimpleText(((Tag) t).getText());
                    fancyTextView.setSelection(isSelected);
                } else if (t instanceof Category) {
                    fancyTextView.setChipText(((Category) t).getName(), DiscourseHelper.parseColor(((Category) t).getColor()));
                }
            }

            @Override
            public void onClick(View view) {
                T t=mList.get(getAdapterPosition());
                if (t instanceof Tag)
                doFilter(((Tag) t).getText(),FILTER_TAGS_TYPE);
            }
        }
    }
}
