package dev.httpmarco.evelon.common.process;

import dev.httpmarco.evelon.common.model.Model;
import dev.httpmarco.evelon.common.process.impl.ConstructProcess;
import dev.httpmarco.evelon.common.process.impl.CreateProcess;
import dev.httpmarco.evelon.common.process.impl.InitializeProcess;
import dev.httpmarco.evelon.common.repository.Repository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
@AllArgsConstructor
public abstract class Build<Q> {

    private final Repository<?> repository;
    private final Model model;

    public abstract InitializeProcess<Q> initialize();

    public abstract CreateProcess create();

    public abstract ConstructProcess construct();

}
