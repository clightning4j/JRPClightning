package jrpc.clightning.rpc.bitcoin;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jrpc.clightning.commands.Command;
import jrpc.clightning.commands.CommandRPCMediator;
import jrpc.clightning.exceptions.CLightningException;
import jrpc.clightning.model.*;
import jrpc.clightning.model.types.AddressType;
import jrpc.clightning.model.types.CLightningPSBT;
import jrpc.clightning.model.types.CLightningTransaction;
import jrpc.clightning.model.types.FeeRateStyle;
import jrpc.clightning.model.types.bitcoin.BitcoinDestination;
import jrpc.clightning.model.types.bitcoin.BitcoinUTXO;
import jrpc.service.CLightningLogger;
import jrpc.util.ParameterChecker;

public class CLightningBitcoinRPC {

  private static final Class TAG = CLightningBitcoinRPC.class;

  public CLightningFeeRate feeRate(CommandRPCMediator mediatorCommand, FeeRateStyle style) {
    if (style == null) {
      throw new CLightningException("style null");
    }
    HashMap<String, Object> payload = new HashMap<>();
    payload.put("style", style.toString());
    return (CLightningFeeRate) mediatorCommand.runCommand(Command.FEERATES, payload);
  }

  public String newAddress(CommandRPCMediator mediatorCommand, AddressType type) {
    if (type == null) {
      throw new IllegalArgumentException("Type address is null");
    }
    String typeString;
    if (type.equals(AddressType.BECH32)) {
      typeString = "bech32";
    } else {
      typeString = "p2sh-segwit";
    }
    HashMap<String, Object> payload = new HashMap<>();
    payload.put("addresstype", typeString);
    CLightningLogger.getInstance().debug(TAG, "Payload: " + payload);
    CLightningNewAddress resultCommand =
        (CLightningNewAddress) mediatorCommand.runCommand(Command.NEWADDR, payload);
    if (type.equals(AddressType.BECH32)) {
      return resultCommand.getBech32();
    }
    return resultCommand.getP2shSegwit();
  }

  public CLightningBitcoinTx txPrepare(
      CommandRPCMediator mediatorCommand,
      List<BitcoinDestination> bitcoinOutputs,
      String feeRate,
      int minConf,
      List<BitcoinUTXO> utxos) {
    ParameterChecker.doCheckObjectNotNull("txPrepare", "bitcoinOutputs", bitcoinOutputs);
    ParameterChecker.doCheckString("txPrepare", "feeRate", feeRate, true);
    ParameterChecker.doCheckPositiveNumber("minConf", "minConf", minConf);
    ParameterChecker.doCheckObjectNotNull("txPrepare", "utxos", utxos);
    if (bitcoinOutputs.isEmpty()) {
      throw new CLightningException(
          "The method getListInvoices have the parameter output is/are empty");
    }

    HashMap<String, Object> payload = new HashMap<>();
    payload.put("outputs", bitcoinOutputs);
    payload.put("minconf", minConf);

    if (!feeRate.isEmpty()) payload.put("feerate", feeRate);

    if (!utxos.isEmpty()) payload.put("utxos", utxos);
    return (CLightningBitcoinTx) mediatorCommand.runCommand(Command.TXPREPARE, payload);
  }

  public CLightningBitcoinTx txDiscard(CommandRPCMediator mediatorCommand, String txId) {
    ParameterChecker.doCheckString("txDiscard", "txId", txId, false);

    HashMap<String, Object> payload = new HashMap<>();
    payload.put("txid", txId);
    return (CLightningBitcoinTx) mediatorCommand.runCommand(Command.TXDISCARD, payload);
  }

