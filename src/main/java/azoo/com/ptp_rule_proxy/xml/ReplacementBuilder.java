package azoo.com.ptp_rule_proxy.xml;

import azoo.com.ptp_rule_proxy.generated.RootType;
import com.google.common.base.Charsets;
import com.google.common.base.Objects;
import com.google.common.io.Resources;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;

import javax.xml.bind.*;
import javax.xml.namespace.QName;
import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class ReplacementBuilder implements InitializingBean {
    private static final Logger LOGGER = Logger.getLogger(ReplacementBuilder.class);

    private RootType nullObject;
    private JAXBContext jaxBContext;

    public RootType toReplacement(String xml) {
        RootType rootType = null;
        if(!StringUtils.isBlank(xml)){
            rootType = buildFromXml(xml);
        }
        return Objects.firstNonNull(rootType, nullObject);
    }

    private RootType buildFromXml(String string) {
        try {
            Unmarshaller unmarshaller = jaxBContext.createUnmarshaller();
            JAXBElement<RootType> jaxbRoot = (JAXBElement<RootType>) unmarshaller.unmarshal(stringToInputStream(string));
            RootType rootType = jaxbRoot.getValue();
            return rootType;
        } catch (Exception e) {
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
        jaxBContext = JAXBContext.newInstance(RootType.class);
        prepareNullObject();
    }

    private void prepareNullObject() {
        String content = readResource("ptpxml/nullObject.xml");
        nullObject = buildFromXml(content);
        if (nullObject == null) {
            throw new RuntimeException("cannot read null object sample");
        }
    }

    public String toXml(RootType rootType) {
        try {
            Marshaller marshaller = jaxBContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        /*see  azoo.com.ptp_rule_proxy.rules.generated.ObjectFactory  _Root_QNAME*/
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            marshaller.marshal(new JAXBElement(new QName("", "root"), RootType.class, rootType), byteArrayOutputStream);

            String string = byteArrayOutputStream.toString();
            return string;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws Exception {
        ReplacementBuilder replacementBuilder = new ReplacementBuilder();
        replacementBuilder.afterPropertiesSet();
        System.err.println("replacementBuilder.nullObject:\n" + replacementBuilder.nullObject);
        System.err.println("replacementBuilder.nullObject xml:\n" + replacementBuilder.toXml(replacementBuilder.nullObject));
    }
}
