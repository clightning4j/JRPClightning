/**
 * This is a wrapper for c-lightning RPC interface. Copyright (C) 2020 Vincenzo Palazzo
 * vincenzopalazzodev@gmail.com
 *
 * <p>This program is free software; you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation; either version 2 of the
 * License, or (at your option) any later version.
 *
 * <p>This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * <p>You should have received a copy of the GNU General Public License along with this program; if
 * not, write to the Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301 USA.
 */
package jrpc.clightning;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jrpc.clightning.commands.Command;
import jrpc.clightning.commands.CommandRPCMediator;
import jrpc.clightning.commands.ICommandKey;
import jrpc.clightning.commands.IRPCCommand;
import jrpc.clightning.exceptions.CLightningException;
import jrpc.clightning.model.*;
import jrpc.clightning.model.types.*;
import jrpc.clightning.model.types.bitcoin.BitcoinDestination;
import jrpc.clightning.model.types.bitcoin.BitcoinUTXO;
import jrpc.clightning.rpc.bitcoin.CLightningBitcoinRPC;
import jrpc.clightning.rpc.channel.CLightningChannelRPC;
import jrpc.clightning.rpc.network.CLightningNetworkRPC;
import jrpc.clightning.rpc.payment.CLightningPaymentRPC;
import jrpc.clightning.service.CLightningConfigurator;
import jrpc.clightning.service.socket.CLightningSocket;
import jrpc.exceptions.ServiceException;
import jrpc.service.CLightningLogger;
import jrpc.util.ParameterChecker;
import jrpc.wrapper.socket.RPCUnixRequestMethod;

/** @author https://github.com/vincenzopalazzo */
public class CLightningRPC {

  private static final Class TAG = CLightningRPC.class;
  @Deprecated private static CLightningRPC SINGLETON;

  @Deprecated
  public static CLightningRPC getInstance() {
    CLightningRPC result = SINGLETON;
    if (result != null) {
      return result;
    }
    synchronized (CLightningRPC.class) {
      if (SINGLETON == null) {
        SINGLETON = new CLightningRPC();
      }
      return SINGLETON;
    }
  }

  protected String path;
  protected CLightningSocket socket;
  private CLightningBitcoinRPC bitcoinRPC;
  private CLightningNetworkRPC networkRPC;
  private CLightningPaymentRPC paymentRPC;
  private CLightningChannelRPC channelRPC;
  protected CommandRPCMediator mediatorCommand;

  /**
   * Create a client where the configuration it is taken from a config file. TODO: Give the
   * opportunity to use also the os env.
   */
  public CLightningRPC() {
    this(CLightningConfigurator.getInstance().getUrl());
  }

  /**
   * Create a client where with a connection with the unix socket at the specified path.
   *
   * @param path: String that contains the path where the socket it is located
   */
  public CLightningRPC(String path) {
    try {
      this.path = path;
      socket = new CLightningSocket(path);
      this.mediatorCommand = new CommandRPCMediator(socket);
      this.bitcoinRPC = new CLightningBitcoinRPC();
      this.networkRPC = new CLightningNetworkRPC();
      this.paymentRPC = new CLightningPaymentRPC();
      this.channelRPC = new CLightningChannelRPC();
    } catch (CLightningException clex) {
      CLightningLogger.getInstance()
          .error(TAG, "CLightningRPC throws an exception " + clex.getLocalizedMessage());
      socket = null;
    } catch (ServiceException e) {
      socket = null;
      throw new CLightningException(
          "Configuration socket error, Message error is:" + e.getLocalizedMessage());
    }
  }

  public CLightningFeeRate feeRate(FeeRateStyle style) {
    return this.bitcoinRPC.feeRate(mediatorCommand, style);
  }

  public CLightningGetRoutes getRoute(String id, String mSatoshi, float riskFactor) {
    return this.networkRPC.getRoute(mediatorCommand, id, mSatoshi, riskFactor, 9, "", "", 20);
  }

