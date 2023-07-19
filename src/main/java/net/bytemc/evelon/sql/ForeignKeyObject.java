package net.bytemc.evelon.sql;

import net.bytemc.evelon.misc.Reflections;

import java.lang.reflect.Field;

public record ForeignKeyObject(String id, String value) {

    public static ForeignKeyObject of(Field primaryField, Object parent) {
        return new ForeignKeyObject(DatabaseHelper.getRowName(primaryField), "'" + Reflections.readField(parent, primaryField).toString() + "'");
    }
}
