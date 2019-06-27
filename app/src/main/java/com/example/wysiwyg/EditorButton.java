package com.example.wysiwyg;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageButton;

public class EditorButton extends AppCompatImageButton {

    private static OnClickListener mOnClickListener;
    private static int CheckedColorFilter;
    private static int UnCheckedColorFilter;

    public EditorButton(Context context){
        super(context);
    }

    public EditorButton(Context context, AttributeSet attrs){
        super(context ,attrs);
    }

    public static void setGlobalOnClickListener(OnClickListener onClickListener){
        mOnClickListener = onClickListener;
    }

    public static void setGlobalUnCheckedColorFilter(int color){
        UnCheckedColorFilter = color;
    }

    public static void setGlobalCheckedColorFilter(int color){
        CheckedColorFilter = color;
    }

    public static OnClickListener getOnClickListener(){
        return mOnClickListener;
    }

}
