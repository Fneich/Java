/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chating;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Recever implements Runnable{

    
    public int PortNb;
    public Boolean Running;
    public Recever(int portnb){
        this.PortNb=portnb;
    }
    @Override
    public void run() {
        try {

            ServerSocket ss = new ServerSocket(this.PortNb);
            Socket s = ss.accept();
            ObjectInputStream is = new ObjectInputStream(s.getInputStream());
            while(this.Running){
                System.out.println("Waiting...");
                Message returnMessage = (Message) is.readObject();
                System.out.println(returnMessage.toString());
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
