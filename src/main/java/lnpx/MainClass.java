
package lnpx;

import java.io.*;
import java.net.*;
import java.util.Date;
import java.util.Map;
import javafx.application.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.*;
import lnpx.messages.*;


public class MainClass extends Application {

    
    
    private static Stage MainStage;
    private static Stage LoginStage;
    private static Stage SignUpStage;
    private static Stage AdminStage;
    
    
    private static void loadLoginStage(){
        
        MainStage.close();
        SignUpStage.close();
        AdminStage.close();
        LoginStage.setTitle("Login");
        LoginStage.setResizable(false);
        LoginStage.setScene(new Scene(new Group(new LoginPaneGUI())));
        LoginStage.sizeToScene();
        LoginStage.show();
        
    }
    
    private static void loadMainStage(){
        
        SignUpStage.close();
        AdminStage.close();
        LoginStage.close();
        MainStage = new Stage();
        MainStage.setTitle("Task2");
        MainStage.setResizable(false);
        MainStage.setScene(new Scene(new Group(new UserPaneGUI())));
        MainStage.sizeToScene();
        MainStage.show();
        
        
        
    }
    
    private static void loadSignupStage(){
        
        LoginStage.close();
        AdminStage.close();
        MainStage.close();
        
        SignUpStage = new Stage();
        SignUpStage.setTitle("Sign up");
        SignUpStage.setResizable(false);
        SignUpStage.setScene(new Scene(new Group(new SignInPaneGUI())));
        SignUpStage.sizeToScene();
        SignUpStage.show();
        
        
    }
    
     public static void loadSignupForm() {
        loadSignupStage();
    }
     
    public static void backToLoginForm(){
        loadLoginStage();
    }
    
    public static int signIn(String user, String pwd, String email, String fn, String ln, Date d){
        
        String handshake = "SIGN_IN";
        try(
            Socket send = new Socket("localhost",7777);
            ServerSocket ss = new ServerSocket(7778);    
            ObjectOutputStream oos = new ObjectOutputStream(send.getOutputStream());
                
              ){
               //we are sending the string used to communicate to the receiver the beginning of the signin phase
               oos.writeObject(handshake);
               // we are sending to the receiver our credentials   
               oos.writeObject(new SignInMsg(user,pwd,email,fn,ln,d));
               //now we wait the response message of the receiver
               Socket rec=ss.accept();
               
               ObjectInputStream ois = new ObjectInputStream(rec.getInputStream());
               String header = (String)ois.readObject();
               //here we are receiving trough the socket the signin response message
               SignInResponseMsg risp = (SignInResponseMsg)ois.readObject();
               
               rec.close();
               ois.close();
               
               if(risp.getCode() == -1){
                   return -1;
               }
               else{
                   return 0;
               }
        }catch(IOException io){
            System.out.println("There was an error during the communication with the server");
            return -2;
        }catch(ClassNotFoundException ce){
            System.out.println("There was an error during the reading/writing of the object");
            return -2;
        }
       
    }
    
    public static ArticlesResponseMsg searchContent(String keyword, Map<String,String> filters){
         
         String handshake = "FIND";
         ArticlesResponseMsg ret= null;
         try(
            Socket send = new Socket("localhost",7777);
            ServerSocket ss = new ServerSocket(7778);    
            ObjectOutputStream oos = new ObjectOutputStream(send.getOutputStream());
                
              ){
               //we are sending the string used to communicate to the receiver the beginning of searching phase
               oos.writeObject(handshake);
               //Now we will send the object containing the keyword and the selected filters
               FindMsg search = new FindMsg(keyword,filters);
               oos.writeObject(search);
               
               Socket rec=ss.accept();
               //Represents the reading of the header that belongs to the search_response_message
               ObjectInputStream ois = new ObjectInputStream(rec.getInputStream());
               String header = (String)ois.readObject();
               ret = (ArticlesResponseMsg)ois.readObject();
               
               rec.close();
               ois.close();
               return ret;
         }catch(IOException io){
            System.out.println("There was an error during the communication with the server");
        }catch(ClassNotFoundException ce){
            System.out.println("There was an error during the reading/writing of the object");
        }
         
         return ret;
                    
    }
    
