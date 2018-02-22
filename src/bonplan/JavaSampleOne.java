/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bonplan;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;



/**
 *
 * @author user
 */
public class JavaSampleOne {
   static String  clientId = "ATyjHqJJ5X8iCFpG1_ZNFAUDR3IA6slnlR5zZLHou_0Q7InjLkUjru4bs0doBUCMIlCZQg1y-Cm8gwNJ";
static String clientSecret = "EINv1LMDA6NxUVyZHFwQZHSvAZPRWLPWDFFRHnAtI3k96BzXlAASqAeOUPX8xOxtv5CIYydAuoSSop0M";


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException {
        Amount amount = new Amount();
amount.setCurrency("USD");
amount.setTotal("1.00");

Transaction transaction = new Transaction();
transaction.setAmount(amount);
List<Transaction> transactions = new ArrayList<Transaction>();
transactions.add(transaction);

Payer payer = new Payer();
payer.setPaymentMethod("paypal");

Payment payment = new Payment();
payment.setIntent("sale");
payment.setPayer(payer);
payment.setTransactions(transactions);

RedirectUrls redirectUrls = new RedirectUrls();
redirectUrls.setCancelUrl("https://example.com/cancel");
redirectUrls.setReturnUrl("https://example.com/return");
payment.setRedirectUrls(redirectUrls);

try {
    APIContext context = new APIContext(clientId, clientSecret, "sandbox");
    Payment createdPayment = payment.create(context);
} catch (PayPalRESTException e) {
    // Handle errors
}

//		System.out.println(ClassLoader.getSystemResource("log4j2.yml")); //Check if file is available in CP
//ClassLoader cl = Thread.currentThread().getContextClassLoader(); //Code as in log4j2 API. Version: 2.8.1
// String [] classes = {"com.fasterxml.jackson.databind.ObjectMapper",
// "com.fasterxml.jackson.databind.JsonNode",
// "com.fasterxml.jackson.core.JsonParser",
// "com.fasterxml.jackson.dataformat.yaml.YAMLFactory"};
//
// for(String className : classes) {
//     cl.loadClass(className);
// }	

	}
    

        
        // TODO code application logic here
    }
    

