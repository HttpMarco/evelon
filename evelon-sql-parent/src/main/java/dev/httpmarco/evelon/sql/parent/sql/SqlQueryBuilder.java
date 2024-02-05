package dev.httpmarco.evelon.sql.parent.sql;

import dev.httpmarco.evelon.common.repository.RepositoryField;
import dev.httpmarco.evelon.sql.parent.types.SqlTypeFinder;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SqlQueryBuilder {

    private final SqlQueryBuilder parent;

    private final List<RepositoryField> queryFields = new ArrayList<>();
    private final List<SqlQueryBuilder> subQuery = new ArrayList<>();

    private SqlQueryBuilder(SqlQueryBuilder parent) {
        this.parent = parent;
    }

    public static SqlQueryBuilder emptyInstance() {
        return new SqlQueryBuilder(null);
    }

    public SqlQueryBuilder withField(RepositoryField field) {
        queryFields.add(field);
        return this;
    }

    public @Nullable SqlQueryBuilder parent() {
        return this.parent;
    }

    public SqlQueryBuilder subQuery() {
        return new SqlQueryBuilder(this);
    }

    public String createTable(String id) {
        return "CREATE TABLE IF NOT EXISTS " + id + "(" + String.join(",",
                queryFields
                        .stream()
                        .map(field -> field.id() + " " + SqlTypeFinder.findType(field.fieldType()).getType())
                        .toList()) +
                ");";
    }
}
