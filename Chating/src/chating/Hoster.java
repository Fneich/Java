/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chating;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fneich
 */
public class Hoster implements Runnable {

    @Override
    public void run() {
         try {

            ServerSocket ss = new ServerSocket(1111);
            Socket s = ss.accept();
            ObjectInputStream is = new ObjectInputStream(s.getInputStream());
            while(true){
                // System.out.println("Recever is running...");
           
                Message returnMessage = (Message) is.readObject();
                System.out.println(returnMessage.Message);
                            if(returnMessage.Message.startsWith("/connect")){
                                
            String ip_port=returnMessage.Message.substring(8);
            String port=ip_port.substring(0, 4);
                        System.out.println(port);
                    System.out.println(s.getInetAddress().getHostAddress());
                Data.Messages.put(returnMessage.User, returnMessage);
                Chating.connection = new Connection(s.getInetAddress().getHostAddress(),Integer.getInteger(port));
                
            Socket s1 = new Socket(Chating.connection.IP,1111);
            ObjectOutputStream os = new ObjectOutputStream(s1.getOutputStream());
            os.writeObject("/accept");
 Sender sender = new Sender(Chating.connection.IP,Chating.connection.PortNb);
        Recever recever = new Recever(Chating.connection.PortNb);
        Thread threadSender = new Thread(sender);
        Thread threadRecever = new Thread(recever);
        threadSender.start();
        threadRecever.start();


//System.out.println(returnMessage.toString());
            }
            }
        } catch (IOException ex) {
            Logger.getLogger(Recever.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (ClassNotFoundException ex) {
                Logger.getLogger(Recever.class.getName()).log(Level.SEVERE, null, ex);
            }
        catch(Exception ex){}
    }
    
}
