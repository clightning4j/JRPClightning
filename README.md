# :zap: JRPClightning :zap:
![GitHub All Releases](https://img.shields.io/github/downloads/vincenzopalazzo/JRPClightning/total?color=%23ff5722&style=for-the-badge)
![GitHub top language](https://img.shields.io/github/languages/top/vincenzopalazzo/JRPClightning?color=%23ff5722&style=for-the-badge)

<p align="center">
    <img src="https://i.ibb.co/tKG2Kkq/final-Icon.png" alt="final-Icon" border="0">
</p>

This is a simple wrapper for c-lightning rpc, this project is inspired by this 
[pull request](https://github.com/ElementsProject/lightning/pull/2223) by @renepickhardt.

The wrapper aims to be versatile, the final version should allow the user to execute commands even of custom plugins, allowing them to write the code for the command.

## License
The license is available [here](https://www.apache.org/licenses/LICENSE-2.0). 

## Status of project :construction:
The project support some command, if you want try it don't use on the **MAINET** but use **TESTNET**

## Command Support

At the moment the library support these commands:

- GETINFO

    You can use the RPC wrapper to call the method **getinfo**, the command returns the 
    json wrapper, called CLightningGetInfo.
    
    ```
        CLightningGetInfo infoNode = CLightningRPC.getInstance().getInfo();
        String id = infoNode.getId();
        String color = infoNode.getColor();
        System.out.println("id=" + id);
        System.out.println("color=" + color);
    ```
    
- NEWADDR

    To call the command **newaddr**, you can use this code, the command returns a String.
    This method gets the enum type called AddressType and this enum will add this value
    - AddressType.BECH32
    - AddressType.P2SH_SEGWIT
    
    ```
    String newAddress = CLightningRPC.getInstance().getNewAddress(AddressType.BECH32);
    ```
- INVOICE

    To call the command **invoice** you can call this method and the command returns
    a json wrapper called _CLightningInvoice_.
    
    This is a simple code
    
    ```
        CLightningInvoice invoice = CLightningRPC.getInstance().getInvoice(50000, "This is an java wrapper", "a description");
    ```
    or 
    
    ```
    CLightningInvoice invoice = CLightningRPC.getInstance().getInvoice(100,
                    "this is a java wrapper", "a description", "1w",
                        new String[]{"2MymqReM8EaYCQKzv4rhcvafGGcddZacUtV", "2NDVm22NNuosAXFbC27Scsn1smMh1QEFZUk"}, "",false);  
    ```
  for this convention the optional value has to pass an empty string to the Java command.
  If you don't use an optional parameter you can use the `""` and not the **null object**.
- LISTINVOICE
- DELINVOICE
- CLOSE
- TXDISCARD
- TXSEND
- WITHDRAW
- CONNECT
- FUNDCHANNEL
- LISTFUNDS
- DECODEPAY
- LISTPEERS
- LISTCHANNELS
- LISTSENDPAYS
- PAY

Refer to the javadoc inside the class [CLightningRPC](https://vincenzopalazzo.github.io/JRPClightning/jrpc/clightning/CLightningRPC.html) to see all the options for this method.
Complete javadoc [here](https://vincenzopalazzo.github.io/JRPClightning/).

# Configuration Unix Socket
For the configuration unix socket you can create a file called **clightning-rpc.properties** inside the classpath in your application.

The example file config is [here](https://github.com/vincenzopalazzo/JRPClightning/blob/master/src/main/resources/clightning-rpc.properties).

```
# Copyright 2019 Vincenzo Palazzo vincenzopalazzodev@gmail.com
# <p>
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
# <p>
# http://www.apache.org/licenses/LICENSE-2.0
# <p>
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.


## The CLightning rpc wrapper configurator

RPC_DIR=/media/vincenzo/Maxtor/C-lightning/node/testnet/lightning-rpc
```


## Support
For any additional support you can use this bitcoin address: `3BQ8qbn8hLdmBKEjt1Hj1Z6SiDsnjJurfU`
