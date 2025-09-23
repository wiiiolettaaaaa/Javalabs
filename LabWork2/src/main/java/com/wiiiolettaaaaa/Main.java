package com.wiiiolettaaaaa;
import com.google.gson.Gson;

public class Main {
    public static void main(String[] args) {
        Person person = new Person("John", "Lennon", 40);

        Gson gson = new Gson();
        String json = gson.toJson(person);
        System.out.println("Serialized to JSON: " + json);

        Person deserializedPerson = gson.fromJson(json, Person.class);
        System.out.println("Deserialized from JSON: " + deserializedPerson);

        boolean areEqual = person.equals(deserializedPerson);
        System.out.println("Are the original and deserialized persons equal? " + areEqual);
    }
}