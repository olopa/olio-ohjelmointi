/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testi;

import java.awt.image.ImageObserver;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.stage.Stage;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import static testi.FXMLDocumentController.citylisttwo;

/**
 * FXML Controller class
 *
 * @author ollitiihonen
 */
public class FXMLpacketMakerController implements Initializable {

    @FXML
    private TextField packetName;
    @FXML
    private TextField packetSize;
    @FXML
    private TextField packetWeight;
    @FXML
    private CheckBox breakable;
    @FXML
    private RadioButton firstClass;
    @FXML
    private RadioButton secondClass;
    @FXML
    private RadioButton thirdClass;
    @FXML
    ComboBox<String> startCity;
    @FXML
    private ComboBox<String> startAutomat;
    @FXML
    private ComboBox<String> endCity;
    @FXML
    private ComboBox<String> endAutomat;
    String citycheck;
    static ObservableList<String> citylisttwo = FXCollections.observableArrayList();
    ;
    static ObservableList<String> itemList = FXCollections.observableArrayList(
            "Korttipakka", "Jalkapallo", "Pesukone", "Tietokone"
    );
    ;
    @FXML
    private ComboBox<String> itemsListCombo;
    private String citychecker, addresschecker, postcodechecker;
    private static String packetinfostring;
    private float startlat, startlon, endlat, endlon;
    @FXML
    private Label packetDoneLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //loading city from xml
        itemsListCombo.setItems(itemList);

