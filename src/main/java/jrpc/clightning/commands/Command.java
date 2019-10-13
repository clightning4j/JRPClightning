/**
 * Copyright 2019 Vincenzo Palazzo vincenzopalazzodev@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jrpc.clightning.commands;

/**
 * @author https://github.com/vincenzopalazzo
 */
public enum Command {

    // ---------- BITCOIN COMMANDS ---------------
    FEERATES,
    NEWADDR, //Supported
    TXDISCARD, //Supported
    TXPREPARE, //Not supported inside the version 0.7.2
    TXSEND, //Supported
    WITHDRAW, //Supported

    // ---------- CHANNELS COMMANDS ---------------
    CLOSE, //Supported
    FUNDCHANNEL, //Supported
    FUNDCHANNEL_CANCEL,
    FUNDCHANNEL_COMPLETE,
    FUNDCHANNEL_START,
    GETROUTE,
    LISTCHANNELS,
    LISTFORWARDS,
    SETCHANNELFEE,

    // ---------- NETWORK COMMANDS ---------------
    CONNECT,
    DISCONNECT,
    LISTNODES,
    LISTPEERS,
    PING,

    // ---------- PAYMENT COMMANDS ---------------
    DECODEPAY,
    DELEXPIREDINVOICE,
    DELINVOICE, //SUPPORTED
    INVOICE, //Supported
    LISTINVOICE, //Supported
    LISTPAYMENTS,
    LISTSENDPAYS,
    PAY,
    WAITINGINVOICE,
    WAITSENDPAY,

    // ---------- UTILITY COMMANDS ---------------
    GETINFO, //Supported
    GETLOG,
    LISTCONFIGS,
    LISTFOUNDS, //Supported
    STOP,

    // ---------- DEVELOPERS COMMANDS ---------------
    DEV_LISTADDRS,
    DEV_RESCAN_OUTPUTS,

    // ---------- PLUGINS COMMANDS -------------
    AUTOCLEANINVOICE, //SUPPORTED
}
