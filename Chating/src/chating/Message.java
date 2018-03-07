/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chating;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 * @author Fneich
 */
public class Message implements Serializable{
    public String User;
    public LocalDateTime Date;
    public String Message;
    public Message(String user,String message){
        this.User=user;
        this.Date=LocalDateTime.now();
        this.Message=message;
    }
    public String toString(){
    return this.User + "@" + this.Date + ":" + this.Message;
    }
    
}
