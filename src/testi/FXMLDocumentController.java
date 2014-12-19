/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testi;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.Date;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import netscape.javascript.JSObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author ollitiihonen
 */
public class FXMLDocumentController implements Initializable {

    String citycheck;
    static ObservableList<String> citylisttwo = FXCollections.observableArrayList();
    ;
    static ObservableList<String> packetinfodatalist = FXCollections.observableArrayList();
    ;
    static ObservableList<String> otherDataFromPackets = FXCollections.observableArrayList();
    ;
    static ObservableList<Integer> packetSizeList = FXCollections.observableArrayList();
    ;

     public String packetinfodata;
    static private ArrayList<String> latlontoindex = new ArrayList<>();
    static private int packetclassnumber;
    static private String alldata;
    static private int packetcheckernumber = 0, indexofselectedpacket;
    private Label label;
    @FXML
    private Button button;
    @FXML
    private WebView webview;
    @FXML
    private Button button1;
    @FXML
    private Button button11;
    @FXML
    private ComboBox<String> cityComboBox;
    @FXML
    private ComboBox<String> packetComboBox;
    @FXML
    private Label warningLabel;
    @FXML
    private Label infoLabel;
    @FXML
    private Slider moneySlider;
    @FXML
    private Label moneyAmount;
    private float money;
    @FXML
    private Button addMoneyBtn;
    @FXML
    private Label moneyLabel;
    @FXML
    private Label costLabel;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        webview.getEngine().load(getClass().getResource("index.html").toExternalForm());

        //load the map to the webview
        InputStream inputXml = null;

