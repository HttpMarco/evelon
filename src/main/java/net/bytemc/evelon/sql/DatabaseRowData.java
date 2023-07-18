package net.bytemc.evelon.sql;

/**
 * @param name The name of the row
 * @param type The type of the row
 * @param key The key of the row, primary or foreign
 */
public record DatabaseRowData(String name, DatabaseType type, DatabaseRowData.KEY key) {

    public enum KEY {
        PRIMARY,
        FOREIGN
    }
}
