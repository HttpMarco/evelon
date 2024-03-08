package dev.httpmarco.evelon.sql.parent.builder;

import dev.httpmarco.evelon.common.builder.BuilderType;
import dev.httpmarco.evelon.common.builder.impl.AbstractBuilder;
import dev.httpmarco.evelon.common.query.response.QueryResponse;
import dev.httpmarco.evelon.common.repository.RepositoryField;
import dev.httpmarco.evelon.common.repository.clazz.RepositoryClass;
import dev.httpmarco.evelon.common.repository.field.PrimaryRepositoryFieldImpl;
import dev.httpmarco.evelon.sql.parent.SqlType;
import dev.httpmarco.evelon.sql.parent.connection.HikariConnection;
import dev.httpmarco.evelon.sql.parent.model.SqlModel;
import dev.httpmarco.osgan.utils.exceptions.NotImplementedException;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public final class SqlQueryBuilder extends AbstractBuilder<SqlQueryBuilder, SqlModel, HikariConnection> {

    private static final String TABLE_CREATION_QUERY = "CREATE TABLE IF NOT EXISTS %s(%s);";
    private static final String VALUE_CREATION_QUERY = "INSERT INTO %s(%s) VALUES (%s);";
    private static final String VALUE_DELETION_QUERY = "DELETE FROM %s;";

    // table initialize options
    private final List<RepositoryField<?>> rowTypes = new ArrayList<>();
    private final List<PrimaryRepositoryFieldImpl<?>> primaryLinking = new ArrayList<>();

    // value options
    private final List<Object> queryArguments = new ArrayList<>();

    // filter options
    // todo

    public SqlQueryBuilder(String id, SqlModel model, BuilderType type, SqlQueryBuilder parent) {
        super(id, model, parent, type);
    }

    public static SqlQueryBuilder emptyInstance(String id, SqlModel model, BuilderType type) {
        return new SqlQueryBuilder(id, model, type, null);
    }

    public <T> void addRowType(RepositoryField<T> field) {
        this.rowTypes.add(field);
    }

    @Override
    public void linkPrimaries(PrimaryRepositoryFieldImpl<?>... fields) {
        this.primaryLinking.addAll(Arrays.asList(fields));
    }

    @Override
    public SqlQueryBuilder subBuilder(String subId) {
        var builder = new SqlQueryBuilder(id() + "_" + subId, model(), type(), this);
        this.children().add(builder);
        return builder;
    }

    @Override
    public SqlQueryBuilder subBuilder(String subId, RepositoryClass<?> parent) {
        var builder = new SqlQueryBuilder(id() + "_" + subId, model(), type(), this);
        this.children().add(builder);
        builder.linkPrimaries(parent.asObjectClass().primaryFields());
        return builder;
    }

    @Override
    public QueryResponse push(HikariConnection connection) {
        var transmitter = connection.transmitter();
        var response = switch (type()) {
            case INITIALIZE -> transmitter.executeUpdate(buildTableInitializeQuery());
            case CREATION -> transmitter.executeUpdate(buildValueCreationQuery(), queryArguments);
            case DELETION -> transmitter.executeUpdate(buildValueDeleteQuery());
        };
        for (var child : this.children()) {
            response.append(child.push(connection));
        }
        return response;
    }

    private String buildTableInitializeQuery() {
        // append foreign key types (default: h2 implementation)
        var parameters = new ArrayList<>(primaryLinking.stream().map(it -> it.id() + " " + SqlType.find(it.clazz().clazz())).toList());

        // add all default values
        parameters.addAll(rowTypes.stream().map(it -> it.id() + " " + SqlType.find(it.clazz().clazz())).toList());

        if (rowTypes.stream().anyMatch(it -> it instanceof PrimaryRepositoryFieldImpl)) {
            parameters.add("PRIMARY KEY (" + collectTypes(false) + ")");
        }

        if (parent() != null && primaryLinking.isEmpty()) {
            System.err.println(parent().getClass().getSimpleName());
            // todo: need object ids to link
            throw new NotImplementedException("Cannot link primary keys without parent");
        } else {
            // add foreign key linking
            parameters.addAll(primaryLinking.stream().map(it -> "foreign key (" + it.id() + ") references " + parent().id() + "(" + it.id() + ")").toList());
        }

        return TABLE_CREATION_QUERY.formatted(id(), String.join(", ", parameters));
    }

    private String buildValueCreationQuery() {
        var parameterValueQuery = new ArrayList<String>();

        for (var i = 0; i < rowTypes.size(); i++) {
            queryArguments.add(values().get(i));
            parameterValueQuery.add("?");
        }

        for (var repositoryField : primaryLinking) {
            parameterValueQuery.add("?");
        }

        return VALUE_CREATION_QUERY.formatted(id(), collectTypes(true), String.join(", ", parameterValueQuery));
    }

    private String buildValueDeleteQuery() {
        return VALUE_DELETION_QUERY.formatted(id());
    }

    private String collectTypes(boolean withPrimaries) {
        return String.join(", ", rowTypes.stream().filter(it -> withPrimaries || it instanceof PrimaryRepositoryFieldImpl).map(RepositoryField::id).toList());
    }
}