  public CLightningGetRoutes getRoute(
      String id,
      String mSatoshi,
      float riskFactor,
      int cltv,
      String fromId,
      String fuzzPercent,
      int maxHops,
      ExcludeChannel... exclude) {
    return this.networkRPC.getRoute(
        mediatorCommand, id, mSatoshi, riskFactor, cltv, fromId, fuzzPercent, maxHops, exclude);
  }

  public CLightningGetInfo getInfo() {
    return (CLightningGetInfo) mediatorCommand.runCommand(Command.GETINFO, new HashMap<>());
  }

  public String newAddress(AddressType type) {
    return this.bitcoinRPC.newAddress(mediatorCommand, type);
  }

  public CLightningInvoice invoice(String milliSatoshi, String label, String description) {
    return this.invoice(milliSatoshi, label, description, null, new String[] {}, "", false);
  }

  public CLightningInvoice invoice(
      String milliSatoshi,
      String label,
      String description,
      BigInteger expiry,
      String[] fallbacks,
      String preImage,
      boolean exposePrivateChannels) {

    return this.paymentRPC.invoice(
        mediatorCommand,
        milliSatoshi,
        label,
        description,
        expiry,
        fallbacks,
        preImage,
        exposePrivateChannels);
  }

  public CLightningListInvoices listInvoices() {
    return listInvoices("");
  }

  public CLightningListInvoices listInvoices(String label) {
    return this.paymentRPC.listInvoices(mediatorCommand, label);
  }

  public CLightningInvoice delInvoice(String label, String status) {
    return this.paymentRPC.delInvoice(mediatorCommand, label, status);
  }

  public String autoCleanInvoice() {
    return autoCleanInvoice("", "");
  }

  public String autoCleanInvoice(String cycleSeconds, String expiredBy) {
    return this.paymentRPC.autoCleanInvoice(mediatorCommand, cycleSeconds, expiredBy);
  }

  public CLightningBitcoinTx txPrepare(
      List<BitcoinDestination> bitcoinOutputs,
      String feeRate,
      int minConf,
      List<BitcoinUTXO> utxos) {
    return this.bitcoinRPC.txPrepare(mediatorCommand, bitcoinOutputs, feeRate, minConf, utxos);
  }

  public CLightningBitcoinTx txPrepare(List<BitcoinDestination> bitcoinOutputs) {
    return this.txPrepare(bitcoinOutputs, "", 1, new ArrayList<>());
  }

  public CLightningBitcoinTx txDiscard(String txId) {
    return this.bitcoinRPC.txDiscard(mediatorCommand, txId);
  }

  public CLightningBitcoinTx withdraw(
      String destination, String satoshi, String feeRate, int minConf, List<BitcoinUTXO> utxos) {
    return this.bitcoinRPC.withdraw(mediatorCommand, destination, satoshi, feeRate, minConf, utxos);
  }

  public CLightningBitcoinTx withdraw(String destination, String satoshi) {
    return this.withdraw(destination, satoshi, "", 1, new ArrayList<>());
  }

  public CLightningClose close(
      String id,
      String unilateralTimeout,
      String destination,
      String feeNegotiationStep,
      String wrongFunding,
      boolean forceLeaseClosed,
      List<Number> feeange) {
    return this.channelRPC.close(
        mediatorCommand,
        id,
        unilateralTimeout,
        destination,
        feeNegotiationStep,
        wrongFunding,
        forceLeaseClosed,
        feeange);
  }

  public CLightningClose close(String channelId) {
    return this.close(channelId, "", "", "", "", false, new ArrayList<>());
  }

  public CLightningBitcoinTx fundChannel(String id, String amount) {
    return fundChannel(id, amount, "normal", true, 1, new ArrayList<>(), "", "");
  }

  public CLightningBitcoinTx fundChannel(
      String id,
      String amount,
      String feeRate,
      boolean announce,
      int minConf,
      List<BitcoinUTXO> utxos,
      String pushMilliSat,
      String closeTo) {
    return this.channelRPC.fundChannel(
        mediatorCommand, id, amount, feeRate, announce, minConf, utxos, pushMilliSat, closeTo);
  }

  public CLightningListFunds listFunds() {
    return this.listFunds(false);
  }

