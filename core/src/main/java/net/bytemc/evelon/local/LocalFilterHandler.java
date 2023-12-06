package net.bytemc.evelon.local;

import net.bytemc.evelon.filters.Filter;
import net.bytemc.evelon.filters.LayerFilterHandler;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public final class LocalFilterHandler implements LayerFilterHandler<Boolean, Object> {

    @Override
    public Filter<Boolean, Object> min(String id, int min) {
        return null;
    }

    @Override
    public Filter<Boolean, Object> max(String id, int max) {
        return null;
    }

    @Override
    public Filter<Boolean, Object> between(String id, int min, int max) {
        return null;
    }

    @Override
    public Filter<Boolean, Object> match(String id, Object value) {
        return null;
    }

    @Override
    public Filter<Boolean, Object> nonMatch(String id, Object values) {
        return null;
    }

    @Override
    public Filter<Boolean, Object> like(String id, String value) {
        return null;
    }

    @Override
    public Filter<Boolean, Object> someDate(String id, Date date, TimeUnit timeUnit) {
        return null;
    }

    @Override
    public Filter<Boolean, Object> betweenTime(String id, Date date, String date2) {
        return null;
    }

    @Override
    public Filter<Boolean, Object> sameTime(String id, Date date) {
        return null;
    }
}
