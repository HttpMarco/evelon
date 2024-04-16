package dev.httpmarco.evelon.process;

import dev.httpmarco.evelon.layer.Layer;
import dev.httpmarco.evelon.repository.Repository;
import dev.httpmarco.evelon.repository.RepositoryEntry;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Getter
public abstract class Process<Q, P extends Process<Q, P>> {

    @Accessors(fluent = true)
    private final List<P> childrenProcesses = new ArrayList<>();

    public abstract Q run(RepositoryEntry entry, Layer<Q> layer);

    public <S extends P> void newSubProcess(S subProcess) {
        this.childrenProcesses.add(subProcess);
    }
}