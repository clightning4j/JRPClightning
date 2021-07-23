/**
 * Copyright 2019-2020 Vincenzo Palazzo vincenzo.palazzo@protonmail.com
 *
 * <p>Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License at
 *
 * <p>http://www.apache.org/licenses/LICENSE-2.0
 *
 * <p>Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jrpc.clightning.model;

import com.google.gson.annotations.SerializedName;

/** @author https://github.com/vincenzopalazzo */
public class CLightningInvoice {

  // Propriety inside the invoice command
  @SerializedName("payment_hash")
  private String paymentHash;

  @SerializedName("expires_at")
  private String expiresAt;

  private String bolt11;

  @SerializedName("warning_private_unused")
  private String warningPrivateUnused;

  @SerializedName("warning_capacity")
  private String warningCapacity;

  @SerializedName("warning_offline")
  private String warningOffline;

  @SerializedName("warning_deadends")
  private String warningDeadends;

  @SerializedName("payment_secret")
  private String paymentSecret;

  @SerializedName("msatoshi")
  private String milliSatoshi;

  // Get information inside the commadn listinvoices
  private String label;
  private String description;
  private String status;

  public String getPaymentHash() {
    return paymentHash;
  }

  public String getExpiresAt() {
    return expiresAt;
  }

  public String getBolt11() {
    return bolt11;
  }

  public String getWarningPrivateUnused() {
    return warningPrivateUnused;
  }

  public String getWarningCapacity() {
    return warningCapacity;
  }

  public String getWarningOffline() {
    return warningOffline;
  }

  public String getWarningDeadends() {
    return warningDeadends;
  }

  public String getLabel() {
    return label;
  }

  public String getMilliSatoshi() {
    return milliSatoshi;
  }

  public String getStatus() {
    return status;
  }

  public String getDescription() {
    return description;
  }

  public String getPaymentSecret() {
    return paymentSecret;
  }
}
