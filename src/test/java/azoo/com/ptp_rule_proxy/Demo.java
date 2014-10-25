package azoo.com.ptp_rule_proxy;

import azoo.com.ptp_rule_proxy.generated.RootType;
import azoo.com.ptp_rule_proxy.xml.FileLoader;
import azoo.com.ptp_rule_proxy.xml.ReplacementBuilder;
import org.junit.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Demo {

    @Test
    public void test0() throws Exception {
        FileLoader fileLoader = new FileLoader();
        ReplacementBuilder replacementBuilder = new ReplacementBuilder();
        replacementBuilder.afterPropertiesSet();
        String xml = fileLoader.readResource("ptpxml/demo_29-07-14.xml");
        RootType rootType = replacementBuilder.toReplacement(xml);
        System.err.println("rootType: " + rootType);

        String string = replacementBuilder.toXml(rootType);
        System.err.println("string: " + string);
    }

//    @Test
//    public void testPathToUri() throws Exception {
//
////        Paths.
//        Path p = Paths.get("/udevelop/udevelop/projects/ptp_rule_proxy/target/test-classes/ptpxml/demo_29-07-14.xml");
//        System.err.println("p" + p);
//        System.err.println("p.toUri" + p.toUri());
//
//        p = Paths.get("http://www.w3schools.com/xml/note.xml");
//        System.err.println("p.toUri" + p.toUri());
//        System.err.println("p.toUri" + p.toUri());
//    }
//
//
//    @Test
//    public void test3() throws MalformedURLException {
//        String path0 = "./loh/poc.txt";
//        String path1 = "/home/tort/soft/loh/poc.txt";
//        String path2 = "http://www.w3schools.com/xml/note.xml";
//        String path3 = "file:/home/tort/soft/loh/poc.txt";
//        String path4 = "file:///home/tort/soft/loh/poc.txt";
//
//
//        try {
//            URL url = new File(path0).toURI().toURL();
////            URI uri = new URI(path1);
//            System.err.println("uri:" + url);
//            System.err.println("uri:" + url);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        try {
//            URL url = new File(path1).toURI().toURL();
////            URI uri = new URI(path1);
//            System.err.println("uri:" + url);
//            System.err.println("uri:" + url);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        try {
//            URL url = new File(path2).toURI().toURL();
////            URI uri = new URI(path1);
//            System.err.println("uri:" + url);
//            System.err.println("uri:" + url);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        try {
//            URI uri = new URI(path2);
//            System.err.println("uri:" + uri);
//            System.err.println("uri:" + uri.toURL());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        try {
//            URI uri = new URI(path3);
//            System.err.println("uri:" + uri);
//            System.err.println("uri:" + uri.toURL());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        try {
//            URI uri = new URI(path4);
//            System.err.println("uri:" + uri);
//            System.err.println("uri:" + uri.toURL());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}