package jrpc.clightning.plugins;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import jrpc.clightning.plugins.log.CLightningLevelLog;
import jrpc.clightning.plugins.rpcmethods.AbstractRPCMethod;
import jrpc.service.converters.jsonwrapper.CLightningJsonObject;

import java.util.List;

/**
 * @author https://github.com/vincenzopalazzo
 */
public interface ICLightningPlugin {

    void start();

    void addRPCMethod(AbstractRPCMethod method);

    List<AbstractRPCMethod> getRpcMethods();

    List<String> getSubscriptions();

    List<String> getHooks();

    boolean isDynamic();

    void log(CLightningLevelLog level, String logMessage);

    void log(CLightningLevelLog level, CLightningJsonObject json);

    void log(CLightningLevelLog level, Object json);

    void addParameter(String key, Object value);

    <T> T getParameter(String key);

}
