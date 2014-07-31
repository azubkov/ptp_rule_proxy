package azoo.com.ptp_rule_proxy.args;

public interface ArgumentDictionary {
    void register(String key, String description);

    void throwIfUnregistered(String key);

    String  getDescription(String key);
}
