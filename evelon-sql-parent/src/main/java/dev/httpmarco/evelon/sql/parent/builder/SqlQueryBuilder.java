package dev.httpmarco.evelon.sql.parent.builder;

import dev.httpmarco.evelon.common.builder.BuilderType;
import dev.httpmarco.evelon.common.builder.impl.AbstractBuilder;
import dev.httpmarco.evelon.common.repository.RepositoryField;
import dev.httpmarco.evelon.common.repository.field.ForeignLinkingRepositoryFieldImpl;
import dev.httpmarco.evelon.common.repository.field.PrimaryRepositoryFieldImpl;
import dev.httpmarco.evelon.sql.parent.SqlType;
import dev.httpmarco.evelon.sql.parent.connection.HikariConnection;
import dev.httpmarco.evelon.sql.parent.model.SqlModel;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public final class SqlQueryBuilder extends AbstractBuilder<SqlQueryBuilder, SqlModel, HikariConnection> {

    private static final String TABLE_CREATION_QUERY = "CREATE TABLE IF NOT EXISTS %s(%s);";

    // table initialize options
    private final List<RepositoryField> rowTypes = new ArrayList<>();
    private final List<ForeignLinkingRepositoryFieldImpl> primaryLinking = new ArrayList<>();

    // value options
    // todo

    // filter options
    // todo

    public SqlQueryBuilder(String id, SqlModel model, BuilderType type, SqlQueryBuilder parent) {
        super(id, model, parent, type);
    }

    public static SqlQueryBuilder emptyInstance(String id, SqlModel model, BuilderType type) {
        return new SqlQueryBuilder(id, model, type, null);
    }

    public void addRowType(RepositoryField field) {
        this.rowTypes.add(field);
    }

    @Override
    public void linkPrimaries(PrimaryRepositoryFieldImpl... fields) {
        this.primaryLinking.addAll(Arrays.asList(fields));
    }

    @Override
    public SqlQueryBuilder subBuilder(String subId) {
        SqlQueryBuilder builder = new SqlQueryBuilder(id() + "_" + subId, model(), type(), this);
        this.children().add(builder);
        return builder;
    }

    @Override
    public void push(HikariConnection connection) {
        switch (type()) {
            case INITIALIZE:
                connection.transmitter().executeUpdate(buildTableInitializeQuery());
                for (var child : this.children()) {
                    child.push(connection);
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + type());
        }
    }

    private String buildTableInitializeQuery() {

        // append foreign key types (default: h2 implementation)
        var parameters = new ArrayList<>(primaryLinking.stream().map(it -> it.id() + " " + SqlType.find(it.clazz().clazz())).toList());

        // add all default values
        parameters.addAll(rowTypes.stream().map(it -> {
            var queryParameter = it.id() + " " + SqlType.find(it.clazz().clazz());

            if (it instanceof PrimaryRepositoryFieldImpl) {
                queryParameter = queryParameter + " PRIMARY KEY";
            }
            return queryParameter;
        }).toList());

        // add foreign key linking // todo
        parameters.addAll(primaryLinking.stream().map(it -> "foreign key (" + it.id() + ") references " + parent().id() + "(" + it.id() + ")").toList());

        return TABLE_CREATION_QUERY.formatted(id(), String.join(", ", parameters));
    }
}
