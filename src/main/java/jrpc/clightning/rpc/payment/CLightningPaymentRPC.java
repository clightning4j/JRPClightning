package jrpc.clightning.rpc.payment;

import java.util.HashMap;
import java.util.Map;
import jrpc.clightning.commands.Command;
import jrpc.clightning.commands.CommandRPCMediator;
import jrpc.clightning.exceptions.CLightningException;
import jrpc.clightning.model.CLightningInvoice;
import jrpc.clightning.model.CLightningListInvoices;
import jrpc.clightning.model.CLightningListPays;
import jrpc.clightning.model.types.CLightningSendPay;
import jrpc.util.ParameterChecker;

public class CLightningPaymentRPC {
  public CLightningInvoice invoice(
      CommandRPCMediator mediatorCommand,
      String milliSatoshi,
      String label,
      String description,
      String expiry,
      String[] fallbacks,
      String preImage,
      boolean exposePrivateChannels) {
    ParameterChecker.doCheckString("invoice", "milliSatoshi", milliSatoshi, false);
    ParameterChecker.doCheckString("invoice", "description", description, false);
    ParameterChecker.doCheckString("invoice", "expiry", expiry, true);
    ParameterChecker.doCheckObjectNotNull("invoice", "expiry", fallbacks);
    ParameterChecker.doCheckObjectNotNull("preImage", "preImage", fallbacks);

    Map<String, Object> payload = new HashMap<>();
    if (!expiry.trim().isEmpty()) {
      payload.put("expiry", expiry);
    }

    if (fallbacks.length > 0) {
      payload.put("fallbacks", fallbacks);
    }

    if (!preImage.trim().isEmpty()) {
      payload.put("preimage", preImage);
    }
    payload.put("exposeprivatechannels", exposePrivateChannels);
    payload.put("msatoshi", milliSatoshi);
    payload.put("label", label);
    payload.put("description", description.trim());
    return (CLightningInvoice) mediatorCommand.runCommand(Command.INVOICE, payload);
  }

  public CLightningListInvoices listInvoices(CommandRPCMediator mediatorCommand, String label) {
    if (label == null) {
      throw new CLightningException("The method getListInvoices have the parameter label null");
    }
    HashMap<String, Object> payload = new HashMap<>();
    if (!label.isEmpty()) {
      payload.put("label", label);
    }

    return (CLightningListInvoices) mediatorCommand.runCommand(Command.LISTINVOICE, payload);
  }

  public CLightningInvoice delInvoice(
      CommandRPCMediator mediatorCommand, String label, String status) {
    if (label == null) {
      throw new CLightningException("The method getListInvoices have the parameter label null");
    }
    if (status == null) {
      throw new CLightningException(
          "The method getListInvoices have the parameter label not valid (empty or null)");
    }

    HashMap<String, Object> payload = new HashMap<>();
    payload.put("label", label);
    payload.put("status", status);
    return (CLightningInvoice) mediatorCommand.runCommand(Command.DELINVOICE, payload);
  }

  public String autoCleanInvoice(
      CommandRPCMediator mediatorCommand, String cycleSeconds, String expiredBy) {
    if (cycleSeconds == null) {
      throw new CLightningException(
          "The method getListInvoices have the parameter cycleSeconds null");
    }
    if (expiredBy == null) {
      throw new CLightningException("The method getListInvoices have the parameter expiredBy null");
    }
    HashMap<String, Object> payload = new HashMap<>();
    if (!cycleSeconds.trim().isEmpty()) {
      payload.put("cycle_seconds", cycleSeconds.trim());
    }
    if (!expiredBy.trim().isEmpty()) {
      payload.put("expired_by", expiredBy.trim());
    }

    return (String) mediatorCommand.runCommand(Command.AUTOCLEANINVOICE, payload);
  }

  public CLightningListPays listPays(
      CommandRPCMediator mediatorCommand, String bolt11, String paymentHash) {
    ParameterChecker.doCheckString("listPays", "bolt11", bolt11, true);
    ParameterChecker.doCheckString("listPays", "paymentHash", paymentHash, true);

    Map<String, Object> payload = new HashMap<>();

    if (!bolt11.trim().isEmpty()) {
      payload.put("bolt11", bolt11.trim());
    }
    if (!paymentHash.trim().isEmpty()) {
      payload.put("payment_hash", paymentHash);
    }
    return (CLightningListPays) mediatorCommand.runCommand(Command.LISTPAYS, payload);
  }

  public CLightningInvoice waitInvoice(CommandRPCMediator mediatorCommand, String label) {
    ParameterChecker.doCheckString("waitInvoice", "label", label, false);
    Map<String, Object> payload = new HashMap<>();
    payload.put("label", label.trim());
    return (CLightningInvoice) mediatorCommand.runCommand(Command.WAITINVOICE, payload);
  }

  public CLightningSendPay waitSendPays(
      CommandRPCMediator mediatorCommand, String paymentHash, int timeout, int partid) {
    ParameterChecker.doCheckString("waitSendPays", "paymentHash", paymentHash, false);
    ParameterChecker.doCheckPositiveNumber("waitSendPays", "timeout", timeout);
    ParameterChecker.doCheckPositiveNumber("waitSendPays", "partid", timeout);

    Map<String, Object> payload = new HashMap<>();
    payload.put("payment_hash", paymentHash.trim());
    if (timeout > 0) {
      payload.put("timeout", timeout);
    }
    if (partid > 0) {
      payload.put("partid", partid);
    }
    return (CLightningSendPay) mediatorCommand.runCommand(Command.WAITSENDPAY, payload);
  }
}
