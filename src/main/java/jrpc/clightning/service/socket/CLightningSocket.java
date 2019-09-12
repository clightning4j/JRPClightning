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
package jrpc.clightning.service.socket;

import jrpc.exceptions.ServiceException;
import jrpc.service.socket.UnixDomainSocketRpc;

/**
 * @author https://github.com/vincenzopalazzo
 */
public class CLightningSocket extends UnixDomainSocketRpc{

    public CLightningSocket(String pathSocket) throws ServiceException {
        super(pathSocket);
    }

    public CLightningSocket() throws ServiceException {
        super(""); //TODO setting default configuration
    }


}
