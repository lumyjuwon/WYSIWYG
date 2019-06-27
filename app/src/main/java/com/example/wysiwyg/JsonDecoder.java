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

    public Object getValue(String key){
        try {
            // Convert Object type to String
            items = new JSONObject(mJson.toUpperCase());
            //if(key.equals("BOLD")) System.out.println(mJson);
            String valStr = items.get(key).toString().toLowerCase();
            if(key.equals("FONTNAME")){
                if(valStr.equals("null")) return "arail"; //기본 폰트로 변경 필요
                return valStr;
            }
            else if(key.equals("FONTSIZE")||key.equals("LINEHEIGHT")){
                if(valStr.equals("null")) return "14"; //기본 폰트 크기로 변경 필요
                return Float.parseFloat(valStr);
            }
            else if(key.equals("FORECOLOR")) {
                if(valStr.equals("null")) {
                    int[] val = {0,0,0}; //기본 배경색으로 변경 필요
                    return val;
                }
                valStr = valStr.substring(4,valStr.length()-1);
                String[] valStrArr = valStr.split("(,\\s)");
                int[] valIntArr = {Integer.parseInt(valStrArr[0]), Integer.parseInt(valStrArr[1]), Integer.parseInt(valStrArr[2])};
                return valIntArr;
            }
            else if(key.equals("BACKCOLOR")) {
                if(valStr.equals("null")) {
                    int[] val = {0,0,0,255}; //투명 배경색으로 변경 필요
                    return val;
                }                  valStr = valStr.substring(5,valStr.length()-1);
                String[] valStrArr = valStr.split("(,\\s)");
                int[] valIntArr = {Integer.parseInt(valStrArr[0]), Integer.parseInt(valStrArr[1]), Integer.parseInt(valStrArr[2]), Integer.parseInt(valStrArr[3])};
                return valIntArr;
            }
            if(valStr.equals("true")) return true;
            return false;
        }
        catch(Exception e){
            Log.d("JsonDecoder", e.toString());
            return null;
        }
    }
}
