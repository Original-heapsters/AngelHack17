package com.example.russell.myapplication;

import java.util.Observable;

/**
 * Created by russell on 5/13/17.
 */

public class TicketInfo extends Observable {
    public static boolean setFull = false;
    public static boolean setID =  false;
    public static String barcodeFull = "123456789101";
    public static String barcodeID = "1234567";
    public static String username;
    public static String password;

    public TicketInfo(){}

    public void changeSetFull(boolean full)
    {
        setFull = full;
        notifyObservers();
    }

    public void changeSetID(boolean id)
    {
        setID = id;
        notifyObservers();
    }

}
