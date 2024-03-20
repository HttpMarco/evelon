package dev.httpmarco.evelon.process;

import dev.httpmarco.evelon.repository.Repository;
import dev.httpmarco.evelon.repository.RepositoryClass;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


@Getter
@Accessors(fluent = true)
public abstract class Process<P extends Process<?>> implements Runnable, Cloneable {

    @Setter
    private String id;
    private final ProcessMeta meta;

    private final Set<RepositoryClass<?>> affectedRows = new LinkedHashSet<>();
    private final Set<P> subProcesses = new LinkedHashSet<>();

    public Process(String id, Repository<?> repository) {
        this.id = id;
        this.meta = new ProcessMeta(repository);
    }

    public P subProcess(String id) {
        var cloned = clone();
        cloned.affectedRows().clear();
        cloned.id(cloned.id() + "_" + id);
        return cloned;
    }

    @Override
    public P clone() {
        try {
            return (P) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public void execute() {
        if (this instanceof UpdateProcess updateProcess) {
            ((UpdateProcess) this).pushUpdate();
        }
        for (P subProcess : this.subProcesses) {
            subProcess.execute();
        }
    }

    public interface UpdateProcess {
        void pushUpdate();
    }
}
