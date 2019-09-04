/**
 * Copyright 2019 https://github.com/vincenzopalazzo
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
package jrpc.service;

import jrpc.exceptions.ServiceException;
import jrpc.mock.RequestLightningRPC;
import jrpc.service.converters.IConverter;
import jrpc.service.converters.JsonConverter;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @author https://github.com/vincenzopalazzo
 */
public class TestJsonConverterDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestJsonConverterDAO.class);

    private IConverter genericDAO;

    private RequestLightningRPC requestLightningRPC = new RequestLightningRPC();

    @Before
    public void setupTests(){
        genericDAO = new JsonConverter();
    }

    @Test
    public void testOneToJson(){
        requestLightningRPC.setMethod("getinfo");
        String objectDecode = genericDAO.serialization(requestLightningRPC);
        LOGGER.debug("The decode from json result is\n" + objectDecode);
        TestCase.assertFalse(objectDecode.isEmpty());
    }

    @Test
    public void testOnefromJson() throws FileNotFoundException, ServiceException {
        InputStream inputStream = new FileInputStream(new File(System.getProperty("user.dir") + "/src/test/resources/object.json"));
        RequestLightningRPC req = (RequestLightningRPC) genericDAO.deserialization(inputStream, RequestLightningRPC.class);
        TestCase.assertEquals("getinfo", req.getMethod());
        TestCase.assertEquals("qwerty", req.getArgument());
    }

}
