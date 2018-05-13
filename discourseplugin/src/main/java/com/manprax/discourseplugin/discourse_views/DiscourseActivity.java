package com.manprax.discourseplugin.discourse_views;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;

import com.manprax.discourseplugin.R;
import com.manprax.discourseplugin.domain.Discourse;
import com.manprax.discourseplugin.domain.DiscourseConfig;
import com.manprax.discourseplugin.model.category.Category;
import com.manprax.discourseplugin.model.topic.Topic;
import com.manprax.discourseplugin.net.DiscourseConstants;
import com.manprax.discourseplugin.net.DiscourseRepository;

import static com.manprax.discourseplugin.net.DiscourseConstants.COMPOSE_TYPE_TOPIC;

public class DiscourseActivity extends AppCompatActivity implements TopicsFragment.OnTopicSelectedListener,
        CategoriesFragment.OnCategorySelectedListener,
        DiscoursePostFragment.OnNewPostFabClickListener,
        EditorFragment.OnEditingDone {
    private int mActionType = 0;
    private Topic mTopic;
    private Category mCategory;
    private DiscourseConfig mConfig;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discourse);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getIntent()!=null){
            mConfig=getIntent().getParcelableExtra(Discourse.CONFIG);
            DiscourseRepository.setConfig(mConfig);
        }
        if (getSupportActionBar()!=null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(getString(R.string.discourse_activity_title));
        }
        showFragment(CategoriesFragment.newInstance(),CategoriesFragment.TAG);
        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.dashboard_container);
                if (fragment != null) {
                    mk("==> " + fragment.getTag());
                    switch (fragment.getTag()) {
                        case CategoriesFragment.TAG://categories
                            setTitleAndSubtitle(getString(R.string.discourse_activity_title), "");
                            break;
                        case TopicsFragment.TAG:  //topics
                            if (mCategory != null)
                                setTitleAndSubtitle(mCategory.getName(), String.valueOf(Html.fromHtml(mCategory.getDescription_excerpt())));
                            break;
                        case DiscoursePostFragment.TAG://post
                            if (mTopic != null && mCategory != null)
                                setTitleAndSubtitle(mTopic.getTitle(), mCategory.getName());
                            break;
                        case EditorFragment.TAG://Editor
                            setTitleByActionType(mActionType);
                            break;
                        default:
                            setTitleAndSubtitle("Discourse", "");
                    }
                }
            }
        });
    }
    private void setTitleByActionType(int actionType) {
        if (actionType==DiscourseConstants.COMPOSE_TYPE_POST){
            setTitleAndSubtitle("Create Post","");
        }else if (actionType==DiscourseConstants.COMPOSE_TYPE_TOPIC){
            setTitleAndSubtitle("Create Topic","");
        }
    }

    private void showFragment(Fragment fragment,String tag) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.dashboard_container, fragment,tag)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .addToBackStack(null)
                .commit();
    }

    private void setTitleOnly(String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }

    private void setTitleAndSubtitle(String title, String subtitle) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
            getSupportActionBar().setSubtitle(subtitle);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount()==1){
            finish();
        }else {
            super.onBackPressed();  //it pop's out immediate fragment
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void mk(String msg) {
        Log.d("    ==>", "-------------------------------------------------------------------");
        Log.d("MSG ==>", "" + msg);
        Log.d("    ==>", "-------------------------------------------------------------------");
    }

//    @Override
//    public void onTopicSelectedListener(Category selectedCategory, Topic selectedTopic) {
//        setTitleAndSubtitle(selectedTopic.getTitle(), selectedCategory.getName());
//        showFragment(DiscoursePostFragment.newInstance(selectedTopic.getId()),DiscoursePostFragment.TAG);
//    }

    @Override
    public void onTopicSelection(Topic topic) {
        this.mTopic=topic;
        setTitleAndSubtitle(topic.getTitle(),mCategory.getName());
        showFragment(DiscoursePostFragment.newInstance(topic.getId()),DiscoursePostFragment.TAG);
    }

    @Override
    public void onNewTopicClick() {
        mActionType=DiscourseConstants.COMPOSE_TYPE_TOPIC;
        showFragment(EditorFragment.newInstance(mCategory.getId(),DiscourseConstants.COMPOSE_TYPE_TOPIC),EditorFragment.TAG);
    }


    @Override
    public void onCategorySelection(Category category) {
        this.mCategory=category;
        setTitleAndSubtitle(category.getName(), String.valueOf(Html.fromHtml(category.getDescription_excerpt())));
        showFragment(TopicsFragment.newInstance(category),TopicsFragment.TAG);
    }

    @Override
    public void onNewPostFabClick() {
        mActionType= DiscourseConstants.COMPOSE_TYPE_POST;
        showFragment(EditorFragment.newInstance(mTopic.getId(),DiscourseConstants.COMPOSE_TYPE_POST),EditorFragment.TAG);
    }

    @Override
    public void onEditDone() {
        onBackPressed();
    }
}