        try {
            String s1 = "http://smartpost.ee/fi_apt.xml";

            inputXml = new URL(s1).openConnection().getInputStream();
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(inputXml);
            doc.getDocumentElement().normalize();
            System.out.println("Root element " + doc.getDocumentElement().getNodeName());
            NodeList nodeLst = doc.getElementsByTagName("place");
            System.out.println("Information of all places");
            //getting the citys from xml
            for (int s = 0; s < nodeLst.getLength(); s++) {

                Node fstNode = nodeLst.item(s);

                if (fstNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element fstElmnt = (Element) fstNode;
                    NodeList city = fstElmnt.getElementsByTagName("city");
                    Element cityel = (Element) city.item(0);
                    NodeList citynode = cityel.getChildNodes();

                    if (!((Node) citynode.item(0)).getNodeValue().equals(citycheck)) {
                        citylisttwo.add(((Node) citynode.item(0)).getNodeValue());

                    }
                    citycheck = ((Node) citynode.item(0)).getNodeValue();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        cityComboBox.setItems(citylisttwo);

    }

    @FXML
    private void addToMap(ActionEvent event) {
        //add citys to map
        InputStream inputXml = null;

        try {
            String s1 = "http://smartpost.ee/fi_apt.xml";

            inputXml = new URL(s1).openConnection().getInputStream();
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(inputXml);
            doc.getDocumentElement().normalize();
            System.out.println("Root element " + doc.getDocumentElement().getNodeName());
            NodeList nodeLst = doc.getElementsByTagName("place");
            System.out.println("Information of all places");

            for (int s = 0; s < nodeLst.getLength(); s++) {

                Node fstNode = nodeLst.item(s);

                if (fstNode.getNodeType() == Node.ELEMENT_NODE) {
                    //get the data from xml
                    Element fstElmnt = (Element) fstNode;
                    NodeList city = fstElmnt.getElementsByTagName("city");
                    Element cityel = (Element) city.item(0);
                    NodeList citynode = cityel.getChildNodes();
                    System.out.println("kaupunki : " + ((Node) citynode.item(0)).getNodeValue());

                    NodeList address = fstElmnt.getElementsByTagName("address");
                    Element addressele = (Element) address.item(0);
                    NodeList addressnode = addressele.getChildNodes();
                    System.out.println("address : " + ((Node) addressnode.item(0)).getNodeValue());

                    NodeList postcode = fstElmnt.getElementsByTagName("code");
                    Element postcodeele = (Element) postcode.item(0);
                    NodeList postcodenode = postcodeele.getChildNodes();
                    System.out.println("postcode : " + ((Node) postcodenode.item(0)).getNodeValue());

                    NodeList availability = fstElmnt.getElementsByTagName("availability");
                    Element availabilityele = (Element) availability.item(0);
                    NodeList availabilitynode = availabilityele.getChildNodes();
                    System.out.println("availability : " + ((Node) availabilitynode.item(0)).getNodeValue());

                    NodeList postoffice = fstElmnt.getElementsByTagName("postoffice");
                    Element postofficeele = (Element) postoffice.item(0);
                    NodeList postofficenode = postofficeele.getChildNodes();
                    System.out.println("postoffice : " + ((Node) postofficenode.item(0)).getNodeValue());
                    //and add to map
                    if (cityComboBox.getValue().contains(((Node) citynode.item(0)).getNodeValue())) {
                        System.out.println("löytyy " + (((Node) citynode.item(0)).getNodeValue()));
                        String add = ((Node) addressnode.item(0)).getNodeValue() + "," + ((Node) postcodenode.item(0)).getNodeValue() + " " + ((Node) citynode.item(0)).getNodeValue();
                        String avail = ((Node) postofficenode.item(0)).getNodeValue() + " " + ((Node) availabilitynode.item(0)).getNodeValue();
                        String col = "#000000";
                        webview.getEngine().executeScript("document.goToLocation('" + add + "', '" + avail + "', 'blue')");

                    }

                }
            }

        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    @FXML
    private void deleteRoutes(ActionEvent event) {
        //delete routes
        webview.getEngine().executeScript("document.deletePaths()");

    }

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

    }

    @FXML
    public void sendPacket(ActionEvent event) {

        
        
        //sending packet
        warningLabel.setText("");
        double receivedDataDouble = (double) (webview.getEngine().executeScript(latlontoindex.get(indexofselectedpacket)));
        if (money < (receivedDataDouble/10)){
            warningLabel.setText("Rahasi eivät riitä.\nLisää rahaa koneeseen");
            webview.getEngine().executeScript("document.deletePaths()");
        }
        else {
            
            
        if ((latlontoindex.get(indexofselectedpacket).contains(",1)")) && receivedDataDouble > 150) {
            System.out.println("Pakettia ei voitu lähettää. Syy: matka yli 150km");
            webview.getEngine().executeScript("document.deletePaths()");
            warningLabel.setText("Pakettia ei voitu lähettää.\n Syy: 1. Luokat paketteja ei voi lähettää\n yli 150km päähän");

        } else {
            costLabel.setText("Paketin lähetys onnistui");
            money -= receivedDataDouble/10;
            moneyLabel.setText("Rahaa: "+money+"euroa.");
            if ((latlontoindex.get(indexofselectedpacket).contains(",1)")) && otherDataFromPackets.get(indexofselectedpacket).equals("true")) {

                warningLabel.setText("Paketti särkyi!");

            } else if (latlontoindex.get(indexofselectedpacket).contains(",2)")) {

            } else {

            }

            infoLabel.setText("Matkanpituus: " + webview.getEngine().executeScript(latlontoindex.get(indexofselectedpacket)) + "km");

            System.out.println("Paketti lähti");
            System.out.println(webview.getEngine().executeScript(latlontoindex.get(indexofselectedpacket)));
            webview.getEngine().executeScript(latlontoindex.get(indexofselectedpacket));
            System.out.println("alldata: " + latlontoindex.get(indexofselectedpacket));
            //check if its breakable
        }
        }
    }

    @FXML
    private void newPacket(ActionEvent event) {
        //new window for making a new packet
        try {
            Stage sendPacketView = new Stage();
            Parent page = FXMLLoader.load(getClass().getResource("FXMLpacketMaker.fxml"));
            Scene scene = new Scene(page);
            sendPacketView.setScene(scene);
            sendPacketView.show();

        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void getOtherDataFromPackets(String breakable) {
        //data if packet i breakable
        otherDataFromPackets.add(breakable);
        System.out.println("booleani" + otherDataFromPackets);
    }

    public String getPacket(String packet) {
        //packet data and saving it to list
        packetinfodata = packet;
        System.out.println("asdpaketti tallennettu" + packetinfodata);
        packetinfodatalist.add(packetinfodata);
        System.out.println("listapaketeista: " + packetinfodatalist);

        return packet;
    }

    @FXML
    private void refreshPackets(ActionEvent event) {
        //update packetlist
        // System.out.println("saatu data: " + packetinfodatalist);
        packetComboBox.setItems(packetinfodatalist);
    }

    @FXML
    private void showIndexNumber(Event event) {
        
        double receivedDataDouble = (double) (webview.getEngine().executeScript(latlontoindex.get(indexofselectedpacket)))/10;
        DecimalFormat df = new DecimalFormat("#.##");
        
        costLabel.setText("Lähetys maksaa: "+df.format(receivedDataDouble)+"euroa" );
        webview.getEngine().executeScript("document.deletePaths()");
        //checking the right index for sending the packet
        System.out.println(packetComboBox.getValue());
        int shaisse = latlontoindex.size();
        for (int i = 0; i < shaisse; i++) {
            System.out.println(packetComboBox.getValue() + " ja toinen on " + latlontoindex.get(i));
            if (packetComboBox.getValue().contains(packetinfodatalist.get(i))) {
                //System.out.println(latlontoindex(i));
                System.out.println("ii on: " + i);
                indexofselectedpacket = i;
            }

        }

    }

    @FXML
    private void sliderChanged(MouseEvent event) {
        String stringi = String.valueOf(moneySlider.getValue());
        moneyAmount.setText(stringi);
    }

    @FXML
    private void addMoney(ActionEvent event) {
        money += moneySlider.getValue();
        moneyLabel.setText("Rahaa: "+money+"euroa.");
        moneySlider.setValue(0);
    }

}


