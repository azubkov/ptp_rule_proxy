package azoo.com.ptp_rule_proxy.args;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

class ArgumentDummyMain {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring/argumentmain.xml");
        ArgumentProvider argumentProvider = (ArgumentProvider) ctx.getBean("argumentProvider");
        String result = argumentProvider.getArgument("loh");
        System.err.println("result: " + result);
        System.err.println("result: " + result);
    }
}
