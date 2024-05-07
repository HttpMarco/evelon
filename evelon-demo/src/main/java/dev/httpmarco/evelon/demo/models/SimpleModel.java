package dev.httpmarco.evelon.demo.models;

import dev.httpmarco.evelon.demo.models.objects.EnumObject;
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
    private String address;
    private EnumObject enumObject;

}