package azoo.com.ptp_rule_proxy.xml;

public interface NameExtractor<T> {
    String getName(T t);

    static class FromReplacementSequenceType implements NameExtractor<azoo.com.ptp_rule_proxy.generated.ReplacementSequenceType> {
        @Override
        public String getName(azoo.com.ptp_rule_proxy.generated.ReplacementSequenceType t) {
            return t.getName();
        }
    }

    static class FromRuleSequenceType implements NameExtractor<azoo.com.ptp_rule_proxy.generated.RuleSequenceType> {
        @Override
        public String getName(azoo.com.ptp_rule_proxy.generated.RuleSequenceType t) {
            return t.getName();
        }
    }
}
