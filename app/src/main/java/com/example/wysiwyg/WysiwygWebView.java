package com.example.wysiwyg;

import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.HashMap;

public class WysiwygWebView extends WebViewClient{
    private HashMap<String, Object> stateMap = new HashMap<String, Object>();

    public interface StateEventListener{
        void onReceivedEvent(HashMap<String, Object> map);
    }

    private StateEventListener stateEventListener;

    public void setStateEventListener(StateEventListener listener){
        stateEventListener = listener;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest stateURI){
        JsonDecoder jsonDecoder = JsonDecoder.decode(stateURI.getUrl().toString());
        for(EvalCommand cmd : EvalCommand.values()) {
            stateMap.put(cmd.name(), jsonDecoder.getValue(cmd.name()));
        }
        stateEventListener.onReceivedEvent(stateMap);
        return true;
    }

}
