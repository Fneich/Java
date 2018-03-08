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
public class Connecter {

  
    

    public static Connection ConnectTo(String MyName,String Ip,String Port) throws IOException, ClassNotFoundException{
            Connection connection=null;
            Socket s = new Socket(Ip,1111);
            ObjectOutputStream os = new ObjectOutputStream(s.getOutputStream());
            Message message = new Message(chating.Chating.Name,"/connect:"+MyName+";"+Ip+";"+Port);
            os.writeObject(message);
            ServerSocket ss = new ServerSocket(1112);
            Socket s1 = ss.accept();
            ObjectInputStream is = new ObjectInputStream(s1.getInputStream());
            Message returnMessage = (Message) is.readObject();
            System.out.println(returnMessage.Message);  
            if(returnMessage.Message.startsWith("/accept")){
                String Name=returnMessage.User;
                connection=new Connection(Name,Ip,Integer.parseInt(Port));
                
            }   
            return connection;
    }
    public static void CloseTo(Connection connection) throws IOException, ClassNotFoundException{
    
            Socket s = new Socket(connection.IP,connection.PortNb);
            ObjectOutputStream os = new ObjectOutputStream(s.getOutputStream());
            Message message = new Message(chating.Chating.Name,"/close");
            os.writeObject(message);
            
    }
public static void SendTo(Connection connection,String messageString) throws IOException, ClassNotFoundException{
            System.out.println(connection.IP+";"+connection.PortNb);
            Socket s = new Socket(connection.IP,connection.PortNb);
            ObjectOutputStream os = new ObjectOutputStream(s.getOutputStream());
            Message message = new Message(chating.Chating.Name,messageString);
            os.writeObject(message);
            s.close();
            
    }


}
    

