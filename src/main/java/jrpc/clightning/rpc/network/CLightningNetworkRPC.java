package jrpc.clightning.rpc.network;

import java.util.HashMap;
import java.util.Map;
import jrpc.clightning.commands.Command;
import jrpc.clightning.commands.CommandRPCMediator;
import jrpc.clightning.model.CLightningGetRoutes;
import jrpc.clightning.model.CLightningListNodes;
import jrpc.clightning.model.types.CLightningPing;
import jrpc.clightning.model.types.ExcludeChannel;
import jrpc.util.ParameterChecker;

public class CLightningNetworkRPC {

  public CLightningGetRoutes getRoute(
      CommandRPCMediator mediatorCommand,
      String id,
      String mSatoshi,
      float riskFactor,
      int cltv,
      String fromid,
      String fuzzpercent,
      int maxHops,
      ExcludeChannel... exclude) {
    ParameterChecker.doCheckString("getRoute", "id", id, false);
    ParameterChecker.doCheckString("getRoute", "mSatoshi", mSatoshi, false);
    ParameterChecker.doCheckString("getRoute", "fromid", fromid, true);
    ParameterChecker.doCheckString("getRoute", "fuzzpercent", fuzzpercent, true);
    ParameterChecker.doCheckPositiveNumber("getRoute", "riskFactor", riskFactor);
    ParameterChecker.doCheckPositiveNumber("getRoute", "cltv", cltv);
    ParameterChecker.doCheckPositiveNumber("getRoute", "maxHops", maxHops);

    HashMap<String, Object> payload = new HashMap<>();

    payload.put("id", id);
    payload.put("amount_msat", maxHops);
    payload.put("riskfactor", riskFactor);
    payload.put("cltv", cltv);
    payload.put("maxhops", maxHops);

    if (!fromid.isEmpty()) {
      payload.put("fromid", fromid);
    }

    if (!fuzzpercent.isEmpty()) {
      payload.put("fuzzpercent", fuzzpercent);
    }

    if (exclude.length > 0) {
      payload.put("exclude", exclude);
    }
    return (CLightningGetRoutes) mediatorCommand.runCommand(Command.GETROUTE, payload);
  }

  public CLightningListNodes listNodes(CommandRPCMediator mediatorCommand, String nodeId) {
    ParameterChecker.doCheckString("listNodes", "nodeId", nodeId, true);
    Map<String, Object> payload = new HashMap<>();
    if (!nodeId.trim().isEmpty()) {
      payload.put("id", nodeId);
    }
    return (CLightningListNodes) mediatorCommand.runCommand(Command.LISTNODES, payload);
  }

  public CLightningPing ping(
      CommandRPCMediator mediatorCommand, String nodeId, int len, int pongBytes) {
    ParameterChecker.doCheckString("ping", "ping", nodeId, false);
    ParameterChecker.doCheckPositiveNumber("ping", "len", len);
    ParameterChecker.doCheckPositiveNumber("ping", "pongBytes", pongBytes);
    Map<String, Object> payload = new HashMap<>();
    payload.put("id", nodeId);
    payload.put("len", len);
    payload.put("pongbytes", pongBytes);
    return (CLightningPing) mediatorCommand.runCommand(Command.PING, payload);
  }
}
