package com.example.wysiwyg;

import android.util.Log;

import org.json.JSONObject;

import java.net.URLDecoder;

public class JsonDecoder {

    private static final String CALLBACK_PREFIX = "we-state://";
    private static String mJson;
    private static JsonDecoder jsonDecoder;
    private static JSONObject items;

    public static JsonDecoder decode(String target){
        if(jsonDecoder == null){
            jsonDecoder = new JsonDecoder();
        }
        try{
            mJson = URLDecoder.decode(target.replace(CALLBACK_PREFIX, ""), "UTF-8");
        }
        catch(Exception e){
            Log.d("JsonDecoder", e.toString());
        }
        return jsonDecoder;
    }

    public Integer getValue(String key){
        try {
            // Convert Object type to String
            items = new JSONObject(mJson);
            return 1;
        }
        catch(Exception e){
            Log.d("JsonDecoder", e.toString());
            return null;
        }
    }
}
