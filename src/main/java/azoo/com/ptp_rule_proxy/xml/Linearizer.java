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

    static class FromRuleSequenceType implements Linearizer<RuleSequenceType> {
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
}
