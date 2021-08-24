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
package jrpc.clightning.service;

import java.io.IOException;
import java.util.Properties;
import java.util.StringTokenizer;
import jrpc.service.CLightningLogger;

/**
 * TODO Refactoring this class
 *
 * @author https://github.com/vincenzopalazzo
 */
public class CLightningConfigurator {

  private static CLightningConfigurator SINGLETON = new CLightningConfigurator();

  private final Class TAG = CLightningConfigurator.class;
  private static final String NAME_FILE_CONFIG = "/clightning-rpc.properties";
  private static final String NAME_RPC = "lightning-rpc";
  // TODO this path is not valid in c-lightning > 0.7.2.1
  private static final String DEFAULT_DIR =
      System.getProperty("home.dir")
          + "/.lightning/"; // TODO work only linux, this is an momentary solution.

  // PROPRIETIES FILE value
  private static final String RPC_DIR = "RPC_DIR";

  private String url;
  private String socketPath;
  private String socketFileName;

  public static CLightningConfigurator getInstance() {
    CLightningConfigurator result = SINGLETON;
    if (result != null) {
      return result;
    }
    synchronized (CLightningConfigurator.class) {
      if (SINGLETON == null) {
        SINGLETON = new CLightningConfigurator();
      }
      return SINGLETON;
    }
  }

  private CLightningConfigurator() {
    url = DEFAULT_DIR + NAME_RPC;
    load();
  }

  private void load() {
    Properties configurator = new Properties();
    try {
      configurator.load(this.getClass().getResourceAsStream(NAME_FILE_CONFIG));
      url = configurator.getProperty(RPC_DIR);
      CLightningLogger.getInstance().debug(TAG, "The url is: " + url);
    } catch (IOException e) {
      CLightningLogger.getInstance()
          .error(
              TAG,
              "Exception generated inside the CLightningConfigurator id "
                  + e.getLocalizedMessage());
      CLightningLogger.getInstance()
          .error(
              TAG,
              "File " + NAME_FILE_CONFIG + " not found, I setting a default dir " + DEFAULT_DIR);
    }
  }

  private void analyzeUrl() {
    // TODO look the delimitator
    StringTokenizer tokenizer = new StringTokenizer(url, "/");
    int start = 0;
    int end = tokenizer.countTokens();
    while (tokenizer.hasMoreElements()) {
      start++;
      if (start == end) {
        socketFileName = tokenizer.nextToken();
        socketPath = url.substring(0, url.length() - socketFileName.length() - 1);
        CLightningLogger.getInstance().debug(TAG, socketPath);
      } else {
        tokenizer.nextToken();
      }
    }
  }

  // getter
  public String getUrl() {
    return url;
  }

  public synchronized void changeUrlRpcFile(String urlRpcFile) {
    this.url = urlRpcFile;
  }

  public String getSocketPath() {
    if (url == null) {
      throw new IllegalArgumentException("URL socket is null");
    }
    if (socketPath == null) {
      analyzeUrl();
    }
    return socketPath;
  }

  public String getSocketFileName() {
    if (url == null) {
      throw new IllegalArgumentException("URL socket is null");
    }
    if (socketFileName == null) {
      analyzeUrl();
    }
    return socketFileName;
  }
}
