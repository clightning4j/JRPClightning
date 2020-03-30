package jrpc.clightning.plugins;

import jrpc.clightning.plugins.rpcmethods.RPCMethod;
import jrpc.clightning.plugins.rpcmethods.manifest.types.Features;

import java.util.List;

/**
 * @author https://github.com/vincenzopalazzo
 */
public interface ICLightningPlugin {

    void start();

    void addRPCMethod(RPCMethod method);

    //Getter method
    String getNamePlugin();

    List<RPCMethod> getRpcMethods();

    String getType();

    String getDefaultPropriety();

    String getDescriptionPlugin();

    List<String> getSubscriptions();

    List<String> getHooks();

    boolean isDynamic();

}
