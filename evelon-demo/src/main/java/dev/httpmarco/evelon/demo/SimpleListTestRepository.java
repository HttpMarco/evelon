package dev.httpmarco.evelon.demo;

import dev.httpmarco.evelon.common.annotations.PrimaryKey;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Accessors(fluent = true)
@AllArgsConstructor
public final class SimpleListTestRepository {

    @PrimaryKey
    private int idPerson;
    private List<String> usernames;
}
