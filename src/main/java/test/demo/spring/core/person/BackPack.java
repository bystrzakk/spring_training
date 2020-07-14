package test.demo.spring.core.person;

import org.springframework.stereotype.Component;

@Component("umbrella")
public class BackPack implements Item{

    private int capacity = 2;
    private String name = "plecak";

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }
}
