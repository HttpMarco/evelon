package dev.httpmarco.evelon.sql.parent.layer.process;

import dev.httpmarco.evelon.process.common.ConstructProcess;
import dev.httpmarco.evelon.query.response.QueryResponse;
import dev.httpmarco.evelon.repository.Repository;

import java.awt.*;
import java.util.List;

public class SqlConstructProcess<T> extends ConstructProcess<T> {

    private final String VALUE_QUERY = "SELECT * FROM %s";

    public SqlConstructProcess(String id, Repository<T> repository, int limit) {
        super(id, repository, limit);
    }

    @Override
    public QueryResponse<List<T>> queryConstruct() {
        var queryResponse = new QueryResponse<List<T>>();
        System.out.println(VALUE_QUERY.formatted(id()));
        queryResponse.result(List.of());
        return queryResponse;
    }
}
