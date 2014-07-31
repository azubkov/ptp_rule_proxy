package azoo.com.ptp_rule_proxy.args.provider;

public interface ArgumentProvider {
    public String getArgument(String name);

    class NullObject implements ArgumentProvider {
        @Override
        public String getArgument(String name) {
            throw new UnsupportedOperationException();
        }
    }
}
