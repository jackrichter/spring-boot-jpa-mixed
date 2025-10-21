package com.demo.healthcare.model;

import jakarta.persistence.*;

//@Inheritance(strategy = InheritanceType.JOINED)     // JOINED Strategy
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)     // SINGLE-TABLE Strategy
//@DiscriminatorColumn(name = "person_type")                // SINGLE-TABLE Strategy
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)     // TABLE-PER-CLASS Strategy
@Entity
public abstract class Person {      // abstract is useful in TABLE-PER-CLASS Strategy

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)     // Cannot use IDENTITY in TABLE-PER-CLASS strategy !
    private Long id;
    private String name;
    private int age;
    private String email;

    @Version                // For Optimistic Locking
    private int version;

    public Person() {
    }

    public Person(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                '}';
    }
}
