package net.bytemc.evelon.repository;

import net.bytemc.evelon.repository.filters.*;

public final class Filters {

    public static Filter min(String id, Number min) {
        return new MinimumFilter(id, min);
    }

    public static Filter max(String id, Number max) {
        return new MaximumFilter(id, max);
    }

    public static Filter between(String id, Number min, Number max) {
        return new BetweenFilter(id, min, max);
    }

    public static Filter match(String id, Object object) {
        return new MatchFilter(id, object);
    }

    public static Filter noneMatch(String id, Object object) {
        return new NoneMatchFilter(id, object);
    }

}
