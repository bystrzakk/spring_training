package test.demo.spring.core.person;

public class Person {

    private String name;
    private String surname;
    private BackPack backPack;

    public Person() {
    }

    public void setBackPack(BackPack backPack) {
        this.backPack = backPack;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Person(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                " backpack = " + backPack.getCapacity() +
                '}';
    }
}
