/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * FXML Controller class
 *
 * @author malek
 */
public class MailCheckController implements Initializable {

    @FXML
    private JFXTextField mailEnv;
    @FXML
    private Button env;
    @FXML
    private JFXTextField msg;
    @FXML
    private JFXTextField obj;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onclickedEnvoyer(ActionEvent event) throws MessagingException {
        
         String host ="smtp.gmail.com";
                String user ="zaafourimalek120@gmail.com";
                String pass ="123malek123";
                String from ="zaafourimalek120@gmail.com";
                String to =mailEnv.getText();
                String subject =obj.getText();
                String messageText =msg.getText();
                boolean sessionDebug =false ;
                
                Properties props = System.getProperties();
                
                props.put("mail.smtp.starttls.enable", "true");
                props.put("mail.smtp.host", host);
                props.put("mail.smtp.port", "587");
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.starttls.required", "true");
                
                java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
                Session mailSession =Session.getDefaultInstance(props, null);
                mailSession.setDebug(sessionDebug);
                Message msg = new MimeMessage(mailSession);
                msg.setFrom(new InternetAddress(from));
                InternetAddress[] address = {new InternetAddress(to)};
                msg.setRecipients(Message.RecipientType.TO, address);
                msg.setSubject(subject);
                msg.setSentDate(new Date());
                msg.setText(messageText);
                System.out.println(user);
                System.out.println(host);
                System.out.println(pass);
                Transport transport =mailSession.getTransport("smtp");
                transport.connect(host, user , pass);
                transport.sendMessage(msg, msg.getAllRecipients());
                transport.close();
                System.out.println("message envoyé");
        
        
        
    }
    
}
