package ru.nsu.fit.saraeva.lab2.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import ru.nsu.fit.saraeva.lab2.enums.LoggerMessages;
import ru.nsu.fit.saraeva.lab2.generated.Node;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.text.MessageFormat;
import java.util.function.Consumer;

public class JAXBParser {
    private static final Logger logger = LogManager.getLogger(JAXBParser.class);

    public void parseXML(XMLStreamReader reader, Consumer<Node> onParseElement) throws JAXBException, XMLStreamException, SAXException {
        String tagName = "node";
        logger.info(MessageFormat.format(LoggerMessages.TAG_PARSING_PROCESS.getMessage(), tagName));
        JAXBContext context = JAXBContext.newInstance(Node.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        while (reader.hasNext()) {
            int event = reader.next();
            if (event == XMLEvent.START_ELEMENT && tagName.equals(reader.getLocalName())) {
                JAXBElement<Node> node = unmarshaller.unmarshal(reader, Node.class);
                Node element = node.getValue();
                onParseElement.accept(element);
            }
        }
    }
}
