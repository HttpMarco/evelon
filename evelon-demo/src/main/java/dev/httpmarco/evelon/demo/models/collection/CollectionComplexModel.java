package dev.httpmarco.evelon.demo.models.collection;

import dev.httpmarco.evelon.demo.models.objects.TestObject1;
import dev.httpmarco.evelon.demo.models.objects.TestObject2;
import dev.httpmarco.evelon.PrimaryKey;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
@ToString
public final class CollectionComplexModel {

    @PrimaryKey
    private final UUID uuid;
    private final int age;
    private final List<TestObject1> permissions;
    private final List<TestObject2> groups;

}
