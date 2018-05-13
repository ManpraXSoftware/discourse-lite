package com.manprax.discourseplugin.discourse_views;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.github.irshulx.Editor;
import com.github.irshulx.models.EditorTextStyle;
import com.manprax.discourseplugin.R;
import com.manprax.discourseplugin.model.ResponseError;
import com.manprax.discourseplugin.model.post.Post;
import com.manprax.discourseplugin.net.DiscourseCallback;
import com.manprax.discourseplugin.net.DiscourseRepository;
import com.manprax.discourseplugin.net.post.PostAPI;

import java.io.IOException;

import static android.app.Activity.RESULT_OK;
import static com.manprax.discourseplugin.net.DiscourseConstants.COMPOSE_TYPE_POST;
import static com.manprax.discourseplugin.net.DiscourseConstants.COMPOSE_TYPE_TOPIC;

/**
 * Created by mukesh on 2/4/18.
 */

public class EditorFragment extends Fragment {
    public static final String TAG = "Discourse.EditorFragment";
    public static final String _ID = "EditorFragment_ID";
    public static final String COMPOSE_TYPE = "EditorFragment_COMPOSE_TYPE";
    Editor editor;
    PostAPI postAPI;
    private int mId;
    private int mComposeType;
    private TextView mMsgText;
    private EditText mTitleEdit;
    private OnEditingDone mListener;

    public static EditorFragment newInstance(int id,int composeType) {

        Bundle args = new Bundle();
        EditorFragment fragment = new EditorFragment();
        args.putInt(_ID,id);
        args.putInt(COMPOSE_TYPE,composeType);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments()!=null){
            mId=getArguments().getInt(_ID);
            mComposeType=getArguments().getInt(COMPOSE_TYPE);
        }
        setHasOptionsMenu(true);
        postAPI= DiscourseRepository.get().getPostApi();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.discourse_editor_layout, container,false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editor = (Editor) view.findViewById(R.id.editor);
        mTitleEdit = (EditText) view.findViewById(R.id.title_edit);
        mMsgText = (TextView) view.findViewById(R.id.msg_text);

        createComposeType();
        view.findViewById(R.id.action_h1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.updateTextStyle(EditorTextStyle.H1);
            }
        });

        view.findViewById(R.id.action_h2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.updateTextStyle(EditorTextStyle.H2);
            }
        });

        view.findViewById(R.id.action_h3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.updateTextStyle(EditorTextStyle.H3);
            }
        });

        view.findViewById(R.id.action_bold).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.updateTextStyle(EditorTextStyle.BOLD);
            }
        });

        view.findViewById(R.id.action_Italic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.updateTextStyle(EditorTextStyle.ITALIC);
            }
        });

        view.findViewById(R.id.action_indent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.updateTextStyle(EditorTextStyle.INDENT);
            }
        });

        view.findViewById(R.id.action_outdent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.updateTextStyle(EditorTextStyle.OUTDENT);
            }
        });

        view.findViewById(R.id.action_bulleted).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.insertList(false);
            }
        });

        view.findViewById(R.id.action_unordered_numbered).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.insertList(true);
            }
        });

        view.findViewById(R.id.action_hr).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.insertDivider();
            }
        });

        view.findViewById(R.id.action_insert_image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.openImagePicker();
            }
        });

        view.findViewById(R.id.action_insert_link).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.insertLink();
            }
        });

        view.findViewById(R.id.action_erase).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.clearAllContents();
            }
        });
        editor.render();
    }

    private void createComposeType() {
        if (mComposeType==COMPOSE_TYPE_TOPIC){
            mMsgText.setVisibility(View.GONE);
            mTitleEdit.setVisibility(View.VISIBLE);
        }else if (mComposeType==COMPOSE_TYPE_POST){
            mMsgText.setVisibility(View.GONE);
            mTitleEdit.setVisibility(View.GONE);
        }

    }

    private void createPost(int topicId,String rawPost){
        postAPI.createPost(topicId,rawPost).enqueue(new DiscourseCallback<Post>() {
            @Override
            protected void onResponse(@NonNull Post responseBody) {
                 Log.d("onResponse",""+responseBody.toString());
                 if (responseBody!=null&&mListener!=null)
                     mListener.onEditDone();
            }
        });
    }
    private void createTopic(int categoryId,String title,String rawPost){
        postAPI.createTopic(categoryId,title,rawPost).enqueue(new DiscourseCallback<Post>() {
            @Override
            protected void onResponse(@NonNull Post responseBody) {
                 Log.d("onResponse",""+responseBody.toString());
                if (responseBody!=null&&mListener!=null)
                    mListener.onEditDone();
            }

            @Override
            protected void onFailure(ResponseError responseError, @NonNull Throwable error) {
                super.onFailure(responseError, error);
            }

        });
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.write_post_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_done) {
            Log.d("HTML->",""+editor.getContentAsHTML());
          //  Log.d("Serialized->",""+editor.getContentAsSerialized());
            validateInput();
        }
        return super.onOptionsItemSelected(item);
    }

    private void validateInput() {
        String raw=editor.getContentAsHTML();
        switch (mComposeType){
            case COMPOSE_TYPE_TOPIC:
                String title=mTitleEdit.getText().toString().trim();
                if (!TextUtils.isEmpty(title)) {
                    createTopic(mId, title, raw);
                }else {
                    mTitleEdit.setError("Please enter topic title.");
                    mTitleEdit.requestFocus();
                }
                break;

            case COMPOSE_TYPE_POST:
                createPost(mId,raw);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == editor.PICK_IMAGE_REQUEST && resultCode == RESULT_OK&& data != null && data.getData() != null) {
            Uri uri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);
                editor.insertImage(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (OnEditingDone) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnEditingDone");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (mListener != null)
            mListener = null;
    }

public interface OnEditingDone{
        void onEditDone();
}

}
