package test.demo.spring.core.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Person {

    private String name;
    private String surname;
    private Item item;

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Autowired
    public Person(@Value("${test.name:Lukasz}") String name,
                  @Value("${test.surname:Nowak}") String surname,
                  @Qualifier(value = "umbrella") Item item) {
        this.name = name;
        this.surname = surname;
        this.item = item;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getItemName() {
        return this.item.getName();
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '}';
    }
}
