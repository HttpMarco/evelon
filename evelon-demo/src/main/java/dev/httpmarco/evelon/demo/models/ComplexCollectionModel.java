package dev.httpmarco.evelon.demo.models;

import dev.httpmarco.evelon.demo.models.objects.TestObject1;
import dev.httpmarco.evelon.demo.models.objects.TestObject2;
import dev.httpmarco.evelon.PrimaryKey;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
public final class ComplexCollectionModel {

    @PrimaryKey
    private final UUID uuid;
    private final List<TestObject1> permissions;
    private final List<TestObject2> groups;

}
