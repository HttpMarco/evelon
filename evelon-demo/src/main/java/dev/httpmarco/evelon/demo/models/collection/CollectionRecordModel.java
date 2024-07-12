package dev.httpmarco.evelon.demo.models.collection;

import dev.httpmarco.evelon.PrimaryKey;
import dev.httpmarco.evelon.demo.models.RecordModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
@ToString
public final class CollectionRecordModel {

    @PrimaryKey
    private final UUID uuid;
    private final int age;
    private final List<RecordModel> groups;

}
