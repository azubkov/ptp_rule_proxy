package azoo.com.ptp_rule_proxy.args;

import org.apache.commons.lang3.StringUtils;

public class DArgumentProvider extends CheckedArgumentProvider {
    private ArgumentProvider defaultArgumentProvider;

    public void setDefaultArgumentProvider(ArgumentProvider defaultArgumentProvider) {
        this.defaultArgumentProvider = defaultArgumentProvider;
    }

    @Override
    public String getArgumentTemplate(String name) {
        String value = System.getProperty(name);
        if (StringUtils.isBlank(value)) {
            value = defaultArgumentProvider.getArgument(name);
        }
        return value;
    }
}
