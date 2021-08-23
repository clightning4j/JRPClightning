package jrpc.clightning.plugin.rpcommand;

import jrpc.mock.rpccommand.CustomCommand;
import jrpc.util.ReflectionManager;
import junit.framework.TestCase;
import org.junit.Test;

public class TestLoadRPCCommand {

  @Test
  public void testLoadRPCCustomCommandOne() {
    var map = ReflectionManager.getInstance().getCustomCommandWithAnnotation();
    TestCase.assertEquals(1, map.size());
  }

  @Test
  public void testLoadRPCCustomCommandTwo() {
    var map = ReflectionManager.getInstance().getCustomCommandWithAnnotation();
    TestCase.assertTrue(map.containsKey(CustomCommand.ANN_DELPAY.getCommandKey()));
  }
}
