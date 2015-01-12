/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testi;

import java.io.InputStream;
import java.net.URL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import static testi.FXMLDocumentController.citylisttwo;

/**
 *
 * @author oLLi
 */
public class addallcitys {
     String citycheck;
      public ObservableList<String> citylisttwo = FXCollections.observableArrayList();
    ;
    public ObservableList<String> addcitys (){
        FXMLDocumentController FXMLDocumentController = new FXMLDocumentController();
        
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
        
       // FXMLDocumentController.cityComboBox.setItems(citylisttwo);
        return citylisttwo;
    }
    
}
