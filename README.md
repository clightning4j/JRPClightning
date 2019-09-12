# JRPClightning

![work in progress](http://s3.amazonaws.com/snd-store/a/26553114/02_02_18_508408464_aab_560x292.jpg)

This is an simple wrapper for c-lightning rpc, this project is inspired by this 
[pull request](https://github.com/ElementsProject/lightning/pull/2223) by @renepickhardt

This  wrapper aims to be versatile, that is the final version should allow to execute commands even of custom plugins, allowing to write the code for the command.

## License
The license is available [here](https://www.apache.org/licenses/LICENSE-2.0) 

## Status project :construction:

## Command Support

For the moment the library support this commands:

- GETINFO

    You can use the RPC wrapper for call method **getinfo**, the command returned the 
    json wrapper, called CLightningGetInfo
    
    ```
        CLightningGetInfo infoNode = CLightningRPC.getInstance().getInfo();
        String id = infoNode.getId();
        String color = infoNode.getColor();
        System.out.println("id=" + id);
        System.out.println("color=" + color);
    ```
    
- NEWADDR

    For call the command **newaddr**, you can use this code, the command return an String
    The method get the enum type called AddressType, and this enum add this value
    - AddressType.BECH32
    - AddressType.P2SH_SEGWIT
    
    ```
    String newAddress = CLightningRPC.getInstance().getNewAddress(AddressType.BECH32);
    ```
- INVOICE
    For call the command **invoice** you can call much method, and the command returned
    a json wrapper called _CLightningInvoice_
    
    This is an simple code
    
    ```
        CLightningInvoice invoice = CLightningRPC.getInstance().getInvoice(50000, "This is an java wrapper", "a description");
    ```
    or 
    
    ```
    CLightningInvoice invoice = CLightningRPC.getInstance().getInvoice(100,
                    "this is a java wrapper", "a description", "1w",
                        new String[]{"2MymqReM8EaYCQKzv4rhcvafGGcddZacUtV", "2NDVm22NNuosAXFbC27Scsn1smMh1QEFZUk"}, "",false);  
    ```
  for convention the optional value do managed to the java command with an empty string.
  if you don't use an optional parameter you can use the `""` and not the **null object**
- LISTINVOICE
- DELINVOICE
- CLOSE
- TXDISCARD
- TXSEND
- WITHDRAW
- CLOSE
- CONNECT
- FUNDCHANNEL
- LISTFUNDS

The javadoc here, inside the class [CLightningRPC](https://vincenzopalazzo.github.io/JRPClightning/jrpc/clightning/CLightningRPC.html) are all method you can be called.
Complete javadoc [here](https://vincenzopalazzo.github.io/JRPClightning/)

# Configuration Unix Socket
For configuration unix socket you can create an file called **clightning-rpc.properties** inside the classpath your application

The example file config is [here](https://github.com/vincenzopalazzo/JRPClightning/blob/master/src/main/resources/clightning-rpc.properties)

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
For support work you can use this address bitcoin: `3BQ8qbn8hLdmBKEjt1Hj1Z6SiDsnjJurfU`