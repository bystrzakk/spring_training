package test.demo.spring.core.person;

import org.springframework.stereotype.Component;

@Component
public class BackPack {

    /*public BackPack(int capacity) {
        this.capacity = capacity;
    }*/

    private int capacity = 2;

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
