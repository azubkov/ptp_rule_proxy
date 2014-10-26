package azoo.com.ptp_rule_proxy.xml;

import azoo.com.ptp_rule_proxy.generated.MessageType;
import azoo.com.ptp_rule_proxy.generated.ReplacementSequenceType;
import azoo.com.ptp_rule_proxy.generated.RootType;
import azoo.com.ptp_rule_proxy.generated.RuleSequenceType;

import java.util.ArrayList;
import java.util.List;

public interface Linearizer<T> {
    List<T> linearize(RootType rootType);

    static class FromReplacementSequenceType implements Linearizer<ReplacementSequenceType> {
        @Override
        public List<ReplacementSequenceType> linearize(RootType rootType) {
            List<ReplacementSequenceType> result = new ArrayList<>();
            for (ReplacementSequenceType replacementSequenceType : rootType.getReplacementSequence()) {
                result.add(replacementSequenceType);
            }
            rootType.getReplacementSequence();
            for (MessageType messageType : rootType.getProcessor().getMessage()) {
                for (ReplacementSequenceType replacementSequenceType : messageType.getReplacementSequences().getReplacementSequence()) {
                    result.add(replacementSequenceType);
                }
            }
            return result;
        }
    }

    static class FromRuleSequenceType implements Linearizer<RuleSequenceType> {
        @Override
        public List<RuleSequenceType> linearize(RootType rootType) {
            return null;
        }
    }
}
