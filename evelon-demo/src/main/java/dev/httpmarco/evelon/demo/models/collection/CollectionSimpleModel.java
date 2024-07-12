package dev.httpmarco.evelon.demo.models.collection;

import dev.httpmarco.evelon.PrimaryKey;
import dev.httpmarco.evelon.demo.models.objects.EnumObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
@Accessors(fluent = true)
@ToString
public final class CollectionSimpleModel {

    @PrimaryKey
    private final UUID uuid;
    private final int age;
    private final List<String> permissions;
    private final List<EnumObject> settings;

}
