package jrpc.clightning.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface PluginOption {
    String name();
    String description();
    //TODO this option sholw have different type!
    String defValue() default  "null";
    String typeValue() default "string";
    boolean deprecated() default false;
}
