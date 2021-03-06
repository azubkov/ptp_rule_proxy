package azoo.com.ptp_rule_proxy.xml;

import org.junit.Test;

import static org.junit.Assert.*;

public class FileLoaderTest {
    private FileLoader fileLoader = new FileLoader();

    @Test
    public void test0() throws Exception {
        String address;
        address = "http://www.example.com/index.html";
        String content;
        content = fileLoader.readUrl(address);
        assertNotNull(content);
        assertTrue(content.contains("Example Domain"));
    }

    @Test(expected = Exception.class)
    public void test1() throws Exception {
        String address;
        address = "http://www.example.com/index.html";
        String content;
        content = fileLoader.readFile(address);
        System.err.println("should never be here!");
    }

    @Test
    public void test2() throws Exception {
        String address;
        address = String.join(System.getProperty("file.separator"), System.getProperty("user.dir"), "pom.xml");
        String content;
        content = fileLoader.readFile(address);
        assertNotNull(content);
        assertTrue(content.contains("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"));
    }

    @Test
    public void test3() throws Exception {
        String address;
        address = "ptpxml/demo_0.xml";
        String content;
        content =  fileLoader.readResource(address);
        assertNotNull(content);
        assertTrue(content.contains("<?xml version=\"1.0\"?>"));
    }

    @Test
    public void test_readContent_0() throws Exception {
        String address;
        address = "http://www.example.com/index.html";
        String content;
        content = fileLoader.readContent(address);
        assertNotNull(content);
        assertTrue(content.contains("Example Domain"));
    }

    @Test(expected = Exception.class)
    public void test_readContent_1() throws Exception {
        String address;
        address = "AAhttp://www.example.com/index.html";
        String content;
        content = fileLoader.readContent(address);
        System.err.println("should never be here!");
    }

    @Test
    public void test_readContent_2() throws Exception {
        String address;
        address = String.join(System.getProperty("file.separator"), System.getProperty("user.dir"), "pom.xml");
        String content;
        content = fileLoader.readContent(address);
        assertNotNull(content);
        assertTrue(content.contains("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"));
    }

    @Test
    public void test_readContent_3() throws Exception {
        String address;
        address = "ptpxml/demo_0.xml";
        String content;
        content =  fileLoader.readContent(address);
        assertNotNull(content);
        assertTrue(content.contains("<?xml version=\"1.0\"?>"));
    }

    @Test
    public void test_readContent_4() throws Exception {
        String address;
        address = "AAAptpxml/demo_0.xml";
        String content;
        content =  fileLoader.readContentSafely(address);
        assertNull(content);
    }
}
