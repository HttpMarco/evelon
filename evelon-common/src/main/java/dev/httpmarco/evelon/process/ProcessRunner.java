package dev.httpmarco.evelon.process;

import dev.httpmarco.evelon.layer.Layer;
import dev.httpmarco.evelon.process.kind.QueryProcess;
import dev.httpmarco.evelon.process.kind.UpdateProcess;
import dev.httpmarco.evelon.query.Query;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;

@AllArgsConstructor
public abstract class ProcessRunner<Q extends ProcessReference<Q>> {

    public Object apply(Layer<?> layer, @NotNull Query<?> query, @NotNull Process<?> process) {
        var base = generateBase();
        var repository = query.associatedRepository();

        if (query.filters().get(layer) != null) {
            process.appendFilters(query.filters().get(layer));
        }

        if (process instanceof UpdateProcess<?> updateProcess) {
            updateProcess.runMapping(repository.entry(), base);
            this.update(base);
            return null;
        } else if (process instanceof QueryProcess<?, ?> queryProcess) {
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