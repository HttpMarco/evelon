package dev.httpmarco.evelon.demo.elements;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public final class HierarchyParentElement {

    private int coinsDate;
    private HierarchyChildrenElement children;

}
