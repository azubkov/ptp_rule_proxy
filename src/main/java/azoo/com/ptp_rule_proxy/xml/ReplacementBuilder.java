package azoo.com.ptp_rule_proxy.xml;

import azoo.com.ptp_rule_proxy.generated.RootType;
import org.apache.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class ReplacementBuilder {
    private static final Logger LOGGER = Logger.getLogger(ReplacementBuilder.class);

    public RootType buildFromXml(String string) {
        try {
            JAXBContext jc = JAXBContext.newInstance(RootType.class);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            JAXBElement<RootType> jaxbRoot = (JAXBElement<RootType>) unmarshaller.unmarshal(stringToInputStream(string));
            RootType rootType = jaxbRoot.getValue();
            return rootType;
        } catch (JAXBException e) {
            LOGGER.error(e, e);
            return nullObject();
        }
    }


    private String readUrl(String urlAddress) {
        try {
            URL url = new URL(urlAddress);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String message = org.apache.commons.io.IOUtils.toString(in);
            return message;
        } catch (Exception e) {
            LOGGER.error(e, e);
            return null;
        }
    }

    private InputStream stringToInputStream(String string) {
        InputStream stream = new ByteArrayInputStream(string.getBytes(StandardCharsets.UTF_8));
        return stream;
    }

    private RootType nullObject() {
        return null;
    }
}
