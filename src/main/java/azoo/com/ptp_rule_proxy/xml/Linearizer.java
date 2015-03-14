package azoo.com.ptp_rule_proxy.xml;

import azoo.com.ptp_rule_proxy.generated.MessageType;
import azoo.com.ptp_rule_proxy.generated.RootType;
import azoo.com.ptp_rule_proxy.generated.RuleSequenceType;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface Linearizer<T> {
    @NotNull
    List<T> linearize(RootType rootType);

    static class toReplacementSequenceType implements Linearizer<azoo.com.ptp_rule_proxy.generated.ReplacementSequenceType> {
        private static final toReplacementSequenceType INSTANCE = new toReplacementSequenceType();

        public static toReplacementSequenceType getInstance() {
            return INSTANCE;
        }

        @Override
        public List<azoo.com.ptp_rule_proxy.generated.ReplacementSequenceType> linearize(RootType rootType) {
            List<azoo.com.ptp_rule_proxy.generated.ReplacementSequenceType> result = new ArrayList<>();
            for (azoo.com.ptp_rule_proxy.generated.ReplacementSequenceType target : rootType.getReplacementSequence()) {
                result.add(target);
            }
            if (rootType.getProcessor().getMessage() == null) {
                return result;
            }
            for (MessageType messageType : rootType.getProcessor().getMessage()) {
                if (messageType.getReplacementSequences() == null) {
                    continue;
                }
                for (azoo.com.ptp_rule_proxy.generated.ReplacementSequenceType target : messageType.getReplacementSequences().getReplacementSequence()) {
                    result.add(target);
                }
            }
            return result;
        }
    }

    static class toRuleSequenceType implements Linearizer<RuleSequenceType> {
        private static final toRuleSequenceType INSTANCE = new toRuleSequenceType();

        public static toRuleSequenceType getInstance() {
            return INSTANCE;
        }

        @Override
        public List<RuleSequenceType> linearize(RootType rootType) {
            List<RuleSequenceType> result = new ArrayList<>();
            for (RuleSequenceType target : rootType.getRuleSequence()) {
                result.add(target);
            }
            for (MessageType messageType : rootType.getProcessor().getMessage()) {
                for (RuleSequenceType target : messageType.getRuleSequences().getRuleSequence()) {
                    result.add(target);
                }
            }
            return result;
        }
    }

    static class Methods {
        @NotNull
        static <T> Map<String, T> toMap(@NotNull List<T> list, @NotNull NameExtractor<T> nameExtractor) {
            Map<String, T> map = new HashMap<>();
            for (T t : list) {
                String s = nameExtractor.getName(t);
                if (StringUtils.isBlank(s)) {
                    continue;
                }
                Object o = map.put(s, t);
                if (o != null) {
                    throw new IllegalArgumentException(String.format("Duplicate element name observed: %s", s));
                }
            }
            return map;
        }
    }
}
