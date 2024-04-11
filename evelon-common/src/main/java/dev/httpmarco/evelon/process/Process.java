package dev.httpmarco.evelon.process;

import dev.httpmarco.evelon.layer.Layer;
import dev.httpmarco.evelon.repository.Repository;

public interface Process {

    void run(Repository<?> repository, Layer layer);

}
