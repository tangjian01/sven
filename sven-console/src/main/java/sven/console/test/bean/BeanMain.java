package sven.console.test.bean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author ：897863952@qq.com
 * @date ：Created in 2021/2/26 17:15
 * @description：
 * @version:
 * @see
 */
public class BeanMain {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/beans.xml");
        BeanTest1 bean = context.getBean(BeanTest1.class);
        bean.callAutowired();
        System.out.println("OK");
    }
}
