/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chating;

import static chating.Chating.*;
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


            while(true){
                // System.out.println("Recever is running...");
           ServerSocket ss = new ServerSocket(1111);
            Socket s = ss.accept();
            ObjectInputStream is = new ObjectInputStream(s.getInputStream());
                Message returnMessage = (Message) is.readObject();
                System.out.println(returnMessage.Message);
                            if(returnMessage.Message.startsWith("/connect:")){
                                
            String ip_port=returnMessage.Message.substring(9);
           String Ip =s.getInetAddress().getHostAddress();
           int Port =Integer.parseInt(ip_port.split(";")[2]);
           String Name=ip_port.split(";")[0];

            Chating.connections.put(Ip,new Connection(Name,Ip,Port));              
            Socket s1 = new Socket(Ip,1112);
            ObjectOutputStream os = new ObjectOutputStream(s1.getOutputStream());
            Message message=new Message(chating.Chating.Name,"/accept");
            Connection connection = new Connection(Name,Ip,Port);
            Chating.connections.put(Ip,connection);
            os.writeObject(message);
            s.close();
            ss.close();



//System.out.println(returnMessage.toString());
            }
            }
        } catch (IOException ex) {
            Logger.getLogger(Recever.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (ClassNotFoundException ex) {
                Logger.getLogger(Recever.class.getName()).log(Level.SEVERE, null, ex);
            }
        catch(Exception ex){System.out.println("Error");}
    }
    
}
