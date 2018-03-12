/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chating;

/**
 *
 * @author Fneich
 */
public class Connection {
    public String Name;
    public String IP;
    public int PortNb;
    //public Sender sender;
    public Recever recever;
    //public Thread SenderThread;
    public Thread ReceverThread;
    
    public Connection(String name,String ip,int portNb){
     this.Name=name;   
    this.IP=ip;
    this.PortNb=portNb;
    //this.sender = new Sender(this.IP,this.PortNb);
    this.recever = new Recever(this.PortNb);
    }
    
    public void Connect(){
        this.recever.Running=true;
        this.ReceverThread = new Thread(this.recever);
        this.ReceverThread.start();
    }
    public void Close() throws Throwable{
    Chating.connections.remove(this.IP);
    this.recever.Running=false;
    this.finalize();
    }
}
