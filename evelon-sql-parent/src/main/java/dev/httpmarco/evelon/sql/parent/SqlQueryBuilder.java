package dev.httpmarco.evelon.sql.parent;

import dev.httpmarco.evelon.common.builder.AbstractDefaultBuilder;
import dev.httpmarco.evelon.common.builder.Builder;
import dev.httpmarco.evelon.common.model.ElementStage;
import dev.httpmarco.evelon.common.model.Model;
import dev.httpmarco.evelon.common.repository.RepositoryField;
import dev.httpmarco.evelon.common.repository.field.PrimaryRepositoryFieldImpl;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public final class SqlQueryBuilder extends AbstractDefaultBuilder<SqlQueryBuilder> {

    private final SqlQueryBuilder parent;
    private final List<SqlQueryBuilder> subQuery = new ArrayList<>();

    private SqlQueryBuilder(Model<SqlQueryBuilder> model, SqlQueryBuilder parent) {
        super(model);
        this.parent = parent;
    }

    public static SqlQueryBuilder emptyInstance(Model<SqlQueryBuilder> model) {
        return new SqlQueryBuilder(model, null);
    }

    public SqlQueryBuilder withField(RepositoryField field) {
        queryFields().add(field);
        return this;
    }

    public @Nullable SqlQueryBuilder parent() {
        return this.parent;
    }

    public String tableQuery(String id) {
        return "CREATE TABLE IF NOT EXISTS " + id + "(" + String.join(", ",
                queryFields().stream().map(this::formatTableCreationLayout).toList()) + ");";
    }

    @SuppressWarnings("unchecked")
    private String formatTableCreationLayout(RepositoryField field) {
        var builder = new StringBuilder(field.id() + " ");
        builder.append(((ElementStage<?, SqlQueryBuilder, SqlType>) field.stage(model()).asElementStage()).classBuilderType(field.fieldType()));
        if (field instanceof PrimaryRepositoryFieldImpl) {
            builder.append(" PRIMARY KEY");
        }
        return builder.toString();
    }

    @Override
    public Builder subBuilder() {
        return new SqlQueryBuilder(model(), this);
    }
}
