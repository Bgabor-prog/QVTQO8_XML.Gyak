import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOMModifyQVTQO8 {

  private static XPath xpath = XPathFactory.newInstance().newXPath();
  private static String filename = "./carsJmdrgg.xml";

  public static void main(String[] args) {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    try {
      DocumentBuilder builder = factory.newDocumentBuilder();

      Document doc = builder.parse(filename);

      removeLuxuryCars(doc);
    } catch (ParserConfigurationException | SAXException | IOException e) {
      e.printStackTrace();
    }
  }

  public static void removeLuxuryCars(Document doc) {
    String expression = "/cars/luxurycars";
    try {
      NodeList luxuryCars = (NodeList) xpath
        .compile(expression)
        .evaluate(doc, XPathConstants.NODESET);

      for (int i = 0; i < luxuryCars.getLength(); i++) {
        Node node = luxuryCars.item(i);
        node.getParentNode().removeChild(node);
      }

      writeXml(doc);
    } catch (XPathExpressionException e1) {
      e1.printStackTrace();
    }
  }

  public static void writeXml(Document doc) {
    try (FileOutputStream output = new FileOutputStream(filename)) {
      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer(
        new StreamSource(new File("./stylesheet.xslt"))
      );
      DOMSource source = new DOMSource(doc);
      StreamResult result = new StreamResult(output);

      transformer.transform(source, result);
    } catch (TransformerException | IOException e) {
      e.printStackTrace();
    }
  }
}