package azoo.com.ptp_rule_proxy.xml;

public interface NameExtractor<T> {
    String getName(T t);

    static class toReplacementSequenceType implements NameExtractor<azoo.com.ptp_rule_proxy.generated.ReplacementSequenceType> {
        private static final toReplacementSequenceType INSTANCE = new toReplacementSequenceType();

        public static toReplacementSequenceType getInstance() {
            return INSTANCE;
        }

        @Override
        public String getName(azoo.com.ptp_rule_proxy.generated.ReplacementSequenceType t) {
            return t.getName();
        }
    }

    static class toRuleSequenceType implements NameExtractor<azoo.com.ptp_rule_proxy.generated.RuleSequenceType> {
        private static final toRuleSequenceType INSTANCE = new toRuleSequenceType();

        public static toRuleSequenceType getInstance() {
            return INSTANCE;
        }

        @Override
        public String getName(azoo.com.ptp_rule_proxy.generated.RuleSequenceType t) {
            return t.getName();
        }
    }
}
