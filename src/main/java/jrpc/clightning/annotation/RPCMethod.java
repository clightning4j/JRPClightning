package jrpc.clightning.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author https://github.com/vincenzopalazzo
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RPCMethod {
    public String name();
    public String description();
    public String longDescription() default "";
    public String parameter() default "";
}
