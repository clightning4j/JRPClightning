package jrpc.clightning.rpc.channel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jrpc.clightning.commands.Command;
import jrpc.clightning.commands.CommandRPCMediator;
import jrpc.clightning.model.*;
import jrpc.clightning.model.types.CLightningConnect;
import jrpc.clightning.model.types.bitcoin.BitcoinUTXO;
import jrpc.util.ParameterChecker;

public class CLightningChannelRPC {

  public CLightningBitcoinTx close(
      CommandRPCMediator mediatorCommand,
      String channelId,
      String unilateralTimeout,
      String feeNegotiationStep,
      String wrongFunding) {
    ParameterChecker.doCheckString("close", "channelId", channelId, false);
    ParameterChecker.doCheckString("close", "unilateralTimeout", unilateralTimeout, true);
    ParameterChecker.doCheckString("close", "feeNegotiationStep", feeNegotiationStep, true);
    ParameterChecker.doCheckString("close", "wrongFunding", wrongFunding, true);

    HashMap<String, Object> payload = new HashMap<>();
    payload.put("id", channelId.trim());

    if (!unilateralTimeout.trim().isEmpty()) {
      payload.put("unilateraltimeout", unilateralTimeout.trim());
    }

    if (!feeNegotiationStep.isEmpty()) {
      payload.put("fee_negotiation_step", feeNegotiationStep.trim());
    }

    if (!wrongFunding.isEmpty()) {
      payload.put("wrong_funding", wrongFunding.trim());
    }
    return (CLightningBitcoinTx) mediatorCommand.runCommand(Command.CLOSE, payload);
  }

  public CLightningBitcoinTx fundChannel(
      CommandRPCMediator mediatorCommand,
      String id,
      String amount,
      String feerate,
      boolean announce,
      int minConf,
      List<BitcoinUTXO> utxos,
      String pushMilliSat,
      String closeTo) {
    ParameterChecker.doCheckString("fundChannel", "id", id, false);
    ParameterChecker.doCheckString("fundChannel", "amount", amount, false);
    ParameterChecker.doCheckString("fundChannel", "feerate", feerate, false);
    ParameterChecker.doCheckString("fundChannel", "pushMilliSat", pushMilliSat, true);
    ParameterChecker.doCheckString("fundChannel", "closeTo", closeTo, true);
    ParameterChecker.doCheckPositiveNumber("fundChannel", "minConf", minConf, false);

    Map<String, Object> payload = new HashMap<>();
    payload.put("id", id);
    payload.put("amount", amount.trim());
    payload.put("feerate", feerate.trim());

    if (!announce) {
      payload.put("announce", announce);
    }

    if (!utxos.isEmpty()) {
      List<String> utxoString = new ArrayList<>();
      utxos.forEach(it -> utxoString.add(it.toString()));
      payload.put("utxos", utxoString);
    }

    if (!pushMilliSat.isEmpty()) {
      payload.put("push_msat", pushMilliSat);
    }

    if (!closeTo.isEmpty()) {
      payload.put("close_to", closeTo);
    }

    return (CLightningBitcoinTx) mediatorCommand.runCommand(Command.FUNDCHANNEL, payload);
  }

  public CLightningConnect connect(
      CommandRPCMediator mediatorCommand, String id, String host, String port) {
    ParameterChecker.doCheckString("connect", "id", id, false);
    ParameterChecker.doCheckString("connect", "host", host, true);
    ParameterChecker.doCheckString("connect", "port", port, true);

    HashMap<String, Object> payload = new HashMap<>();
    payload.put("id", id);
    if (!host.isEmpty()) {
      payload.put("host", host);
    } else {
      payload.put("host", "127.0.0.1");
    }
    if (!port.isEmpty()) {
      payload.put("port", port);
    } else {
      payload.put("port", "9735");
    }

    return (CLightningConnect) mediatorCommand.runCommand(Command.CONNECT, payload);
  }

  public boolean disconnect(CommandRPCMediator mediatorCommand, String id, boolean force) {
    ParameterChecker.doCheckString("disconnect", "id", id, false);

    HashMap<String, Object> payload = new HashMap<>();
    payload.put("id", id);
    payload.put("force", force);

    return mediatorCommand.runCommand(Command.DISCONNECT, payload) != null;
  }

