package azoo.com.ptp_rule_proxy.args;

import azoo.com.ptp_rule_proxy.args.dictionary.ArgumentDictionary;
import azoo.com.ptp_rule_proxy.args.provider.ArgumentProvider;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;

public class UsagePrinter implements InitializingBean {
    private static final Logger LOGGER = Logger.getLogger(UsagePrinter.class);
    private ArgumentProvider defaultArgumentProvider;
    private ArgumentProvider argumentProvider;
    private ArgumentDictionary argumentDictionary;

    public void setDefaultArgumentProvider(ArgumentProvider defaultArgumentProvider) {
        this.defaultArgumentProvider = defaultArgumentProvider;
    }

    public void setArgumentProvider(ArgumentProvider argumentProvider) {
        this.argumentProvider = argumentProvider;
    }

    public void setArgumentDictionary(ArgumentDictionary argumentDictionary) {
        this.argumentDictionary = argumentDictionary;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        String arg = null;
        arg = "local.port";
        if (!StringUtils.equals(argumentProvider.getArgument(arg), defaultArgumentProvider.getArgument(arg))) {
            return;
        }
        arg = "remote.port";
        if (!StringUtils.equals(argumentProvider.getArgument(arg), defaultArgumentProvider.getArgument(arg))) {
            return;
        }
        arg = "remote.host";
        if (!StringUtils.equals(argumentProvider.getArgument(arg), defaultArgumentProvider.getArgument(arg))) {
            return;
        }
        String separator = "\n     ";
        StringBuilder sb = new StringBuilder();
        sb.append("Known arguments are:");
        sb.append(separator);
        sb.append(argumentDictionary.printKnownArguments());
        sb.append("use -D prefix to specify target parameter:");
        sb.append(separator);
        sb.append(separator);
        sb.append("-Dlocal.port=8888 -Dremote.host=example.com -Dremote.port=80");
        LOGGER.error(sb.toString());
    }
}
