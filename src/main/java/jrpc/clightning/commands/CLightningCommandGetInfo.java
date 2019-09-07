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
import jrpc.clightning.model.CLightningGetInfo;
import jrpc.wrapper.response.RPCResponseWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;

/**
 * @author https://github.com/vincenzopalazzo
 */
class CLightningCommandGetInfo extends AbstractRPCCommand<CLightningGetInfo>{

    private static final Logger LOGGER = LoggerFactory.getLogger(CLightningCommandGetInfo.class);
    private static final String COMMAND_NAME = "getinfo";

    public CLightningCommandGetInfo() {
        super(COMMAND_NAME);
    }

    @Override
    protected Type toTypeFromClass() {
        return new TypeToken<RPCResponseWrapper<CLightningGetInfo>>(){}.getType();
    }
}
