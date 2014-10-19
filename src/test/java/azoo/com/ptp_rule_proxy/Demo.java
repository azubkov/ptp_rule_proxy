package azoo.com.ptp_rule_proxy;

import azoo.com.ptp_rule_proxy.generated.RootType;
import azoo.com.ptp_rule_proxy.xml.ReplacementBuilder;
import org.junit.Test;

public class Demo {

    @Test
    public void test0() throws Exception {
        ReplacementBuilder replacementBuilder = new ReplacementBuilder();
        replacementBuilder.afterPropertiesSet();
        String xml = replacementBuilder.readResource("ptpxml/demo_29-07-14.xml");
        RootType rootType =replacementBuilder.toReplacement(xml);
        System.err.println("rootType: " + rootType);

        String string = replacementBuilder.toXml(rootType);
        System.err.println("string: " + string);
    }
}