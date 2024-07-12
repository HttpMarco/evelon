package dev.httpmarco.evelon.demo.models.maps;

import dev.httpmarco.evelon.PrimaryKey;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Map;
import java.util.UUID;

@Getter
@Accessors(fluent = true)
@AllArgsConstructor
@ToString
public final class MapSimpleModel {

    @PrimaryKey
    private final UUID uniqueId;
    private final int age;
    private final String username;
    private final Map<String, Boolean> properties;

}
