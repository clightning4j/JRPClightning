package jrpc.clightning.plugins.interceptor;

import java.lang.reflect.Field;
import jrpc.clightning.annotation.PluginOption;
import jrpc.clightning.plugins.CLightningPlugin;
import jrpc.clightning.plugins.exceptions.CLightningPluginException;
import jrpc.clightning.plugins.log.PluginLog;
import jrpc.service.converters.jsonwrapper.CLightningJsonObject;
import org.reflections.Reflections;

public class MappingCmdOptions implements Interceptor {

  private Reflections reflections;

  public MappingCmdOptions(Reflections reflections) {
    this.reflections = reflections;
  }

  @Override
  public void doAction(
      CLightningPlugin plugin, CLightningJsonObject request, CLightningJsonObject response) {
    if (plugin.hasParametersReady()) return;
    for (Field field : reflections.getFieldsAnnotatedWith(PluginOption.class)) {
      if (field.isAnnotationPresent(PluginOption.class)) {
        PluginOption annotation = field.getAnnotation(PluginOption.class);
        if (plugin.hasParameter(annotation.name())) {
          Object value = plugin.getParameter(annotation.name());
          plugin.log(PluginLog.DEBUG, "Option " + annotation.name() + "=" + value);
          try {
            field.setAccessible(true);
            field.set(plugin, value);
            field.setAccessible(false); // Good manners
          } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new CLightningPluginException(-1, e.getLocalizedMessage(), e.getCause());
          }
        }
      }
    }
  }
}
