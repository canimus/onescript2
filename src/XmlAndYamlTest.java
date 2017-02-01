import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;

/**
 * Created by hvazquez on 1/30/2017.
 */
public class XmlAndYamlTest {
  public static void main(String[] args) {
    final String fileName = "C:\\Users\\hvazquez\\IdeaProjects\\TestProject\\schemas\\test.yaml";

    Yaml yaml = new Yaml();

    try {
      InputStream ios = new FileInputStream(new File(fileName));

      // Parse the YAML file and return the output as a series of Maps and Lists
      Map<String, Object> result = (Map<String, Object>) yaml.load(ios);
      System.out.println(result.toString());

      String xmlDocument = "C:\\Users\\hvazquez\\IdeaProjects\\TestProject\\schemas\\shiporder.xml";
      SAXBuilder saxBuilder = new SAXBuilder();
      Document document = saxBuilder.build(new FileInputStream(xmlDocument));

      result.forEach( (k,v) -> {
        Element root = new Element(k.toString());
        if (v.getClass().isInstance(Map.class)) {
          System.out.println(v);
        }
      } );

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
