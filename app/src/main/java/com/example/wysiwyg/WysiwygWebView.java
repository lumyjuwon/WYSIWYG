package com.example.wysiwyg;

import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.HashMap;

public class WysiwygWebView extends WebViewClient{
    // private HashMap<String, Integer> stateMap = new HashMap<String, Integer>();

    public interface StateEventListener{
        void onReceivedEvent(String str);
    }

    private StateEventListener stateEventListener;

    public void setStateEventListener(StateEventListener listener){
        stateEventListener = listener;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request){
        JsonDecoder jsonDecoder = JsonDecoder.decode(request.getUrl().toString());
//        for(EvalCommand cmd : EvalCommand.values()){
//            stateMap.put()
//        }

        try {
            // Convert Object type to String
            // stateEventListener.onReceivedEvent(jsonDecoder.getValue(EvalCommand.BOLD.toString()));

        }
        catch(Exception e){
            Log.d("JsonDecoder", e.toString());
        }

        return true;
    }

}
