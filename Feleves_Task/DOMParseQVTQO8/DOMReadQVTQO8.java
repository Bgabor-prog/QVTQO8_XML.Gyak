import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class DOMReadQVTQO8 {

    public static void main(String argv[]) throws SAXException,
            IOException, ParserConfigurationException {

        File xmlFile = new File(
                "C:/Users/Z004KHJR/source/repos/Egyetem/4.ev1.felev/Adatkezelés XML-ben/QVTQO8_XML.Gyak/FelevesTask/XMLQVTQO8.xml");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);
        

        doc.getDocumentElement().normalize();

        System.out.println("Root element: " + doc.getDocumentElement().getNodeName());

        NodeList kirodaList = doc.getElementsByTagName("Konyveloiroda");

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
                System.out.printf("%nUgyvezeto: %s%n", uvezeto);

                //Elementek es ertekeik kiirasa a fajlba
                try(FileWriter fw = new FileWriter("text.txt", true);){

                // fajlba iras megkezdése
                fw.write("Konyveloiroda:------ \n");
                fw.write("Adoszam: " + uid + "\n");
                fw.write("Szekhely: \n" +
                        "\tIrszam:" + elem.getElementsByTagName("irszam").item(0).getTextContent() + "\n" +
                        "\tHazszam:" + elem.getElementsByTagName("hazszam").item(0).getTextContent() + "\n" + 
                        "\tUtca:" + elem.getElementsByTagName("utca").item(0).getTextContent() + "\n" +
                        "\tVaros:" + elem.getElementsByTagName("varos").item(0).getTextContent()+ "\n");
                fw.write("Ugyvezeto: " + uvezeto  + "\n\n");
                }catch(IOException e)
                {
                    e.printStackTrace();
                }
            }
        }

        // Cegek kiiratasa
        NodeList cegList = doc.getElementsByTagName("Ceg");

        for (int i = 0; i < cegList.getLength(); i++) {

            Node nNode = cegList.item(i);

            System.out.println("\nElement: " + nNode.getNodeName());

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                Element elem = (Element) nNode;

                String nev = elem.getAttribute("nev");

                Node node1 = elem.getElementsByTagName("email").item(0);
                String email = node1.getTextContent();

                Node node2 = elem.getElementsByTagName("szekhely").item(0);
                String szekhely = node2.getTextContent();

                Node node3 = elem.getElementsByTagName("alapitas_eve").item(0);
                String alapitas_eve = node3.getTextContent();

                System.out.printf("Nev: %s%n", nev);
                System.out.printf("Email: %s%n", email);
                System.out.printf("Szekhely: %s%n", szekhely);
                System.out.printf("Alapitas: %s%n", alapitas_eve);

                //Elementek es ertekeik kiirasa a fajlba
                try(FileWriter fw = new FileWriter("text.txt", true);){

                // fajlba iras megkezdése
                fw.write("Ceg:------ \n");
                fw.write("Nev: " + nev + "\n");
                fw.write("Email: \n" + email + "\n");
                fw.write("Szekhely: \n" + szekhely + "\n");
                fw.write("Alapitas: " + alapitas_eve  + "\n\n");
                }catch(IOException e)
                {
                    e.printStackTrace();
                }
            }
        }

        // Ugyfelek kiiratasa
        NodeList ugyfelekList = doc.getElementsByTagName("Ugyfelek");

        for (int i = 0; i < ugyfelekList.getLength(); i++) {

            Node nNode = ugyfelekList.item(i);

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
                        "%n\tVaros:" + elem.getElementsByTagName("varos").item(0).getTextContent() + "\n");

                //Elementek es ertekeik kiirasa a fajlba
                try(FileWriter fw = new FileWriter("text.txt", true);){

                    // fajlba iras megkezdése
                    fw.write("Ugyfelek:------ \n");
                    fw.write("Adoszam:" + uid + "\n");
                    fw.write("Nev: " + nev + "\n");
                    fw.write("Email: \n" + email + "\n");
                    fw.write("Lakcim:" +
                        "\tIrszam:" + elem.getElementsByTagName("irszam").item(0).getTextContent()+ "\n" +
                        "\tHazszam:" + elem.getElementsByTagName("hazszam").item(0).getTextContent()+ "\n" +
                        "\tUtca:" + elem.getElementsByTagName("utca").item(0).getTextContent() + "\n" +
                        "\tVaros:" + elem.getElementsByTagName("varos").item(0).getTextContent() + "\n\n");
                }catch(IOException e)
                {
                        e.printStackTrace();
                }
            }
        }

        // Nav kiiratasa
        NodeList navList = doc.getElementsByTagName("Nav");

        for (int i = 0; i < navList.getLength(); i++) {

            Node nNode = navList.item(i);

            System.out.println("\nElement: " + nNode.getNodeName());

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                Element elem = (Element) nNode;

                String uid = elem.getAttribute("n_adoszam");

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

                //Elementek es ertekeik kiirasa a fajlba
                try(FileWriter fw = new FileWriter("text.txt", true);){

                    // fajlba iras megkezdése
                    fw.write("Nav:------ \n");
                    fw.write("Adoszam:" + uid + "\n");
                    fw.write("Email: \n" + email + "\n");
                    fw.write("Ugyintezo: \n" +
                        "\tIrszam:" + elem.getElementsByTagName("email").item(0).getTextContent()+ "\n" +
                        "\tHazszam:" + elem.getElementsByTagName("telefon").item(0).getTextContent()+ "\n" +
                        "\tUtca:" + elem.getElementsByTagName("nev").item(0).getTextContent() + "\n");
                    fw.write("Nyitvatartas: \n" +
                        "\t" + elem.getElementsByTagName("tol").item(0).getTextContent()+ "-tol" + "\n" +
                        "\t" + elem.getElementsByTagName("ig").item(0).getTextContent()+ "-ig" + "\n\n");    
                }catch(IOException e)
                {
                        e.printStackTrace();
                }
            }
        }

        // Oep kiiratasa
        NodeList oepList = doc.getElementsByTagName("Oep");

        for (int i = 0; i < oepList.getLength(); i++) {

            Node nNode = oepList.item(i);

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

                //Elementek es ertekeik kiirasa a fajlba
                try(FileWriter fw = new FileWriter("text.txt", true);){

                    // fajlba iras megkezdése
                    fw.write("Oep:------ \n");
                    fw.write("Adoszam:" + uid + "\n");
                    fw.write("Email: \n" + email + "\n");
                    fw.write("Ugyintezo: \n" +
                        "\tIrszam:" + elem.getElementsByTagName("email").item(0).getTextContent()+ "\n" +
                        "\tHazszam:" + elem.getElementsByTagName("telefon").item(0).getTextContent()+ "\n" +
                        "\tUtca:" + elem.getElementsByTagName("nev").item(0).getTextContent() + "\n");
                    fw.write("Nyitvatartas: \n" +
                        "\t" + elem.getElementsByTagName("tol").item(0).getTextContent()+ "-tol" + "\n" +
                        "\t" + elem.getElementsByTagName("ig").item(0).getTextContent()+ "-ig" + "\n\n");    
                }catch(IOException e)
                {
                        e.printStackTrace();
                }
            }
        }

        // Onkormanyzat kiiratasa
        NodeList onkormanyzatList = doc.getElementsByTagName("Onkormanyzat");

        for (int i = 0; i < onkormanyzatList.getLength(); i++) {

            Node nNode = onkormanyzatList.item(i);

            System.out.println("\nElement: " + nNode.getNodeName());

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                Element elem = (Element) nNode;

                String uid = elem.getAttribute("on_adoszam");

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

                //Elementek es ertekeik kiirasa a fajlba
                try(FileWriter fw = new FileWriter("text.txt", true);){

                    // fajlba iras megkezdése
                    fw.write("Onkormanyzat:------ \n");
                    fw.write("Adoszam:" + uid + "\n");
                    fw.write("Email: \n" + email + "\n");
                    fw.write("Ugyintezo: \n" +
                        "\tIrszam:" + elem.getElementsByTagName("email").item(0).getTextContent()+ "\n" +
                        "\tHazszam:" + elem.getElementsByTagName("telefon").item(0).getTextContent()+ "\n" +
                        "\tUtca:" + elem.getElementsByTagName("nev").item(0).getTextContent() + "\n");
                    fw.write("Nyitvatartas: \n" +
                        "\t" + elem.getElementsByTagName("tol").item(0).getTextContent()+ "-tol" + "\n" +
                        "\t" + elem.getElementsByTagName("ig").item(0).getTextContent()+ "-ig" + "\n\n");    
                }catch(IOException e)
                {
                        e.printStackTrace();
                }
            }
        }

        // Konyvelesi anyagok kiiratasa
        NodeList konyvanyagokList = doc.getElementsByTagName("Konyv_Onk");

        for (int i = 0; i < konyvanyagokList.getLength(); i++) {

            Node nNode = konyvanyagokList.item(i);

            System.out.println("\nElement: " + nNode.getNodeName());

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                Element elem = (Element) nNode;

                Node node1 = elem.getElementsByTagName("dok.id").item(0);
                String dokId = node1.getTextContent();

                Node node2 = elem.getElementsByTagName("dok.szam").item(0);
                String dokSzam = node2.getTextContent();

                System.out.printf("Dokumentum Id: %s%n", dokId);
                System.out.printf("Dokumentumok Szama: %s%n", dokSzam);

                //Elementek es ertekeik kiirasa a fajlba
                try(FileWriter fw = new FileWriter("text.txt", true);){

                    // fajlba iras megkezdése
                    fw.write("Konyvelesi Anyag:-- \n");
                    fw.write("Dokumentum Id:" + dokId + "\n");
                    fw.write("Dokumentumok szama:" + dokSzam + "\n\n"); 
                }catch(IOException e)
                {
                        e.printStackTrace();
                }
            }
        }
    }
}
