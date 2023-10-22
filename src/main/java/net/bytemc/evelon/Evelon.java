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

    public static void setDatabaseCradinates(DatabaseCradinates databaseCradinates) {
        if (DATABASE_CRADINATES != null) {
            throw new UnsupportedOperationException("Database cradinates have already been set. Maybe you set useThisConfig=true in the evlon-config.json?");
        }

        DATABASE_CRADINATES = databaseCradinates;
        StorageHandler.initStorage(databaseCradinates.databaseProtocol());
    }

    @SneakyThrows
    public static DatabaseCradinates getDatabaseCradinates() {
        if (DATABASE_CRADINATES == null) {
            EvelonConfig evelonConfig;

            if (Files.notExists(DEFAULT_CONFIG_PATH)) {
                Files.createDirectories(DEFAULT_CONFIG_PATH.getParent());
                Files.createFile(DEFAULT_CONFIG_PATH);

                evelonConfig = EvelonConfig.DEFAULT;

                if (Files.exists(DEFAULT_CONFIG_PATH)) {
                    Files.writeString(DEFAULT_CONFIG_PATH, GSON.toJson(evelonConfig));
                } else {
                    System.out.println("Could not create config file. Evelon will fail to init!");
                    return null;
                }
            } else {
                evelonConfig = GSON.fromJson(Files.readString(DEFAULT_CONFIG_PATH), EvelonConfig.class);
            }

            if (evelonConfig.isUseThisConfig()) {
                setDatabaseCradinates(new DatabaseCradinates(
                        evelonConfig.getDatabaseProtocol(),
                        evelonConfig.getHostname(),
                        evelonConfig.getPassword(),
                        evelonConfig.getUsername(),
                        evelonConfig.getDatabase(),
                        evelonConfig.getPort()
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
        setDatabaseCradinates(new DatabaseCradinates(DatabaseProtocol.H2, "", "", "", "default", 3306));
    }

}
