/**
 * Copyright 2019-2020 Vincenzo Palazzo vincenzo.palazzo@protonmail.com
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jrpc.clightning.service.socket;

import jrpc.clightning.service.CLightningConfigurator;
import jrpc.exceptions.ServiceException;
import jrpc.service.socket.UnixDomainSocketRpc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author https://github.com/vincenzopalazzo
 */
public class CLightningSocket extends UnixDomainSocketRpc {

    private static final Logger LOGGER = LoggerFactory.getLogger(CLightningSocket.class);

    public CLightningSocket(String pathSocket) throws ServiceException {
        super(pathSocket);
        LOGGER.debug("Path socket is: " + pathSocket);
    }

    public CLightningSocket() throws ServiceException {
        super(CLightningConfigurator.getInstance().getUrl().trim()); //TODO setting default configuration
        LOGGER.debug(CLightningConfigurator.getInstance().getUrl().trim());
    }
}
