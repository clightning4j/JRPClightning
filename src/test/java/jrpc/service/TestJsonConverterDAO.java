/**
 * Copyright 2019 Vincenzo Palazzo vincenzopalazzodev@gmail.com
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
package jrpc.service;

import com.google.gson.reflect.TypeToken;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;
import jrpc.clightning.plugins.rpcmethods.manifest.types.Notification;
import jrpc.exceptions.ServiceException;
import jrpc.mock.RequestLightningRPC;
import jrpc.service.converters.IConverter;
import jrpc.service.converters.JsonConverter;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** @author https://github.com/vincenzopalazzo */
public class TestJsonConverterDAO {

  private static final Logger LOGGER = LoggerFactory.getLogger(TestJsonConverterDAO.class);

  private IConverter genericDAO;

  private RequestLightningRPC requestLightningRPC = new RequestLightningRPC();

  @Before
  public void setupTests() {
    genericDAO = new JsonConverter();
  }

  @Test
  public void testOneToJson() {
    requestLightningRPC.setMethod("getinfo");
    String objectDecode = genericDAO.serialization(requestLightningRPC);
    LOGGER.debug("The decode from json result is\n" + objectDecode);
    TestCase.assertFalse(objectDecode.isEmpty());
  }

  @Test
  public void testOneFromJson() throws FileNotFoundException, ServiceException {
    InputStream inputStream =
        new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/object.json");
    RequestLightningRPC req =
        (RequestLightningRPC) genericDAO.deserialization(inputStream, RequestLightningRPC.class);
    TestCase.assertEquals("getinfo", req.getMethod());
    TestCase.assertEquals("qwerty", req.getArgument());
  }

  @Test
  public void testSerializeHasSet() throws ServiceException {
    Set<Notification> notifications = new HashSet<>();
    notifications.add(new Notification("testOne"));
    notifications.add(new Notification("testTwo"));
    notifications.add(new Notification("testThree"));

    String decoding = genericDAO.serialization(notifications);
    int count = 0;
    for (char c : decoding.toCharArray()) {
      if (c == '[' || c == ']') {
        count++;
      }
    }
    TestCase.assertEquals(2, count);
  }

  @Test
  public void testDecodingHasSet() throws ServiceException {
    String decoding = "[]";
    Set<Notification> notifications =
        (Set<Notification>)
            genericDAO.deserialization(decoding, new TypeToken<Set<Notification>>() {}.getType());
    TestCase.assertEquals(0, notifications.size());
  }
}
