package dev.httpmarco.evelon.common.local;

import dev.httpmarco.evelon.common.filters.LayerFilterHandler;
import dev.httpmarco.evelon.common.local.filters.*;
import dev.httpmarco.evelon.common.filters.Filter;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public final class LocalFilterHandler implements LayerFilterHandler<Boolean, Object> {

    @Override
    public Filter<Boolean, Number> min(String id, int min) {
        return new MinimalLocalFilter(id, min);
    }

    @Override
    public Filter<Boolean, Number> max(String id, int max) {
        return new MaximalLocalFilter(id, max);
    }

    @Override
    public Filter<Boolean, Number> between(String id, int min, int max) {
        return new BetweenLocalFilter(id, min, max);
    }

    @Override
    public Filter<Boolean, Object> match(String id, Object value) {
        return new MatchLocalFilter(id, value);
    }

    @Override
    public Filter<Boolean, Object> noneMatch(String id, Object value) {
        return new NoneMatchFilter(id, value);
    }

    @Override
    public Filter<Boolean, String> like(String id, String value) {
        return new LikeLocalFilter(id, value);
    }

    @Override
    public Filter<Boolean, Date> sameDate(String id, Date date, TimeUnit timeUnit) {
        return new SameDateLocalFilter(id, date, timeUnit);
    }

    @Override
    public Filter<Boolean, Date> betweenTime(String id, Date minDate, Date maxDate) {
        return new BetweenTimeLocalFilter(id, minDate, maxDate);
    }

    @Override
    public Filter<Boolean, Date> sameTime(String id, Date date) {
        return new SameDateLocalFilter(id, date, TimeUnit.MILLISECONDS);
    }
}
