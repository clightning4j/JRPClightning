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
package jrpc.clightning.model;

import java.util.ArrayList;
import java.util.List;
import jrpc.clightning.model.types.CLightningInfoChannel;
import jrpc.clightning.model.types.CLightningOutput;

/** @author https://github.com/vincenzopalazzo */
public class CLightningListFunds {

  private List<CLightningOutput> outputs = new ArrayList<>();
  private List<CLightningInfoChannel> channels = new ArrayList<>();

  public List<CLightningOutput> getOutputs() {
    return outputs;
  }

  public void setOutputs(List<CLightningOutput> outputs) {
    this.outputs = outputs;
  }

  public List<CLightningInfoChannel> getChannels() {
    return channels;
  }

  public void setChannels(List<CLightningInfoChannel> channels) {
    this.channels = channels;
  }
}