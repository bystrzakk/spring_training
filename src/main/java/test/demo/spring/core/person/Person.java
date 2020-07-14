package test.demo.spring.core.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Person {

    private String name;
    private String surname;
    private BackPack backPack;

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Autowired
    public Person(@Value("${test.name:Lukasz}") String name,
                  @Value("${test.surname:Nowak}") String surname,
                  BackPack backPack) {
        this.name = name;
        this.surname = surname;
        this.backPack = backPack;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Integer getBackPackCapactiy() {
        return this.backPack.getCapacity();
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
//                " backpack = " + backPack.getCapacity() +
                '}';
    }
}
