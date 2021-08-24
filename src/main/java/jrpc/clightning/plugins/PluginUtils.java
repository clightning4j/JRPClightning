package jrpc.clightning.plugins;

import com.google.gson.JsonObject;
import jrpc.util.ParameterChecker;

public class PluginUtils {

  public static boolean isNotification(JsonObject object) {
    ParameterChecker.checkJSONObjectNotNull("isNotification", PluginUtils.class, object);
    return !object.has("id");
  }

  public static boolean isRpcCall(JsonObject object) {
    ParameterChecker.checkJSONObjectNotNull("isRpcCall", PluginUtils.class, object);
    // TODO it is an and conditions?
    return object.has("method") || object.has("jsonrpc") || object.has("params");
  }
}
