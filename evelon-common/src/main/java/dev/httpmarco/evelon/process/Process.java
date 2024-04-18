package dev.httpmarco.evelon.process;

import lombok.Getter;
import lombok.experimental.Accessors;
import java.util.ArrayList;
import java.util.List;

@Getter
@Accessors(fluent = true)
public abstract class Process {

    private final List<Object> arguments = new ArrayList<>();

}