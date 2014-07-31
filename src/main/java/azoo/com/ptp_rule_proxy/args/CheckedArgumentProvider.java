package azoo.com.ptp_rule_proxy.args;

public abstract class CheckedArgumentProvider implements ArgumentProvider {
    private ArgumentDictionary argumentDictionary;

    public void setArgumentDictionary(ArgumentDictionary argumentDictionary) {
        this.argumentDictionary = argumentDictionary;
    }

    @Override
    public final String getArgument(String name) {
        argumentDictionary.throwIfUnregistered(name);
        return getArgumentTemplate(name);
    }

    public abstract String getArgumentTemplate(String name);
}