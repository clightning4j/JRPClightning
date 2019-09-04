/**
 * Copyright 2019 https://github.com/vincenzopalazzo
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
package jrpc.clightning;

import jrpc.clightning.commands.Command;
import jrpc.clightning.commands.CommandRPCMediator;
import jrpc.clightning.exceptions.CLightningException;
import jrpc.clightning.model.CLightningGetInfo;
import jrpc.clightning.model.CLightningInvoice;
import jrpc.clightning.model.CLightningNewAddress;
import jrpc.clightning.model.types.AddressType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.plugin.ClassLoaderInfo;

/**
 * @author https://github.com/vincenzopalazzo
 */
public class CLightningRPC {

    private static final Logger LOGGER = LoggerFactory.getLogger(CLightningRPC.class);
    private static final CLightningRPC SINGLETON = new CLightningRPC();

    public static CLightningRPC getInstance(){
        return SINGLETON;
    }

    protected CommandRPCMediator mediatorCommand;

    private CLightningRPC() {
        this.mediatorCommand = new CommandRPCMediator();
    }


    public CLightningGetInfo getInfo(){
        String payload = "";
        CLightningGetInfo resultCommand = (CLightningGetInfo) mediatorCommand.runCommand(Command.GETINFO, payload);
        return resultCommand;
    }

    public String getNewAddress(AddressType type){
        if(type == null){
            throw new IllegalArgumentException("Type address is null");
        }
        String typeString = null;
        if(type.equals(AddressType.BECH32)){
            typeString = "bech32";
        }else {
            typeString = "p2sh-segwit";
        }
        String payload = "addresstype=" + typeString;
        LOGGER.debug("Payload: " + payload);
        CLightningNewAddress resultCommand = (CLightningNewAddress) mediatorCommand.runCommand(Command.NEWADDR, payload);
        if(type.equals(AddressType.BECH32)){
           return resultCommand.getBech32();
        }
        return resultCommand.getP2shSegwit();
    }

    //TODO added the invoice for proprieties fallbacks, preimage, exposeprivatechannels
    public CLightningInvoice getInvoice(int msatoshi, String label, String description, String expiry){
        if(label == null || label.isEmpty()){
            throw new CLightningException("The method getInvoice have the parameter label null");
        }
        if(description == null || description.isEmpty()){
            throw new CLightningException("The method getInvoice have the parameter description null");
        }
        if(expiry == null){
            throw new CLightningException("The method getInvoice have the parameter expiry null");
        }
        String payload = "msatoshi=" + msatoshi + "_label=" + label +
                         "_description=" + description;
        if(!expiry.isEmpty()){
            payload += "_expiry=" + expiry;
        }
        CLightningInvoice response = (CLightningInvoice) mediatorCommand.runCommand(Command.INVOICE, payload);
        return response;
    }

    public CLightningInvoice getInvoice(int msatoshi, String label, String description){
        if(label == null || label.isEmpty()){
            throw new CLightningException("The method getInvoice have the parameter label null");
        }
        if(description == null || description.isEmpty()){
            throw new CLightningException("The method getInvoice have the parameter description null");
        }
        return this.getInvoice(msatoshi, label, description, "");
    }

}
