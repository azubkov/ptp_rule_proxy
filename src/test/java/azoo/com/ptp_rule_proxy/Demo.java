package azoo.com.ptp_rule_proxy;

import azoo.com.ptp_rule_proxy.xml.FileLoader;
import azoo.com.ptp_rule_proxy.xml.ReplacementBuilder;
import azoo.com.ptp_rule_proxy.xml.ReplacementWrapper;
import org.junit.BeforeClass;
import org.junit.Test;

public class Demo {
    private static FileLoader fileLoader;
    private static ReplacementBuilder replacementBuilder;

    @BeforeClass
    public static void init() throws Exception {
        fileLoader = new FileLoader();
        replacementBuilder = new ReplacementBuilder();
        replacementBuilder.setFileLoader(fileLoader);
        replacementBuilder.afterPropertiesSet();
    }

    @Test
    public void test0() throws Exception {
        String xml = fileLoader.readResource("ptpxml/demo_0.xml");
        ReplacementWrapper replacementWrapper = replacementBuilder.toReplacement(xml);
        System.err.println("replacementWrapper: " + replacementWrapper);
        String string = replacementBuilder.toXml(replacementWrapper);
        System.err.println("string: " + string);
    }

    @Test
    public void test_DollarReplacement() throws Exception {
        String xml = fileLoader.readContent("ptpxml/demo_1.xml");
        ReplacementWrapper replacementWrapper = replacementBuilder.toReplacement(xml);
        System.err.println("replacementWrapper: " + replacementWrapper);
        String string = replacementBuilder.toXml(replacementWrapper);
        System.err.println("string: " + string);
    }
}