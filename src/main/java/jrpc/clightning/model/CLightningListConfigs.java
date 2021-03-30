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

import com.google.gson.annotations.SerializedName;
import java.util.List;
import jrpc.clightning.model.types.CLightningPluginConfModel;

/** @author https://github.com/vincenzopalazzo */
public class CLightningListConfigs {
  // TODO if there are the propriety allowDeprecatedApis (true by default) gson generate an error
  // because
  // contains more proprietis plugin :-) the in this case the list plugin was empty!
  @SerializedName("# version")
  private String version;

  @SerializedName("lightning-dir")
  private String lightningDir;

  private String network;

  @SerializedName("allow-deprecated-apis")
  private String allowDeprecatedApis;

  @SerializedName("rpc-file")
  private String rpcFile;
  // TODO create a plugin wrapper
  private List<CLightningPluginConfModel> plugins;

  @SerializedName("important-plugins")
  private List<CLightningPluginConfModel> importantPlugins;

  @SerializedName("disable-plugin")
  private List<String> disablePlugin;

  @SerializedName("always-use-proxy")
  private boolean alwaysUseProxy;

  private boolean daemon;
  private String wallet;
  private boolean wumbo;

  @SerializedName("rgb")
  private String color;

  private String alias;

  @SerializedName("pid-file:")
  private String pidFile;

  @SerializedName("ignore-fee-limits")
  private boolean ignoreFeeLimits;

  @SerializedName("watchtime-blocks")
  private int watchTimeBlocks;

  @SerializedName("max-locktime-blocks")
  private long maxLockTimeBlocks;

  @SerializedName("funding-confirms")
  private long fundingConfirms;

  @SerializedName("commit-fee-min")
  private int commitFeeMin;

  @SerializedName("commit-fee-max")
  private int commitFeeMax;

  @SerializedName("cltv-delta")
  private int cltvDeelta;

  @SerializedName("cltv-final")
  private int cltvFinal;

  @SerializedName("commit-time")
  private int commitTime;

  @SerializedName("fee-base")
  private int feeBase;

  private long rescan;

  @SerializedName("fee-per-satoshi")
  private int feePerSatoshi;

  @SerializedName("max-concurrent-htlcs")
  private int maxConcurrentHtlcs;

  @SerializedName("min-capacity-sat")
  private int minCapacitySat;

  private String addr;

  @SerializedName("bind-addr")
  private String bindAddr;

  @SerializedName("announce-addr")
  private String announceAddr;

  private boolean offline;
  private boolean autolisten;
  private String proxy;

  @SerializedName("disable-dns")
  private boolean disableDns;

  @SerializedName("enable-autotor-v2-mode")
  private boolean enableAutotorV2Mode;

  @SerializedName("encrypted-hsm")
  private boolean encryptedHsm;

  @SerializedName("rpc-file-mode")
  private String rpcFileMode;

  @SerializedName("log-level")
  private String logLevel;

  @SerializedName("log-prefix")
  private String logPrefix;

  public String getVersion() {
    return version;
  }

  public String getLightningDir() {
    return lightningDir;
  }

  public String getNetwork() {
    return network;
  }

  public String getAllowDeprecatedApis() {
    return allowDeprecatedApis;
  }

  public String getRpcFile() {
    return rpcFile;
  }

  public List<CLightningPluginConfModel> getPlugins() {
    return plugins;
  }

  public List<CLightningPluginConfModel> getImportantPlugins() {
    return importantPlugins;
  }

  public List<String> getDisablePlugin() {
    return disablePlugin;
  }

  public boolean isAlwaysUseProxy() {
    return alwaysUseProxy;
  }

  public boolean isDaemon() {
    return daemon;
  }

  public String getWallet() {
    return wallet;
  }

  public boolean isWumbo() {
    return wumbo;
  }

  public String getColor() {
    return color;
  }

  public String getAlias() {
    return alias;
  }

  public String getPidFile() {
    return pidFile;
  }

  public boolean isIgnoreFeeLimits() {
    return ignoreFeeLimits;
  }

  public int getWatchTimeBlocks() {
    return watchTimeBlocks;
  }

  public long getMaxLockTimeBlocks() {
    return maxLockTimeBlocks;
  }

  public long getFundingConfirms() {
    return fundingConfirms;
  }

  public int getCommitFeeMin() {
    return commitFeeMin;
  }

  public int getCommitFeeMax() {
    return commitFeeMax;
  }

  public int getCltvDeelta() {
    return cltvDeelta;
  }

  public int getCltvFinal() {
    return cltvFinal;
  }

  public int getCommitTime() {
    return commitTime;
  }

  public int getFeeBase() {
    return feeBase;
  }

  public long getRescan() {
    return rescan;
  }

  public int getFeePerSatoshi() {
    return feePerSatoshi;
  }

  public int getMaxConcurrentHtlcs() {
    return maxConcurrentHtlcs;
  }

  public int getMinCapacitySat() {
    return minCapacitySat;
  }

  public String getAddr() {
    return addr;
  }

  public String getBindAddr() {
    return bindAddr;
  }

  public String getAnnounceAddr() {
    return announceAddr;
  }

  public boolean isOffline() {
    return offline;
  }

  public boolean isAutolisten() {
    return autolisten;
  }

  public String getProxy() {
    return proxy;
  }

  public boolean isDisableDns() {
    return disableDns;
  }

  public boolean isEnableAutotorV2Mode() {
    return enableAutotorV2Mode;
  }

  public boolean isEncryptedHsm() {
    return encryptedHsm;
  }

  public String getRpcFileMode() {
    return rpcFileMode;
  }

  public String getLogLevel() {
    return logLevel;
  }

  public String getLogPrefix() {
    return logPrefix;
  }
}
