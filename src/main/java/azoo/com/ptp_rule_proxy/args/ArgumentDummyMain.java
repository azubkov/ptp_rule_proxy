package azoo.com.ptp_rule_proxy.args;

import azoo.com.ptp_rule_proxy.args.dictionary.ArgumentDictionary;
import azoo.com.ptp_rule_proxy.args.provider.ArgumentProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

class ArgumentDummyMain {/*TODO move me to unit test, spring config as well*/
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring/argumentmain.xml");
        ArgumentProvider argumentProvider = (ArgumentProvider) ctx.getBean("argumentProvider");
        String result = argumentProvider.getArgument("local.port");
        System.err.println("result: " + result);
        System.err.println("result: " + result);

        ArgumentDictionary argumentDictionary = (ArgumentDictionary) ctx.getBean("argumentDictionary");
        argumentDictionary.printKnownArguments();
    }
}
