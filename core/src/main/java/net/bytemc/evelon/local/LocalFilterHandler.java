package net.bytemc.evelon.local;

import net.bytemc.evelon.filters.Filter;
import net.bytemc.evelon.filters.LayerFilterHandler;

public class LocalFilterHandler implements LayerFilterHandler {

    @Override
    public Filter min(String id, int min) {
        return null;
    }

    @Override
    public Filter max(String id, int max) {
        return null;
    }

    @Override
    public Filter between(String id, int min, int max) {
        return null;
    }

    @Override
    public Filter match(String id, Object value) {
        return null;
    }

    @Override
    public Filter nonMatch(String id, Object values) {
        return null;
    }

    @Override
    public Filter like(String id, String value) {
        return null;
    }

    @Override
    public Filter sameDay(String id, String date) {
        return null;
    }

    @Override
    public Filter sameMonth(String id, String date) {
        return null;
    }

    @Override
    public Filter sameYear(String id, String date) {
        return null;
    }

    @Override
    public Filter sameWeek(String id, String date) {
        return null;
    }

    @Override
    public Filter sameQuarter(String id, String date) {
        return null;
    }

    @Override
    public Filter sameHour(String id, String date) {
        return null;
    }

    @Override
    public Filter sameMinute(String id, String date) {
        return null;
    }

    @Override
    public Filter sameSecond(String id, String date) {
        return null;
    }

    @Override
    public Filter sameMillisecond(String id, String date) {
        return null;
    }

    @Override
    public Filter betweenTime(String id, String date1, String date2) {
        return null;
    }

    @Override
    public Filter sameTime(String id, String date) {
        return null;
    }
}
