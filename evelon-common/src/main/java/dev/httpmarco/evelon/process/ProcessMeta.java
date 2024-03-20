package dev.httpmarco.evelon.process;

import dev.httpmarco.evelon.repository.Repository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
@Getter
@AllArgsConstructor
public class ProcessMeta {

    private final Repository<?> repository;

}
