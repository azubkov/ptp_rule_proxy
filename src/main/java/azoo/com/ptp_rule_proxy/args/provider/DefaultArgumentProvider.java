package azoo.com.ptp_rule_proxy.args.provider;

import java.util.Properties;

public class DefaultArgumentProvider extends CheckedArgumentProvider {
    private Properties defaultArguments;

    public void setDefaultArguments(Properties defaultArguments) {
        this.defaultArguments = defaultArguments;
    }

    @Override
    public String getArgumentTemplate(String name) {
        return defaultArguments.getProperty(name);
    }
}