  public CLightningListFunds listFunds(boolean spent) {
    Map<String, Object> payload = new HashMap<>();
    payload.put("spent", spent);
    return (CLightningListFunds) mediatorCommand.runCommand(Command.LISTFOUNDS, payload);
  }

  public CLightningConnect connect(String id, String host, String port) {
    return this.channelRPC.connect(mediatorCommand, id, host, port);
  }

  public CLightningConnect connect(String id) {
    return this.connect(id, "", "");
  }

  public boolean disconnect(String id) {
    return this.disconnect(id, true);
  }

  public boolean disconnect(String id, boolean force) {
    return this.channelRPC.disconnect(mediatorCommand, id, force);
  }

  public CLightningPayResult pay(String bolt11) {
    return pay(bolt11, "", "", 10, "", 60, "", "");
  }

  public CLightningPayResult pay(
      String bolt11,
      String milliSatoshi,
      String label,
      float riskFactor,
      String maxFeePercent,
      int retryFor,
      String maxDelay,
      String exemptfee) {
    return this.channelRPC.pay(
        mediatorCommand,
        bolt11,
        milliSatoshi,
        label,
        riskFactor,
        maxFeePercent,
        retryFor,
        maxDelay,
        exemptfee);
  }

  public CLightningListSendPays listSendPays() {
    return this.listSendPays("", "");
  }

  public CLightningListSendPays listSendPays(String bolt11, String paymentHash) {
    return this.channelRPC.listSendPays(mediatorCommand, bolt11, paymentHash);
  }

  public CLightningListChannels listChannels() {
    return this.listChannels("", "", "");
  }

  public CLightningListChannels listChannels(
      String shortIdChannel, String source, String destination) {
    return this.channelRPC.listChannels(mediatorCommand, shortIdChannel, source, destination);
  }

  public CLightningListPeers listPeers() {
    return this.listPeers("", "");
  }

  public CLightningListPeers listPeers(String id, String level) {
    return this.channelRPC.listPeers(mediatorCommand, id, level);
  }

  public CLightningDecodePay decodePay(String bolt11) {
    return this.decodePay(bolt11, "");
  }

  public CLightningDecodePay decodePay(String bolt11, String description) {
    return this.channelRPC.decodePay(mediatorCommand, bolt11, description);
  }

  public boolean stop() {
    HashMap<String, Object> payload = new HashMap<>();
    return mediatorCommand
        .runCommand(Command.STOP, payload)
        .toString()
        .contains("Shutdown complete");
  }

  public CLightningListPays listPays(String bolt11, String paymentHash) {
    return this.paymentRPC.listPays(mediatorCommand, bolt11, paymentHash);
  }

  public CLightningListPays listPays() {
    return this.listPays("", "");
  }

  public CLightningInvoice waitInvoice(String label) {
    return this.paymentRPC.waitInvoice(mediatorCommand, label);
  }

  public CLightningSendPay waitSendPay(String paymentHash) {
    return this.waitSendPays(paymentHash, 0, 0);
  }

  public CLightningSendPay waitSendPays(String paymentHash, int timeout, int partid) {
    return this.paymentRPC.waitSendPays(mediatorCommand, paymentHash, timeout, partid);
  }

  public CLightningListNodes listNodes(String nodeId) {
    return this.networkRPC.listNodes(mediatorCommand, nodeId);
  }

  public CLightningListNodes listNodes() {
    return this.listNodes("");
  }

  public CLightningPing ping(String nodeId, int len, int pongBytes) {
    return this.networkRPC.ping(mediatorCommand, nodeId, len, pongBytes);
  }

  public CLightningPing ping(String nodeId) {
    return this.ping(nodeId, 128, 128);
  }

  public CLightningListTransactions listTransactions() {
    return (CLightningListTransactions)
        mediatorCommand.runCommand(Command.LISTTRANSACTIONS, new HashMap<>());
  }

  public CLightningHelp help() {
    return (CLightningHelp) mediatorCommand.runCommand(Command.HELP, new HashMap<>());
  }

