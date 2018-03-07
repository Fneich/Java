/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chating;

import java.util.Scanner;

/**
 *
 * @author Fneich
 */
public class Chating {

    public static String Name;
    public static Connection connection;
    public static Sender sender ;
    public static Recever recever ;
       public static Thread threadSender;
       public static Thread threadRecever ;
        
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter You Name:");
        Name=sc.nextLine();
        Hoster hoster = new Hoster();
        Thread threadhoster = new Thread(hoster);
        threadhoster.start();
        Connecter connecter = new Connecter();
        Thread threadconnecter = new Thread(connecter);
        threadconnecter.start();
        
    }
    
}
