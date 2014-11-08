package azoo.com.ptp_rule_proxy.xml;

public interface NameExtractor<T> {
    String getName(T t);

    static class FromReplacementSequenceType implements NameExtractor<azoo.com.ptp_rule_proxy.generated.ReplacementSequenceType> {
        private static final FromReplacementSequenceType INSTANCE = new FromReplacementSequenceType();

        public static FromReplacementSequenceType getInstance() {
            return INSTANCE;
        }

        @Override
        public String getName(azoo.com.ptp_rule_proxy.generated.ReplacementSequenceType t) {
            return t.getName();
        }
    }

    static class FromRuleSequenceType implements NameExtractor<azoo.com.ptp_rule_proxy.generated.RuleSequenceType> {
        private static final FromRuleSequenceType INSTANCE = new FromRuleSequenceType();

        public static FromRuleSequenceType getInstance() {
            return INSTANCE;
        }

        @Override
        public String getName(azoo.com.ptp_rule_proxy.generated.RuleSequenceType t) {
            return t.getName();
        }
    }
}
