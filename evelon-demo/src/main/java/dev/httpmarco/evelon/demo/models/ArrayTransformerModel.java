package dev.httpmarco.evelon.demo.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.UUID;

@Getter
@Accessors(fluent = true)
@AllArgsConstructor
public final class ArrayTransformerModel {

    private UUID uniqueId;
    private int age;
    private String[] musics;

}
