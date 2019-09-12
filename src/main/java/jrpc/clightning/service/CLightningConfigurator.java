/**
 * Copyright 2019 Vincenzo Palazzo vincenzopalazzodev@gmail.com
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
package jrpc.clightning.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

/**
 * @author https://github.com/vincenzopalazzo
 */
public class CLightningConfigurator {

    private static final CLightningConfigurator SINGLETON = new CLightningConfigurator();

    private final Logger LOGGER = LoggerFactory.getLogger("CLightningConfigurator");
    private static final String NAME_FILE_CONFIG = "clightning-rpc.properties";
    private static final String NAME_RPC = "lightning-rpc";
    private static final String DEFAULT_DIR = System.getProperty("home.dir") + "/.lightning/"; //TODO work only linux, this is an tempory solution.

    //PROPRIET FILE const
    private static final String RPC_DIR = "RPC_DIR";

    private String url;

    public static CLightningConfigurator getInstance(){
        return SINGLETON;
    }

    private CLightningConfigurator(){
        url = DEFAULT_DIR + NAME_RPC;
        load();
    }

    private void load() {
        Properties configurator = new Properties();
        try {
            configurator.load(ClassLoader.getSystemClassLoader().getResourceAsStream(NAME_FILE_CONFIG));
            url = configurator.getProperty(RPC_DIR);
            LOGGER.debug("The url is: " + url);
        } catch (IOException e) {
            LOGGER.warn("Exception generated inside the CLightningConfigurator id " + e.getLocalizedMessage());
            LOGGER.error("File " + NAME_FILE_CONFIG + " not found, I setting a default dir " + DEFAULT_DIR);
        }

    }

    //getter

    public String getUrl() {
        return url;
    }
}
