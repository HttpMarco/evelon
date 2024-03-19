package dev.httpmarco.evelon.process;

import dev.httpmarco.evelon.repository.RepositoryClass;

import java.util.LinkedHashSet;
import java.util.Set;

public abstract class Process implements Runnable {

    private String id;
    private final Set<RepositoryClass<?>> affectedRows = new LinkedHashSet<>();

}
