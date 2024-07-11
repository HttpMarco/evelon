package dev.httpmarco.evelon.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Accessors(fluent = true)
@AllArgsConstructor
public final class Pair<F, S> {

    private F first;
    private S second;

}
