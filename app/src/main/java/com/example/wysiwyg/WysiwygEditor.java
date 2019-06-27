package com.example.wysiwyg;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class WysiwygEditor extends LinearLayout {

    private EditorButton mButtonSize;
    private EditorButton mButtonColor;
    private EditorButton mButtonBgColor;
    private EditorButton mButtonBold;
    private EditorButton mButtonItalic;
    private EditorButton mButtonUnder;
    private EditorButton mButtonStrike;
    private EditorButton mButtonAlign;
    private EditorButton mButtonImg;
    private EditorButton mButtonVideo;
    private EditorButton mButtonConfirm;
    private EditorButton mButtonCancel;

    public WysiwygEditor(Context context) {
        super(context);
        init();
    }

    public WysiwygEditor(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public WysiwygEditor(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        inflate(getContext(), R.layout.frame_wysiwyg, this);

//        EditorButton.setGlobalOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

        mButtonSize = findViewById(R.id.btn_size);
        mButtonColor = findViewById(R.id.btn_color);
        mButtonBgColor = findViewById(R.id.btn_bgcolor);
        mButtonBold = findViewById(R.id.btn_bold);
        mButtonItalic = findViewById(R.id.btn_italic);
        mButtonUnder = findViewById(R.id.btn_under);
        mButtonStrike = findViewById(R.id.btn_strike);
        mButtonAlign = findViewById(R.id.btn_align);
        mButtonImg = findViewById(R.id.btn_img);
        mButtonVideo = findViewById(R.id.btn_video);
        mButtonConfirm = findViewById(R.id.btn_cfrm);
        mButtonCancel = findViewById(R.id.btn_cancel);

    }
}
