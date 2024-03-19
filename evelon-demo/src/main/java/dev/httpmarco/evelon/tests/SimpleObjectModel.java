package dev.httpmarco.evelon.tests;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
@Getter
@AllArgsConstructor
@EqualsAndHashCode
public final class SimpleObjectModel {

    private int username;
    private Integer money;

}
