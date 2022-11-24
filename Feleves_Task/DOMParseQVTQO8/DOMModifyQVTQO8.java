package Feleves_Task.DOMParseQVTQO8;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.xml.sax.SAXException;

public class DOMModifyQVTQO8 {

    private static final String FORMAT_XSLT = "C:/Users/Z004KHJR/source/repos/Egyetem/4.ev1.felev/Adatkezelés XML-ben/QVTQO8_XML.Gyak/QVTQO8_XML.Gyak/Feleves_Task/DOMParseQVTQO8/styling.xslt";

    public static void main(String argv[]) throws SAXException,
            IOException, ParserConfigurationException {

        File xmlFile = new File(
                "C:/Users/Z004KHJR/source/repos/Egyetem/4.ev1.felev/Adatkezelés XML-ben/QVTQO8_XML.Gyak/FelevesTask/XMLQVTQO8.xml");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);

        //Második ugyfel azonosíto attributomának modositasa
        NodeList ugyfelekList = doc.getElementsByTagName("Ugyfelek"); 
        Node ugyfel = ugyfelekList.item(1);

        ugyfel.getAttributes().getNamedItem("u_adoazonosito").setTextContent("ua2");
        
        //---------

        //Konyvelo iroda minden utca element ertekenek modositasa
        NodeList kirodaList = doc.getElementsByTagName("Konyveloiroda");
        modifyElement(kirodaList, "utca", "Abla");

        //Modositott elemek Kiirasa konzolra
        for (int i = 0; i < kirodaList.getLength(); i++) {

            Node nNode = kirodaList.item(i);

            System.out.println("\nElement: " + nNode.getNodeName());

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                Element elem = (Element) nNode;

                String uid = elem.getAttribute("k_adoszam");

                Node node2 = elem.getElementsByTagName("ugyvezeto").item(0);
                String uvezeto = node2.getTextContent();

                System.out.printf("Adoszam: %s%n", uid);
                System.out.printf("Szekhely:" +
                        "%n\tIrszam:" + elem.getElementsByTagName("irszam").item(0).getTextContent() +
                        "%n\tHazszam:" + elem.getElementsByTagName("hazszam").item(0).getTextContent() +
                        "%n\tUtca:" + elem.getElementsByTagName("utca").item(0).getTextContent() +
                        "%n\tVaros:" + elem.getElementsByTagName("varos").item(0).getTextContent());
                System.out.printf("%nUgyvezeto: %s%n \n", uvezeto);
            }
        }

        //---------


        //Uj gyermek element hozzáadása a 3.Ugyfel elementhez
        Node ugyfelTel = ugyfelekList.item(2);

        Element telefonszam = doc.createElement("telefonszam");
        telefonszam.appendChild(doc.createTextNode("06205672543"));
        ugyfelTel.appendChild(telefonszam);

        //Modositott Ugyfel elem kiirasa
        System.out.println("Ugyfel:" + ugyfelekList.item(2).getTextContent() + "\n");

        //---------

        //Nav element ugyintéző elementjének gyerek 'email' elementjének átnevezése
        NodeList navList = doc.getElementsByTagName("Nav");
        
        for (int i = 0; i < navList.getLength(); i++) {
            Node nav = navList.item(i);

            Element elem = (Element) nav;

            //Nav ugyintezo elementjenek lekerdezese
            Node childs = elem.getElementsByTagName("ugyintezo").item(0);

            //az ugyintezo element gyerek elementjeinek lekerdezese
            NodeList childNodes = childs.getChildNodes();

            for (int j = 0; j < childNodes.getLength(); j++) {

                Node item = childNodes.item(j);

                if (item.getNodeType() == Node.ELEMENT_NODE) {

                    if ("email".equalsIgnoreCase(item.getNodeName())) {

                        String email = item.getTextContent();

                        //email element torlése
                        childs.removeChild(item);

                        //Uj xml element hozzáadása
                        Element n = doc.createElement("uemail");
                        n.appendChild(doc.createTextNode(email));

                        childs.appendChild(n);

                    }
                }
            }
                //Modositott Nav elementek kiirasa
                Element elem1 = (Element) nav;

                String uid = elem1.getAttribute("n_adoszam");

                Node node1 = elem.getElementsByTagName("email").item(0);
                String email = node1.getTextContent();

             
                System.out.printf("Nav: \n");
                System.out.printf("Adoszam: %s%n", uid);
                System.out.printf("Email: %s%n", email);
                System.out.printf("Ugyintezo:" +
                        "%n\tUEmail::" + elem1.getElementsByTagName("uemail").item(0).getTextContent() +
                        "%n\tTelefon:" + elem1.getElementsByTagName("telefon").item(0).getTextContent() +
                        "%n\tNev:" + elem1.getElementsByTagName("nev").item(0).getTextContent() + "\n");
                System.out.printf("Nyitvatartas:" +
                        "%n\t" + elem1.getElementsByTagName("tol").item(0).getTextContent() + "-tol." +
                        "%n\t" + elem1.getElementsByTagName("ig").item(0).getTextContent() + "-ig." + "\n\n");
        }

        //Oep email elementjének törlése
        NodeList oepList = doc.getElementsByTagName("Oep");
        
        for (int i = 0; i < oepList.getLength(); i++) {
            Node oep = oepList.item(i);

            NodeList childNodes = oep.getChildNodes();

            for (int j = 0; j < childNodes.getLength(); j++) {

                Node item = childNodes.item(j);

                if (item.getNodeType() == Node.ELEMENT_NODE) {

                    if ("email".equalsIgnoreCase(item.getNodeName())) {  

                        //email element torlése
                        oep.removeChild(item);

                    }
                }
            }

            Element elem = (Element) oep;

                String uid = elem.getAttribute("o_adoszam");

                System.out.printf("Oep: \n");
                System.out.printf("Adoszam: %s%n", uid);
                System.out.printf("Ugyintezo:" +
                        "%n\tEmail::" + elem.getElementsByTagName("email").item(0).getTextContent() +
                        "%n\tTelefon:" + elem.getElementsByTagName("telefon").item(0).getTextContent() +
                        "%n\tNev:" + elem.getElementsByTagName("nev").item(0).getTextContent() + "\n");
                System.out.printf("Nyitvatartas:" +
                        "%n\t" + elem.getElementsByTagName("tol").item(0).getTextContent() + "-tol." +
                        "%n\t" + elem.getElementsByTagName("ig").item(0).getTextContent() + "-ig." + "\n\n");
        }



        try (FileOutputStream output = new FileOutputStream(
                "C:/Users/Z004KHJR/source/repos/Egyetem/4.ev1.felev/Adatkezelés XML-ben/QVTQO8_XML.Gyak/QVTQO8_XML.Gyak/modified.xml")) {
            writeXml(doc, output);
        } catch (TransformerException e) {
            e.printStackTrace();
        }

    }

    //Element tag értékek modositasahoz metódus
    public static void modifyElement(NodeList nodeList, String tagName, String newValue) {
        if (nodeList != null) {
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                Element elem = (Element) node;
                try {
                    elem.getElementsByTagName(tagName).item(0).setTextContent(newValue);
                } catch (DOMException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //Modositott xml fajl megirása
    private static void writeXml(Document doc, OutputStream output)
            throws TransformerException, UnsupportedEncodingException {

        TransformerFactory transformerFactory = TransformerFactory.newInstance();

        Transformer transformer = transformerFactory.newTransformer(
                new StreamSource(new File(FORMAT_XSLT)));

        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.STANDALONE, "no");

        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(output);

        transformer.transform(source, result);

    }
}