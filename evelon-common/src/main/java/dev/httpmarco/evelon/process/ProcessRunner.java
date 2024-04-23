package dev.httpmarco.evelon.process;

import dev.httpmarco.evelon.Repository;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;

@AllArgsConstructor
public abstract class ProcessRunner<Q> {

    public void update(@NotNull Process process, @NotNull Repository<?> repository) {
        this.update(generateQuery(process, repository));
    }

    @SuppressWarnings("unchecked")
    public Q generateQuery(@NotNull Process process, @NotNull Repository<?> repository) {
        Q query;
        if (process instanceof AbstractEntryProcess<?> entryProcess) {
            query = (Q) entryProcess.run(repository.entry());
        } else {
            throw new RuntimeException("Process is not an instance of AbstractEntryProcess");
        }
        return query;
    }

    protected abstract void query(Q query);

    protected abstract void update(Q query);

}