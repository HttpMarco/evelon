package dev.httpmarco.evelon.process;

import lombok.Getter;
import lombok.experimental.Accessors;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@Accessors(fluent = true)
public abstract class Process {

    // for example for put the foreign value in the next sub process
    private final Map<String, Object> properties = Map.of();

}