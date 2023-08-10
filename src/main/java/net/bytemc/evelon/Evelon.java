package net.bytemc.evelon;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import net.bytemc.evelon.cradinates.DatabaseCradinates;
import net.bytemc.evelon.cradinates.EvelonConfig;
import net.bytemc.evelon.cradinates.PasswordDecoder;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public final class Evelon {

    private static final Gson GSON = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
    private static final Path DEFAULT_CRADINATES_FILE = Path.of("evelon/cradinates.json");

    @Setter
    @Getter
    private static EvelonConfig config;

    @Setter
    private static DatabaseCradinates databaseCradinates;

    @SneakyThrows
    public static DatabaseCradinates getDatabaseCradinates() {
        if (databaseCradinates == null) {
            final Path path = (config.getConfigPath() != null ? config.getConfigPath() : DEFAULT_CRADINATES_FILE);
            if (!Files.exists(path)) {
                System.out.println("No Cradinates was defined in code, generating file...");

                final File file = path.toFile();
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
            final DatabaseCradinates fileCradinates = GSON.fromJson(Files.readString(path), DatabaseCradinates.class);
            if (!fileCradinates.hostname().isEmpty()) {
                final PasswordDecoder decoder = config.getPasswordDecoder();
                databaseCradinates = new DatabaseCradinates(
                    fileCradinates.databaseProtocol(),
                    fileCradinates.hostname(),
                    (decoder != null ? decoder.decode(fileCradinates.password()) : fileCradinates.password()),
                    fileCradinates.user(),
                    fileCradinates.database(),
                    fileCradinates.port()
                );
            } else {
                System.err.println("Database cradinates are null! Please set them before using the database! " +
                    "Evelon#setDatabaseCradinates(DatabaseCradinates) or fill in config-file (" + config.getConfigPath().toAbsolutePath() + ")");
            }
        }
        return databaseCradinates;
    }
}
