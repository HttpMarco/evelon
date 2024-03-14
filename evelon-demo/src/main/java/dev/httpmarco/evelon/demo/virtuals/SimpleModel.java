package dev.httpmarco.evelon.demo.virtuals;

import dev.httpmarco.evelon.common.annotations.PrimaryKey;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.Objects;

@Accessors(fluent = true)
@Getter
@AllArgsConstructor
@EqualsAndHashCode
public final class SimpleModel {

    @PrimaryKey
    private int username;
    private Integer money;

}
