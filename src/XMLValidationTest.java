import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.input.sax.XMLReaderJDOMFactory;
import org.jdom2.input.sax.XMLReaderSchemaFactory;
import org.jdom2.input.sax.XMLReaders;

import javax.xml.XMLConstants;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;

/**
 * Created by hvazquez on 1/30/2017.
 */
public class XMLValidationTest {
  public static void main(String[] args) throws Exception {
    String xmlDocument = "C:\\Users\\hvazquez\\IdeaProjects\\TestProject\\schemas\\shiporder.xml";
    String schemaFile = "C:\\Users\\hvazquez\\IdeaProjects\\TestProject\\schemas\\shiporder.xsd";

    // Define a schema factory and a schema
    SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
    Schema schema = schemaFactory.newSchema(new File(schemaFile));


    // create an XMLReaderJDOMFactory by passing the schema
    XMLReaderJDOMFactory factory = new XMLReaderSchemaFactory(schema);
    // create a SAXBuilder using the XMLReaderJDOMFactory
    SAXBuilder sb = new SAXBuilder(factory);
    Document doc = sb.build(new File(xmlDocument));
    System.out.println(doc.getRootElement().getName());

  }
}
