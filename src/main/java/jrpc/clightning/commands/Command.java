/**
 * Copyright 2019-2020 https://github.com/vincenzopalazzo vincenzo.palazzo@protonmail.com
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
public enum Command implements ICommandKey{

    // ---------- BITCOIN COMMANDS ---------------
    FEERATES("FEERATES"), //Supported
    NEWADDR("NEWADDR"), //Supported
    TXDISCARD("TXDISCARD"), //Supported
    TXPREPARE("TXPREPARE"), //Supported
    TXSEND("TXSEND"), //Supported
    WITHDRAW("WITHDRAW"), //Supported

    // ---------- CHANNELS COMMANDS ---------------
    CLOSE("CLOSE"), //Supported
    FUNDCHANNEL("FUNDCHANNEL"), //Supported
    FUNDCHANNEL_CANCEL("FUNDCHANNEL_CANCEL"),
    FUNDCHANNEL_COMPLETE("FUNDCHANNEL_COMPLETE"),
    FUNDCHANNEL_START("FUNDCHANNEL_START"),
    GETROUTE("GETROUTE"), //Supported
    LISTCHANNELS("LISTCHANNELS"), //Supported
    LISTFORWARDS("LISTFORWARDS"),
    SETCHANNELFEE("SETCHANNELFEE"),

    // ---------- NETWORK COMMANDS ---------------
    CONNECT("CONNECT"), // Supported
    DISCONNECT("DISCONNECT"), // Supported
    LISTNODES("LISTNODES"), //Support
    LISTPEERS("LISTPEERS"), //Support
    PING("PING"), //Supported

    // ---------- PAYMENT COMMANDS ---------------
    DECODEPAY("DECODEPAY"), //Suppoeted
    DELEXPIREDINVOICE("DELEXPIREDINVOICE"),
    DELINVOICE("DELINVOICE"), //SUPPORTED
    INVOICE("INVOICE"), //Supported
    LISTINVOICE("LISTINVOICE"), //Supported
    LISTPAYS("LISTPAYS"), //Supported
    LISTSENDPAYS("LISTSENDPAYS"),// Supported
    PAY("PAY"), //Supported
    WAITINVOICE("WAITINVOICE"), //supported
    WAITSENDPAY("WAITSENDPAY"), // Supported
    LISTTRANSACTIONS("LISTTRANSACTIONS"), //Supported


    // ---------- UTILITY COMMANDS ---------------
    GETINFO("GETINFO"), //Supported
    GETLOG("GETLOG"),
    LISTCONFIGS("LISTCONFIGS"),
    LISTFOUNDS("LISTFOUNDS"), //Supported
    STOP("STOP"), //supported

    // ---------- DEVELOPERS COMMANDS ---------------
    DEV_LISTADDRS("DEV_LISTADDRS"),
    DEV_RESCAN_OUTPUTS("DEV_RESCAN_OUTPUTS"),

    // ---------- PLUGINS COMMANDS -------------
    AUTOCLEANINVOICE("AUTOCLEANINVOICE"); //SUPPORTED

    private String commandKey;

    Command(String commandKey) {
        this.commandKey = commandKey;
    }

    @Override
    public String getCommandKey() {
        return commandKey.toLowerCase();
    }
}
