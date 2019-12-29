/**
 * Copyright 2019-2020 Vincenzo Palazzo vincenzo.palazzo@protonmail.com
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
package jrpc.clightning.model;

import com.google.gson.annotations.SerializedName;

import javax.xml.crypto.Data;

/**
 * @author https://github.com/vincenzopalazzo
 */
public class CLightningDecodePay {

    private String currency;
    private Data timestamp;
    private long expiry;
    private String payee;
    @SerializedName("payment_hash")
    private String paymentHash;
    private String signature;
    private String description;
    private String msatoshi;
    @SerializedName("amount_msat")
    private String amountMSat;
    //also another field optional


    public String getCurrency() {
        return currency;
    }

    public Data getTimestamp() {
        return timestamp;
    }

    public long getExpiry() {
        return expiry;
    }

    public String getPayee() {
        return payee;
    }

    public String getPaymentHash() {
        return paymentHash;
    }

    public String getSignature() {
        return signature;
    }

    public String getDescription() {
        return description;
    }

    public String getMsatoshi() {
        return msatoshi;
    }

    public String getAmountMSat() {
        return amountMSat;
    }
}
