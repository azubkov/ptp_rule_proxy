package azoo.com.ptp_rule_proxy.rules;


import com.google.common.io.Resources;
import azoo.com.ptp_rule_proxy.rules.generated.RootType;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import java.net.URL;

public class Demo {

    public static void main(String[] args) throws Exception {
        JAXBContext jc = JAXBContext.newInstance(RootType.class);
        Unmarshaller unmarshaller = jc.createUnmarshaller();

        URL url = Resources.getResource("processor_29-07-14.xml");
        JAXBElement<RootType> jaxbRoot = (JAXBElement<RootType>) unmarshaller.unmarshal(url);
        RootType rootType = jaxbRoot.getValue();
        System.err.println("object: " + rootType);

        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//        marshaller.marshal(rootType, System.out);
        /*see  azoo.com.ptp_rule_proxy.rules.generated.ObjectFactory  _Root_QNAME*/
        marshaller.marshal(new JAXBElement(new QName("", "root"), RootType.class, rootType), System.out);
    }

}