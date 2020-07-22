package jrpc.clightning.plugins.rpcmethods;

import jrpc.clightning.plugins.log.CLightningLevelLog;
import jrpc.exceptions.ServiceException;
import jrpc.service.CLightningLogger;
import jrpc.service.converters.jsonwrapper.CLightningJsonObject;
import jrpc.clightning.plugins.ICLightningPlugin;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RPCMethodReflection extends AbstractRPCMethod {

    private static final Class TAG = RPCMethodReflection.class;

    private Method method;

    public RPCMethodReflection(String name, String usage, String description, String longDescription, Method method) {
        super(name, usage, description, longDescription);
        this.method = method;
    }

    @Override
    public void doRun(ICLightningPlugin plugin, CLightningJsonObject request, CLightningJsonObject response) {
        try {
            method.invoke(plugin, new Object[]{plugin, request, response});
        } catch (ServiceException |
                IllegalAccessException | InvocationTargetException exception) {
            CLightningLogger.getInstance().error(TAG, exception.getLocalizedMessage());
            plugin.log(CLightningLevelLog.ERROR, exception.getLocalizedMessage());
            exception.printStackTrace();
        }
    }
}