    public static TrendResponseMsg requestTrendingKeywords(){
        
         String handshake = "TREND";
         TrendResponseMsg ret= null;
         try(
            Socket send = new Socket("localhost",7777);
            ServerSocket ss = new ServerSocket(7778);    
            ObjectOutputStream oos = new ObjectOutputStream(send.getOutputStream());
                
              ){
               //we are sending the string used to communicate to the receiver the request of the trending keywords
               oos.writeObject(handshake);
               //now the client waits for the response
               Socket rec=ss.accept();
               //Represents the reading of the header that belongs to the trend_response_message
               ObjectInputStream ois = new ObjectInputStream(rec.getInputStream());
               String header = (String)ois.readObject();
               ret=(TrendResponseMsg)ois.readObject();
               
               rec.close();
               ois.close();
               return ret;
         }catch(IOException io){
            System.out.println("There was an error during the communication with the server");
        }catch(ClassNotFoundException ce){
            System.out.println("There was an error during the reading/writing of the object");
        }
        
       return ret;  
               
    }
    
    public static ArticlesResponseMsg requestRecommendedArticles(){
         
         String handshake = "RECOMMENDED";
         ArticlesResponseMsg ret= null;
         try(
            Socket send = new Socket("localhost",7777);
            ServerSocket ss = new ServerSocket(7778);    
            ObjectOutputStream oos = new ObjectOutputStream(send.getOutputStream());
                
              ){
               //we are sending the string used to communicate to the receiver the request of the trending keywords
               oos.writeObject(handshake);
               //now the client waits for the response
               Socket rec=ss.accept();
               //Represents the reading of the header that belongs to the recommended_response_message
               ObjectInputStream ois = new ObjectInputStream(rec.getInputStream());
               String header = (String)ois.readObject();
               //the clients wait for the recommended articles
               
               ret = (ArticlesResponseMsg)ois.readObject();
               
               rec.close();
               ois.close();
               
               return ret;
         }catch(IOException io){
            System.out.println("There was an error during the communication with the server");
        }catch(ClassNotFoundException ce){
            System.out.println("There was an error during the reading/writing of the object");
        }
         
       return ret;  
        
        
    }
    
    public static void sendViewedArticle(String link,Map<String,String> filters){
        
         String handshake = "VIEW";
         try(
            Socket send = new Socket("localhost",7777);
            ServerSocket ss = new ServerSocket(7778);    
            ObjectOutputStream oos = new ObjectOutputStream(send.getOutputStream());
                
              ){
               //we are sending the string used to communicate to the receiver the sending of the viewedMessage keywords
               oos.writeObject(handshake);
               ViewMsg v = new ViewMsg(link,filters);
               //sended the viewMessage
               oos.writeObject(v);
               //now the client waits for the ACK
               Socket rec=ss.accept();
               ObjectInputStream ois = new ObjectInputStream(rec.getInputStream());
               String header = (String) ois.readObject();
               ACKMsg ack = (ACKMsg)ois.readObject();
               
               rec.close();
               ois.close();
               
         }catch(IOException io){
            System.out.println("There was an error during the communication with the server");
        }catch(ClassNotFoundException ce){
            System.out.println("There was an error during the reading/writing of the object");
        }
               
               
               
        
    }
    
    
    public static void sendSites(Map<String,Boolean> sites){
        
         String handshake = "CHANGE_SITES";
         try(
            Socket send = new Socket("localhost",7777);
            ServerSocket ss = new ServerSocket(7778);    
            ObjectOutputStream oos = new ObjectOutputStream(send.getOutputStream());
                
              ){
               //we are sending the string used to communicate to the receiver the sending of the list of Scraped sites
               oos.writeObject(handshake);
               //now the clients send the list of selected scraping sites
               ChangeSitesMsg csm = new ChangeSitesMsg(sites);
               oos.writeObject(csm);
               //Now the client wait for the response (ACK)
               Socket rec = ss.accept();
               ObjectInputStream ois = new ObjectInputStream(rec.getInputStream());
               //the client reads the ack message
               String header = (String) ois.readObject();
               ACKMsg ack = (ACKMsg)ois.readObject();
               
               rec.close();
               ois.close();
         }catch(IOException io){
            System.out.println("There was an error during the communication with the server");
        }catch(ClassNotFoundException ce){
            System.out.println("There was an error during the reading/writing of the object");
        }
               
             
    }
    
    public static void scrapeNow(){
        
         String handshake = "SCRAPE_NOW";
         try(
            Socket send = new Socket("localhost",7777);
            ServerSocket ss = new ServerSocket(7778);    
            ObjectOutputStream oos = new ObjectOutputStream(send.getOutputStream());
                
              ){
               //we are sending the string used to communicate to the receiver the SCRAPE NOW request
               oos.writeObject(handshake);
               //now the client waits for the response
               Socket rec = ss.accept();
               
               ObjectInputStream ois = new ObjectInputStream(rec.getInputStream());
               String header = (String) ois.readObject();
               ACKMsg ack = (ACKMsg) ois.readObject();
               
               rec.close();
               ois.close();
         }catch(IOException io){
            System.out.println("There was an error during the communication with the server");
        }catch(ClassNotFoundException ce){
            System.out.println("There was an error during the reading/writing of the object");
        }
        
        
        
    }
    
