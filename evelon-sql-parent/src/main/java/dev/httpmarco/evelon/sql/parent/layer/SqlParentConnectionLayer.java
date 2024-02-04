package dev.httpmarco.evelon.sql.parent.layer;

import dev.httpmarco.evelon.common.filters.LayerFilterHandler;
import dev.httpmarco.evelon.common.layers.ConnectableEvelonLayer;
import dev.httpmarco.evelon.common.model.Model;
import dev.httpmarco.evelon.common.query.DataQuery;
import dev.httpmarco.evelon.common.query.SortedOrder;
import dev.httpmarco.evelon.sql.parent.connection.HikariConnection;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.sql.Connection;
import java.util.List;
import java.util.function.Predicate;

@Getter
@Accessors(fluent = true)
public final class SqlParentConnectionLayer implements ConnectableEvelonLayer<Object, Connection> {

    private final HikariConnection connection;

    public SqlParentConnectionLayer() {
        this.connection = new HikariConnection(null);
    }

    @Override
    public void preConnection() {

    }

    @Override
    public void postConnection() {

    }

    @Override
    public void create(DataQuery<Object> query, Object value) {

    }

    @Override
    public void createIfNotExists(DataQuery<Object> query, Object value) {

    }

    @Override
    public void deleteAll(DataQuery<Object> query) {

    }

    @Override
    public void delete(DataQuery<Object> query, Object value) {

    }

    @Override
    public void update(DataQuery<Object> query, Object value) {

    }

    @Override
    public void updateIf(DataQuery<Object> query, Object value, Predicate<Object> predicate) {

    }

    @Override
    public void upsert(DataQuery<Object> query, Object value) {

    }

    @Override
    public List<Object> findAll(DataQuery<Object> query) {
        return null;
    }

    @Override
    public List<Object> findAll(DataQuery<Object> query, int limit) {
        return null;
    }

    @Override
    public Object find(DataQuery<Object> query) {
        return null;
    }

    @Override
    public boolean exists(DataQuery<Object> query) {
        return false;
    }

    @Override
    public long count(DataQuery<Object> query) {
        return 0;
    }

    @Override
    public long sum(DataQuery<Object> query, String id) {
        return 0;
    }

    @Override
    public double avg(DataQuery<Object> query, String id) {
        return 0;
    }

    @Override
    public List<Object> order(DataQuery<Object> query, String id, int limit, SortedOrder order) {
        return null;
    }

    @Override
    public <E> List<E> collect(DataQuery<Object> query, String id, Class<E> clazz) {
        return null;
    }

    @Override
    public <E> List<E> collect(DataQuery<Object> query, String id, int limit, Class<E> clazz) {
        return null;
    }

    @Override
    public <E> E collectSingle(DataQuery<Object> query, String id, Class<E> clazz) {
        return null;
    }

    @Override
    public Object max(DataQuery<Object> query, String id) {
        return null;
    }

    @Override
    public Object min(DataQuery<Object> query, String id) {
        return null;
    }

    @Override
    public LayerFilterHandler<?, ?> filterHandler() {
        return null;
    }

    @Override
    public Model model() {
        return null;
    }
}
