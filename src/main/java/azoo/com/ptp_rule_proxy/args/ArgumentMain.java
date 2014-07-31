package azoo.com.ptp_rule_proxy.args;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ArgumentMain {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/argumentmain.xml");
        ArgumentProvider argumentProvider = (ArgumentProvider) context.getBean("argumentProvider");
        argumentProvider.getArgument("test");
    }
}
