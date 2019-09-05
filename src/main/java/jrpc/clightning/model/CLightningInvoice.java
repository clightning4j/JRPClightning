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
package jrpc.clightning.model;

import com.google.gson.annotations.SerializedName;

/**
 * @author https://github.com/vincenzopalazzo
 */
public class CLightningInvoice {

    @SerializedName("payment_hash")
    private String paymentHash;
    @SerializedName("expires_at")
    private String expiresAt;
    private String bolt11;
    @SerializedName("warning_capacity")
    private String warningCapacity;
    private String label;
    @SerializedName("msatoshi")
    private String mSatoshi;
    private String status;
    private String description;

    public String getPaymentHash() {
        return paymentHash;
    }

    public String getExpiresAt() {
        return expiresAt;
    }

    public String getBolt11() {
        return bolt11;
    }

    public String getWarningCapacity() {
        return warningCapacity;
    }

    public String getLabel() {
        return label;
    }

    public String getmSatoshi() {
        return mSatoshi;
    }

    public String getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }
}
