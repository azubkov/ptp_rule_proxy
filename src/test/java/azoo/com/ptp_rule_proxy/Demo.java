package azoo.com.ptp_rule_proxy;

import azoo.com.ptp_rule_proxy.xml.ReplacementBuilder;
import com.google.common.io.Resources;

import azoo.com.ptp_rule_proxy.generated.RootType;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import java.net.URL;

public class Demo {

    public static void main(String[] args) throws Exception {
        JAXBContext jc = JAXBContext.newInstance(RootType.class);

        ReplacementBuilder replacementBuilder = new ReplacementBuilder();
        replacementBuilder.afterPropertiesSet();
        String xml = replacementBuilder.readResource("ptpxml/demo_29-07-14.xml");
        RootType rootType =replacementBuilder.toReplacement(xml);
        System.err.println("rootType: " + rootType);

        String string = replacementBuilder.toXml(rootType);
        System.err.println("string: " + string);
    }

}