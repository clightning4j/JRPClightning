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
package jrpc.clightning.commands;

/** @author https://github.com/vincenzopalazzo */
public enum Command implements ICommandKey {
  NULL("NULL"), // Used to indicate that null value

  HELP("HELP"), // Supported

  // ---------- BITCOIN COMMANDS ---------------
  FEERATES("FEERATES"), // Supported
  FUNDPSBT("FUNDPSBT"), // Supported
  RESERVEINPUTS("RESERVEINPUTS"), // Supported
  SENDPSBT("SENDPSBT"), // Supported
  SIGNPSBT("SIGNPSBT"), // Supported
  UNRESERVEINPUTS("UNRESERVEINPUTS"),
  NEWADDR("NEWADDR"), // Supported
  TXDISCARD("TXDISCARD"), // Supported
  TXPREPARE("TXPREPARE"), // Supported
  TXSEND("TXSEND"), // Supported
  WITHDRAW("WITHDRAW"), // Supported

  // ---------- CHANNELS COMMANDS ---------------
  CLOSE("CLOSE"), // Supported
  FUNDCHANNEL("FUNDCHANNEL"), // Supported
  FUNDCHANNEL_CANCEL("FUNDCHANNEL_CANCEL"),
  FUNDCHANNEL_COMPLETE("FUNDCHANNEL_COMPLETE"),
  FUNDCHANNEL_START("FUNDCHANNEL_START"),
  GETROUTE("GETROUTE"), // Supported
  LISTCHANNELS("LISTCHANNELS"), // Supported
  LISTFORWARDS("LISTFORWARDS"),
  SETCHANNELFEE("SETCHANNELFEE"),

  // ---------- NETWORK COMMANDS ---------------
  CONNECT("CONNECT"), // Supported
  DISCONNECT("DISCONNECT"), // Supported
  LISTNODES("LISTNODES"), // Support
  LISTPEERS("LISTPEERS"), // Support
  PING("PING"), // Supported

  // ---------- PAYMENT COMMANDS ---------------
  DECODEPAY("DECODEPAY"), // Suppoeted
  DELEXPIREDINVOICE("DELEXPIREDINVOICE"),
  DELINVOICE("DELINVOICE"), // SUPPORTED
  INVOICE("INVOICE"), // Supported
  LISTINVOICE("LISTINVOICE"), // Supported
  LISTPAYS("LISTPAYS"), // Supported
  LISTSENDPAYS("LISTSENDPAYS"), // Supported
  PAY("PAY"), // Supported
  WAITINVOICE("WAITINVOICE"), // supported
  WAITSENDPAY("WAITSENDPAY"), // Supported
  LISTTRANSACTIONS("LISTTRANSACTIONS"), // Supported

  // ---------- UTILITY COMMANDS ---------------
  GETINFO("GETINFO"), // Supported
  GETLOG("GETLOG"),
  LISTCONFIGS("LISTCONFIGS"),
  LISTFOUNDS("LISTFOUNDS"), // Supported
  STOP("STOP"), // supported

  // ---------- DEVELOPERS COMMANDS ---------------
  DEV_LISTADDRS("DEV_LISTADDRS"),
  DEV_RESCAN_OUTPUTS("DEV_RESCAN_OUTPUTS"),

  // ---------- PLUGINS COMMANDS -------------
  AUTOCLEANINVOICE("AUTOCLEANINVOICE"); // SUPPORTED

  private String commandKey;

  Command(String commandKey) {
    this.commandKey = commandKey;
  }

  @Override
  public String getCommandKey() {
    return commandKey.toLowerCase();
  }
}
