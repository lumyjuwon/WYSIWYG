package com.example.wysiwyg;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageButton;

public class EditorButton extends AppCompatImageButton {

    public interface ClickedListener{
        void onReceiveClickedListener();
    }

    private static int CheckedColorFilter;
    private static int UnCheckedColorFilter;
    private boolean isChecked = false;

    public EditorButton(Context context){
        super(context);
    }

    public EditorButton(Context context, AttributeSet attrs){
        super(context ,attrs);
    }

    public static void setGlobalUnCheckedColorFilter(int color){
        UnCheckedColorFilter = color;
    }

    public static void setGlobalCheckedColorFilter(int color){
        CheckedColorFilter = color;
    }

    public static int getCheckedColorFilter(){
        return CheckedColorFilter;
    }

    public static int getUnCheckedColorFilter(){
        return UnCheckedColorFilter;
    }

    public boolean getChecked(){
        return this.isChecked;
    }

    public void toggleClicked(){
        if(this.isChecked)
            this.isChecked = false;
        else
            this.isChecked = true;
    }

    public void changeColorFilter(){
        if(isChecked)
            this.setColorFilter(R.color.colorPrimary);
        else
            this.setColorFilter(R.color.colorBackYellow);
    }

}
