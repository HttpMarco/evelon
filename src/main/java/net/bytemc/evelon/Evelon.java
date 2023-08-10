package net.bytemc.evelon;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Setter;
import lombok.SneakyThrows;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public final class Evelon {

    private static final Gson GSON = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
    private static final Path DEFAULT_CRADINATES_FILE = Path.of("evelon/cradinates.json");

    @Setter
    private static DatabaseCradinates databaseCradinates;

    @SneakyThrows
    public static DatabaseCradinates getDatabaseCradinates() {
        if (databaseCradinates == null) {
            if (!Files.exists(DEFAULT_CRADINATES_FILE)) {
                System.out.println("No Cradinates was defined in code, generating file...");

                final File file = DEFAULT_CRADINATES_FILE.toFile();
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                if (!file.exists() && file.createNewFile()) {
                    Files.writeString(file.toPath(), GSON.toJson(new DatabaseCradinates(
                        DatabaseProtocol.MARIADB,
                        "",
                        "",
                        "",
                        "",
                        3306
                    )));
                }
            }
            final DatabaseCradinates cradinates = GSON.fromJson(Files.readString(DEFAULT_CRADINATES_FILE), DatabaseCradinates.class);
            if (!cradinates.hostname().isEmpty() && !cradinates.user().isEmpty() &&
                !cradinates.password().isEmpty() && !cradinates.database().isEmpty()) {
                databaseCradinates = cradinates;
            } else {
                System.err.println("Database cradinates are null! Please set them before using the database! " +
                    "Evelon#setDatabaseCradinates(DatabaseCradinates) or fill in config-file (" + DEFAULT_CRADINATES_FILE.toAbsolutePath() + ")");
            }
        }
        return databaseCradinates;
    }
}
