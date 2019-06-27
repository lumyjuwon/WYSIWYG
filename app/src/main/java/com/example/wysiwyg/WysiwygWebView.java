package com.example.wysiwyg;

import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.HashMap;

public class WysiwygWebView extends WebViewClient{
    private HashMap<String, String> stateMap = new HashMap<String, String>();

    public interface StateEventListener{
        void onReceivedEvent(HashMap<String, String> map);
    }

    private StateEventListener stateEventListener;

    public void setStateEventListener(StateEventListener listener){
        stateEventListener = listener;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest stateURI){
        System.out.println("URI load...");
        JsonDecoder jsonDecoder = JsonDecoder.decode(stateURI.getUrl().toString());
        for(EvalCommand cmd : EvalCommand.values()) {
            stateMap.put(cmd.name(), jsonDecoder.getValue(cmd.name()));

            try {
                // Convert Object type to String
                // stateEventListener.onReceivedEvent(jsonDecoder.getValue(EvalCommand.BOLD.toString()));
                /*if(cmd.name().equals("BOLD")) {
                    System.out.print(cmd.name());
                    System.out.print("/");
                    Object tmpobj = jsonDecoder.getValue(cmd.name());
                    System.out.print(tmpobj);
                    System.out.print("/");
                    System.out.println(tmpobj instanceof String);
                }*/
            }
            catch (Exception e) {
                Log.d("JsonDecoder", e.toString());
            }

        }

        return true;
    }

}
