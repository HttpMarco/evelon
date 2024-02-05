package dev.httpmarco.evelon.sql.parent.types;

public class SqlTypeFinder {

    public static SqlType findType(Class<?> clazz) {
        return SqlType.CACHED_TYPES
                .stream()
                .findFirst()
                .filter(it -> it.getCompatibleClasses().contains(clazz))
                .orElse(SqlType.UNKNOWN);
    }
}
