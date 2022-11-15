import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class DomWriteQVTQO8 {

  public static void main(String[] args)
          throws ParserConfigurationException, TransformerException {

      DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

      Document doc = docBuilder.newDocument();
      Element rootElement = doc.createElement("Users");
      doc.appendChild(rootElement);

      
      createUser(doc, "1", "Gábor", "Bartók", "CEO");
      createUser(doc, "2", "Kristóf", "Hegyesi", "Kukás");
      createUser(doc, "3", "Marcell", "Juhász", "Eladó");

      writeXml(doc, System.out);

      try (FileOutputStream output =
                     new FileOutputStream("C:/Users/Z004KHJR/source/repos/Egyetem/4.év 1.félév/Adatkezelés XML-ben/QVTQO8_XML.Gyak/HetedikGyak/users1Neptunkod.xml")) {
            writeXml(doc, output);
        } catch (IOException e) {
            e.printStackTrace();
        }

  }

  private static void writeXml(Document doc,
                               OutputStream output)
          throws TransformerException {

      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer();

      transformer.setOutputProperty(OutputKeys.INDENT, "yes");

      DOMSource source = new DOMSource(doc);
      StreamResult result = new StreamResult(output);

      transformer.transform(source, result);

  }

  private static Node createUser(Document doc,String id,String firstName, String lastName,String profession)
  {
    Element user = doc.createElement("user");

    user.setAttribute("id", id);
    user.appendChild(createUserElement(doc,"firstname",firstName));
    user.appendChild(createUserElement(doc,"lastname",lastName));
    user.appendChild(createUserElement(doc,"profession",profession));

    return user;

  }

  private static Node createUserElement(Document doc,String name, String value){
    Element node = doc.createElement(name);
    node.appendChild(doc.createTextNode(value));

    return node;

  }
}