package dev.httpmarco.evelon.layer;

import dev.httpmarco.evelon.process.ProcessRunner;

public interface Layer<Q> {

    String id();

    ProcessRunner<Q> runner();

}
