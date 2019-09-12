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
package jrpc.wrapper.socket;


import java.util.Map;

public class RPCUnixRequestMethod implements IWrapperSocketCall{

    private int id = 1;
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


    public int getRandomNumber(){
        return (int)(Math.random()*100);
    }
}