  public CLightningPayResult pay(
      CommandRPCMediator mediatorCommand,
      String bolt11,
      String milliSatoshi,
      String label,
      float riskFactor,
      String maxFeePercent,
      int retryFor,
      String maxDelay,
      String exemptfee) {
    ParameterChecker.doCheckString("pay", "bolt11", bolt11, false);
    ParameterChecker.doCheckString("pay", "milliSatoshi", milliSatoshi, true);
    ParameterChecker.doCheckString("pay", "label", label, true);
    ParameterChecker.doCheckString("pay", "maxFeePercent", maxFeePercent, true);
    ParameterChecker.doCheckString("pay", "maxDelay", maxDelay, true);
    ParameterChecker.doCheckString("pay", "exemptfee", exemptfee, true);

    HashMap<String, Object> payload = new HashMap<>();
    payload.put("bolt11", bolt11.trim());
    payload.put("riskfactor", riskFactor);
    payload.put("retry_for", retryFor);
    if (!milliSatoshi.trim().isEmpty()) {
      payload.put("msatoshi", milliSatoshi.trim());
    }

    if (!label.trim().isEmpty()) {
      payload.put("label", label.trim());
    }

    if (!maxFeePercent.trim().isEmpty()) {
      payload.put("maxfeepercent", maxFeePercent.trim());
    }

    if (!maxDelay.trim().isEmpty()) {
      payload.put("maxdelay", maxDelay.trim());
    }

    if (!exemptfee.trim().isEmpty()) {
      payload.put("exemptfee", exemptfee);
    }

    return (CLightningPayResult) mediatorCommand.runCommand(Command.PAY, payload);
  }

  public CLightningListSendPays listSendPays(
      CommandRPCMediator mediatorCommand, String bolt11, String paymentHash) {
    ParameterChecker.doCheckString("listSendPays", "bolt11", bolt11, true);
    ParameterChecker.doCheckString("listSendPays", "paymentHash", paymentHash, true);

    HashMap<String, Object> payload = new HashMap<>();
    if (!bolt11.trim().isEmpty()) {
      payload.put("bolt11", bolt11.trim());
    }
    if (!paymentHash.trim().isEmpty()) {
      payload.put("payment_hash", bolt11.trim());
    }
    return (CLightningListSendPays) mediatorCommand.runCommand(Command.LISTSENDPAYS, payload);
  }

  public CLightningListChannels listChannels(
      CommandRPCMediator mediatorCommand,
      String shortIdChannel,
      String source,
      String destination) {
    ParameterChecker.doCheckString("listChannels", "shortIdChannel", shortIdChannel, true);
    ParameterChecker.doCheckString("listChannels", "source", source, true);
    ParameterChecker.doCheckString("listChannels", "destination", source, true);

    HashMap<String, Object> payload = new HashMap<>();
    if (!shortIdChannel.trim().isEmpty()) {
      payload.put("short_channel_id", shortIdChannel.trim());
    }

    if (!source.isEmpty()) {
      payload.put("source", source.trim());
    }

    if (!destination.isEmpty()) {
      payload.put("destination", source.trim());
    }

    return (CLightningListChannels) mediatorCommand.runCommand(Command.LISTCHANNELS, payload);
  }

  public CLightningListPeers listPeers(
      CommandRPCMediator mediatorCommand, String id, String level) {
    ParameterChecker.doCheckString("listPeers", "id", id, true);
    ParameterChecker.doCheckString("listPeers", "level", level, true);
    HashMap<String, Object> payload = new HashMap<>();

    if (!id.trim().isEmpty()) {
      payload.put("id", id);
    }
    if (!level.isEmpty()) {
      payload.put("level", level.trim());
    }

    return (CLightningListPeers) mediatorCommand.runCommand(Command.LISTPEERS, payload);
  }

  public CLightningDecodePay decodePay(
      CommandRPCMediator mediatorCommand, String bolt11, String description) {
    ParameterChecker.doCheckString("decodePay", "bolt11", bolt11, false);
    ParameterChecker.doCheckString("decodePay", "description", description, true);

    HashMap<String, Object> payload = new HashMap<>();
    payload.put("bolt11", bolt11.trim());
    if (!description.trim().isEmpty()) {
      payload.put("description", description.trim());
    }

    return (CLightningDecodePay) mediatorCommand.runCommand(Command.DECODEPAY, payload);
  }
}
