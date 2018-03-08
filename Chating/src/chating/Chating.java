/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chating;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author Fneich
 */
public class Chating {

    public static String Name;
    public static HashMap<String,Connection> connections=new HashMap<String,Connection>();

        
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter You Name:");
        Name=sc.nextLine();
        Hoster hoster = new Hoster();
        Thread threadhoster = new Thread(hoster);
        threadhoster.start();
        Request request = new Request();
        Thread threadRequest = new Thread(request);
        threadRequest.start();
        
    }
    
}
