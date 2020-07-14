package test.demo.spring.core.person;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_SINGLETON;

@Component
//@Scope("singleton")
@Scope(value = SCOPE_SINGLETON)
public class Umbrella implements Item {
    private String name = "parasol";

    public String getName() {
        return name;
    }
}
