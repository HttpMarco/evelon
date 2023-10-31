package net.bytemc.evelon;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import net.bytemc.evelon.cradinates.DatabaseCradinates;
import net.bytemc.evelon.cradinates.EvelonConfig;

import java.nio.file.Files;
import java.nio.file.Path;

public final class Evelon {

    private static final Gson GSON = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
    private static final Path DEFAULT_CONFIG_PATH = Path.of("configs/evelon-config.json");

    @Setter
    @Getter
    private static EvelonConfig config;

    private static DatabaseCradinates DATABASE_CRADINATES;

    public static void setCradinates(DatabaseProtocol databaseProtocol, String hostname, String password, String user, String database, int port) {
        setCradinates(new DatabaseCradinates(databaseProtocol, hostname, password, user, database, port));
    }

    public static void setCradinates(DatabaseCradinates databaseCradinates) {
        if (DATABASE_CRADINATES != null) {
            throw new UnsupportedOperationException("Database cradinates have already been set. Maybe you set useThisConfig=true in the evlon-config.json?");
        }

        DATABASE_CRADINATES = databaseCradinates;
        StorageHandler.initStorage(databaseCradinates.databaseProtocol());
    }

    @SneakyThrows
    public static DatabaseCradinates getCradinates() {
        if (DATABASE_CRADINATES == null) {
            EvelonConfig config;
            if (Files.notExists(DEFAULT_CONFIG_PATH)) {
                Files.createDirectories(DEFAULT_CONFIG_PATH.getParent());
                Files.createFile(DEFAULT_CONFIG_PATH);
                config = EvelonConfig.DEFAULT;
                if (Files.exists(DEFAULT_CONFIG_PATH)) {
                    Files.writeString(DEFAULT_CONFIG_PATH, GSON.toJson(config));
                } else {
                    System.out.println("Could not create config file. Evelon will fail to init!");
                    return null;
                }
            } else {
                config = GSON.fromJson(Files.readString(DEFAULT_CONFIG_PATH), EvelonConfig.class);
            }

            if (config.isUseThisConfig()) {
                setCradinates(new DatabaseCradinates(
                        config.getDatabaseProtocol(),
                        config.getHostname(),
                        config.getPassword(),
                        config.getUsername(),
                        config.getDatabase(),
                        config.getPort()
                ));
            } else {
                System.err.println("The provided evelon config tells evelon to not use the values provided in it. " +
                        "DATABASE CRADINATES ARE NULL! " +
                        "You have to set them yourself using Evelon#setDatbaseCradinates(DatabaseCradinates) " +
                        "or fill them in the config file (" + DEFAULT_CONFIG_PATH.toAbsolutePath() + ") " +
                        "before interacting with the database.");
            }
        }

        return DATABASE_CRADINATES;
    }

    public static void useDefaultLocalDatabase() {
        setCradinates(new DatabaseCradinates(DatabaseProtocol.H2, "", "", "", "default", 3306));
    }
}