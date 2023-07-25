package net.bytemc.evelon.sql;

public final class Schema {

    public static String encloseSchema(Object value) {
        return "'" + value + "'";
    }

}
