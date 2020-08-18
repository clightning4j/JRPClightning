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
package jrpc.clightning.model.types;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * @author https://github.com/vincenzopalazzo
 */
public class CLightningPayment {

    private long id;
    @SerializedName("payment_hash")
    private String paymentHash;
    private String destination;
    @SerializedName("amount_msat")
    private String amountMSat;
    @SerializedName("created_at")
    private Date createdAt;
    private String status;
    @SerializedName("payment_preimage")
    private String paymentPreImage;
    private String label;
    private String bolt11;

    public long getId() {
        return id;
    }

    public String getPaymentHash() {
        return paymentHash;
    }

    public String getDestination() {
        return destination;
    }

    public String getAmountMSat() {
        return amountMSat;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public String getStatus() {
        return status;
    }

    public String getPaymentPreImage() {
        return paymentPreImage;
    }

    public String getLabel() {
        return label;
    }

    public String getBolt11() {
        return bolt11;
    }
}