  public CLightningFundPSBT fundPSBT(String satoshi, int feeRate, int startWeight) {
    return fundsPSBT(satoshi, feeRate, startWeight, 1, 0, null, null, false);
  }

  public CLightningReserveInputs reserveInputs(String pdbt) {
    return this.reserveInputs(pdbt, false);
  }

  public CLightningReserveInputs reserveInputs(String psbt, boolean exclusive) {
    return this.bitcoinRPC.reserveInputs(mediatorCommand, psbt, exclusive);
  }

  public CLightningPSBT signPSBT(String psbt) {
    return this.bitcoinRPC.signPSBT(mediatorCommand, psbt);
  }

  public CLightningTransaction sendPSBT(String psbt) {
    return this.bitcoinRPC.sendPSBT(mediatorCommand, psbt);
  }

  public CLightningFundPSBT fundsPSBT(
      String satoshi,
      int feeRate,
      int startWeight,
      int minConf,
      int reserve,
      BigInteger lockTime,
      BigInteger minWitnessWeight,
      Boolean excessAsChange) {
    return this.bitcoinRPC.fundsPSBT(
        mediatorCommand,
        satoshi,
        feeRate,
        startWeight,
        minConf,
        reserve,
        lockTime,
        minWitnessWeight,
        excessAsChange);
  }

  public CLightningReserveInputs unReserveInputs(String psbt) {
    return this.bitcoinRPC.unReserveInputs(mediatorCommand, psbt);
  }

  public CLightningListConfigs listConfigs(String config) {
    ParameterChecker.doCheckString("listConfig", "config", config, true);
    Map<String, Object> payload = new HashMap<>();
    if (!config.trim().isEmpty()) {
      payload.put("config", config.trim());
    }

    return (CLightningListConfigs) mediatorCommand.runCommand(Command.LISTCONFIGS, payload);
  }

  public CLightningListConfigs listConfigs() {
    return this.listConfigs("");
  }

  // Register commands
  public <T> void registerCommand(ICommandKey key, IRPCCommand<T> command) {
    if (key == null || command == null) {
      throw new IllegalArgumentException("Key and/or command null");
    }
    mediatorCommand.registerCommand(key, command);
  }

  public void unregisterCommand(ICommandKey key) {
    this.mediatorCommand.unregisterCommand(key);
  }

  public <T> T runRegisterCommand(ICommandKey key, Map<String, Object> payload) {
    return mediatorCommand.runRegisterCommand(key, payload);
  }

  public <T> T runRegisterCommand(String key, Map<String, Object> payload) {
    return mediatorCommand.runRegisterCommand(key, payload);
  }

  public boolean hasCommand(ICommandKey key) {
    return this.mediatorCommand.containsCommand(key, false);
  }

  public boolean hasCommand(ICommandKey key, boolean custom) {
    return this.mediatorCommand.containsCommand(key, custom);
  }

  /**
   * Run any command directly over the UNIX socket, this command is faster, but the final user need
   * to manage the JSON string received from the Socket.
   *
   * @param commandName: The c-lightning command name.
   * @param params: The c-lightning command parameter, if any.
   * @return the raw JSON string received from the UNIX socket.
   * @throws IOException Throw an exception that the UNIX socket generate in case of error
   */
  public String rawCommand(String commandName, Map<String, Object> params) throws IOException {
    ParameterChecker.doCheckString("rawCommand", "commandName", commandName, false);
    ParameterChecker.doCheckObjectNotNull("rawCommand", "params", params);
    RPCUnixRequestMethod jsonRpcWrapper = new RPCUnixRequestMethod(commandName, params);
    return socket.doRawCall(jsonRpcWrapper);
  }

  /**
   * Run any command directly over the UNIX socket, this command is faster, but the final user need
   * to manage the JSON string received from the Socket.
   *
   * @param commandName: The c-lightning command name.
   * @return the raw JSON string received from the UNIX socket.
   * @throws IOException Throw an exception that the UNIX socket generate in case of error
   */
  public String rawCommand(String commandName) throws IOException {
    return this.rawCommand(commandName, new HashMap<>());
  }
}
