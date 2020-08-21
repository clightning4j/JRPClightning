package jrpc.clightning.plugins.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface PluginOption {
    public String name();
    public String defValue() default "";
    public String typeValue() default "string";
    public String description();
    public boolean deprecated() default false;
}
