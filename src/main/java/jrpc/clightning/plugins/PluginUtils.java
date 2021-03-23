package jrpc.clightning.plugins;

import com.google.gson.JsonObject;

public class PluginUtils {

    public static boolean isNotification(JsonObject object) {
        if(object == null){
            throw new IllegalArgumentException("Rpc call from daemon lightningd with obj null");
        }
        return !object.has("id");
    }

    public static boolean isRpcCall(JsonObject object) {
        if (object == null) {
            throw new IllegalArgumentException("JsonObject null");
        }
        return object.has("method") || object.has("jsonrpc") || object.has("params");
    }
}
