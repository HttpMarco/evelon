package dev.httpmarco.evelon.process;

import dev.httpmarco.evelon.Repository;
import dev.httpmarco.evelon.process.kind.QueryProcess;
import dev.httpmarco.evelon.process.kind.UpdateProcess;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class ProcessRunner<Q extends ProcessReference<Q>> {

    public Object apply(Process process, Repository<?> repository) {
        var base = generateBase();
        if (process instanceof UpdateProcess<?> updateProcess) {
            updateProcess.runMapping(repository.entry(), base);
            this.update(base);
            return null;
        } else if (process instanceof QueryProcess<?> queryProcess) {
            var object = queryProcess.runMapping(repository.entry(), base);
            this.query(base);
            return object;

        }
        throw new UnsupportedOperationException("Unsupported process type: " + process.getClass().getSimpleName());
    }

    public abstract Q generateBase();

    protected abstract void query(Q query);

    protected abstract void update(Q query);

}