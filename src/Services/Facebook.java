/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entite.Publicite;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.FacebookType;
import com.restfb.types.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 *
 * @author user
 */
public class Facebook {
     public void partager(Publicite p){
        
          String domain="https://www.facebook.com/";
          //domain="https://google.fr/";
         String appId="2052658948344519";
         String appSecret="53d959866e6ab42ccae92b3c983a0ac8";
         String authURL="https://graph.facebook.com/oauth/authorize?type=user_agent&client_id="+appId+"&redirect_uri="+domain
                 +"&scope=user_about_me,ads_management,publish_actions";
         
         System.setProperty("webdriver.chrome.driver", "api/chromedriver_win32/chromedriver.exe");
         WebDriver driver= new ChromeDriver();
         driver.get(authURL);
         String accessToken="" ;
         
         boolean ok=true;
         while(ok)
         {
             if ( (! driver.getCurrentUrl().contains("facebook.com")) && (driver.getCurrentUrl()!=authURL) )
             {
                 String url =driver.getCurrentUrl();
                 accessToken =url.replaceAll(".*#access_token=(.+)&.*", "$1");
                 System.out.println(accessToken);
                
                 ok=false;
              }
             
         }
          //driver.quit();
         System.out.println("act:"+accessToken);
         driver.quit();
         FacebookClient fbClient = new DefaultFacebookClient(accessToken);
              User me = fbClient.fetchObject("me", User.class);
              System.out.println(me.getName());
            
              FacebookType publishMessageResponse =
            fbClient.publish("me/feed", FacebookType.class,com.restfb.Parameter.with("message", "BonPlan"+""));
            System.out.println("Published message ID: " + publishMessageResponse.getId());
    
            
    }
    
    
    
}
