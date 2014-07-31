package azoo.com.ptp_rule_proxy.args;

public class DArgumentProvider implements ArgumentProvider {
    private static DArgumentProvider instance = new DArgumentProvider();

    public static DArgumentProvider getInstance() {
        return instance;
    }

    private DArgumentProvider() {
    }

    @Override
    public String getArgument(String name) {
        return System.getProperty(name);
    }
}
