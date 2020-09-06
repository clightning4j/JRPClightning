package jrpc.clightning.annotation;

import jrpc.clightning.commands.Command;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface RPCCommand {
    String name() default "null";
    boolean custom() default true;
    Command commandName() default Command.NULL;
}
