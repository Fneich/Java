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
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fneich
 */
public class Connecter implements Runnable{

    @Override
    public void run() {
             try {

            
                Scanner sc = new Scanner(System.in);
            while(true){
                //System.out.println("Sender is running...");
            Message message=new Message(chating.Chating.Name,sc.nextLine());
            if(message.Message.startsWith("/connect")){
            String ip_port=message.Message.substring(8);
            String port=ip_port.substring(0, 4);
            String ip=ip_port.substring(8);
            Socket s = new Socket(ip,1111);
            ObjectOutputStream os = new ObjectOutputStream(s.getOutputStream());
            os.writeObject(message);
             ServerSocket ss = new ServerSocket(s.getPort());
            Socket s1 = ss.accept();
            ObjectInputStream is = new ObjectInputStream(s.getInputStream());
                Message returnMessage = (Message) is.readObject();
                if(returnMessage.Message.startsWith("/accept")){
                Chating.connection = new Connection(s1.getInetAddress().getHostAddress(),s.getPort());
                        Sender sender = new Sender(Chating.connection.IP,Chating.connection.PortNb);
        Recever recever = new Recever(Chating.connection.PortNb);
        Thread threadSender = new Thread(sender);
        Thread threadRecever = new Thread(recever);
        threadSender.start();
        threadRecever.start();
                }
                
                //System.out.println(returnMessage.toString());
            
            }

            }
        } catch (IOException ex) {
            Logger.getLogger(Recever.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(Exception ex){}
    }
    
}