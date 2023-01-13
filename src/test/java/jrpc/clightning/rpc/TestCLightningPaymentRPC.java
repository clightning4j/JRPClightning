package jrpc.clightning.rpc;

import jrpc.clightning.exceptions.CLightningException;
import jrpc.clightning.model.*;
import jrpc.service.CLightningLogger;
import jrpc.util.MocksUtils;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

public class TestCLightningPaymentRPC extends AbstractTestRPC {

  @Before
  public void cleanAll() {
    MocksUtils.fundCLightningNodeTwo();
  }

  @Test
  public void testCommandGetInvoiceOne() {
    String label = "Hello this is an test " + Math.random();
    CLightningInvoice invoice = rpc.invoice("1000", label, "description");
    CLightningLogger.getInstance().debug(TAG, "invoice: " + invoice.getBolt11());
    TestCase.assertNotNull(invoice.getBolt11());
    rpc.delInvoice(label, "unpaid");
  }

  @Test
  public void testCommandGetInvoiceTwo() {
    String label = "This is an test " + Math.random();
    CLightningInvoice invoice = rpc.invoice("1000", label, "description");
    CLightningLogger.getInstance().debug(TAG, "invoice: " + invoice.getBolt11());
    TestCase.assertNotNull(invoice.getBolt11());
    rpc.delInvoice(label, "unpaid");
  }

  @Test
  public void testCommandGetInvoiceThree() {
    String label = "This is an test " + Math.random();
    CLightningInvoice invoice =
        rpc.invoice(
            "100",
            label,
            "description",
            null,
            new String[] {
              "2MymqReM8EaYCQKzv4rhcvafGGcddZacUtV", "2NDVm22NNuosAXFbC27Scsn1smMh1QEFZUk"
            },
            "",
            false);
    CLightningLogger.getInstance().debug(TAG, "invoice: " + invoice.getBolt11());
    TestCase.assertNotNull(invoice.getBolt11());
    rpc.delInvoice(label, "unpaid");
  }

  @Test
  public void testCommandGetInvoiceFour() {
    String label = "Hello this is an test " + Math.random();
    CLightningInvoice invoice = rpc.invoice("1000", label, "description");
    TestCase.assertNotNull(invoice.getBolt11());
    TestCase.assertNotNull(invoice.getExpiresAt());
    TestCase.assertNotNull(invoice.getPaymentHash());
    rpc.delInvoice(label, "unpaid");
  }

  @Test
  public void testCommandGetListInvoiceOne() {
    CLightningListInvoices listInvoices = rpc.listInvoices("");
    TestCase.assertNotNull(listInvoices.getListInvoice());
    TestCase.assertTrue(listInvoices.getListInvoice().isEmpty());
  }

  @Test
  public void testCommandGetListInvoiceTwo() {
    String label = "This invice was created for test command listIncovoice";
    CLightningInvoice invoice = rpc.invoice("1000", label, "description");
    TestCase.assertNotNull(invoice);
    CLightningListInvoices listInvoices = rpc.listInvoices("");
    TestCase.assertEquals(1, listInvoices.getListInvoice().size());
    rpc.delInvoice(label, "unpaid");
  }

  @Test
  public void testCommandListFunds() {
    CLightningListFunds listFounds = rpc.listFunds();
    TestCase.assertNotNull(listFounds);
  }

  @Test
  public void testCommandPayOne() {
    try {
      String bolt11 =
          "lntb120p1pwc8ep9pp5t330eyge3p2eukenek7wkzspny8jzt07csxjx3a8hyczzqrk63yqdqdwdjxzumywdskgxqyjw5qcqp28yycdhumpzy8avt4g4crawc7hc5xhdq04tnlqnh458ywvpy0wxp96rhws063g6jr68x3cldckf3s56ynj2q8y2fmnms8khhpvah8s6sqwh4m3e";
      rpc.pay(bolt11);
      TestCase.fail();
    } catch (CLightningException ex) {
      TestCase.assertTrue(ex.getMessage().contains("Error inside command with error code:"));
    }
  }

  @Test(expected = RuntimeException.class)
  public void testCommandPayOTwo() {
    String bolt11 = "";
    CLightningPayResult pay = rpc.pay(bolt11);
    TestCase.assertNotNull(pay);
  }

  @Test
  public void testCommandListSendPaysOTwo() {
    CLightningListSendPays pays = rpc.listSendPays();
    TestCase.assertNotNull(pays);
  }

  @Test
  public void testCommandDecodePayOne() {
    String bolt11 =
        "lntb133n1pw6xee8pp5lejvtfa9vutxh4yxeavznv9fzk30ghcn32pmee8jrgtgzjqp7ytsdr4235x"
            + "jueqd9h8vmmfvdjjq6tnyp3hyetpw3jkggr0dek8jgrxdaezqar9wd6xjmn8yp3k7mtdv9hxggry"
            + "v43k7er92pshjgrfdcs8g6r9ypmhyctswpjhyxqyjw5qcqp2f9qswccpctzw2ya82w83rqkkr0d8rvayctcl7"
            + "5dh6z4q85uqp0jseflzjfap5fajwa35m6ughfrq69l96ur37jgrl63s655es88htygqughf82";
    CLightningDecodePay decodePay = rpc.decodePay(bolt11);
    TestCase.assertNotNull(decodePay);
    TestCase.assertEquals("13300", decodePay.getAmountMSat());
  }

  @Test(expected = CLightningException.class)
  public void testCommandDecodePayTwo() {
    String bolt11 = "";
    CLightningDecodePay decodePay = rpc.decodePay(bolt11);
    TestCase.assertNotNull(decodePay);
    TestCase.assertEquals("13300msat", decodePay.getAmountMSat());
  }

  @Test
  public void testListPaysOne() {
    CLightningListPays listPays = rpc.listPays();
    TestCase.assertNotNull(listPays.getPays());
  }

  @Test
  public void testListSendPaymentsOne() {
    CLightningListSendPays payments = rpc.listSendPays();
    TestCase.assertNotNull(payments);
  }
}
