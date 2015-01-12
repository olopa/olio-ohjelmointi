/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testi;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author oLLi
 */
public class storage {
     public String packetinfodata;
     public String breakablestring;
     final static ObservableList<String> packetinfodatalist = FXCollections.observableArrayList();
    ;
         static ObservableList<String> otherDataFromPackets = FXCollections.observableArrayList();
    ;
     public void getPackettoStorage(String packet) {
        //packet data and saving it to list
        packetinfodata = packet;
        System.out.println("paketti tallennettu " + packetinfodata);
        packetinfodatalist.add(packetinfodata);
        System.out.println("listapaketeista: " + packetinfodatalist);

        //return packet;
    }
     public ObservableList<String> sendvalues (){
         System.out.println("palauttaa: "+packetinfodatalist);
         return packetinfodatalist;
     }
     public void getOtherDataFromPackets(String breakable) {
        //data if packet i breakable
         breakablestring = breakable;
        otherDataFromPackets.add(breakable);
        System.out.println("booleani" + otherDataFromPackets);
    }
     public String breakabledatasend (){
         return breakablestring;
     }
    
 /*   private int packetclassnumber;
    static private String alldata;
     public void getlonlat(float a, float b, float c, float d, float packetclass) {
        //data to draw the route
        packetclassnumber = Math.round(packetclass);
        String routeColor;
        if (packetclassnumber == 1) {
            routeColor = "blue";
        } else if (packetclassnumber == 2) {
            routeColor = "red";
        } else {
            routeColor = "green";
        }

        alldata = "document.createPath([" + a + ", " + b + ", " + c + ", " + d + "], " + "'" + routeColor + "'," + packetclassnumber + ")";
        System.out.println(alldata);
        System.out.println("saatu data latlon " + latlontoindex + " pakettinumero: " + packetclassnumber);
        latlontoindex.add(alldata);
        */

    

    
}
