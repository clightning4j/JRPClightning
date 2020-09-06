# :zap: JRPClightning :zap:
![GitHub All Releases](https://img.shields.io/github/downloads/vincenzopalazzo/JRPClightning/total?color=%23ff5722&style=for-the-badge)
![GitHub top language](https://img.shields.io/github/languages/top/vincenzopalazzo/JRPClightning?color=%23ff5722&style=for-the-badge)

<p align="center">
    <img src="https://i.ibb.co/tKG2Kkq/final-Icon.png" alt="final-Icon" border="0">
</p>

This is a wrapper for c-lightning rpc, this project is inspired by this 
[pull request](https://github.com/ElementsProject/lightning/pull/2223) by @renepickhardt.

The wrapper aims to be versatile, the final version should allow the user to execute commands even of custom plugins, allowing them to write the code for the command.

## Status of project :construction:
The project support some command, if you want try it don't use on the **MAINET** but use **TESTNET**

## Command Support

At the moment the library doesn't support all command avaible on clightning, a list of command is described inside [the javadoc](https://vincenzopalazzo.github.io/JRPClightning/)

- You can use the RPC wrapper to call the method **getinfo**, the command returns the 
json wrapper, called CLightningGetInfo.
    
    ```java
        CLightningGetInfo infoNode = CLightningRPC.getInstance().getInfo();
        String id = infoNode.getId();
        String color = infoNode.getColor();
        System.out.println("id=" + id);
        System.out.println("color=" + color);
    ```

- You can create the personal wrapper to support new commands (i.e: personal plugins or command not supported yet by library)
   
  **Create the command wrapper**
  
  ```java
  public class PersonalDelPayRPCCommand extends AbstractRPCCommand<CLightningListPays> {
  
      private static final String COMMAND_NAME = "delpay";
  
      public PersonalDelPayRPCCommand() {
          super(COMMAND_NAME);
      }
  
      @Override
      protected Type toTypeFromClass() {
          return new TypeToken<RPCResponseWrapper<CLightningListPays>>(){}.getType();
      }
  }
  ```
  
  **Create the personal Json wrapper** 
  - In this cases is used a library wrapper, you can see all wrapper available in the library [here](https://vincenzopalazzo.github.io/JRPClightning/)
  
  **Register the command and run it**
  
  ```java
  PersonalRPCCommand paysCommand = new PersonalRPCCommand();
  CLightningRPC.registerCommand(CustomCommand.DELPAY, paysCommand);
  CLightningListPay result = CLightningRPC.runRegisterCommand(CustomCommand.DELPAY, payload);
  ```

Complete javadoc [here](https://vincenzopalazzo.github.io/JRPClightning/).

# Configuration Unix Socket
For the configuration unix socket you can create a file called **clightning-rpc.properties** inside the classpath in your application.

The example file config is [here](https://github.com/vincenzopalazzo/JRPClightning/blob/master/src/main/resources/clightning-rpc.properties).

```
## The CLightning rpc wrapper configurator

RPC_DIR=/media/vincenzo/Maxtor/C-lightning/node/testnet/lightning-rpc
```

# Plugin support

The library support now the plugins and you can subscribe the plugin to notify with the annotation.

### Example

### Plugin example 

```java
    @RPCMethod(
            name = "annotation_hello",
            description = "Annotation plugin"
    )
    public void hello(CLightningPlugin plugin, CLightningJsonObject request, CLightningJsonObject response) {
        log(CLightningLevelLog.WARNING, request.toString());
        response.add("type", "random");
    }
```

### Notification example 

```java
    @Subscription(notification = "invoice_creation")
    public void doInvoiceCreation(CLightningJsonObject data){
        log(CLightningLevelLog.WARNING, "Notification invoice_creation received inside the plugin lightning rest");
        CLightningLogger.getInstance().debug( this.getClass(), "Data received by notification are \n" + data.toString());
    }
```

### Hook example

```java
    @Hook(hook = "rpc_command")
    public void logAllRPCCommand(CLightningPlugin plugin, CLightningJsonObject request, CLightningJsonObject response) {
        log(CLightningLevelLog.WARNING, request.toString());
        response.add("result", "continue");
    }
```

## Support
If you like the library and want support it, please considerer to donate with the following system

- [liberapay.com/vincenzopalazzo](https://liberapay.com/vincenzopalazzo)
- [3BQ8qbn8hLdmBKEjt1Hj1Z6SiDsnjJurfU](bitcoin:3BQ8qbn8hLdmBKEjt1Hj1Z6SiDsnjJurfU)
- [Github support](https://github.com/sponsors/vincenzopalazzo)

## License

<div align="center">
  <img src="https://opensource.org/files/osi_keyhole_300X300_90ppi_0.png" width="150" height="150"/>
</div>

 This is a wrapper for c-lightning RPC interface.
 Copyright (C) 2020 Vincenzo Palazzo vincenzopalazzodev@gmail.com
 
 This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation; either version 2 of the License.
 
 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.
 
 You should have received a copy of the GNU General Public License along
 with this program; if not, write to the Free Software Foundation, Inc.,
 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 