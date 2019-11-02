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
package jrpc.clightning.model.types;

/**
 * @author https://github.com/vincenzopalazzo
 */
public class BitcoinOutput {
    //TODO FIXME when run the command txprepare I have this error
    // The output format must be {destination: amount}
    private String address;
    private String amount;

    public BitcoinOutput() {}

    public BitcoinOutput(String address, String amount) {
        this.address = address;
        this.amount = amount;
    }

    public String getAddress() {
        return address;
    }

    public String getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        String amountString;
        if(amount.trim().isEmpty()){
            amountString = "all";
        }else{
            amountString = amount;
        }
        return address + "#" + amountString;
    }
}
