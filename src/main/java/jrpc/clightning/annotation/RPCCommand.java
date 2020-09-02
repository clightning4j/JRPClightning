package jrpc.clightning.annotation;

import jrpc.clightning.commands.Command;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
//TODO look inside it! I need to know only one key??
public @interface RPCCommand {
    String name();
    boolean custom() default true;
    Command commandName() default Command.NULL;
}
