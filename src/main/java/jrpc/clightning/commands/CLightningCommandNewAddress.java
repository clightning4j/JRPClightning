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
package jrpc.clightning.commands;

import com.google.gson.reflect.TypeToken;
import jrpc.clightning.exceptions.CommandException;
import jrpc.clightning.model.CLightningNewAddress;
import jrpc.clightning.service.socket.CLightningSocket;
import jrpc.exceptions.ServiceException;
import jrpc.wrapper.response.RPCResponseWrapper;
import jrpc.wrapper.socket.RPCUnixRequestMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;
import java.util.HashMap;

/**
 * @author https://github.com/vincenzopalazzo
 */
public class CLightningCommandNewAddress extends AbstractRPCCommand<CLightningNewAddress>{

    private static final Logger LOGGER = LoggerFactory.getLogger(CLightningCommandNewAddress.class);
    private static final String IDENTIFIER_KEY = "addresstyp";
    private static final String COMMAND_NAME = "newaddr";

    public CLightningCommandNewAddress() {
        super(COMMAND_NAME);
    }

    @Override
    public CLightningNewAddress doRPCCommand(CLightningSocket socket, HashMap<String, Object> payload) throws ServiceException, CommandException {
        super.doRPCCommand(socket, payload);

        RPCUnixRequestMethod wrapper = new RPCUnixRequestMethod(commandName, payload);
        Type collectionType = new TypeToken<RPCResponseWrapper<CLightningNewAddress>>(){}.getType();
        RPCResponseWrapper<CLightningNewAddress> response = (RPCResponseWrapper<CLightningNewAddress>) socket.doCall(wrapper, collectionType);
        return response.getResult();
    }
}
