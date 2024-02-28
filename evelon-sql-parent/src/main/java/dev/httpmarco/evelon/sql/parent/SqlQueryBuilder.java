package dev.httpmarco.evelon.sql.parent;

import dev.httpmarco.evelon.common.model.ElementStage;
import dev.httpmarco.evelon.common.model.Model;
import dev.httpmarco.evelon.common.repository.RepositoryField;
import dev.httpmarco.evelon.common.repository.field.PrimaryRepositoryFieldImpl;
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

    public String tableQuery(String id) {
        return "CREATE TABLE IF NOT EXISTS " + id + "(" + String.join(", ",
                queryFields.stream().map(this::formatTableCreationLayout).toList()) + ");";
    }

    @SuppressWarnings("unchecked")
    private String formatTableCreationLayout(RepositoryField field) {
        var builder = new StringBuilder(field.id() + " ");
        builder.append(((ElementStage<?, SqlQueryBuilder, SqlType>) field.stage(model).asElementStage()).classBuilderType(field.fieldType()));
        if (field instanceof PrimaryRepositoryFieldImpl) {
            builder.append(" PRIMARY KEY");
        }
        return builder.toString();
    }
}
