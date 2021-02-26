package sven.console.test.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author ：897863952@qq.com
 * @date ：Created in 2021/2/26 17:15
 * @description：
 * @version:
 * @see
 */
public class BeanTest1  implements InitializingBean, ApplicationContextAware {

    private ApplicationContext applicationContext;
    @Autowired
    private BeanTest2 test2;

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean.afterPropertiesSet");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("ApplicationContextAware.setApplicationContext");
        this.applicationContext = applicationContext;
    }

    public void init(){
        BeanTest2 bean = applicationContext.getBean(BeanTest2.class);
        bean.sayHello();
    }

    public void callAutowired(){
        System.out.println("test2 is null " + (test2 == null) );
    }
}
