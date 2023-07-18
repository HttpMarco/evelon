package net.bytemc.evelon.sql;

import java.lang.reflect.Field;

public record ForeignKey(String parentTable, Field foreignKey) {

    public String parentField() {
        return DatabaseHelper.getRowName(foreignKey);
    }

}
