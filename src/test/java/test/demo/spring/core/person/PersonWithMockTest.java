package test.demo.spring.core.person;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import test.demo.spring.core.Application;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@TestPropertySource(locations = "classpath:application-test.yml")
@ContextConfiguration(classes = Application.class, loader = AnnotationConfigContextLoader.class)
public class PersonWithMockTest {

    /*@Autowired
    Person person;*/

    @Mock
    private BackPack backPack;

    @InjectMocks
    private Person person;

/*    @Before
    public void init() {
        initMocks(this);
        person = new Person("test", "test", backPack);
    }*/

    @Test
    public void testSampleService() {
        //given
        final int mockedCapacity = 40;
        //when
        when(backPack.getCapacity()).thenReturn(mockedCapacity);
        //then
        assertTrue(mockedCapacity == person.getBackPackCapactiy());
    }
}