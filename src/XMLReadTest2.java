import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.filter.Filters;
import org.jdom2.input.SAXBuilder;
import org.jdom2.xpath.XPathFactory;

import java.io.FileInputStream;
import java.util.List;

/**
 * Created by hvazquez on 1/30/2017.
 */
public class XMLReadTest2 {
  public static void main(String[] args) throws Exception {
    String xmlDocument = "C:\\Users\\hvazquez\\IdeaProjects\\TestProject\\schemas\\shiporder.xml";
    SAXBuilder saxBuilder = new SAXBuilder();
    Document document = saxBuilder.build(new FileInputStream(xmlDocument));

    XPathFactory xpathFactory = XPathFactory.instance();
    List<Element> elements = xpathFactory.compile("//shiporder", Filters.element()).evaluate(document);

    for (Element emt : elements) {
      emt.getChildren().forEach(p -> {
        System.out.println(p.getName());
      });
    }
  }
}
