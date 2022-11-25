import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomQueryQVTQO8 {

  private static XPath xpath = XPathFactory.newInstance().newXPath();

  public static void main(String[] args) {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    try {
      DocumentBuilder builder = factory.newDocumentBuilder();

      Document doc = builder.parse("./carsJmdrgg.xml");
      printCars(queryCarsByCompany(doc, "Lamborghini"));
    } catch (ParserConfigurationException | SAXException | IOException e) {
      e.printStackTrace();
    }
  }

  public static NodeList queryCarsByCompany(Document doc, String name) {
    String expression = "/cars/supercars[@company = '" + name + "']";
    try {
      return (NodeList) xpath
        .compile(expression)
        .evaluate(doc, XPathConstants.NODESET);
    } catch (XPathExpressionException e1) {
      e1.printStackTrace();
    }

    return null;
  }

  public static void printCars(NodeList cars) {
    if (cars != null) {
      Element elem = (Element) cars.item(0);
      System.out.println(elem.getAttribute("company"));
      NodeList carnames = elem.getElementsByTagName("carname");
      for (int i = 0; i < carnames.getLength(); i++) {
        System.out.println(carnames.item(i).getTextContent());
      }
    }
  }
}