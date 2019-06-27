package com.example.wysiwyg;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class WysiwygEditor extends LinearLayout{
    private final String SET_HTML = "file:///android_asset/editor.html";

    @SuppressWarnings("FieldCanBeLocal")
    private WebView mWebView;
    @SuppressWarnings("FieldCanBeLocal")
    private EditorButton mButtonSize;
    @SuppressWarnings("FieldCanBeLocal")
    private EditorButton mButtonColor;
    @SuppressWarnings("FieldCanBeLocal")
    private EditorButton mButtonBgColor;
    @SuppressWarnings("FieldCanBeLocal")
    private EditorButton mButtonBold;
    @SuppressWarnings("FieldCanBeLocal")
    private EditorButton mButtonItalic;
    @SuppressWarnings("FieldCanBeLocal")
    private EditorButton mButtonUnder;
    @SuppressWarnings("FieldCanBeLocal")
    private EditorButton mButtonStrike;
    @SuppressWarnings("FieldCanBeLocal")
    private EditorButton mButtonAlign;
    @SuppressWarnings("FieldCanBeLocal")
    private EditorButton mButtonImg;
    @SuppressWarnings("FieldCanBeLocal")
    private EditorButton mButtonVideo;
    @SuppressWarnings("FieldCanBeLocal")
    private EditorButton mButtonConfirm;
    @SuppressWarnings("FieldCanBeLocal")
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

        // WebView
        mWebView = findViewById(R.id.webview);
        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.setWebViewClient(new WysiwygWebView());
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);

        mWebView.loadUrl(SET_HTML);

        // Editor Button
        EditorButton.setGlobalOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(v.getId()){
                    case R.id.btn_size:
                        break;
                    case R.id.btn_color:
                        break;
                    case R.id.btn_bgcolor:
                        break;
                    case R.id.btn_bold:
                        mWebView.evaluateJavascript("javascript:WE.exec('bold');", null);
                        break;
                    case R.id.btn_italic:
                        mWebView.evaluateJavascript("javascript:WE.exec('italic');", null);
                        break;
                    case R.id.btn_under:
                        mWebView.evaluateJavascript("javascript:WE.exec('underline');", null);
                        break;
                    case R.id.btn_strike:
                        mWebView.evaluateJavascript("javascript:WE.exec('strikeThrough');", null);
                        break;
                    case R.id.btn_align:
                        break;
                    case R.id.btn_img:
                        break;
                    case R.id.btn_video:
                        break;
                    case R.id.btn_cfrm:
                        break;
                    case R.id.btn_cancel:
                        break;
                }
            }
        });

        mButtonSize = findViewById(R.id.btn_size);
        mButtonSize.setOnClickListener(EditorButton.getOnClickListener());

        mButtonColor = findViewById(R.id.btn_color);
        mButtonColor.setOnClickListener(EditorButton.getOnClickListener());

        mButtonBgColor = findViewById(R.id.btn_bgcolor);
        mButtonBgColor.setOnClickListener(EditorButton.getOnClickListener());

        mButtonBold = findViewById(R.id.btn_bold);
        mButtonBold.setOnClickListener(EditorButton.getOnClickListener());

        mButtonItalic = findViewById(R.id.btn_italic);
        mButtonItalic.setOnClickListener(EditorButton.getOnClickListener());

        mButtonUnder = findViewById(R.id.btn_under);
        mButtonUnder.setOnClickListener(EditorButton.getOnClickListener());

        mButtonStrike = findViewById(R.id.btn_strike);
        mButtonStrike.setOnClickListener(EditorButton.getOnClickListener());

        mButtonAlign = findViewById(R.id.btn_align);
        mButtonAlign.setOnClickListener(EditorButton.getOnClickListener());

        mButtonImg = findViewById(R.id.btn_img);
        mButtonImg.setOnClickListener(EditorButton.getOnClickListener());

        mButtonVideo = findViewById(R.id.btn_video);
        mButtonVideo.setOnClickListener(EditorButton.getOnClickListener());

        mButtonConfirm = findViewById(R.id.btn_cfrm);
        mButtonConfirm.setOnClickListener(EditorButton.getOnClickListener());

        mButtonCancel = findViewById(R.id.btn_cancel);
        mButtonCancel.setOnClickListener(EditorButton.getOnClickListener());

    }

}
