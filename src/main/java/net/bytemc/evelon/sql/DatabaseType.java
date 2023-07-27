/*
 * Copyright 2019-2023 ByteMC team & contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
