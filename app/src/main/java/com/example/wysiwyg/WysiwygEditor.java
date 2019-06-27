package com.example.wysiwyg;

import android.content.Context;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static android.content.Context.INPUT_METHOD_SERVICE;
import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class WysiwygEditor extends LinearLayout implements WysiwygWebView.StateEventListener, EditorButton.OnClickListener{
    private final String SET_HTML = "file:///android_asset/editor.html";
    private WysiwygWebView wysiwygWebView;
    private LayoutInflater layoutInflater;
    private View popupView;
    private PopupWindow mPopupWindow;
    private FrameLayout frameLayout;

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
    private int AlignCount = 0;
    @SuppressWarnings("FieldCanBeLocal")
    private EditorButton mButtonImg;
    @SuppressWarnings("FieldCanBeLocal")
    private EditorButton mButtonVideo;
    @SuppressWarnings("FieldCanBeLocal")
    private EditorButton mButtonConfirm;
    @SuppressWarnings("FieldCanBeLocal")
    private EditorButton mButtonCancel;
    @SuppressWarnings("FiledCanBeLocal")
    private EditorButton mButtonDetail;

    private ArrayList<EditorButton> Buttons;
    private ArrayList<EditorButton> popupButtons;

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
        // Initialize Inflater
        layoutInflater = (LayoutInflater) getContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        inflate(getContext(), R.layout.frame_wysiwyg, this);
        frameLayout = findViewById(R.id.framelayout);

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

        mButtonSize = findViewById(R.id.btn_text_size);
        mButtonSize.setOnClickListener(this);

        mButtonColor = findViewById(R.id.btn_color);
        mButtonColor.setOnClickListener(this);

        mButtonBold = findViewById(R.id.btn_bold);
        mButtonBold.setOnClickListener(this);

        mButtonItalic = findViewById(R.id.btn_italic);
        mButtonItalic.setOnClickListener(this);

        mButtonUnder = findViewById(R.id.btn_under);
        mButtonUnder.setOnClickListener(this);

        mButtonAlign = findViewById(R.id.btn_align);
        mButtonAlign.setOnClickListener(this);

        mButtonDetail = findViewById(R.id.btn_detail);
        mButtonDetail.setOnClickListener(this);

        mButtonConfirm = findViewById(R.id.btn_cfrm);
        mButtonConfirm.setOnClickListener(this);

        Buttons = new ArrayList<>(Arrays.asList(mButtonSize, mButtonColor, mButtonBold, mButtonItalic, mButtonUnder, mButtonAlign, mButtonDetail));
        popupButtons = new ArrayList<>(Arrays.asList(mButtonSize, mButtonColor, mButtonDetail));

    }

    @Override
    public void onReceivedEvent(HashMap<String, Object> map) {
        System.out.println(map.toString());
        if(Boolean.valueOf(map.get("BOLD").toString())){
            if(!mButtonBold.getChecked())
                mButtonBold.toggleClicked();
            mButtonBold.setColorFilter(getResources().getColor(R.color.colorPrimaryDark), PorterDuff.Mode.SRC_ATOP);
        }
        else{
            if(mButtonBold.getChecked())
                mButtonBold.toggleClicked();
            mButtonBold.setColorFilter(getResources().getColor(R.color.colorIconDefault), PorterDuff.Mode.SRC_ATOP);
        }

        if(Boolean.valueOf(map.get("ITALIC").toString())){
            if(!mButtonItalic.getChecked())
                mButtonItalic.toggleClicked();
            mButtonItalic.setColorFilter(getResources().getColor(R.color.colorPrimaryDark), PorterDuff.Mode.SRC_ATOP);
        }
        else{
            if(mButtonItalic.getChecked())
                mButtonItalic.toggleClicked();
            mButtonItalic.setColorFilter(getResources().getColor(R.color.colorIconDefault), PorterDuff.Mode.SRC_ATOP);
        }

        if(Boolean.valueOf(map.get("UNDERLINE").toString())){
            if(!mButtonUnder.getChecked())
                mButtonUnder.toggleClicked();
            mButtonUnder.setColorFilter(getResources().getColor(R.color.colorPrimaryDark), PorterDuff.Mode.SRC_ATOP);
        }
        else{
            if(mButtonUnder.getChecked())
                mButtonUnder.toggleClicked();
            mButtonUnder.setColorFilter(getResources().getColor(R.color.colorIconDefault), PorterDuff.Mode.SRC_ATOP);
        }

//        if((int[])map.get("FORECOLOR")){
//            if(!mButtonUnder.getChecked())
//                mButtonUnder.toggleClicked();
//            mButtonUnder.setColorFilter(getResources().getColor(R.color.colorPrimaryDark), PorterDuff.Mode.SRC_ATOP);
//        }
//        else{
//            if(mButtonUnder.getChecked())
//                mButtonUnder.toggleClicked();
//            mButtonUnder.setColorFilter(getResources().getColor(R.color.colorIconDefault), PorterDuff.Mode.SRC_ATOP);
//        }

    }

    @Override
    public void onClick(View v) {
        EditorButton btn = (EditorButton) v;
        btn.toggleClicked();
        toggleButtonColor(btn);
        clearPopup(btn);

        // Function
        switch (v.getId()) {
            case R.id.btn_text_size:
                togglePopupWindow(v);
                break;
            case R.id.btn_color:
                togglePopupWindow(v);
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
            case R.id.btn_align:
                switch(AlignCount){
                    case 0:
                        mWebView.evaluateJavascript("javascript:WE.exec('" + EvalCommand.JUSTIFYCENTER.toString() + "');", null);
                        mButtonAlign.setImageResource(R.drawable.ic_center_align);
                        break;
                    case 1:
                        mWebView.evaluateJavascript("javascript:WE.exec('" + EvalCommand.JUSTIFYRIGHT.toString() + "');", null);
                        mButtonAlign.setImageResource(R.drawable.ic_right_align);
                        break;
                    case 2:
                        mWebView.evaluateJavascript("javascript:WE.exec('" + EvalCommand.JUSTIFYLEFT.toString() + "');", null);
                        mButtonAlign.setImageResource(R.drawable.ic_left_align);
                        break;
                }
                AlignCount++;
                AlignCount %= 3;
                break;
            case R.id.btn_detail:
                togglePopupWindow(v);
                break;
        }
    }

    public void toggleButtonColor(EditorButton btn){
        if(btn.getId() == R.id.btn_align)
            return;
        if(btn.getChecked())
            btn.setColorFilter(getResources().getColor(R.color.colorPrimaryDark), PorterDuff.Mode.SRC_ATOP);
        else
            btn.setColorFilter(getResources().getColor(R.color.colorIconDefault), PorterDuff.Mode.SRC_ATOP);
    }

    public void togglePopupWindow(View view){
        EditorButton btn = (EditorButton) view;
        switch(view.getId()) {
            case R.id.btn_text_size:
                managePopupWindow(btn, view, R.layout.popup_textsize);
                break;
            case R.id.btn_color:
                managePopupWindow(btn, view, R.layout.popup_textcolor);
                break;
            case R.id.btn_detail:
                if(mButtonDetail.getChecked()) {
                    frameLayout.setVisibility(View.VISIBLE);
                    layoutInflater = (LayoutInflater) getContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                    layoutInflater.inflate(R.layout.detail_layout, frameLayout, true);
                }
                else{
                    frameLayout.setVisibility(View.GONE);
                }
                InputMethodManager imm = (InputMethodManager) getContext().getSystemService(INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.HIDE_NOT_ALWAYS, 0);

                EditorButton boldButton = frameLayout.findViewById(R.id.btn_bold_detail);
                boldButton.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mWebView.evaluateJavascript("javascript:WE.exec('" + EvalCommand.BOLD.toString() + "');", null);
                    }
                });
                EditorButton italicButton = frameLayout.findViewById(R.id.btn_bold_italic_detail);
                italicButton.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mWebView.evaluateJavascript("javascript:WE.exec('" + EvalCommand.ITALIC.toString() + "');", null);
                    }
                });
                EditorButton underlineButton = frameLayout.findViewById(R.id.btn_bold_underline_detail);
                underlineButton.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mWebView.evaluateJavascript("javascript:WE.exec('" + EvalCommand.UNDERLINE.toString() + "');", null);
                    }
                });
                EditorButton strikeButton = frameLayout.findViewById(R.id.btn_bold_strike_detail);
                strikeButton.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mWebView.evaluateJavascript("javascript:WE.exec('" + EvalCommand.STRIKETHROUGH.toString() + "');", null);
                    }
                });
                EditorButton alignLeftButton = frameLayout.findViewById(R.id.btn_alignLeft_detail);
                alignLeftButton.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mWebView.evaluateJavascript("javascript:WE.exec('" + EvalCommand.JUSTIFYLEFT.toString() + "');", null);
                    }
                });
                EditorButton alignCenterButton = frameLayout.findViewById(R.id.btn_bold_alignCenter_detail);
                alignCenterButton.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mWebView.evaluateJavascript("javascript:WE.exec('" + EvalCommand.JUSTIFYCENTER.toString() + "');", null);
                    }
                });
                EditorButton alignRightButton = frameLayout.findViewById(R.id.btn_bold_alignRight_detail);
                alignRightButton.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mWebView.evaluateJavascript("javascript:WE.exec('" + EvalCommand.JUSTIFYRIGHT.toString() + "');", null);
                    }
                });
                EditorButton youtubeButton = frameLayout.findViewById(R.id.btn_img_detail);
                youtubeButton.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                EditorButton imgButton = frameLayout.findViewById(R.id.btn_youtube_detail);
                imgButton.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                EditorButton sizeDownButton = frameLayout.findViewById(R.id.btn_sizeDown_detail);
                sizeDownButton.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                EditorButton sizeUpButton = frameLayout.findViewById(R.id.btn_sizeUp_detail);
                sizeUpButton.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                EditorButton colorButton = frameLayout.findViewById(R.id.btn_color_detail);
                colorButton.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                EditorButton bgColorButton = frameLayout.findViewById(R.id.btn_bgcolor_detail);
                bgColorButton.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                EditorButton indentationOutButton = frameLayout.findViewById(R.id.btn_indentation_out);
                indentationOutButton.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                EditorButton indentationInButton = frameLayout.findViewById(R.id.btn_indentation_in);
                indentationInButton.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                EditorButton orderedButton = frameLayout.findViewById(R.id.btn_ordered_detail);
                orderedButton.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                EditorButton unorderedButton = frameLayout.findViewById(R.id.btn_unordered_detail);
                unorderedButton.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                EditorButton spacingUpButton = frameLayout.findViewById(R.id.btn_spacing_up_detail);
                unorderedButton.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                EditorButton spacingDownButton = frameLayout.findViewById(R.id.btn_spacing_down_detail);
                unorderedButton.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
        }
    }

    public void managePopupWindow(EditorButton btn, View view, int layoutName){
        if (mPopupWindow != null) {
            mPopupWindow.dismiss();
            if (!btn.getChecked()) {
                return;
            }
        }
        popupView = layoutInflater.inflate(layoutName, null);
        mPopupWindow = new PopupWindow(popupView, RelativeLayout.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setAnimationStyle(-1); // 생성 애니메이션 -1, 생성 애니메이션 사용 안 함 0
        mPopupWindow.showAsDropDown(view, -20, -250);

        if(layoutName == R.layout.popup_textsize){
            Button sizeXSTextButton = popupView.findViewById(R.id.btn_size_xs);
            sizeXSTextButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    mWebView.evaluateJavascript("javascript:WE.exec('" + EvalCommand.FONTSIZE + "', '" + ResourceData.NINEFONTSIZE + "');", null);
                    clearPopup(new EditorButton(getContext()));
                }
            });
            Button sizeSTextButton = popupView.findViewById(R.id.btn_size_s);
            sizeSTextButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    mWebView.evaluateJavascript("javascript:WE.exec('" + EvalCommand.FONTSIZE + "', '" + ResourceData.TENFONTSIZE + "');", null);
                    clearPopup(new EditorButton(getContext()));
                }
            });
            Button sizeMTextButton = popupView.findViewById(R.id.btn_size_m);
            sizeMTextButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    mWebView.evaluateJavascript("javascript:WE.exec('" + EvalCommand.FONTSIZE + "', '" + ResourceData.ELEVENFONTSIZE + "');", null);
                    clearPopup(new EditorButton(getContext()));
                }
            });
            Button sizeLTextButton = popupView.findViewById(R.id.btn_size_l);
            sizeLTextButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    mWebView.evaluateJavascript("javascript:WE.exec('" + EvalCommand.FONTSIZE + "', '" + ResourceData.TWENTEENFONTSIZE + "');", null);
                    clearPopup(new EditorButton(getContext()));
                }
            });
            Button sizeXLTextButton = popupView.findViewById(R.id.btn_size_xl);
            sizeXLTextButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    mWebView.evaluateJavascript("javascript:WE.exec('" + EvalCommand.FONTSIZE + "', '" + ResourceData.FOURTEENFONTSIZE + "');", null);
                    clearPopup(new EditorButton(getContext()));
                }
            });
        }
        else if(layoutName == R.layout.popup_textcolor){
            Button blackButton = popupView.findViewById(R.id.color_text_black);
            blackButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    mWebView.evaluateJavascript("javascript:WE.exec('" + EvalCommand.FORECOLOR.toString() + "', '" + ResourceData.BLACK.toString() + "');", null);
                    clearPopup(new EditorButton(getContext()));
                }
            });
            Button whiteButton = popupView.findViewById(R.id.color_text_white);
            whiteButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    mWebView.evaluateJavascript("javascript:WE.exec('" + EvalCommand.FORECOLOR.toString() + "', '" + ResourceData.WHITE.toString() + "');", null);
                    clearPopup(new EditorButton(getContext()));}
            });
            Button redButton = popupView.findViewById(R.id.color_text_red);
            redButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    mWebView.evaluateJavascript("javascript:WE.exec('" + EvalCommand.FORECOLOR.toString() + "', '" + ResourceData.RED.toString() + "');", null);
                    clearPopup(new EditorButton(getContext()));
                }
            });
            Button blueButton = popupView.findViewById(R.id.color_text_blue);
            blueButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    mWebView.evaluateJavascript("javascript:WE.exec('" + EvalCommand.FORECOLOR.toString() + "', '" + ResourceData.BLUE.toString() + "');", null);
                    clearPopup(new EditorButton(getContext()));
                }
            });
            Button greenButton = popupView.findViewById(R.id.color_text_green);
            greenButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    mWebView.evaluateJavascript("javascript:WE.exec('" + EvalCommand.FORECOLOR.toString() + "', '" + ResourceData.GREEN.toString() + "');", null);
                    clearPopup(new EditorButton(getContext()));
                }
            });
        }
    }

    public void clearPopup(EditorButton editorButton){
        if(mPopupWindow != null)
            mPopupWindow.dismiss();
        for(EditorButton btn : popupButtons){
            if(btn.getChecked() && btn.getId() != editorButton.getId()) {
                btn.toggleClicked();
                toggleButtonColor(btn);
                if(btn.getId() == R.id.btn_detail){
                    frameLayout.setVisibility(View.GONE);
                }
            }
        }
    }

    public void clearButtons(){
        for(EditorButton btn : Buttons){
            if(btn.getChecked()) {
                btn.toggleClicked();
                btn.setColorFilter(getResources().getColor(R.color.colorIconDefault), PorterDuff.Mode.SRC_ATOP);
            }
        }
    }
}
