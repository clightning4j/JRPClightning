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
import jrpc.clightning.model.CLightningListInvoices;
import jrpc.clightning.model.CLightningInvoice;
import jrpc.clightning.model.CLightningNewAddress;
import jrpc.clightning.model.types.AddressType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    public CLightningInvoice getInvoice(int mSatoshi, String label, String description){
        return this.getInvoice(mSatoshi, label, description, "", new String[]{}, "", false);
    }

    /**
     *
     * @param mSatoshi
     * @param label
     * @param description
     * @param expiry is optionally the time the invoice is valid for; without a suffix it is interpreted as seconds,
     *               otherwise suffixes s, m, h, d, w indicate seconds, minutes, hours, days and weeks respectively.
     * @return
     */
    public CLightningInvoice getInvoice(int mSatoshi, String label, String description, String expiry){
        return this.getInvoice(mSatoshi, label, description, expiry, new String[]{}, "", false);
    }

    public CLightningInvoice getInvoice(int mSatoshi, String label, String description, String expiry, String[] fallbacks, String preImage, boolean exposePrivateChannels){
        if(label == null || label.trim().isEmpty()){
            throw new CLightningException("The method getInvoice have the parameter label null");
        }
        if(description == null || description.trim().isEmpty()){
            throw new CLightningException("The method getInvoice have the parameter description null");
        }
        if(expiry == null){
            throw new CLightningException("The method getInvoice have the parameter expiry null");
        }
        if(fallbacks == null){
            throw new CLightningException("The method getInvoice have the parameter fallbacks null");
        }
        if(preImage == null){
            throw new CLightningException("The method getInvoice have the parameter preImage null");
        }
        StringBuilder optionalString = new StringBuilder();
        if(!expiry.trim().isEmpty()){
            optionalString.append("_expiry=").append(expiry.trim()).append("");
        }

        if(fallbacks.length > 0){
            optionalString.append("_fallbacks=[");
            for(int i = 0; i < fallbacks.length; i++){
                if(i == 0){
                    optionalString.append(fallbacks[i].trim());
                    continue;
                }
                optionalString.append(",").append(fallbacks[i].trim());
            }
            optionalString.append("]");
        }

        if(!preImage.trim().isEmpty()){
            optionalString.append("_preimage=").append(preImage.trim());
        }
        if(exposePrivateChannels){
            optionalString.append("_exposeprivatechannels=").append(exposePrivateChannels);
        }

        StringBuilder payload = new StringBuilder();

        payload.append("msatoshi=").append(mSatoshi);
        payload.append("_label=").append(label.trim()).append("");
        payload.append("_description=").append(description.trim()).append("");
        if(!optionalString.toString().trim().isEmpty()){
            payload.append(optionalString.toString());
        }

        String payloadResult = payload.toString();
        LOGGER.debug("Payload: " + payloadResult);

        return (CLightningInvoice) mediatorCommand.runCommand(Command.INVOICE, payloadResult);
    }

    public CLightningListInvoices getListInvoices(String label){
        if(label == null){
            throw new CLightningException("The method getListInvoices have the parameter label null");
        }
        String payload = "";
        if(!label.isEmpty()){
            payload = "label=" + label;
        }

        return (CLightningListInvoices) mediatorCommand.runCommand(Command.LISTINVOICE, payload);
    }

    public CLightningListInvoices getListInvoices(){
        return getListInvoices("");
    }
}
