package com.example.wysiwyg;

import android.content.Context;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatImageButton;

public class EditorButton extends AppCompatImageButton {

    private static OnClickListener mOnClickListener;

    public EditorButton(Context context, AttributeSet attrs){
        super(context ,attrs);
    }

    public static void manageOnClickListener(OnClickListener onClickListener){
        mOnClickListener = onClickListener;
    }

}
