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
package jrpc.clightning.model.types;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.math.BigInteger;

/** @author https://github.com/vincenzopalazzo */
public class CLightningOutput {

  @Expose
  @SerializedName("txid")
  private String txId;

  @Expose private Integer output;
  @Expose private BigInteger value;

  @Expose
  @SerializedName("amount_msat")
  private String amount;

  @Expose private String address;
  @Expose private String status;

  @Expose
  @SerializedName("scriptpubkey")
  private String scriptPubKey;

  @Expose
  @SerializedName("blockheight")
  private BigInteger blockHeight;

  @Expose private Boolean reserved;

  @Expose
  @SerializedName("reserved_to_block")
  private BigInteger reservedToBlock;

  public String getTxId() {
    return txId;
  }

  public Integer getOutput() {
    return output;
  }

  public BigInteger getValue() {
    return value;
  }

  public String getAmount() {
    return amount;
  }

  public String getAddress() {
    return address;
  }

  public String getStatus() {
    return status;
  }

  public void setTxId(String txId) {
    this.txId = txId;
  }

  public void setOutput(Integer output) {
    this.output = output;
  }

  public void setValue(BigInteger value) {
    this.value = value;
  }

  public void setAmount(String amount) {
    this.amount = amount;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getScriptPubKey() {
    return scriptPubKey;
  }

  public void setScriptPubKey(String scriptPubKey) {
    this.scriptPubKey = scriptPubKey;
  }

  public BigInteger getBlockHeight() {
    return blockHeight;
  }

  public void setBlockHeight(BigInteger blockHeight) {
    this.blockHeight = blockHeight;
  }

  public Boolean getReserved() {
    return reserved;
  }

  public BigInteger getReservedToBlock() {
    return reservedToBlock;
  }

  public void setReserved(Boolean reserved) {
    this.reserved = reserved;
  }
}
