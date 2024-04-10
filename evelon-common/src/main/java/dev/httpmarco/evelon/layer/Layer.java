package dev.httpmarco.evelon.layer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
@Getter
@AllArgsConstructor
public abstract class Layer  {

    private String id;

}
