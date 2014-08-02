package azoo.com.ptp_rule_proxy.xml;

import azoo.com.ptp_rule_proxy.generated.RootType;
import com.google.common.base.Charsets;
import com.google.common.base.Objects;
import com.google.common.io.Resources;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class ReplacementBuilder implements InitializingBean {
    private static final Logger LOGGER = Logger.getLogger(ReplacementBuilder.class);

    private RootType nullObject;

    private RootType toReplacement(String xml) {
        RootType rootType = buildFromXml(xml);
        return Objects.firstNonNull(rootType, nullObject);
    }

    private RootType buildFromXml(String string) {
        try {
            JAXBContext jc = JAXBContext.newInstance(RootType.class);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            JAXBElement<RootType> jaxbRoot = (JAXBElement<RootType>) unmarshaller.unmarshal(stringToInputStream(string));
            RootType rootType = jaxbRoot.getValue();
            return rootType;
        } catch (JAXBException e) {
            LOGGER.error(e, e);
            return null;
        }
    }

    private String readUrl(String string) {
        try {
            URL url = new URL(string);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String message = org.apache.commons.io.IOUtils.toString(in);
            return message;
        } catch (Exception e) {
            LOGGER.error(e, e);
            return null;
        }
    }

    public static String readResource(String path) {
        try {
            URL url = Resources.getResource(path);
            String content = Resources.toString(url, Charsets.UTF_8);
            return content;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private InputStream stringToInputStream(String string) {
        InputStream stream = new ByteArrayInputStream(string.getBytes(StandardCharsets.UTF_8));
        return stream;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        prepareNullObject();
    }

    private void prepareNullObject() {
        String content = readResource("ptpxml/nullObject.xml");
        nullObject = buildFromXml(content);
        if (nullObject == null) {
            throw new RuntimeException("cannot read null object sample");
        }
    }

    public static void main(String[] args) {
        ReplacementBuilder replacementBuilder = new ReplacementBuilder();
        replacementBuilder.prepareNullObject();
        System.err.println("replacementBuilder.nullObject" + replacementBuilder.nullObject);
    }
}
