package ru.nsu.fit.saraeva.lab2.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.nsu.fit.saraeva.lab2.enums.LoggerMessages;
import ru.nsu.fit.saraeva.lab2.generated.Node;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import java.text.MessageFormat;
import java.util.function.Consumer;

public class JAXBParser {
    private static final Logger logger = LogManager.getLogger(JAXBParser.class);

    public <T> void parseXML(XMLStreamReader reader, Class<T> type, Consumer<T> onParseElement) throws JAXBException, XMLStreamException {
        String tagName = getTagName(type);
        logger.info(MessageFormat.format(LoggerMessages.TAG_PARSING_PROCESS.getMessage(), tagName));
        JAXBContext context = JAXBContext.newInstance(type);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        while (reader.hasNext()) {
            int event = reader.next();
            if (event == XMLEvent.START_ELEMENT && tagName.equals(reader.getLocalName())) {
                T element = unmarshaller.unmarshal(reader, type).getValue();
                onParseElement.accept(element);
            }
        }
    }

    private <T> String getTagName(Class<T> type) {
        XmlRootElement xmlRootElement = type.getAnnotation(XmlRootElement.class);
        String tagName;

        if (xmlRootElement == null) {
            tagName = type.getName();
        }
        else {
            tagName = xmlRootElement.name();
        }
        return tagName;
    }
}
