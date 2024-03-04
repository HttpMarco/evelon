package dev.httpmarco.evelon.sql.parent.builder;

import dev.httpmarco.evelon.common.builder.BuilderType;
import dev.httpmarco.evelon.common.builder.impl.AbstractBuilder;
import dev.httpmarco.evelon.common.repository.clazz.RepositoryClass;
import dev.httpmarco.evelon.sql.parent.SqlType;
import dev.httpmarco.evelon.sql.parent.connection.HikariConnection;
import dev.httpmarco.evelon.sql.parent.model.SqlModel;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public final class SqlQueryBuilder extends AbstractBuilder<SqlQueryBuilder, SqlModel, HikariConnection> {

    private static final String TABLE_CREATION_QUERY = "CREATE TABLE IF NOT EXISTS %s(%s);";

    // table initialize options
    private final List<RepositoryClass<?>> rowTypes = new ArrayList<>();

    // value options
    // todo

    // filter options
    // todo

    public SqlQueryBuilder(String id, SqlModel model, BuilderType type) {
        super(id, model, type);
    }

    public static SqlQueryBuilder emptyInstance(String id, SqlModel model, BuilderType type) {
        return new SqlQueryBuilder(id, model, type);
    }

    public void addRowType(RepositoryClass<?> repositoryClass) {
        this.rowTypes.add(repositoryClass);
    }

    @Override
    public SqlQueryBuilder subBuilder(String subId) {
        return new SqlQueryBuilder(subId + "_" + subId, model(), type());
    }

    @Override
    public void push(HikariConnection connection) {
        switch (type()) {
            case INITIALIZE:
                connection.transmitter().executeUpdate(buildTableInitializeQuery());
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + type());
        }
    }

    private String buildTableInitializeQuery() {
        var parameters = String.join(", ", rowTypes.stream().map(it -> SqlType.find(it.clazz()).getType()).toList());
        return TABLE_CREATION_QUERY.formatted(id(), parameters);
    }
}
