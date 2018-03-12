/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chating;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fneich
 */
public class RequestRepostry implements Runnable{
        @Override
    public void run() {

            Scanner sc = new Scanner(System.in);
            while(true){
            String request = sc.nextLine();
            if(request.startsWith("/connect:")){       
                try {
                    String IP =request.substring(9).split(";")[0];
                    if(!Chating.connections.containsKey(IP)){
                    String Port =request.split(";")[1];
                    
                    Connection connection=Connecter.ConnectTo(Chating.Name,IP, Port);
                    System.out.println("count:"+connection);
                    if(connection!=null){Chating.connections.put(connection.IP, connection);
                    connection.Connect();
                    }
                    }                   
                } catch (IOException ex) {
                    Logger.getLogger(RequestRepostry.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(RequestRepostry.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(request.startsWith("/close:")){
            try {
                    String IP =request.substring(7);
                    if(Chating.connections.containsKey(IP)){
                    Connecter.CloseTo(Chating.connections.get(IP));
                    }                   
                } catch (IOException ex) {
                    Logger.getLogger(RequestRepostry.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(RequestRepostry.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if(request.startsWith("/send:")){
            try {
                    String IP =request.substring(6).split(";")[0];
                    String message = request.substring(6).split(";")[1];
                    if(Chating.connections.containsKey(IP)){
                    Connecter.SendTo(Chating.connections.get(IP),message);
                    }                   
                } catch (IOException ex) {
                    Logger.getLogger(RequestRepostry.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(RequestRepostry.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            }
        
    }
}
