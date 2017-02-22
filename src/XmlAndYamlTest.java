import com.esotericsoftware.yamlbeans.YamlReader;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Text;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by hvazquez on 1/30/2017.
 */
public class XmlAndYamlTest {
  static Element finalRoot;

  public static void main(String args[]) throws Exception {
    YamlReader reader = new YamlReader(new FileReader("C:\\Users\\hvazquez\\IdeaProjects\\TestProject\\schemas\\test.yaml"));

    while (true) {
      Map contact = (Map) reader.read();
      if (contact != null) {
        //Element root = new Element(contact.keySet().iterator().next().toString());
        productize(contact, null);
      }

      if (contact == null) break;
      System.out.println(contact);
      XMLOutputter xout = new XMLOutputter(Format.getPrettyFormat());
      xout.output(finalRoot, System.out);
    }
  }

  private static Element productize(Map e, Element root) {

    e.forEach( (k,v) -> {
      if (v instanceof Map) {
        Element tempElement = new Element(k.toString());
        if (root != null) {
          root.addContent(tempElement);
        }
        productize((Map)v, tempElement);
      } else {
        //System.out.println(k + ":" + v);
        Element t = new Element(k.toString());
        t.addContent(new Text(v.toString()));
        root.addContent(t);
      }
    });

    if (root != null) {
      finalRoot = root;
    }
    return root;
  }

  public static void main2(String[] args) {
    final String fileName = "C:\\Users\\hvazquez\\IdeaProjects\\TestProject\\schemas\\test.yaml";

    Yaml yaml = new Yaml();

    try {
      InputStream ios = new FileInputStream(new File(fileName));

      // Parse the YAML file and return the output as a series of Maps and Lists
      Map<String, Object> result = (Map<String, Object>) yaml.load(ios);

      String xmlDocument = "C:\\Users\\hvazquez\\IdeaProjects\\TestProject\\schemas\\shiporder.xml";
      SAXBuilder saxBuilder = new SAXBuilder();
      Document document = saxBuilder.build(new FileInputStream(xmlDocument));

      XMLOutputter xout = new XMLOutputter(Format.getPrettyFormat());
      result.forEach( (k,v) -> {
        Element root = new Element(k.toString());

        if (v instanceof Map) {

        }

        try {
          xout.output(root, System.out);
        } catch (IOException e) {
          e.printStackTrace();
        }

      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
