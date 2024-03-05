package dev.httpmarco.evelon.demo;

import dev.httpmarco.evelon.common.annotations.PrimaryKey;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;

@Getter
@Accessors(fluent = true)
@AllArgsConstructor
public final class MapBothElementTestRepository {

    @PrimaryKey
    private final String mapName;
    private final int age;
    private final Map<String, String> properties = new HashMap<>();

}
