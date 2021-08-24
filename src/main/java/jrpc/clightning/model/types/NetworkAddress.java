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

/** @author https://github.com/vincenzopalazzo */
public class NetworkAddress {

  @Expose private String type;
  @Expose private String address;
  @Expose private int port;

  public String getType() {
    return type;
  }

  public String getAddress() {
    return address;
  }

  public int getPort() {
    return port;
  }

  /**
   * This method check if the object is fill from the json or the propriety inside the json was
   * empty
   *
   * @return return true if the the object is good formed
   */
  public boolean isValid() {
    return type != null && address != null;
  }
}
