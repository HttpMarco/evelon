package dev.httpmarco.evelon.tests;

import dev.httpmarco.evelon.annotation.PrimaryKey;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
@Getter
@AllArgsConstructor
@EqualsAndHashCode
public final class SimpleObjectModel {

    @PrimaryKey
    private int username;
    private String address;

}
