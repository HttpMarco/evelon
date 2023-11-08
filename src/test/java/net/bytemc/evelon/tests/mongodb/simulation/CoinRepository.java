package net.bytemc.evelon.tests.mongodb.simulation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import net.bytemc.evelon.repository.annotations.Entity;
import net.bytemc.evelon.repository.annotations.PrimaryKey;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Entity(name = "simulation_coins")
public final class CoinRepository {

    @PrimaryKey
    private final UUID uniqueId;
    private int coins;
    private long lastUpdate;

}
