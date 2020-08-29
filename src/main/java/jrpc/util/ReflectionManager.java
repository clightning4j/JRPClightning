package jrpc.util;

import java.util.HashMap;
import java.util.Map;

import jrpc.clightning.annotation.RPCCommand;
import jrpc.clightning.commands.IRPCCommand;
import jrpc.service.CLightningLogger;
import org.reflections.Reflections;
import org.reflections.scanners.FieldAnnotationsScanner;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

public class ReflectionManager {
    //TODO MIGRATE ALL REFLECTION CODE INSIDE THIS CLASS
    //TODO test this class before to migrate all
    private static ReflectionManager SINGLETON;

    public static ReflectionManager getInstance(){
        if(SINGLETON == null){
            SINGLETON = new ReflectionManager();
        }
        return SINGLETON;
    }

    private Reflections reflections = new Reflections(new ConfigurationBuilder()
            .setUrls(ClasspathHelper.forPackage("jrpc.clightning"))
            .setScanners(new MethodAnnotationsScanner(),
                    new FieldAnnotationsScanner(),
                    new TypeAnnotationsScanner(),
                    new SubTypesScanner()));

    public Map<String, IRPCCommand> getCommandWithAnnotation() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Map<String, IRPCCommand> commandsAnnotated = new HashMap<>();
        for(Class clazz : reflections.getTypesAnnotatedWith(RPCCommand.class)){
            if(clazz.isAnnotationPresent(RPCCommand.class)){
                RPCCommand rpcCommand = (RPCCommand) clazz.getAnnotation(RPCCommand.class);
                String key = rpcCommand.name();
                CLightningLogger.getInstance().debug(this.getClass(), String.format("Command annotate with : %s", key));
                IRPCCommand newInstance = (IRPCCommand) this.getClass().getClassLoader().loadClass(clazz.getCanonicalName()).newInstance();
                commandsAnnotated.put(key, newInstance);
            }
        }
        CLightningLogger.getInstance().debug(this.getClass(),
                String.format("getCommandWithAnnotation return with %d new methods", commandsAnnotated.size()));
        return commandsAnnotated;
    }

}
