package dev.httpmarco.evelon.demo;

import dev.httpmarco.evelon.demo.elements.HierarchyParentElement;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class HierarchyTestRepository {

    private String name;
    private int coins;
    private HierarchyParentElement parent;

}
