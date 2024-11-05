package core.xml;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class XmlParser {

    private static final Logger LOGGER = Logger.getLogger("XmlParser");


    public static <T> String convertObjectToXml(T type) {
        StringWriter stringWriter = new StringWriter();
        try {
            JAXBContext context = JAXBContext.newInstance(type.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(type, stringWriter);
        } catch (JAXBException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        }
        return stringWriter.toString();
    }


    public static <T> T convertXmlToObject(Class<T> type, String xml) {
        T unmarshaledObject = null;
        try {
            JAXBContext context = JAXBContext.newInstance(type);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            unmarshaledObject = (T) unmarshaller.unmarshal(new StringReader(xml));
        } catch (JAXBException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        }
        return unmarshaledObject;
    }


}
