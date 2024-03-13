package dev.httpmarco.evelon.demo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
@AllArgsConstructor
public final class HierarchyElement {

    private String nameId;
    private int childrenCount;

}
