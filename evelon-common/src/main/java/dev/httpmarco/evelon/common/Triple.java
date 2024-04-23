package dev.httpmarco.evelon.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
@AllArgsConstructor
public final class Triple<A, B, C> {
    //todo
    private A first;
    private B second;
    private C third;

}
