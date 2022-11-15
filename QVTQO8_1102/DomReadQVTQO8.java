
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class DomReadQVTQO8 {

  private static final String FILENAME = "C:/Users/Z004KHJR/source/repos/Egyetem/4.év 1.félév/Adatkezelés XML-ben/QVTQO8_XML.Gyak/HetedikGyak/usersQVTQO8.xml";

  public static void main(String[] args) {

      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

 try {
          
          dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

          DocumentBuilder db = dbf.newDocumentBuilder();

          Document doc = db.parse(new File(FILENAME));
          doc.getDocumentElement().normalize();

          System.out.println("Root Element :" + doc.getDocumentElement().getNodeName());
          System.out.println("------");

          NodeList list = doc.getElementsByTagName("user");

          for (int temp = 0; temp < list.getLength(); temp++) {

              Node node = list.item(temp);

              if (node.getNodeType() == Node.ELEMENT_NODE) {

                  Element element = (Element) node;

                  // get staff's attribute
                  String id = element.getAttribute("id");

                  // get text
                  String firstname = element.getElementsByTagName("firstname").item(0).getTextContent();
                  String lastname = element.getElementsByTagName("lastname").item(0).getTextContent();
                  String profession = element.getElementsByTagName("profession").item(0).getTextContent();

                  System.out.println("Current Element :" + node.getNodeName());
                  System.out.println("Id : " + id);
                  System.out.println("First Name : " + firstname);
                  System.out.println("Last Name : " + lastname);
                  System.out.println("Profession : " + profession);

              }
          }

      } catch (ParserConfigurationException | SAXException | IOException e) {
          e.printStackTrace();
      }

  }

}