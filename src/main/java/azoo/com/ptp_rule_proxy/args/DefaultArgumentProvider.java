package azoo.com.ptp_rule_proxy.args;

import java.util.Properties;

public class DefaultArgumentProvider implements ArgumentProvider {
    private Properties defaultArguments;

    public void setDefaultArguments(Properties defaultArguments) {
        this.defaultArguments = defaultArguments;
    }

    @Override
    public String getArgument(String name) {
        return defaultArguments.getProperty(name);
    }
}
