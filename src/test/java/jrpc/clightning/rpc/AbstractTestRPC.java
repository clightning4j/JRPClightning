package jrpc.clightning.rpc;

import jrpc.clightning.CLightningRPC;
import jrpc.clightning.model.CLightningGetInfo;
import jrpc.service.converters.JsonConverter;
import jrpc.util.MocksUtils;

public class AbstractTestRPC {

  protected static final Class TAG = AbstractTestRPC.class;

  protected CLightningRPC rpc = CLightningRPC.getInstance();
  protected JsonConverter converter = new JsonConverter();
  protected CLightningGetInfo infoFirstNode = MocksUtils.getInfoFirstNode();
}
