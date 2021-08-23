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

import com.google.gson.annotations.SerializedName;
import java.util.Map;
import java.util.Random;

public class RPCUnixRequestMethod implements IWrapperSocketCall {

  private int id;
  private String method;

  @SerializedName("jsonrpc")
  private String version;

  private Map<String, Object> params;
  private RandomUtils randomUtils;

  public RPCUnixRequestMethod(String method, Map<String, Object> params) {
    randomUtils = new RandomUtils();
    this.id = getRandomNumber();
    this.method = method;
    this.params = params;
  }

  public int getId() {
    return id;
  }

  @Override
  public String getVersion() {
    return version;
  }

  public String getMethod() {
    return method;
  }

  public Map<String, Object> getParams() {
    return params;
  }

  public int getRandomNumber() {
    // TODO 0, 20000 is the correct number?
    return randomUtils.nextInt(0, 20000);
  }

  private static class RandomUtils extends Random {

    /**
     * @param min generated value. Can't be > then max
     * @param max generated value
     * @return values in closed range [min, max].
     */
    public int nextInt(int min, int max) {
      if (min > max)
        throw new IllegalArgumentException(
            "min can't be > then max; values:[" + min + ", " + max + "]");
      if (min == max) {
        return max;
      }
      return nextInt(max - min + 1) + min;
    }
  }
}
