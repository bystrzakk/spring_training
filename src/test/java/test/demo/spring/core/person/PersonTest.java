package test.demo.spring.core.person;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import test.demo.spring.core.Application;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(locations = "classpath:application-test.yml")
@ContextConfiguration(classes = Application.class, loader = AnnotationConfigContextLoader.class)
public class PersonTest {

    @Autowired
    Person person;

    /*@Autowired
    Person person2;
    @Autowired
    Person person3;*/


    @Test
    public void testSampleService() {
        System.out.println(person.hashCode());
        /*System.out.println(person2.hashCode());
        System.out.println(person3.hashCode());*/
        //given
        final int expectedResult = 2;
        //when
        //then
        assertEquals("Kamil", person.getName());
        assertTrue(expectedResult == person.getBackPackCapactiy());
    }
}