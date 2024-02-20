package dev.httpmarco.evelon.common.local;

import dev.httpmarco.evelon.common.layers.EvelonLayer;
import dev.httpmarco.evelon.common.local.sync.LocalSynchronization;
import dev.httpmarco.evelon.common.repository.RepositoryBuilder;

import java.util.List;

public final class LocalStorageBuilder<T> extends RepositoryBuilder<T> {

    public LocalStorageBuilder(Class<T> clazz, List<Class<EvelonLayer<?>>> layerClasses) {
        super(clazz, true, layerClasses);
    }

    public void setSynchronizationAdapter(LocalSynchronization localSynchronization) {
        //todo
    }
}
