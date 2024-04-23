package dev.httpmarco.evelon.process;

import dev.httpmarco.evelon.repository.Repository;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@AllArgsConstructor
public abstract class ProcessRunner<Q> {

    public void update(@NotNull Process process, @NotNull Repository<?> repository) {
        this.update(generateQuery(process, repository));
    }

    @SuppressWarnings("unchecked")
    public Q generateQuery(@NotNull Process process, @NotNull Repository<?> repository) {
        Q query = null;
        if (process instanceof AbstractEntryProcess<?> entryProcess) {
            query = (Q) entryProcess.run(repository.entry());
        } else {
            throw new RuntimeException("Process is not an instance of AbstractEntryProcess");
        }
        return query;
    }

    public List<?> query(@NotNull Process process, @NotNull Repository<?> repository) {
        return this.query(generateQuery(process, repository));
    }

    protected abstract void update(Q query);

    protected abstract List<?> query(Q query);

}