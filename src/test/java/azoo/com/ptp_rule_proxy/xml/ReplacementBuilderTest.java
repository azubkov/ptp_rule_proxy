package azoo.com.ptp_rule_proxy.xml;

import org.junit.Test;

import static org.junit.Assert.*;

public class ReplacementBuilderTest {
    private ReplacementBuilder replacementBuilder = new ReplacementBuilder();

    @Test
    public void test0() throws Exception {
        String address;
        address = "http://www.example.com/index.html";
        String content;
        content = replacementBuilder.readUrl(address);
        assertNotNull(content);
        assertTrue(content.contains("Example Domain"));
    }

    @Test(expected = Exception.class)
    public void test1() throws Exception {
        String address;
        address = "http://www.example.com/index.html";
        String content;
        content = replacementBuilder.readFile(address);
        System.err.println("should never be here!");
    }

    @Test
    public void test2() throws Exception {
        String address;
        address = String.join(System.getProperty("file.separator"), System.getProperty("user.dir"), "pom.xml");
        String content;
        content = replacementBuilder.readFile(address);
        assertNotNull(content);
        assertTrue(content.contains("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"));
    }

    @Test
    public void test3() throws Exception {
        String address;
        address = "ptpxml/demo_29-07-14.xml";
        String content;
        content =  replacementBuilder.readResource(address);
        assertNotNull(content);
        assertTrue(content.contains("<?xml version=\"1.0\"?>"));
    }

}
