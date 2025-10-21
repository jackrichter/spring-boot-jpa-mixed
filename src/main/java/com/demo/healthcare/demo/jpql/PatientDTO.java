package com.demo.healthcare.demo.jpql;

public class PatientDTO {
    private String name;
    private int age;

    public PatientDTO(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "PatientSummary{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
