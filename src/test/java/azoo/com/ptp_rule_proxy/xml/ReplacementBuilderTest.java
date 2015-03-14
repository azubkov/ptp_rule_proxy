package azoo.com.ptp_rule_proxy.xml;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:ptpspring/ptpmain.xml")
//@ContextConfiguration(locations = {"classpath:/ptpspring/ptpmain.xml"})
public class ReplacementBuilderTest implements ApplicationContextAware {
    private ReplacementBuilder replacementBuilder;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        replacementBuilder = (ReplacementBuilder) context.getBean("replacementBuilder");
    }

    @Test
    public void test() {
        Object o = replacementBuilder.toReplacement(null);
        Assert.assertNotNull(o);
    }
}
