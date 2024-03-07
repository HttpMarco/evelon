package dev.httpmarco.evelon.demo;

import dev.httpmarco.evelon.common.annotations.PrimaryKey;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public final class HierarchyTestRepository {

    @PrimaryKey
    private String name;
    private int coins;
    private HierarchyElement parent;

}
