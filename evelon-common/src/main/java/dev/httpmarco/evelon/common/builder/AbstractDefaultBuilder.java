package dev.httpmarco.evelon.common.builder;

import dev.httpmarco.evelon.common.model.Model;
import dev.httpmarco.evelon.common.repository.RepositoryField;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Accessors(fluent = true)
@Getter
@AllArgsConstructor
public abstract class AbstractDefaultBuilder<T extends Builder> implements Builder {

    private final Model<T> model;
    private final List<RepositoryField> queryFields = new ArrayList<>();

}
