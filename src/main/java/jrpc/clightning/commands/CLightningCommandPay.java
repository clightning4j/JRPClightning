/**
 * Copyright 2019-2020 https://github.com/vincenzopalazzo vincenzo.palazzo@protonmail.com
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
package jrpc.clightning.commands;

import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import jrpc.clightning.model.CLightningPayResult;
import jrpc.wrapper.response.RPCResponseWrapper;

/** @author https://github.com/vincenzopalazzo */
public class CLightningCommandPay extends AbstractRPCCommand<CLightningPayResult> {

  private static final String COMMAND_NAME = "pay";

  public CLightningCommandPay() {
    super(COMMAND_NAME);
  }

  @Override
  protected Type toTypeFromClass() {
    return new TypeToken<RPCResponseWrapper<CLightningPayResult>>() {}.getType();
  }
}
