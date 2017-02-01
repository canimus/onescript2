import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Text;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by hvazquez on 1/30/2017.
 */
public class XMLWriteTest {
  public static void main(String[] args) throws Exception {

    // Define expression
    String xmlDocument = "C:\\Users\\hvazquez\\IdeaProjects\\TestProject\\schemas\\shiporder.xml";
    SAXBuilder saxBuilder = new SAXBuilder();
    Document document = saxBuilder.build(new FileInputStream(xmlDocument));

    Element item = new Element("item");

    HashMap<String, String> content = new LinkedHashMap<String, String>();
    content.put("title", "Canimus");
    content.put("note", "Special Edition");
    content.put("quantity", "1");
    content.put("price", "11.11");

    content.forEach((k,v) -> {
      Element t = new Element(k);
      item.addContent(t.addContent(new Text(v)));
    });

    document.getRootElement().addContent(item);

    XMLOutputter xout = new XMLOutputter(Format.getPrettyFormat());
    xout.output(document, System.out);
  }
}