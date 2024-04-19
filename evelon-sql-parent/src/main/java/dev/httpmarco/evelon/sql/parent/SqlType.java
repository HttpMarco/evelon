package dev.httpmarco.evelon.sql.parent;

import dev.httpmarco.evelon.repository.RepositoryEntry;

import java.util.Arrays;
import java.util.List;

public enum SqlType {

    TINYINT(byte.class, Byte.class),
    SMALLINT,
    MEDIUMINT,
    INT(int.class, Integer.class),
    BIGINT(long.class, Long.class),
    BIT,

    FLOAT(float.class, Float.class),
    DOUBLE(double.class, Double.class),
    DECIMAL,
    BOOL(boolean.class, Boolean.class),

    VARCHAR("VARCHAR(%d)"),
    CHAR(char.class),
    TINYTEXT,
    TEXT(String.class),
    MEDIUMTEXT,
    LONGTEXT,
    JSON,
    UUID(java.util.UUID.class),

    BINARY,
    VARBINARY,
    TINYBLOB,
    BLOB,
    MEDIUMBLOB,
    LONGBLOB,

    DATE,
    TIME,
    YEAR,
    DATETIME,
    TIMESTAMP,

    POINT,
    LINESTRING,
    POLYGON,
    GEOMETRY,
    MULTIPOINT,
    MULTILINESTRING,
    MULTIPOLYGON,
    GEOMETRYCOLLECTION,

    UNKNOWN,
    ENUM("ENUM(%s)"),
    SET;

    public static final List<SqlType> CACHED_TYPES = Arrays.stream(values()).toList();

    private final String format;
    private List<Class<?>> compatibleClasses;

    SqlType(String type) {
        this.format = type;
    }

    SqlType(Class<?>... compatibleClasses) {
        this();
        this.compatibleClasses = List.of(compatibleClasses);
    }

    SqlType() {
        this.format = this.name();
    }

    public static SqlType detect(RepositoryEntry entry) {
        return CACHED_TYPES.stream().filter(type -> type.compatibleClasses != null && type.compatibleClasses.contains(entry.clazz())).findFirst().orElse(UNKNOWN);
    }

    @Override
    public String toString() {
        return this.format;
    }
}
