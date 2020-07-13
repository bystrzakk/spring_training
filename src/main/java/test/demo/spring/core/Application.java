package test.demo.spring.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import test.demo.spring.core.person.Person;

public class Application {
    public static void main(String[] args) {
        /*ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        Person person = (Person) context.getBean("person");
        System.out.println(person.toString());*/


        // PROTOTYPE
       /* ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        final Person person = (Person) context.getBean("person");
        final Person person2 = (Person) context.getBean("person");
        final Person person3 = (Person) context.getBean("person");

        System.out.println(person.hashCode());
        System.out.println(person2.hashCode());
        System.out.println(person3.hashCode());

        System.out.println(person.equals(person2) && person.equals(person3));*/

        ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        final Person person = (Person) context.getBean("person");
        System.out.println(person.toString());
    }
}
