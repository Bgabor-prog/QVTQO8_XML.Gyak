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

public class xPathQVTQO8 {

  private static XPath xpath = XPathFactory.newInstance().newXPath();

  public static void main(String[] args) {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    try {
      DocumentBuilder builder = factory.newDocumentBuilder();

      Document doc = builder.parse("./studentQVTQO8.xml");

      // printStudents(getAllStudents(doc));
      // printStudents(getStudentsById(doc, "02"));
      // printStudents(getSecondStudent(doc));
      // printStudents(getLastStudent(doc));
      printStudents(getFirstTwoStudents(doc));
    } catch (ParserConfigurationException | SAXException | IOException e) {
      e.printStackTrace();
    }
  }

  public static NodeList getAllStudents(Document doc) {
    String expression = "/class/student";
    try {
      return (NodeList) xpath
        .compile(expression)
        .evaluate(doc, XPathConstants.NODESET);
    } catch (XPathExpressionException e1) {
      e1.printStackTrace();
    }

    return null;
  }

  public static void printStudents(NodeList nodeList) {
    for (int i = 0; i < nodeList.getLength(); i++) {
      Element e = (Element) nodeList.item(i);
      System.out.println(
        e.getElementsByTagName("vezeteknev").item(0).getTextContent()
      );
      System.out.println(
        e.getElementsByTagName("keresztnev").item(0).getTextContent()
      );
      System.out.println(
        e.getElementsByTagName("becenev").item(0).getTextContent()
      );
      System.out.println(
        e.getElementsByTagName("kor").item(0).getTextContent()
      );
    }
  }

  public static NodeList getStudentsById(Document doc, String id) {
    String expression = "/class/student[@id = " + id + "  ]";
    try {
      return (NodeList) xpath
        .compile(expression)
        .evaluate(doc, XPathConstants.NODESET);
    } catch (XPathExpressionException e1) {
      e1.printStackTrace();
    }

    return null;
  }

  public static NodeList getSecondStudent(Document doc) {
    String expression = "/class/student[2]";
    try {
      return (NodeList) xpath
        .compile(expression)
        .evaluate(doc, XPathConstants.NODESET);
    } catch (XPathExpressionException e1) {
      e1.printStackTrace();
    }

    return null;
  }

  public static NodeList getLastStudent(Document doc) {
    String expression = "/class/student[last()]";
    try {
      return (NodeList) xpath
        .compile(expression)
        .evaluate(doc, XPathConstants.NODESET);
    } catch (XPathExpressionException e1) {
      e1.printStackTrace();
    }

    return null;
  }

  public static NodeList getFirstTwoStudents(Document doc) {
    String expression = "/class/student[position() <= 2]";
    try {
      return (NodeList) xpath
        .compile(expression)
        .evaluate(doc, XPathConstants.NODESET);
    } catch (XPathExpressionException e1) {
      e1.printStackTrace();
    }

    return null;
  }

  public static NodeList getAllChilds(Document doc) {
    String expression = "/class";
    try {
      return (NodeList) xpath
        .compile(expression)
        .evaluate(doc, XPathConstants.NODESET);
    } catch (XPathExpressionException e1) {
      e1.printStackTrace();
    }

    return null;
  }

  public static NodeList getStudentsByAge(Document doc, int age) {
    String expression = "/class/student[kor >= " + age + "]";
    try {
      return (NodeList) xpath
        .compile(expression)
        .evaluate(doc, XPathConstants.NODESET);
    } catch (XPathExpressionException e1) {
      e1.printStackTrace();
    }

    return null;
  }

  public static NodeList getAllNames(Document doc) {
    String expression = "/class/student/vezeteknev | /class/student/keresztnev";
    try {
      return (NodeList) xpath
        .compile(expression)
        .evaluate(doc, XPathConstants.NODESET);
    } catch (XPathExpressionException e1) {
      e1.printStackTrace();
    }

    return null;
  }
}