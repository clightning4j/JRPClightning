package jrpc.clightning.service;

import jrpc.clightning.service.socket.CLightningSocket;
import jrpc.exceptions.ServiceException;
import jrpc.service.socket.UnixDomainSocketRpc;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;


public class CommunicateWithCLightning {

    private UnixDomainSocketRpc socketRpc;

    @Before
    public void init() {
        try {
            socketRpc = new CLightningSocket();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    //@Test
    public void testSendInformationWithSocket() {
        try {
            byte[] buffer = new byte[socketRpc.getReceiveBufferSize()];
            while ((socketRpc.getInputStream().read(buffer) != -1)) {
                String messageReceive = new String(buffer, StandardCharsets.UTF_8);
                if(isCLightningPlugin(messageReceive)){
                    TestCase.assertTrue(true);
                }else{
                    TestCase.fail("No jsonrpc call");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isCLightningPlugin(String message) {
        boolean methodPropriety = message.contains("method");
        boolean paramsPropriety = message.contains("params");
        boolean descriptorPropriety = message.contains("jsonrpc");
        return methodPropriety || paramsPropriety || descriptorPropriety;
    }
}
