package dev.httpmarco.evelon.demo.models;

import dev.httpmarco.evelon.PrimaryKey;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
@ToString
public final class SimpleCollectionModel {

    @PrimaryKey
    private final UUID uuid;
    private final int age;
    private final List<String> permissions;
    private final List<Boolean> settings;

}
