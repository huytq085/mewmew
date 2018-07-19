package com.culicode.dating.mewmew.util;


import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class JsonUtil {
    private static Gson gson = new Gson();

    public static String encode(Object obj) {
        String jsonStr = "";
        jsonStr = gson.toJson(obj);

        return jsonStr;
    }

    public static Gson getGson() {
        return gson;
    }

    public static <T> T decode(String jsonStr, Class<T> c) {
        T obj = null;
        try {
            obj = gson.fromJson(jsonStr, c);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return obj;
    }
}