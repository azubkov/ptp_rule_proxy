package azoo.com.ptp_rule_proxy;

public class ExampleMain {

    public static void main(String[] args) throws Exception {
        System.setProperty("local.port", "1235");
        System.setProperty("remote.port", "80");
        System.setProperty("remote.host", "example.com");
        System.setProperty("replacement.config.file", String.join("", System.getProperty("user.dir"), "/target/test-classes/ptpxml/demo_29-07-14.xml"));

        PTPProxyMain.main(args);
        System.out.println("now commands are available:");
        System.out.println("wget localhost:1235");
        System.out.println("firefox localhost:1235");
    }
}
