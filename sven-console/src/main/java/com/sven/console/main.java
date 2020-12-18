package com.sven.console;

import com.sven.console.pojo.Person;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class main {
    public static void main(String[] args){

    }

    @Test
    public  void test(){
        KieServices kss = KieServices.get();
        KieContainer kc = kss.getKieClasspathContainer();
        KieSession ks = kc.newKieSession("testFirstRule");
        Person p = new Person();
        ks.insert(p);
        int count = ks.fireAllRules();
        System.out.println("执行了" + count + "规则");
        System.out.println("p="+p.getAge());
        ks.dispose();
    }
}
