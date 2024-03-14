package dev.httpmarco.evelon.demo.collections;

import dev.httpmarco.evelon.common.annotations.PrimaryKey;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Accessors(fluent = true)
@AllArgsConstructor
public final class SimpleCollectionsModel {

    @PrimaryKey
    private String idPerson;
    private long money;
    private List<String> usernames;
}
