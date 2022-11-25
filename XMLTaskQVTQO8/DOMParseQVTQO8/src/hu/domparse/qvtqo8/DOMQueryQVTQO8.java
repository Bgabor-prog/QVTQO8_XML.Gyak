package hu.domparse.qvtqo8;


import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class DOMQueryQVTQO8 {

    private static XPath xPath = XPathFactory.newInstance().newXPath();
    private static Document document;

    public static void main(String[] args) {

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder;

            dBuilder = dbFactory.newDocumentBuilder();

            document = dBuilder.parse("./XMLQVTQO8.xml");

            document.getDocumentElement().normalize();

            //Minden konyveloiroda lekérdezése
            queryAllKonyveloIroda();

            //Lekérés id alapján
            queryKonyveloIrodaById("k2");

            //név alapján lekérdezés
            ugyfelekQueryByName("Higo Hugo");

            //Irszam es varos alapjan lekerdezes
            ugyfelekQueryByIrszamAndVaros("6723","Rekedt");

            //7-től nyitva levő oep lekerdezése
            oepQueryByOpening("7:00");

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Az osszes Konyveloiroda lekerdezese
    public static NodeList queryAllKonyveloIroda() {
        String expression = String.format("/Tulaj/Konyveloiroda");
        try {

            System.out.printf("Konyveloirodak lekerdezese: \n");

            NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(document, XPathConstants.NODESET);

            for (int i = 0; i < nodeList.getLength(); i++) {

                Node nNode = nodeList.item(i);

                System.out.println("Element: " + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element elem = (Element) nNode;

                    String uid = elem.getAttribute("k_adoszam");

                    Node node2 = elem.getElementsByTagName("ugyvezeto").item(0);
                    String uvezeto = node2.getTextContent();

                    System.out.printf("Adoszam: %s%n", uid);
                    System.out.printf("Szekhely:" +
                            "%n\tIrszam:" + elem.getElementsByTagName("irszam").item(0).getTextContent()+
                            "%n\tHazszam:" + elem.getElementsByTagName("hazszam").item(0).getTextContent()+
                            "%n\tUtca:" + elem.getElementsByTagName("utca").item(0).getTextContent()+
                            "%n\tVaros:" + elem.getElementsByTagName("varos").item(0).getTextContent());
                    System.out.printf("Ugyvezeto: %s%n\n", uvezeto);
                }
            }

        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }

        return null;
    }

    // Konyveloiroda lekerdezese id alapján
    public static NodeList queryKonyveloIrodaById(String value) {
        String id = "k_adoszam";
       
        String expression = String.format("/Tulaj/Konyveloiroda[@%s = '%s']", id, value);
        try {
            System.out.printf("---------------------\n\n");
            System.out.printf("Id alapjan lekerdezés: \n");

            NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(document, XPathConstants.NODESET);

            for (int i = 0; i < nodeList.getLength(); i++) {

                Node nNode = nodeList.item(i);

                System.out.println("Element: " + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element elem = (Element) nNode;

                    String uid = elem.getAttribute("k_adoszam");

                    Node node2 = elem.getElementsByTagName("ugyvezeto").item(0);
                    String uvezeto = node2.getTextContent();

                    System.out.printf("Adoszam: %s%n", uid);
                    System.out.printf("Szekhely:" +
                            "%n\tIrszam:" + elem.getElementsByTagName("irszam").item(0).getTextContent()+
                            "%n\tHazszam:" + elem.getElementsByTagName("hazszam").item(0).getTextContent()+
                            "%n\tUtca:" + elem.getElementsByTagName("utca").item(0).getTextContent()+
                            "%n\tVaros:" + elem.getElementsByTagName("varos").item(0).getTextContent());
                    System.out.printf("Ugyvezeto: %s%n\n", uvezeto);
                }
            }

        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }

        return null;
    }

    //Ugyfelek lekerdezese név alapján
    public static NodeList ugyfelekQueryByName(String name) {
       
        String expression = String.format("/Tulaj/Ugyfelek[nev='%s']", name);
        try {
            System.out.printf("---------------------\n\n");
            System.out.printf("Nev alapjan lekerdezés: \n");

            NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(document, XPathConstants.NODESET);

            for (int i = 0; i < nodeList.getLength(); i++) {

                Node nNode = nodeList.item(i);
    
                System.out.println("\nElement: " + nNode.getNodeName());
    
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
    
                    Element elem = (Element) nNode;
    
                    String uid = elem.getAttribute("u_adoazonosito");
    
                    Node node2 = elem.getElementsByTagName("email").item(0);
                    String email = node2.getTextContent();
    
                    System.out.printf("Adoszam: %s%n", uid);
                    System.out.printf("Nev: %s%n", name);
                    System.out.printf("Email: %s%n", email);
                    System.out.printf("Lakcim:" +
                            "%n\tIrszam:" + elem.getElementsByTagName("irszam").item(0).getTextContent() +
                            "%n\tHazszam:" + elem.getElementsByTagName("hazszam").item(0).getTextContent() +
                            "%n\tUtca:" + elem.getElementsByTagName("utca").item(0).getTextContent() +
                            "%n\tVaros:" + elem.getElementsByTagName("varos").item(0).getTextContent() + "\n");
                }
            }

        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }

        return null;
    }

     //Ugyfelek lekerdezese irszam es varos alapján
     public static NodeList ugyfelekQueryByIrszamAndVaros(String irszam,String varos) {
       
        String expression = String.format("/Tulaj/Ugyfelek[lakcim/irszam='%s' and lakcim/varos='%s']", irszam,varos);
        try {
            System.out.printf("---------------------\n\n");
            System.out.printf("Irszam es varos alapjan lekerdezés: \n");

            NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(document, XPathConstants.NODESET);

            for (int i = 0; i < nodeList.getLength(); i++) {

                Node nNode = nodeList.item(i);
    
                System.out.println("\nElement: " + nNode.getNodeName());
    
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
    
                    Element elem = (Element) nNode;
    
                    String uid = elem.getAttribute("u_adoazonosito");
    
                    Node node1 = elem.getElementsByTagName("nev").item(0);
                    String nev = node1.getTextContent();

                    Node node2 = elem.getElementsByTagName("email").item(0);
                    String email = node2.getTextContent();
    
                    System.out.printf("Adoszam: %s%n", uid);
                    System.out.printf("Nev: %s%n", nev);
                    System.out.printf("Email: %s%n", email);
                    System.out.printf("Lakcim:" +
                            "%n\tIrszam:" + elem.getElementsByTagName("irszam").item(0).getTextContent() +
                            "%n\tHazszam:" + elem.getElementsByTagName("hazszam").item(0).getTextContent() +
                            "%n\tUtca:" + elem.getElementsByTagName("utca").item(0).getTextContent() +
                            "%n\tVaros:" + elem.getElementsByTagName("varos").item(0).getTextContent() + "\n\n");
                }
            }

        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }

        return null;
    }

    //7től nyitva levő oep lekerdezése
    public static NodeList oepQueryByOpening(String opening) {
       
        String expression = String.format("/Tulaj/Oep[nyitvatartas/tol='%s']", opening);
        try {
            System.out.printf("---------------------\n\n");
            System.out.printf("7-tol nyitva levo Oep lekérdezése: \n");

            NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(document, XPathConstants.NODESET);

            for (int i = 0; i < nodeList.getLength(); i++) {

                Node nNode = nodeList.item(i);
    
                System.out.println("\nElement: " + nNode.getNodeName());
    
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
    
                    Element elem = (Element) nNode;
    
                    String uid = elem.getAttribute("o_adoszam");
    
                    Node node1 = elem.getElementsByTagName("email").item(0);
                    String email = node1.getTextContent();
    
                    System.out.printf("Adoszam: %s%n", uid);
                    System.out.printf("Email: %s%n", email);
                    System.out.printf("Ugyintezo:" +
                            "%n\tEmail::" + elem.getElementsByTagName("email").item(0).getTextContent() +
                            "%n\tTelefon:" + elem.getElementsByTagName("telefon").item(0).getTextContent() +
                            "%n\tNev:" + elem.getElementsByTagName("nev").item(0).getTextContent() + "\n");
                    System.out.printf("Nyitvatartas:" +
                            "%n\t" + elem.getElementsByTagName("tol").item(0).getTextContent() + "-tol." +
                            "%n\t" + elem.getElementsByTagName("ig").item(0).getTextContent() + "-ig." + "\n");
                }
            }

        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }

        return null;
    }
}
