package com.example.wysiwyg;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;

public class WysiwygEditor extends LinearLayout implements WysiwygWebView.StateEventListener, EditorButton.OnClickListener{
    private final String SET_HTML = "file:///android_asset/editor.html";
    private WysiwygWebView wysiwygWebView;
    private LayoutInflater layoutInflater;
    private View popupView;
    private PopupWindow mPopupWindow;

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
        // State Listener
        wysiwygWebView = new WysiwygWebView();
        wysiwygWebView.setStateEventListener(this);

        mWebView.setWebViewClient(wysiwygWebView);
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);

        mWebView.loadUrl(SET_HTML);

        // Editor Button
        EditorButton.setGlobalCheckedColorFilter(R.color.colorBackBlue);
        EditorButton.setGlobalUnCheckedColorFilter(R.color.colorTextBlack);

        mButtonSize = findViewById(R.id.btn_size);
        mButtonSize.setOnClickListener(this);
        // mButtonSize.setColorFilter

        mButtonColor = findViewById(R.id.btn_color);
        mButtonColor.setOnClickListener(this);

//        mButtonBgColor = findViewById(R.id.btn_bgcolor);
//        mButtonBgColor.setOnClickListener(this);

        mButtonBold = findViewById(R.id.btn_bold);
        mButtonBold.setOnClickListener(this);

        mButtonItalic = findViewById(R.id.btn_italic);
        mButtonItalic.setOnClickListener(this);

//        mButtonUnder = findViewById(R.id.btn_under);
//        mButtonUnder.setOnClickListener(this);

//        mButtonStrike = findViewById(R.id.btn_strike);
//        mButtonStrike.setOnClickListener(this);

        mButtonAlign = findViewById(R.id.btn_align);
        mButtonAlign.setOnClickListener(this);

//        mButtonImg = findViewById(R.id.btn_img);
//        mButtonImg.setOnClickListener(this);
//
//        mButtonVideo = findViewById(R.id.btn_video);
//        mButtonVideo.setOnClickListener(this);

        mButtonConfirm = findViewById(R.id.btn_cfrm);
        mButtonConfirm.setOnClickListener(this);

//        mButtonCancel = findViewById(R.id.btn_cancel);
//        mButtonCancel.setOnClickListener(this);

    }

    @Override
    public void onReceivedEvent(String state) {
        System.out.println("Listener " + state);
    }

    @Override
    public void onClick(View v) {
        // down casting
        EditorButton btn = (EditorButton) v;

        // Change Clicked boolean;
        btn.changeClicked();
        // btn.changeColorFilter();

        // Function
        switch (v.getId()) {
            case R.id.btn_color:
                showColorPopupWindow(v);
                break;
            case R.id.btn_size:
                break;
            case R.id.btn_bold:
                mWebView.evaluateJavascript("javascript:WE.exec('" + EvalCommand.BOLD.toString() + "');", null);
                break;
            case R.id.btn_italic:
                mWebView.evaluateJavascript("javascript:WE.exec('" + EvalCommand.ITALIC.toString() + "');", null);
                break;
            case R.id.btn_under:
                mWebView.evaluateJavascript("javascript:WE.exec('" + EvalCommand.UNDERLINE.toString() + "');", null);
                break;
        }
    }

    public void showColorPopupWindow(View view){
        layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        popupView =layoutInflater.inflate(R.layout.popup_textcolor, null);
        mPopupWindow = new PopupWindow(popupView, RelativeLayout.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setAnimationStyle(1); // 생성 애니메이션 -1, 생성 애니메이션 사용 안 함 0
        mPopupWindow.showAsDropDown(view, -20, - 250);

        Button colorTextBlackButton =findViewById(R.id.color_text_black);
        // colorTextBlackButton.backgroundTint
    }

    public void closePopupWindow(){
        if(mPopupWindow.isShowing())
            mPopupWindow.dismiss();
    }
}