  public CLightningBitcoinTx withdraw(
      CommandRPCMediator mediatorCommand,
      String destination,
      String satoshi,
      String feeRate,
      int minconf,
      List<BitcoinUTXO> utxos) {
    ParameterChecker.doCheckString("withdraw", "destination", destination, false);
    ParameterChecker.doCheckString("withdraw", "satoshi", satoshi, false);
    ParameterChecker.doCheckString("withdraw", "feerate", feeRate, true);
    ParameterChecker.doCheckPositiveNumber("withdraw", "minconf", minconf, true);

    HashMap<String, Object> payload = new HashMap<>();

    payload.put("destination", destination.trim());
    payload.put("satoshi", satoshi.trim());
    payload.put("minconf", minconf);

    if (!utxos.isEmpty()) {
      payload.put("utxos", utxos);
    }

    if (!feeRate.isEmpty()) {
      payload.put("feerate", feeRate.trim());
    }

    return (CLightningBitcoinTx) mediatorCommand.runCommand(Command.WITHDRAW, payload);
  }

  public CLightningFundPSBT fundsPSBT(
      CommandRPCMediator mediatorCommand,
      String satoshi,
      int feeRate,
      int startWeight,
      int minConf,
      boolean reserve,
      BigInteger lockTime,
      BigInteger minWitnessWeight,
      Boolean excessAsChange) {
    ParameterChecker.doCheckString("fundPSBT", "satishi", satoshi, false);
    ParameterChecker.doCheckPositiveNumber("fundPSBT", "feeRate", feeRate);
    ParameterChecker.doCheckPositiveNumber("fundPSBT", "startWeight", startWeight);
    ParameterChecker.doCheckPositiveNumber("fundPSBT", "minconf", minConf);
    ParameterChecker.doCheckPositiveNumber("fundPSBT", "locktime", lockTime, true);
    ParameterChecker.doCheckPositiveNumber("fundPSBT", "minWitnessWeight", minWitnessWeight, true);

    Map<String, Object> payload = new HashMap<>();

    payload.put("satoshi", satoshi);
    payload.put("feerate", feeRate);
    payload.put("startweight", startWeight);
    payload.put("minconf", minConf);
    payload.put("reserve", reserve);

    if (lockTime != null) payload.put("locktime", lockTime);
    if (minWitnessWeight != null) payload.put("min_witness_weight", minWitnessWeight);
    // TODO added after 0.9.3
    // if (excessAsChange != null) payload.put("excess_as_change", excessAsChange);

    return (CLightningFundPSBT) mediatorCommand.runCommand(Command.FUNDPSBT, payload);
  }

  public CLightningReserveInputs unReserveInputs(CommandRPCMediator mediatorCommand, String psbt) {
    ParameterChecker.doCheckString("unReserveInputs", "psbt", psbt, false);
    Map<String, Object> payload = new HashMap<>();
    payload.put("psbt", psbt);
    return (CLightningReserveInputs) mediatorCommand.runCommand(Command.UNRESERVEINPUTS, payload);
  }

  public CLightningReserveInputs reserveInputs(
      CommandRPCMediator mediatorCommand, String psbt, boolean exclusive) {
    ParameterChecker.doCheckString("reserveInputs", "psbt", psbt, false);
    ParameterChecker.doCheckString("reserveInputs", "psbt", psbt, false);
    Map<String, Object> payload = new HashMap<>();
    payload.put("pdbt", psbt);
    payload.put("exclusive", exclusive);
    return (CLightningReserveInputs) mediatorCommand.runCommand(Command.RESERVEINPUTS, payload);
  }

  public CLightningPSBT signPSBT(CommandRPCMediator mediatorCommand, String psbt) {
    ParameterChecker.doCheckString("signPSBT", "psbt", psbt, false);
    Map<String, Object> payload = new HashMap<>();
    payload.put("psbt", psbt);
    return (CLightningPSBT) mediatorCommand.runCommand(Command.SIGNPSBT, payload);
  }

  public CLightningTransaction sendPSBT(CommandRPCMediator mediatorCommand, String psbt) {
    ParameterChecker.doCheckString("sendPSBT", "psbt", psbt, false);
    Map<String, Object> payload = new HashMap<>();
    payload.put("psbt", psbt);
    return (CLightningTransaction) mediatorCommand.runCommand(Command.SENDPSBT, payload);
  }
}
