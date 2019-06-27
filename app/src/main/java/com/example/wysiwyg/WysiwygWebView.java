package com.example.wysiwyg;

import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import org.json.JSONObject;

public class WysiwygWebView extends WebViewClient {

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request){
        JsonDecoder jsonDecoder = JsonDecoder.decode(request.getUrl().toString());

        try {
            // Convert Object type to String
            System.out.println(jsonDecoder.get(EvalCommand.BOLD.toString()));
        }
        catch(Exception e){
            Log.d("JsonDecoder", e.toString());
        }

        return true;
    }

}
