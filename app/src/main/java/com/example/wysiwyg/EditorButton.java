package com.example.wysiwyg;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageButton;

public class EditorButton extends AppCompatImageButton {

    public interface ClickedListener{
        void onReceiveClickedListener();
    }

    private ClickedListener mClickedListener;
    private static OnClickListener mOnClickListener;
    private static int CheckedColorFilter;
    private static int UnCheckedColorFilter;
    private boolean isChecked = false;

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

    public static int getCheckedColorFilter(){
        return CheckedColorFilter;
    }

    public static int getUnCheckedColorFilter(){
        return UnCheckedColorFilter;
    }

    public void changeClicked(){
        if(isChecked)
            isChecked = false;
        else
            isChecked = true;
        System.out.println(isChecked);
    }

    public void changeColorFilter(){
        if(isChecked)
            this.setColorFilter(R.color.colorPrimary);
        else
            this.setColorFilter(R.color.colorBackYellow);
    }

    public void setOnClickedListener(EditorButton.ClickedListener listener){
        this.mClickedListener = listener;
    }

}
