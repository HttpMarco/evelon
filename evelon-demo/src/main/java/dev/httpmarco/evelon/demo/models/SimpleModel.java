package dev.httpmarco.evelon.demo.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
@Getter
@AllArgsConstructor
public class SimpleModel {

    private char name;
    private int age;
    private long money;
    private boolean dead;

}
