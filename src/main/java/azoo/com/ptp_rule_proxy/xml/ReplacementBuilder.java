package azoo.com.ptp_rule_proxy.xml;

import azoo.com.ptp_rule_proxy.generated.ReplacementSequenceType;
import azoo.com.ptp_rule_proxy.generated.RootType;
import azoo.com.ptp_rule_proxy.generated.RuleSequenceType;
import com.google.common.base.Objects;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.InitializingBean;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ReplacementBuilder implements InitializingBean {
    private static final Logger LOGGER = Logger.getLogger(ReplacementBuilder.class);

    private ReplacementWrapper nullObject;
    private JAXBContext jaxBContext;
    private FileLoader fileLoader;

    public void setFileLoader(FileLoader fileLoader) {
        this.fileLoader = fileLoader;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        jaxBContext = JAXBContext.newInstance(RootType.class);
        prepareNullObject();
    }

    private void prepareNullObject() {
        String content = fileLoader.readResource("ptpxml/nullObject.xml");
        RootType rootType = buildFromXml(content);
        nullObject = toWrapper(rootType);
        if (nullObject == null) {
            throw new RuntimeException("cannot create null object sample");
        }
    }

    private ReplacementWrapper toWrapper(RootType rootType) {
        ReplacementWrapper replacementWrapper = new ReplacementWrapper();
        replacementWrapper.setRootType(rootType);
        List<ReplacementSequenceType> list = Linearizer.ReplacementSequenceTypeLinearizer.getInstance().linearize(rootType);
//        Linearizer.Methods.toMap(list, )
//        replacementWrapper.
        List<RuleSequenceType> list2 = Linearizer.RuleSequenceTypeLinearizer.getInstance().linearize(rootType);

//        List<RuleSequenceType> ruleSequenceList= toLinearRuleSequence(rootType);
//        toLinearReplacementSequence(rootType);
        return replacementWrapper;
    }

    private void toLinear(RootType rootType) {
        rootType.getRuleSequence();
        rootType.getReplacementSequence();

    }

    public ReplacementWrapper toReplacement(@Nullable String xml) {
        ReplacementWrapper replacementWrapper = null;
        if (!StringUtils.isBlank(xml)) {
            RootType rootType = buildFromXml(xml);
            replacementWrapper = toWrapper(rootType);
        }
        return Objects.firstNonNull(replacementWrapper, nullObject);
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

    private InputStream stringToInputStream(String string) {
        InputStream stream = new ByteArrayInputStream(string.getBytes(StandardCharsets.UTF_8));
        return stream;
    }

    @NotNull
    public String toXml(ReplacementWrapper replacementWrapper) {
        return toXml(replacementWrapper.getRootType());
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
}
