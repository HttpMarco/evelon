package dev.httpmarco.evelon.sql.parent;

import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.Arrays;
import java.util.List;

@Getter
@Accessors(fluent = true)
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
    CHAR,
    TINYTEXT,
    TEXT(String.class),
    MEDIUMTEXT,
    LONGTEXT,
    JSON,
    UUID,

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

    private String type;
    private List<Class<?>> compatibleClasses;

    SqlType(String type) {
        this.type = type;
    }

    SqlType(Class<?>... compatibleClasses) {
        this.compatibleClasses = List.of(compatibleClasses);
    }

    SqlType() {
        this.type = this.name();
    }

    public static SqlType find(Class<?> clazz) {
        return CACHED_TYPES.stream().filter(type -> type.compatibleClasses != null && type.compatibleClasses.contains(clazz)).findFirst().orElse(UNKNOWN);
    }
}
