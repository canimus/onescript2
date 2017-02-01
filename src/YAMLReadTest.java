import java.io.*;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

public class YAMLReadTest {
  public static void main(String[] args) {
    // The path of your YAML file.
    final String fileName = "C:\\Users\\hvazquez\\IdeaProjects\\TestProject\\schemas\\test.yaml";

    Yaml yaml = new Yaml();

    try {
      InputStream ios = new FileInputStream(new File(fileName));

      // Parse the YAML file and return the output as a series of Maps and Lists
      Map<String, Object> result = (Map<String, Object>) yaml.load(ios);
      System.out.println(result.toString());

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}