package dev.httpmarco.evelon.test;

public class TestRepository {

    private final String name;
    private final int age;

    public TestRepository(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
