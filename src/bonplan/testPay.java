/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bonplan;

import Entite.Paiement;
import Services.ServicePaiement;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;

/**
 *
 * @author user
 */
public class testPay {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
        Paiement p = new Paiement();
        
        Services.ServicePaiement s = new ServicePaiement();
        System.out.println(s.payer("4242424242424242", 12, 18, "458", 1000, "testpaimenet")); 
        // TODO code application logic here
    }
    
}
