package azoo.com.ptp_rule_proxy;

import azoo.com.ptp_rule_proxy.generated.MessageType;
import azoo.com.ptp_rule_proxy.xml.FileLoader;
import azoo.com.ptp_rule_proxy.xml.ReplacementBuilder;
import azoo.com.ptp_rule_proxy.xml.ReplacementWrapper;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

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

    @Test(expected = IllegalArgumentException.class)
    public void test_duplicateName() throws Exception {
        String xml = fileLoader.readContent("ptpxml/demo_1.xml");
        ReplacementWrapper replacementWrapper = replacementBuilder.toReplacement(xml);
        System.err.println("replacementWrapper: " + replacementWrapper);
        String string = replacementBuilder.toXml(replacementWrapper);
        System.err.println("string: " + string);
    }

    @Test
    public void test_dollar_reference() throws Exception {
        String xml = fileLoader.readContentSafely("ptpxml/demo_2.xml");
        ReplacementWrapper replacementWrapper = replacementBuilder.toReplacement(xml);
        System.err.println("replacementWrapper: " + replacementWrapper);
        String string = replacementBuilder.toXml(replacementWrapper);
        List<MessageType> list =
                replacementWrapper.getRootType().getProcessor().getMessage();
        Assert.assertEquals(list.size(), 2);
        Assert.assertEquals(list.get(0).getRuleSequences().getRuleSequence().get(0), list.get(1).getRuleSequences().getRuleSequence().get(0));
        System.err.println("string: " + string);
    }
}