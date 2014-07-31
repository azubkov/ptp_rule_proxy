package azoo.com.ptp_rule_proxy.args.dictionary;

import azoo.com.ptp_rule_proxy.PTPProxyMain;
import com.google.common.base.Joiner;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.util.Properties;

public class FromFileArgumentDictionary implements ArgumentDictionary {
    private static final Logger LOGGER = Logger.getLogger(FromFileArgumentDictionary.class);
    private Properties properties;

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    @Override
    public void register(String key, String description) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void throwIfUnregistered(String key) {
        String value = properties.getProperty(key);
        if (StringUtils.isBlank(value)) {
            throw new IllegalArgumentException("Unknown property: " + key);
        }
    }

    @Override
    public String getDescription(String key) {
        String value = properties.getProperty(key);
        return value;
    }

    @Override
    public String printKnownArguments() {
        String separator = "\n     ";
        StringBuilder sb = new StringBuilder();
        sb.append(separator);
        sb.append(Joiner.on(separator).join(properties.entrySet()));
        return sb.toString();
    }
}