        addallcitys addallcityss = new addallcitys();
        //addallcityss.addcitys();
        ObservableList<String> obstring = addallcityss.addcitys();
        System.out.println(obstring);
        startCity.setItems(obstring);
        endCity.setItems(obstring);
        /*  InputStream inputXml = null;

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

         } */
        //add them to comboboxes
        //  startCity.setItems(citylisttwo);
        //  endCity.setItems(citylisttwo);

    }

    @FXML
    private void packetInfo(ActionEvent event) {
        //new window for packet info
        try {
            Stage packetInfo = new Stage();
            Parent pageinfo = FXMLLoader.load(getClass().getResource("FXMLPacketInfo.fxml"));
            Scene sceneinfo = new Scene(pageinfo);
            packetInfo.setScene(sceneinfo);
            packetInfo.show();

        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String getPacket(String packet) {
        //
        System.out.println(packetinfostring);
        return packetinfostring;
    }

    @FXML
    private void createPacket(ActionEvent event) throws ScriptException {

        //generate packet
        String packetname;
        System.out.println("pakettinimi " + packetName.getText());
        System.out.println("pakettinimi2 " + itemsListCombo.getValue());
        if ((packetName.getText().equals(""))) {
            System.out.println("on tyhjä");
            packetname = itemsListCombo.getValue();
        } else {
            System.out.println("ei tyhjä");
            packetname = packetName.getText();
        }

        String packetsize = packetSize.getText();
        String packetweight = packetWeight.getText();
        String chosenStartCity = startCity.getValue();
        String chosenEndCity = endCity.getValue();
        String chosenStartAutomat = startAutomat.getValue();
        String chosenEndAutomat = endAutomat.getValue();

        if (!(itemsListCombo.getValue() == null)) {
            if (itemsListCombo.getValue().equals("Korttipakka")) {
                packetsize = "10*5*1";
                packetweight = "1";
            } else if (itemsListCombo.getValue().equals("Jalkapallo")) {
                packetsize = "20*20*20";
                packetweight = "2";
            } else if (itemsListCombo.getValue().equals("Pesukone")) {
                packetsize = "60*50*40";
                packetweight = "33";
            } else if (itemsListCombo.getValue().equals("Tietokone")) {
                packetsize = "40*20*30";
                packetweight = "10";
            }
        }
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        System.out.println("laskusta tulee: " + engine.eval(packetsize));
        int packetSizeInt = (int) engine.eval(packetsize);

        System.out.println("nimi: " + packetname + " koko: " + packetsize + " paino: " + packetweight + " särkyvää: " + breakable.isSelected());
        System.out.println("alkukaupunki: " + chosenStartCity + " ja loppu: " + chosenEndCity);
        System.out.println("alkuautomaatti: " + chosenStartAutomat + " ja loppu: " + chosenEndAutomat);
        float packetclass = 0;
        if (firstClass.isSelected() == true) {
            System.out.println("eka valittu");
            packetclass = 1;
        }
        if (secondClass.isSelected() == true) {
            System.out.println("toka valittu");
            packetclass = 2;

        }
        if (thirdClass.isSelected() == true) {
            System.out.println("kolmas valittu");
            packetclass = 3;
        }
        //debends on packetclass if size is not big/small enought, packet cannot be done
        int packetweightint = Integer.parseInt(packetweight);
        if ((packetclass == 2) && ((packetSizeInt > 27000) || (packetweightint > 15))) {
            packetDoneLabel.setText("2. pakettiluokan esine täytyy olla\npienempi kuin 30cm*30cm*30cm ja\npainaa alle 15kg");
        } else if ((packetclass == 3) && ((packetSizeInt < 27000) || (packetweightint < 35))) {
            packetDoneLabel.setText("3. pakettiluokan esine täytyy olla\nsuurempi kuin 30cm*30cm*30cm ja\npainaa yli 35kg");
        } else {
            ArrayList<Float> lonlatlist = new ArrayList<>();
            lonlatlist.add(startlat);
            lonlatlist.add(startlon);
            lonlatlist.add(endlat);
            lonlatlist.add(endlon);
            lonlatlist.add(packetclass);
            FXMLDocumentController sendlonlat = new FXMLDocumentController();

            sendlonlat.getlonlat(startlat, startlon, endlat, endlon, packetclass);
            //sending packetdata 
            storage sendinfo = new storage();
            packetinfostring = "Name: " + packetname + ", Class: " + packetclass + ", from: " + chosenStartCity + ", to: " + chosenEndCity;

            sendinfo.getPackettoStorage(packetinfostring);
            String breakabledata = String.valueOf(breakable.isSelected());
            sendinfo.getOtherDataFromPackets(breakabledata);

            packetDoneLabel.setText("Paketti luotu: Nimi: " + packetname + "\nLuokka: " + packetclass + "\nLähtö: " + chosenStartCity + "\nSaapumis: " + chosenEndCity);
            packetWeight.setText("");
            packetName.setText("");
            packetSize.setText("");
            firstClass.setSelected(false);
            secondClass.setSelected(false);
            thirdClass.setSelected(false);
            itemsListCombo.setValue("");
            breakable.setSelected(false);
            startCity.setValue("Lähtökaupunki");
            endCity.setValue("Kohdekaupunki");
            startAutomat.setValue("Automaatti");
            endAutomat.setValue("Automaatti");
        }
    }

    @FXML
    private void checkfirstautomat(Event event) {
        //getting citydata after city has been chosen

        startAutomat.setItems(null);
        addautomats addautomats = new addautomats();
        //addautomats.getstartcity(startCity.getValue());
        ObservableList<String> obstring = addautomats.addautomats(startCity.getValue());
        startAutomat.setItems(obstring);
        /*  InputStream inputXml = null;
         ObservableList<String> automatlistone = FXCollections.observableArrayList();

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

         if (startCity.getValue().contains(((Node) citynode.item(0)).getNodeValue())) {
         System.out.println("löytyy " + (((Node) citynode.item(0)).getNodeValue()));
         String add = ((Node) addressnode.item(0)).getNodeValue() + ", " + ((Node) postcodenode.item(0)).getNodeValue() + " " + ((Node) citynode.item(0)).getNodeValue();
         String avail = ((Node) postofficenode.item(0)).getNodeValue() + " " + ((Node) availabilitynode.item(0)).getNodeValue();
         String col = "#000000";
         automatlistone.add(add + " " + avail);
         postcodechecker = ((Node) postcodenode.item(0)).getNodeValue();
         addresschecker = ((Node) addressnode.item(0)).getNodeValue();
         citychecker = ((Node) citynode.item(0)).getNodeValue();
         }

         }
         }

         } catch (Exception e) {
         e.printStackTrace();

         } */
        //add them to combobox
        // startAutomat.setItems(automatlistone);

    }

    @FXML
    private void checksecondautomat(Event event) {
        //same as above

        // InputStream inputXml = null;
        endAutomat.setItems(null);
        addautomats addautomats = new addautomats();

        ObservableList<String> obstring = addautomats.addautomats(endCity.getValue());
        endAutomat.setItems(obstring);

        /*   ObservableList<String> automatlisttwo = FXCollections.observableArrayList();

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

         if (endCity.getValue().contains(((Node) citynode.item(0)).getNodeValue())) {
         System.out.println("löytyy " + (((Node) citynode.item(0)).getNodeValue()));
         String add = ((Node) addressnode.item(0)).getNodeValue() + ", " + ((Node) postcodenode.item(0)).getNodeValue() + " " + ((Node) citynode.item(0)).getNodeValue();
         String avail = ((Node) postofficenode.item(0)).getNodeValue() + " " + ((Node) availabilitynode.item(0)).getNodeValue();
         String col = "#000000";
         automatlisttwo.add(add + " " + avail);
         }

         }
         }

         } catch (Exception e) {
         e.printStackTrace();

         } */
       // endAutomat.setItems(automatlisttwo);
    }

    @FXML
    private void checklonlatstart(Event event) {
        //getting lan and lot lata

        xmlparse xmlparse = new xmlparse();
        xmlparse.getlanlot(startAutomat.getValue());
        startlat = xmlparse.returnlat();
        startlon = xmlparse.returnlon();
        System.out.println(startlat + " starttilat ja lon:" + startlon);
        /*   InputStream inputXml = null;

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

         Element fstElmnt = (Element) fstNode;
         NodeList city = fstElmnt.getElementsByTagName("city");
         Element cityel = (Element) city.item(0);
         NodeList citynode = cityel.getChildNodes();
         // System.out.println("kaupunki : "  + ((Node) citynode.item(0)).getNodeValue());

         NodeList address = fstElmnt.getElementsByTagName("address");
         Element addressele = (Element) address.item(0);
         NodeList addressnode = addressele.getChildNodes();
         // System.out.println("address : "  + ((Node) addressnode.item(0)).getNodeValue());

         NodeList postcode = fstElmnt.getElementsByTagName("code");
         Element postcodeele = (Element) postcode.item(0);
         NodeList postcodenode = postcodeele.getChildNodes();
         // System.out.println("postcode : "  + ((Node) postcodenode.item(0)).getNodeValue());

         NodeList lat = fstElmnt.getElementsByTagName("lat");
         Element latele = (Element) lat.item(0);
         NodeList latnode = latele.getChildNodes();
         // System.out.println("latitude : "  + ((Node) latnode.item(0)).getNodeValue());

         NodeList lng = fstElmnt.getElementsByTagName("lng");
         Element lngele = (Element) lng.item(0);
         NodeList lngnode = lngele.getChildNodes();
         // System.out.println("longtitude : "  + ((Node) lngnode.item(0)).getNodeValue());

         String getpostcode = startAutomat.getValue();
         if (getpostcode.contains(((Node) addressnode.item(0)).getNodeValue() + ", " + ((Node) postcodenode.item(0)).getNodeValue())) {
         System.out.println("lat: " + ((Node) latnode.item(0)).getNodeValue() + " lon " + ((Node) lngnode.item(0)).getNodeValue());

         startlat = Float.parseFloat(((Node) latnode.item(0)).getNodeValue());
         startlon = Float.parseFloat(((Node) lngnode.item(0)).getNodeValue());
         System.out.println("floatit " + startlat + " " + startlon);
         System.out.println(Float.valueOf(((Node) latnode.item(0)).getNodeValue()));

         // public float startlat, startlon, endlat, endlon;
         }

         }
         }

         } catch (Exception e) {
         e.printStackTrace();

         } */
        //endAutomat.setItems(automatlisttwo);

    }

    @FXML
    private void checklonlatend(Event event) {
        //getting lan and lot data

        xmlparse xmlparse = new xmlparse();
        xmlparse.getlanlottwo(endAutomat.getValue());
        endlat = xmlparse.returnlattwo();
        endlon = xmlparse.returnlontwo();
        System.out.println(endlat + " endtilat ja lon:" + endlon);

        /*   InputStream inputXml = null;

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

         Element fstElmnt = (Element) fstNode;
         NodeList city = fstElmnt.getElementsByTagName("city");
         Element cityel = (Element) city.item(0);
         NodeList citynode = cityel.getChildNodes();
         // System.out.println("kaupunki : "  + ((Node) citynode.item(0)).getNodeValue());

         NodeList address = fstElmnt.getElementsByTagName("address");
         Element addressele = (Element) address.item(0);
         NodeList addressnode = addressele.getChildNodes();
         // System.out.println("address : "  + ((Node) addressnode.item(0)).getNodeValue());

         NodeList postcode = fstElmnt.getElementsByTagName("code");
         Element postcodeele = (Element) postcode.item(0);
         NodeList postcodenode = postcodeele.getChildNodes();
         // System.out.println("postcode : "  + ((Node) postcodenode.item(0)).getNodeValue());

         NodeList lat = fstElmnt.getElementsByTagName("lat");
         Element latele = (Element) lat.item(0);
         NodeList latnode = latele.getChildNodes();
         // System.out.println("latitude : "  + ((Node) latnode.item(0)).getNodeValue());

         NodeList lng = fstElmnt.getElementsByTagName("lng");
         Element lngele = (Element) lng.item(0);
         NodeList lngnode = lngele.getChildNodes();
         // System.out.println("longtitude : "  + ((Node) lngnode.item(0)).getNodeValue());

         String getpostcode = endAutomat.getValue();
         if (getpostcode.contains(((Node) addressnode.item(0)).getNodeValue() + ", " + ((Node) postcodenode.item(0)).getNodeValue())) {
         System.out.println("lat: " + ((Node) latnode.item(0)).getNodeValue() + " lon " + ((Node) lngnode.item(0)).getNodeValue());
         endlat = Float.parseFloat(((Node) latnode.item(0)).getNodeValue());
         endlon = Float.parseFloat(((Node) lngnode.item(0)).getNodeValue());

         }

         }
         }

         } catch (Exception e) {
         e.printStackTrace();

         } */
    }

    @FXML
    private void enableDisableRadioBtns(Event event) {
        //checking object combobox and eliminate packetclasses if an object cannot be sent in that packet
        System.out.println("meneeköbuttonhommeliin");
        if (itemsListCombo.getValue().equals("Korttipakka")) {
            firstClass.setVisible(true);
            secondClass.setVisible(true);
            thirdClass.setVisible(true);
        } else if (itemsListCombo.getValue().equals("Jalkapallo")) {
            firstClass.setVisible(true);
            secondClass.setVisible(true);
            thirdClass.setVisible(true);
        } else if (itemsListCombo.getValue().equals("Pesukone")) {
            secondClass.setVisible(false);
            firstClass.setVisible(true);
            thirdClass.setVisible(true);
        } else if (itemsListCombo.getValue().equals("Tietokone")) {
            secondClass.setVisible(false);
            firstClass.setVisible(true);
            thirdClass.setVisible(true);
        } else {
            firstClass.setVisible(true);
            secondClass.setVisible(true);
            thirdClass.setVisible(true);
        }

    }

}