    public static void sendScrapePeriod(double period){
        
         String handshake = "CHANGE_PERIOD";
         try(
            Socket send = new Socket("localhost",7777);
            ServerSocket ss = new ServerSocket(7778);    
            ObjectOutputStream oos = new ObjectOutputStream(send.getOutputStream());
                
              ){
               //we are sending the string used to communicate to the receiver the sending of the new scrape period
               oos.writeObject(handshake);
               //now the client sends the new scrape period using ChangePeriodMsg
               
               ChangePeriodMsg cpm = new ChangePeriodMsg(period);
               oos.writeObject(cpm);
               
               //now the client waits for the response
               Socket rec = ss.accept();
               ObjectInputStream ois = new ObjectInputStream(rec.getInputStream());
               String header = (String) ois.readObject();
               ACKMsg ack = (ACKMsg) ois.readObject();
               
               rec.close();
               ois.close();
               
         }catch(IOException io){
            System.out.println("There was an error during the communication with the server");
        }catch(ClassNotFoundException ce){
            System.out.println("There was an error during the reading/writing of the object");
        }  
        
    }
    
    public static ClientsResponseMsg requestUsersList(){
        
        String handshake = "CLIENTS";
        ClientsResponseMsg ret = null;
         try(
            Socket send = new Socket("localhost",7777);
            ServerSocket ss = new ServerSocket(7778);    
            ObjectOutputStream oos = new ObjectOutputStream(send.getOutputStream());
                
              ){
               //we are sending the string used to communicate to the receiver the request of users list
               oos.writeObject(handshake);
               
               //now the client waits for the response
               Socket rec = ss.accept();
               ObjectInputStream ois = new ObjectInputStream(rec.getInputStream());
               String header = (String) ois.readObject();
               ret = (ClientsResponseMsg)ois.readObject();
               
               rec.close();
               ois.close();
               
               return ret;
               
         }catch(IOException io){
            System.out.println("There was an error during the communication with the server");
        }catch(ClassNotFoundException ce){
            System.out.println("There was an error during the reading/writing of the object");
        }  
        
       return null;
         
    }
    
    
    private static void loadAdminStage(){
        
        LoginStage.close();
        SignUpStage.close();
        MainStage.close();
        
        AdminStage = new Stage();
        AdminStage.setTitle("Admin control panel");
        AdminStage.setResizable(false);
        AdminStage.setScene(new Scene(new Group(new AdminPaneGUI())));
        AdminStage.sizeToScene();
        AdminStage.show();
        
    }
    
   
    
    public static int login(String username, String password){
        
        String handshake = "LOGIN";
        try(
            Socket send = new Socket("localhost",7777);
            ServerSocket ss = new ServerSocket(7778);    
            ObjectOutputStream oos = new ObjectOutputStream(send.getOutputStream());
                
              ){
               //we are sending the string used to communicate to the receiver the beginning of the login phase
               oos.writeObject(handshake);
               // we are sending to the receiver our credentials   
               oos.writeObject(new LoginMsg(username,password));
               //now we wait the response message of the receiver
               Socket rec=ss.accept();
               //Represents the reading of the header that belongs to the login_response_message
               ObjectInputStream ois = new ObjectInputStream(rec.getInputStream());
               String header = (String)ois.readObject();
               //here we are receiving trough the socket the login response message
               LoginResponseMsg risp = (LoginResponseMsg)ois.readObject();
               
               rec.close();
               ois.close();
               
               int code=risp.getCode();
               
               if(code == 1){
                   loadAdminStage();
                   return code;
                   
               }
               if(code == 0){
                   loadMainStage();
                   return code;
               }
               
              
               return code;
            
        }catch(IOException io){
            System.out.println("There was an error during the communication with the server");
        }catch(ClassNotFoundException ce){
            System.out.println("There was an error during the reading/writing of the object");
        }
        
       return 5; 
    }
    
    
    
    
    @Override
    public void start(Stage primaryStage) {
        
        MainStage = new Stage();
        AdminStage = new Stage();
        SignUpStage = new Stage();
        LoginStage = new Stage();
        loadLoginStage();
        
        
    }
    
    
}
