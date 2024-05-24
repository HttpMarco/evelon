package dev.httpmarco.evelon.demo.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
@Getter
@AllArgsConstructor
@ToString
public final class StaticModel {

    private static final String birdSize = "";

    private char name;
    private int age;

}