package net.bytemc.evelon.sql;

import lombok.Getter;

import java.util.Arrays;

public enum DatabaseType {

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

    VARCHAR("VARCHAR(%s)"),
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

    private String type;
    @Getter
    private Class<?>[] compatibleClasses;

    DatabaseType(String type) {
        this.type = type;
    }

    DatabaseType(Class<?>... compatibleClasses) {
        this.compatibleClasses = compatibleClasses;
    }

    DatabaseType() {
        this.type = this.name();
    }

    public String type() {
        return type == null ? this.name() : type;
    }

    public static DatabaseType getType(Class<?> clazz, DatabaseType... databaseType) {
        return Arrays.stream(databaseType).filter(it -> Arrays.stream(it.getCompatibleClasses()).anyMatch(s -> s.equals(clazz))).findFirst().orElse(null);
    }
}
