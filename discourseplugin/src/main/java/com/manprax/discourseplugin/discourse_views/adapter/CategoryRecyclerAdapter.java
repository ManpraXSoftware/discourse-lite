package com.manprax.discourseplugin.discourse_views.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.manprax.discourseplugin.R;
import com.manprax.discourseplugin.discourse_views.RecyclerItemClickListener;
import com.manprax.discourseplugin.domain.DiscourseHelper;
import com.manprax.discourseplugin.model.category.Category;

import java.util.List;

/**
 * Created by Mukesh on 30-11-2017.
 */

public class CategoryRecyclerAdapter extends RecyclerView.Adapter<CategoryRecyclerAdapter.MyHolder> {
    private List<Category> mCategoryList;
    private RecyclerItemClickListener<Category> listener;
    //private int selectedPosition=0;

    public CategoryRecyclerAdapter(Context context, RecyclerItemClickListener<Category> listener) {
        this.listener = listener;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.bind(mCategoryList.get(position));
    }

    public void refreshCategories(List<Category> categoryList) {
        this.mCategoryList = categoryList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mCategoryList == null ? 0 : mCategoryList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nameText, descText, topicCountText;
        View categoryView;

        public MyHolder(View itemView) {
            super(itemView);
            categoryView = itemView.findViewById(R.id.category_layout);
            nameText = (TextView) itemView.findViewById(R.id.category_name_text);
            descText = (TextView) itemView.findViewById(R.id.category_description_text);
            topicCountText = (TextView) itemView.findViewById(R.id.topic_count_text);
            itemView.setOnClickListener(this);
        }

        public void bind(Category category) {
            categoryView.setBackgroundColor(DiscourseHelper.parseColor(category.getColor()));
            nameText.setText(category.getName());
            descText.setText(Html.fromHtml(category.getDescription_excerpt()));
            topicCountText.setText("T: " + category.getTopic_count());
        }

        @Override
        public void onClick(View view) {
            listener.onItemClick(view,mCategoryList.get(getAdapterPosition()));
        }
    }
}
