package com.example.wysiwyg;

import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.HashMap;

public class WysiwygWebView extends WebViewClient{
    private HashMap<String, Object> stateMap = new HashMap<String, Object>();

    public interface StateEventListener{
        void onReceivedEvent(String str);
    }

    private StateEventListener stateEventListener;

    public void setStateEventListener(StateEventListener listener){
        stateEventListener = listener;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest stateURI){
        //System.out.println("URI load...");
        JsonDecoder jsonDecoder = JsonDecoder.decode(stateURI.getUrl().toString());
        for(EvalCommand cmd : EvalCommand.values()) {
            stateMap.put(cmd.name(), jsonDecoder.getValue(cmd.name()));

            try {
                // Convert Object type to String
                // stateEventListener.onReceivedEvent(jsonDecoder.getValue(EvalCommand.BOLD.toString()));
                /*if(cmd.name().equals("FORECOLOR")) {
                    System.out.print(cmd.name());
                    System.out.print("/");
                    Object tmpobj = jsonDecoder.getValue(cmd.name());
                    //String valStr = ((String)tmpobj).substring(4,((String)tmpobj).length()-1);
                    String valStr = ("RGB(255, 33, 222)").substring(4,("RGB(255, 33, 222)").length()-1);
                    String[] valStrArr = valStr.split("(,\\s)");
                    int[] valIntArr = {Integer.parseInt(valStrArr[0]),Integer.parseInt(valStrArr[1]),Integer.parseInt(valStrArr[2])};
                    System.out.print(valIntArr[0]);//tmpobj);
                    System.out.print(",");
                    System.out.print(valIntArr[1]);
                    System.out.print(",");
                    System.out.print(valIntArr[2]);
                    System.out.print("/");
                    System.out.println(tmpobj instanceof String);
                }//*/
            }
            catch (Exception e) {
                Log.d("JsonDecoder", e.toString());
            }
        }
        return true;
    }

}
