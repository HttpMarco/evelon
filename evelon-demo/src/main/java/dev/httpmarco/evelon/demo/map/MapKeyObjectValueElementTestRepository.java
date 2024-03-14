package dev.httpmarco.evelon.demo.map;

import dev.httpmarco.evelon.common.annotations.PrimaryKey;
import dev.httpmarco.evelon.demo.virtuals.HierarchyElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;

@Getter
@Accessors(fluent = true)
@AllArgsConstructor
public final class MapKeyObjectValueElementTestRepository {

    @PrimaryKey
    private final String mapName;
    private final int age;
    private final Map<HierarchyElement, String> properties = new HashMap<>();

}
