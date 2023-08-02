package net.bytemc.evelon;

import lombok.Setter;

public final class Evelon {

    @Setter
    private static DatabaseCradinates databaseCradinates;

    public static DatabaseCradinates getDatabaseCradinates() {
        if(databaseCradinates == null) {
            System.err.println("Database cradinates are null! Please set them before using the database! Evelon#setDatabaseCradinates(DatabaseCradinates)");
        }
        return databaseCradinates;
    }
}
