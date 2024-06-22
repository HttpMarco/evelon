package dev.httpmarco.evelon.demo.models;

import dev.httpmarco.evelon.PrimaryKey;
import dev.httpmarco.evelon.demo.models.objects.TestObject1;
import dev.httpmarco.evelon.demo.models.objects.TestObject2;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
@ToString
public final class RecordCollectionModel {

    @PrimaryKey
    private final UUID uuid;
    private final int age;
    private final List<RecordModel> groups;

}
