package com.manprax.discourseplugin.discourse_views;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;

import com.manprax.discourseplugin.R;


/**
 * Created by mukesh on 3/4/18.
 */

public class FancyTextView extends AppCompatTextView {
    public static final int SIMPLE = 12;
    public static final int CHIP = 13;

    public FancyTextView(Context context) {
        super(context);
        init();
    }

    public FancyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FancyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setPadding(16, 8, 16, 8);
        setTextSize(16f);
        setGravity(Gravity.CENTER_VERTICAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(14,8,14,8);
        setLayoutParams(params);
    }

    public void setSimpleText(String text) {
        setText(text);
        setChipBgColor(ContextCompat.getColor(getContext(), R.color.grey_act_background));
    }

    public void setChipText(String text, int color) {
        setChipBgColor(color);
        setTextColor(Color.WHITE);
        setText(text);
    }

    public void setChipBgColor(int color) {
        RoundRectShape roundRectShape = new RoundRectShape(new float[]{
                10, 10, 10, 10,
                10, 10, 10, 10}, null, null);
        ShapeDrawable shapeDrawable = new ShapeDrawable(roundRectShape);
        shapeDrawable.getPaint().setColor(color);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//            setBackground(shapeDrawable);
//        }else{
            setBackgroundDrawable(shapeDrawable);
//        }
    }

    public void setSelection(boolean isSelected) {
        if (isSelected) {
            setTextSize(16f);
            setTextColor(Color.WHITE);
            setChipBgColor(ContextCompat.getColor(getContext(),R.color.colorPrimaryDark));
        }else{
            setTextSize(16f);
            setTextColor(Color.BLACK);
            setChipBgColor(ContextCompat.getColor(getContext(),R.color.grey_act_background));
        }
    }
}
