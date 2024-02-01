package dev.httpmarco.evelon.h2.layer;

import dev.httpmarco.evelon.common.filters.LayerFilterHandler;
import dev.httpmarco.evelon.common.misc.SortedOrder;
import dev.httpmarco.evelon.common.model.Model;
import dev.httpmarco.evelon.common.query.DataQuery;
import dev.httpmarco.evelon.sql.parent.layer.AbstractSqlLayer;

import java.util.List;
import java.util.function.Predicate;


public class H2RepositoryLayer extends AbstractSqlLayer {

    public H2RepositoryLayer(LayerFilterHandler<?, ?> filterHandler, Model model) {
        super(filterHandler, model);
    }

    @Override
    public <T> void create(DataQuery<T> query, T value) {

    }

    @Override
    public <T> void createIfNotExists(DataQuery<T> query, T value) {

    }

    @Override
    public <T> void deleteAll(DataQuery<T> query) {

    }

    @Override
    public <T> void delete(DataQuery<T> query, T value) {

    }

    @Override
    public <T> void update(DataQuery<T> query, T value) {

    }

    @Override
    public <T> void updateIf(DataQuery<T> query, T value, Predicate<T> predicate) {

    }

    @Override
    public <T> void upsert(DataQuery<T> query, T value) {

    }

    @Override
    public <T> List<T> findAll(DataQuery<T> query) {
        return null;
    }

    @Override
    public <T> List<T> findAll(DataQuery<T> query, int limit) {
        return null;
    }

    @Override
    public <T> T find(DataQuery<T> query) {
        return null;
    }

    @Override
    public <T> boolean exists(DataQuery<T> query) {
        return false;
    }

    @Override
    public <T> long count(DataQuery<T> query) {
        return 0;
    }

    @Override
    public <T> long sum(DataQuery<T> query, String id) {
        return 0;
    }

    @Override
    public <T> double avg(DataQuery<T> query, String id) {
        return 0;
    }

    @Override
    public <T> List<T> order(DataQuery<T> query, String id, int limit, SortedOrder order) {
        return null;
    }

    @Override
    public <E, T> List<E> collect(DataQuery<T> query, String id, Class<E> clazz) {
        return null;
    }

    @Override
    public <E, T> List<E> collect(DataQuery<T> query, String id, int limit, Class<E> clazz) {
        return null;
    }

    @Override
    public <E, T> E collectSingle(DataQuery<T> query, String id, Class<E> clazz) {
        return null;
    }

    @Override
    public <T> T max(DataQuery<T> query, String id) {
        return null;
    }

    @Override
    public <T> T min(DataQuery<T> query, String id) {
        return null;
    }

}
