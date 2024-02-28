package dev.httpmarco.evelon.sql.parent;

import dev.httpmarco.evelon.common.model.ElementStage;
import dev.httpmarco.evelon.common.model.Model;
import dev.httpmarco.evelon.common.repository.RepositoryField;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SqlQueryBuilder {

    private final Model<?> model;
    private final SqlQueryBuilder parent;

    private final List<RepositoryField> queryFields = new ArrayList<>();
    private final List<SqlQueryBuilder> subQuery = new ArrayList<>();

    private SqlQueryBuilder(Model model, SqlQueryBuilder parent) {
        this.parent = parent;
        this.model = model;
    }

    public static SqlQueryBuilder emptyInstance(Model<?> model) {
        return new SqlQueryBuilder(model, null);
    }

    public SqlQueryBuilder withField(RepositoryField field) {
        queryFields.add(field);
        return this;
    }

    public @Nullable SqlQueryBuilder parent() {
        return this.parent;
    }

    public SqlQueryBuilder subQuery() {
        return new SqlQueryBuilder(model, this);
    }

    @SuppressWarnings("unchecked")
    public String createTable(String id) {
        return "CREATE TABLE IF NOT EXISTS " + id + "(" + String.join(",",
                queryFields
                        .stream()
                        .map(it -> it.id() + " " + ((ElementStage<?, SqlQueryBuilder, SqlType>) it.stage(model).asElementStage()).classBuilderType())
                        .toList()) +
                ");";
    }
}
