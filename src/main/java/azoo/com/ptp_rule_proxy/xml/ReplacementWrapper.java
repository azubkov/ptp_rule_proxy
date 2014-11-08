package azoo.com.ptp_rule_proxy.xml;

import azoo.com.ptp_rule_proxy.generated.ReplacementSequenceType;
import azoo.com.ptp_rule_proxy.generated.RootType;
import azoo.com.ptp_rule_proxy.generated.RuleSequenceType;

import java.util.Map;

public class ReplacementWrapper {
    private RootType rootType;
    private Map<String, ReplacementSequenceType> replacementSequenceTypeMap;
    private Map<String, RuleSequenceType> ruleSequenceTypeMap;

    public RootType getRootType() {
        return rootType;
    }

    public void setRootType(RootType rootType) {
        this.rootType = rootType;
    }

    public Map<String, ReplacementSequenceType> getReplacementSequenceTypeMap() {
        return replacementSequenceTypeMap;
    }

    public void setReplacementSequenceTypeMap(Map<String, ReplacementSequenceType> replacementSequenceTypeMap) {
        this.replacementSequenceTypeMap = replacementSequenceTypeMap;
    }

    public Map<String, RuleSequenceType> getRuleSequenceTypeMap() {
        return ruleSequenceTypeMap;
    }

    public void setRuleSequenceTypeMap(Map<String, RuleSequenceType> ruleSequenceTypeMap) {
        this.ruleSequenceTypeMap = ruleSequenceTypeMap;
    }
}
