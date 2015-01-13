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

    public ObservableList<String> sendvalues() {
        System.out.println("palauttaa: " + packetinfodatalist);
        return packetinfodatalist;
    }

    public void getOtherDataFromPackets(String breakable) {
        //data if packet i breakable
        breakablestring = breakable;
        otherDataFromPackets.add(breakable);
        System.out.println("booleani" + otherDataFromPackets);
    }

    public String breakabledatasend() {
        return breakablestring;
    }

}
