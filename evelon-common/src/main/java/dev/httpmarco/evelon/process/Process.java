package dev.httpmarco.evelon.process;

import org.jetbrains.annotations.NotNull;

import java.util.LinkedHashMap;
import java.util.Map;

public abstract class Process {

    // for example for put the foreign value in the next sub process
    private final Map<Object, Object> properties = new LinkedHashMap<>();

    public void property(@NotNull Object key, Object value) {
        properties.put(key.toString(), value);
    }

    public Object property(@NotNull Object key) {
        return properties.get(key.toString());
    }
}