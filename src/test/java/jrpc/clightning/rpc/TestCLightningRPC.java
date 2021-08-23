/**
 * Copyright 2019 Vincenzo Palazzo vincenzopalazzodev@gmail.com
 *
 * <p>Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License at
 *
 * <p>http://www.apache.org/licenses/LICENSE-2.0
 *
 * <p>Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jrpc.clightning.rpc;

import java.util.ArrayList;
import java.util.HashMap;
import jrpc.clightning.exceptions.CLightningException;
import jrpc.clightning.exceptions.CommandException;
import jrpc.clightning.model.*;
import jrpc.clightning.service.CLightningConfigurator;
import jrpc.mock.rpccommand.CustomCommand;
import jrpc.mock.rpccommand.PersonalDelPayRPCCommand;
import junit.framework.TestCase;
import org.junit.Test;

/** @author https://github.com/vincenzopalazzo */
public class TestCLightningRPC extends AbstractTestRPC {

  @Test
  public void testCommandGetInfo() {
    CLightningGetInfo infoNode = rpc.getInfo();
    TestCase.assertNotNull(infoNode.getNetwork());
    TestCase.assertNotNull(infoNode.getColor());
    TestCase.assertNotNull(infoNode.getId());
  }

  @Test
  public void testCommandFundChannelOne() {
    try {
      String[] addresses = new String[] {"2N9bpBQHvJvM3FtbTn4XuSMRR2ZxCHR2J97"};
      CLightningBitcoinTx txBitcoin =
          rpc.fundChannel(
              infoFirstNode.getId(), "10000", "normal", true, 1, new ArrayList<>(), "", "");
      TestCase.fail();
    } catch (CLightningException ex) {
      TestCase.assertTrue(ex.getMessage().contains("Error inside command with error code:"));
    }
  }

  @Test
  public void testListTransactionsOne() {
    CLightningListTransactions listTransactions = rpc.listTransactions();
    TestCase.assertNotNull(listTransactions);
  }

  @Test
  public void testHelpOne() {
    CLightningHelp help = rpc.help();
    TestCase.assertNotNull(help);
  }

  @Test
  public void testHelpTwo() {
    CLightningHelp help = rpc.help();
    TestCase.assertTrue(help.getHelpItems().size() > 0);
    var count = (int) help.getHelpItems().stream().count();
    TestCase.assertEquals(help.getHelpItems().size(), count);
  }

  @Test
  public void testListConfigs() {
    CLightningListConfigs configs = rpc.listConfigs();
    TestCase.assertNotNull(configs);
    TestCase.assertEquals("regtest", configs.getNetwork());
    TestCase.assertEquals(
        CLightningConfigurator.getInstance().getSocketPath(),
        configs.getLightningDir() + "/" + configs.getNetwork());
  }

  // This command is available inside the lightning project
  // from version 0.9.1
  @Test(expected = CommandException.class)
  public void testCustomCommandDelPayOne() {
    HashMap<String, Object> payload = new HashMap<>();
    payload.put("payment_hash", "YOUR_BOLT11");
    PersonalDelPayRPCCommand paysCommand = new PersonalDelPayRPCCommand();
    rpc.registerCommand(CustomCommand.DELPAY, paysCommand);

    CLightningListPays result = rpc.runRegisterCommand(CustomCommand.DELPAY, payload);
    TestCase.assertNotNull(result);
    rpc.unregisterCommand(CustomCommand.DELPAY);
    TestCase.assertFalse(rpc.hasCommand(CustomCommand.DELPAY, true));
  }

  @Test(expected = CommandException.class)
  public void testCustomCommandDelPayWithAnnotation() {
    HashMap<String, Object> payload = new HashMap<>();
    payload.put("payment_hash", "YOUR_BOLT11");

    TestCase.assertTrue(rpc.hasCommand(CustomCommand.ANN_DELPAY, true));
    CLightningListPays result = rpc.runRegisterCommand(CustomCommand.ANN_DELPAY, payload);
    TestCase.assertNotNull(result);
  }
}
