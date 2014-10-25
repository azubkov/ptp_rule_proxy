package azoo.com.ptp_rule_proxy;

import azoo.com.ptp_rule_proxy.generated.RootType;
import azoo.com.ptp_rule_proxy.xml.FileLoader;
import azoo.com.ptp_rule_proxy.xml.ReplacementBuilder;
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
        String xml = fileLoader.readResource("ptpxml/demo_29-07-14.xml");
        RootType rootType = replacementBuilder.toReplacement(xml);
        System.err.println("rootType: " + rootType);
        String string = replacementBuilder.toXml(rootType);
        System.err.println("string: " + string);
    }

    @Test
    public void test1() throws Exception {
        String xml = fileLoader.readContent("ptpxml/demo_25-10-14.xml");
        RootType rootType = replacementBuilder.toReplacement(xml);
        System.err.println("rootType: " + rootType);
        String string = replacementBuilder.toXml(rootType);
        System.err.println("string: " + string);
    }
}