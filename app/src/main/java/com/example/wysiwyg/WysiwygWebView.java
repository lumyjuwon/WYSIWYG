package com.example.wysiwyg;

import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WysiwygWebView extends WebViewClient{

    public interface StateEventListener{
        void onReceivedEvent(String state);
    }

    private StateEventListener stateEventListener;

    public void setStateEventListener(StateEventListener listener){
        stateEventListener = listener;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request){
        JsonDecoder jsonDecoder = JsonDecoder.decode(request.getUrl().toString());

        try {
            // Convert Object type to String
            stateEventListener.onReceivedEvent(jsonDecoder.get(EvalCommand.BOLD.toString()));
        }
        catch(Exception e){
            Log.d("JsonDecoder", e.toString());
        }

        return true;
    }

}
