package azoo.com.ptp_rule_proxy.args.provider;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;

public class DArgumentProvider extends CheckedArgumentProvider implements InitializingBean {
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

    @Override
    public void afterPropertiesSet() throws Exception {
        String arg = null;
        arg = "local.port";
        if (!StringUtils.equals(getArgument(arg), defaultArgumentProvider.getArgument(arg))) {
            return;
        }
        arg = "remote.port";
        if (!StringUtils.equals(getArgument(arg), defaultArgumentProvider.getArgument(arg))) {
            return;
        }
        arg = "remote.host";
        if (!StringUtils.equals(getArgument(arg), defaultArgumentProvider.getArgument(arg))) {
            return;
        }
//        argumentDictionary.printUsage();
    }
}
