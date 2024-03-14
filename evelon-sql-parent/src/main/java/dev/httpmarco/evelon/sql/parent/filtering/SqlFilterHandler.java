package dev.httpmarco.evelon.sql.parent.filtering;

import dev.httpmarco.evelon.common.filtering.Filter;
import dev.httpmarco.evelon.common.filtering.LayerFilterHandler;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class SqlFilterHandler implements LayerFilterHandler<String, Object> {
    @Override
    public Filter<String, Number> min(String id, int min) {
        //todo
        return null;
    }

    @Override
    public Filter<String, Number> max(String id, int max) {
        //todo
        return null;
    }

    @Override
    public Filter<String, Number> between(String id, int min, int max) {
        //todo
        return null;
    }

    @Override
    public Filter<String, Object> match(String id, Object value) {
        return new SqlFilter.Match(id, value);
    }

    @Override
    public Filter<String, Object> noneMatch(String id, Object value) {
        //todo
        return null;
    }

    @Override
    public Filter<String, String> like(String id, String value) {
        //todo
        return null;
    }

    @Override
    public Filter<String, Date> sameDate(String id, Date date, TimeUnit timeUnit) {
        //todo
        return null;
    }

    @Override
    public Filter<String, Date> betweenTime(String id, Date minDate, Date maxDate) {
        //todo
        return null;
    }

    @Override
    public Filter<String, Date> sameTime(String id, Date date) {
        //todo
        return null;
    }
}
