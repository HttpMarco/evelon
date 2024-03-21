package dev.httpmarco.evelon.sql.parent.layer.process;

import dev.httpmarco.evelon.layer.Layer;
import dev.httpmarco.evelon.process.common.ConstructProcess;
import dev.httpmarco.evelon.query.Query;
import dev.httpmarco.evelon.query.response.QueryResponse;
import dev.httpmarco.evelon.repository.RepositoryObjectClass;
import dev.httpmarco.evelon.sql.parent.layer.connection.HikariConnectionTransmitter;
import dev.httpmarco.evelon.sql.parent.layer.filtering.SqlFilter;
import dev.httpmarco.osgan.reflections.Reflections;

import java.util.ArrayList;
import java.util.List;

public class SqlConstructProcess<T> extends ConstructProcess<T> {

    private final HikariConnectionTransmitter transmitter;
    // todo limit parameter
    private static final String VALUE_QUERY = "SELECT * FROM %s%s;";

    public SqlConstructProcess(HikariConnectionTransmitter transmitter, Query<T> query, int limit) {
        super(query.repository().clazz(), query.repository().name(), query, limit);
        this.transmitter = transmitter;
    }

    @Override
    public QueryResponse<List<T>> queryConstruct(Layer layer) {
        var queryResponse = new QueryResponse<List<T>>();
        queryResponse.append(this.transmitter.executeQuery(VALUE_QUERY.formatted(id(), SqlFilter.convertFilters(repository(), query().filters(layer))), resultSet -> {
            var list = new ArrayList<T>();
            while (resultSet.next()) {
                var object = new Reflections<>(reconstructClass().originalClass()).allocate();
                if (reconstructClass() instanceof RepositoryObjectClass<?> objectClass) {
                    for (var field : objectClass.fields()) {
                        new Reflections<>(object.getClass()).withValue(object).modify(field.field(), resultSet.getObject(field.id()));
                    }
                }
                list.add((T) object);
            }
            return list;
        }, this));
        return queryResponse;
    }
}
