import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by hvazquez on 1/30/2017.
 */
public class XMLReadTest {
  public static void main(String[] args) throws Exception {
    String xmlDocument = "C:\\Users\\hvazquez\\IdeaProjects\\TestProject\\schemas\\shiporder.xml";
    // Reading Example
    DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
    Document doc = docBuilder.parse(xmlDocument);

    Node order = doc.getFirstChild();
    NamedNodeMap earthAttributes = order.getAttributes();

    System.out.println(earthAttributes.getNamedItem("orderid").getNodeValue());

  }
}
