/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chating;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fneich
 */
public class Sender implements Runnable {
public String IP;
public int PortNb;

public Sender(String ip,int nbport){
this.IP=ip;
this.PortNb=nbport;
}
    @Override
    public void run() {
        try {
            
            Scanner sc = new Scanner(System.in);
            Socket s = new Socket(this.IP,this.PortNb);
            ObjectOutputStream os = new ObjectOutputStream(s.getOutputStream());

            while(true){
                //System.out.println("Sender is running...");
            Message message=new Message(chating.Chating.Name,sc.nextLine());
            os.writeObject(message);
            
            }
        } catch (IOException ex) {
            Logger.getLogger(Sender.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
}
