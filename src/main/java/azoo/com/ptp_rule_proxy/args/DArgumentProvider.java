package azoo.com.ptp_rule_proxy.args;

import org.apache.commons.lang3.StringUtils;

public class DArgumentProvider implements ArgumentProvider {
    private static DArgumentProvider instance = new DArgumentProvider();
    private ArgumentProvider defaultArgumentProvider;

    private DArgumentProvider() {
    }

    public void setDefaultArgumentProvider(ArgumentProvider defaultArgumentProvider) {
        this.defaultArgumentProvider = defaultArgumentProvider;
    }

    @Override
    public String getArgument(String name) {
        String value = System.getProperty(name);
        if (StringUtils.isBlank(value)) {
            value = defaultArgumentProvider.getArgument(name);
        }
        return value;
    }

    public static DArgumentProvider getInstance() {
        return instance;
    }
}
