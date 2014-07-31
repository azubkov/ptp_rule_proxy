package azoo.com.ptp_rule_proxy.args;

import org.apache.commons.lang3.StringUtils;

import java.util.Properties;

public class FromFileArgumentDictionary implements ArgumentDictionary {
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
}
