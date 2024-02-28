package dev.httpmarco.evelon.sql.parent;

import dev.httpmarco.evelon.common.builder.AbstractDefaultBuilder;
import dev.httpmarco.evelon.common.model.ElementStage;
import dev.httpmarco.evelon.common.model.Model;
import dev.httpmarco.evelon.common.repository.RepositoryField;
import dev.httpmarco.evelon.common.repository.field.PrimaryRepositoryFieldImpl;
import dev.httpmarco.evelon.sql.parent.connection.HikariConnection;

public final class SqlQueryBuilder extends AbstractDefaultBuilder<SqlQueryBuilder> {

    private SqlQueryBuilder(String id, Model<SqlQueryBuilder> model, SqlQueryBuilder parent) {
        super(id, model, parent);
    }

    public static SqlQueryBuilder emptyInstance(String id, Model<SqlQueryBuilder> model) {
        return new SqlQueryBuilder(id, model, null);
    }

    public SqlQueryBuilder withField(RepositoryField field) {
        queryFields().add(field);
        return this;
    }

    private String tableQuery(String id) {
        var builder = new StringBuilder("CREATE TABLE IF NOT EXISTS %s (");


        builder.append(String.join(",", this.queryFields().stream().map(this::formatTableCreationLayout).toList()));

        if (!foreignLinking().isEmpty()) {
            builder.append(", ");
            builder.append(String.join(",", this.foreignLinking().stream().map(this::formatTableCreationLayout).toList()));
            builder.append(", ");
            builder.append(String.join(", ", foreignLinking().stream().map(it -> "FOREIGN KEY(" + it.id() + ") REFERENCES NULL(" + it.id() + ") ON DELETE CASCADE" ).toList()));
        }

        return builder.append(");").toString().formatted(id);
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

    public void executeTableQuery(HikariConnection connection) {
        connection.transmitter().executeUpdate(tableQuery(id()));

        for (var child : this.children()) {
            child.executeTableQuery(connection);
        }
    }

    @Override
    public SqlQueryBuilder subBuilder(String id) {
        var children = new SqlQueryBuilder(id() + "_" + id, model(), this);
        this.children().add(children);
        return children;
    }
}
