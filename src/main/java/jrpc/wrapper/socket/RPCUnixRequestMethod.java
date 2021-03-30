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
package jrpc.wrapper.socket;

import java.util.Map;

public class RPCUnixRequestMethod implements IWrapperSocketCall {

  private int id;
  private String method;
  private Map<String, Object> params;

  public RPCUnixRequestMethod(String method, Map<String, Object> params) {
    this.id = getRandomNumber();
    this.method = method;
    this.params = params;
  }

  public int getId() {
    return id;
  }

  public String getMethod() {
    return method;
  }

  public Map<String, Object> getParams() {
    return params;
  }

  // TODO generate a correct id number, is correct this number?
  public int getRandomNumber() {
    return (int) (Math.random() * 100);
  }
}
