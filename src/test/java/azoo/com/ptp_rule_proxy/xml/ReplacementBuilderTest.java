package azoo.com.ptp_rule_proxy.xml;

import org.junit.Test;

public class ReplacementBuilderTest {
    private ReplacementBuilder replacementBuilder = new ReplacementBuilder();

    @Test
    public void test0() {
        String address;
        address = "http://www.example.com/index.html";
        String content;
        content = replacementBuilder.readUrl(address);
        System.err.println("content: "+content);
        System.err.println("content: "+content);
    }
}
