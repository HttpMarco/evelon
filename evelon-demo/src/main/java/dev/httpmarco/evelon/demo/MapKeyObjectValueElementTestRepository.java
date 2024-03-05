package dev.httpmarco.evelon.demo;

import dev.httpmarco.evelon.common.annotations.PrimaryKey;
import dev.httpmarco.evelon.demo.elements.HierarchyChildrenElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;

@Getter
@Accessors(fluent = true)
@AllArgsConstructor
public class MapKeyObjectValueElementTestRepository {

    @PrimaryKey
    private final String mapName;
    private final int age;
    private final Map<HierarchyChildrenElement, String> properties = new HashMap<>();

}
