package com.wiiiolettaaaaa;
import java.util.Objects;

class Person {
    private String lastName;
    private String firstName;
    private int age;

    // Конструктори
    public Person() {}
    public Person(String lastName, String firstName, int age) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.age = age;
    }

    // equals & hashCode
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Person person = (Person) obj;
        return age == person.age &&
                Objects.equals(lastName, person.lastName) &&
                Objects.equals(firstName, person.firstName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastName, firstName, age);
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + ", age " + age;
    }

}
