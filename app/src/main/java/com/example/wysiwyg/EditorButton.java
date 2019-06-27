package com.example.wysiwyg;

import android.content.Context;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatImageButton;

public class EditorButton extends AppCompatImageButton {

    private static EditorButton instance;
    private static int UnCheckedColorFilter;
    private static int CheckedColorFilter;


    public EditorButton(Context context, AttributeSet attrs){
        super(context ,attrs);
    }

    public static void setGlobalOnClickListener(OnClickListener onClickListener){
        instance.setOnClickListener(onClickListener);
    }

    public static void setGlobalUnCheckedColorFilter(int color){
        UnCheckedColorFilter = color;
    }

    public static void setGlobalCheckedColorFilter(int color){
        CheckedColorFilter = color;
    }

}
