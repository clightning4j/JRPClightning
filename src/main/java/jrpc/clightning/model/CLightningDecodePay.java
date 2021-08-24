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
package jrpc.clightning.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import javax.xml.crypto.Data;

/** @author https://github.com/vincenzopalazzo */
public class CLightningDecodePay {

  @Expose private String currency;
  @Expose private Data timestamp;
  @Expose private long expiry;
  @Expose private String payee;

  @Expose
  @SerializedName("payment_hash")
  private String paymentHash;

  @Expose private String signature;
  @Expose private String description;
  @Expose private String msatoshi;

  @Expose
  @SerializedName("amount_msat")
  private String amountMSat;
  // also another field optional

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
