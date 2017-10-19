package message.utils;

import java.lang.reflect.Type;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class GsonUtils {
    static GsonBuilder builder;
    static Gson gson;

    static {
        builder = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss");
        gson = builder.create();
    }

    public static Object jsonToObject(String json, Type type) {
        return gson.fromJson(json, type);
    }

    public static String objectToJson(Object obj) {
        if (null == obj) {
            return null;
        }
        return gson.toJson(obj);
    }

    public static String maptoJsonstr(Map map) {
        return gson.toJson(map);
    }

    public static Object jsonToObject(Map map, Class<?> type) {
        String json = maptoJsonstr(map);
        return gson.fromJson(json, type);
    }
}
