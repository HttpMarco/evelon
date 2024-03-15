package dev.httpmarco.evelon.common.process;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
@AllArgsConstructor
public abstract class Process<Q> implements Runnable {

    private Build<Q> build;

    // id can be the repository name, but also a substage id
    private final String id;

    private ProcessState state = ProcessState.NOT_STARTED;

    public Process(Build<Q> build) {
        this.build = build;
        this.id = build.repository().name();
    }

    public void preQuery(){}

    public void postQuery(){}

    public abstract void execute();

}
