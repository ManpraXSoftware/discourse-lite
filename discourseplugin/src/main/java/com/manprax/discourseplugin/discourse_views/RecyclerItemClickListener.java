package com.manprax.discourseplugin.discourse_views;

import android.view.View;

/**
 * Created by mukesh on 23/3/18.
 */

public interface RecyclerItemClickListener<T> {
   void onItemClick(View view, T item);
}
