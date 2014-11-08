package azoo.com.ptp_rule_proxy.xml;

import azoo.com.ptp_rule_proxy.generated.MessageType;
import azoo.com.ptp_rule_proxy.generated.ReplacementSequenceType;
import azoo.com.ptp_rule_proxy.generated.RootType;
import azoo.com.ptp_rule_proxy.generated.RuleSequenceType;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface Linearizer<T> {
    List<T> linearize(RootType rootType);

    static class ReplacementSequenceTypeLinearizer implements Linearizer<ReplacementSequenceType> {
        private static final ReplacementSequenceTypeLinearizer INSTANCE = new ReplacementSequenceTypeLinearizer();

        public static ReplacementSequenceTypeLinearizer getInstance() {
            return INSTANCE;
        }

        @Override
        public List<ReplacementSequenceType> linearize(RootType rootType) {
            List<ReplacementSequenceType> result = new ArrayList<>();
            for (ReplacementSequenceType target : rootType.getReplacementSequence()) {
                result.add(target);
            }
            for (MessageType messageType : rootType.getProcessor().getMessage()) {
                for (ReplacementSequenceType target : messageType.getReplacementSequences().getReplacementSequence()) {
                    result.add(target);
                }
            }
            return result;
        }
    }

    static class RuleSequenceTypeLinearizer implements Linearizer<RuleSequenceType> {
        private static final RuleSequenceTypeLinearizer INSTANCE = new RuleSequenceTypeLinearizer();

        public static RuleSequenceTypeLinearizer getInstance() {
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

        static <T> Map<String, T> toMap(List<T> list, NameExtractor<T> nameExtractor) {
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
