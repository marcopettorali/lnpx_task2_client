/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lnpx;

import java.io.*;
import java.net.*;
import lnpx.messages.*;

/**
 *
 * @author rnoce
 */
public abstract class ConnectionToServer {
    
    private static Socket socket;
    private static MessageReceiver receiver;
    private static ObjectOutputStream oos;
    private static DataOutputStream dos;
    
    private static String ipAddress = "localhost";
    private static int port = 7799;
    
    public static void createConnection(){
        
        try{
            socket = new Socket(ipAddress,port);
            oos = new ObjectOutputStream(socket.getOutputStream());
            dos = new DataOutputStream(socket.getOutputStream());
        }catch(IOException io){
            System.out.println(io.getMessage());
        }
        
        receiver = new MessageReceiver(socket);
        receiver.setDaemon(true);
        receiver.start();
          
    }
    
    public static void loginToServer(LoginMsg msg){
        
        String handshake = "LOGIN";
        try{
            //we are sending the string used to communicate to the receiver the beginning of the login phase
            dos.writeUTF(handshake);
            // we are sending to the receiver our credentials   
            oos.writeObject(msg);
        }catch(IOException io){
            System.out.println(io.getMessage());
        }
    }
    
    public static void signInToServer(SignInMsg msg){
        
        String handshake = "SIGN_IN";
        try{
            //we are sending the string used to communicate to the receiver the beginning of the signin phase
            dos.writeUTF(handshake);
            // we are sending to the receiver our credentials   
            oos.writeObject(msg);
        }catch(IOException io){
            System.out.println(io.getMessage());
        }
            
    }
    
    public static void sendSearchRequest(FindMsg msg){
        
        String handshake = "FIND";
        try{
            //we are sending the string used to communicate to the receiver the beginning of searching phase
            dos.writeUTF(handshake);
            //Now we will send the object containing the keyword and the selected filters
            oos.writeObject(msg);
                
        }catch(IOException io){
            System.out.println(io.getMessage());
        }
            
    }
    
    public static void sendTrendingRequest(){
        
        String handshake = "TREND";
        try{
            dos.writeUTF(handshake);
        }catch(IOException io){
            System.out.println(io.getMessage());
        }
        
    }
    
    public static void sendRecommendedRequest(){
        
        String handshake = "RECOMMENDED";
        try{
            dos.writeUTF(handshake);
        }catch(IOException io){
            System.out.println(io.getMessage());
        }
        
    }
    
    public static void sendViewedRequest(ViewMsg view){
        
        String handshake = "VIEW";
        try{
            //we are sending the string used to communicate to the receiver the sending of the viewedMessage keywords
            dos.writeUTF(handshake);
            //sended the viewMessage
            oos.writeObject(view);
        }catch(IOException io){
            System.out.println(io.getMessage());
        }
        
    }
    
    public static void sendSitesRequest(ChangeSitesMsg msg){
        
        String handshake = "CHANGE_SITES";
        try{
            //we are sending the string used to communicate to the receiver the sending of the list of Scraped sites
            dos.writeUTF(handshake);
            //now the clients send the list of selected scraping sites
            oos.writeObject(msg);
        }catch(IOException io){
            System.out.println(io.getMessage());
        }
        
    }
    
    public static void sendScrapeNowRequest(){
        
        String handshake = "SCRAPE_NOW";
        try{
            dos.writeUTF(handshake);
        }catch(IOException io){
            System.out.println(io.getMessage());
        }
        
    }
    
    public static void sendScrapePeriodRequest(ChangePeriodMsg msg){
        
        String handshake = "CHANGE_PERIOD";
        try{
            //we are sending the string used to communicate to the receiver the sending of the new scrape period
            dos.writeUTF(handshake);
            //now the client sends the new scrape period using ChangePeriodMsg
            oos.writeObject(msg);
        }catch(IOException io){
            System.out.println(io.getMessage());
        }
        
    }
    
    public static void sendDeletedUser(String username){
        
        String handshake = "DELETE_USER";
        try{
            //we are sending the string used to communicate to the server the delection of a user
            dos.writeUTF(handshake);
            //Now we are sending to the server the onject which contains the users that has to be deleted
            DeleteUserMsg d = new DeleteUserMsg(username);
            oos.writeObject(d);
        }catch(IOException io){
            System.out.println(io.getMessage());
        }
        
    }
    
    public static void sendUserListRequest(){
        
         String handshake = "CLIENTS";
         try{
             //we are sending the string used to communicate to the receiver the request of users list
            dos.writeUTF(handshake);
         }catch(IOException io){
            System.out.println(io.getMessage());
        }
    }
    
    public static void closeConnection(){
        
        try{    
            socket.close();
            oos.close();
            
           }catch(IOException io){
            System.out.println(io.getMessage());
        }
        receiver.endReception();
    }
}
