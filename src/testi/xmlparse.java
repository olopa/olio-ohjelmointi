/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testi;

import java.io.InputStream;
import java.net.URL;
import javafx.fxml.FXML;
import javafx.scene.web.WebView;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author oLLi
 */
public class xmlparse {

    public void addtomap(String x, WebView y) {
        InputStream inputXml = null;
        FXMLDocumentController FXMLDocumentController = new FXMLDocumentController();
        WebView webiviewi = FXMLDocumentController.webview;
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
                    if (x.contains(((Node) citynode.item(0)).getNodeValue())) {
                        System.out.println("löytyy " + (((Node) citynode.item(0)).getNodeValue()));
                        String add = ((Node) addressnode.item(0)).getNodeValue() + "," + ((Node) postcodenode.item(0)).getNodeValue() + " " + ((Node) citynode.item(0)).getNodeValue();
                        String avail = ((Node) postofficenode.item(0)).getNodeValue() + " " + ((Node) availabilitynode.item(0)).getNodeValue();
                        String col = "#000000";
                        y.getEngine().executeScript("document.goToLocation('" + add + "', '" + avail + "', 'blue')");

                    }

                }
            }

        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    private float startlat, startlon, endlat, endlon;

    public void getlanlot(String postcodeaddress) {

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

                    String getpostcode = postcodeaddress; //startAutomat.getValue();
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

        }

    }

    public void getlanlottwo(String postcodeaddresstwo) {

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

                    String getpostcode = postcodeaddresstwo; //endAutomat.getValue();
                    if (getpostcode.contains(((Node) addressnode.item(0)).getNodeValue() + ", " + ((Node) postcodenode.item(0)).getNodeValue())) {
                        System.out.println("lat: " + ((Node) latnode.item(0)).getNodeValue() + " lon " + ((Node) lngnode.item(0)).getNodeValue());
                        endlat = Float.parseFloat(((Node) latnode.item(0)).getNodeValue());
                        endlon = Float.parseFloat(((Node) lngnode.item(0)).getNodeValue());

                    }

                }
            }

        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    public Float returnlat() {
        return startlat;
    }

    public Float returnlon() {
        return startlon;
    }

    public Float returnlattwo() {
        return endlat;
    }

    public Float returnlontwo() {
        return endlon;
    }

}
