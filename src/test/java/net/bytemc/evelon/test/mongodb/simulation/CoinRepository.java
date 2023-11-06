package net.bytemc.evelon.test.mongodb.simulation;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.bytemc.evelon.repository.annotations.Entity;
import net.bytemc.evelon.repository.annotations.PrimaryKey;

import java.util.UUID;

@Getter
@RequiredArgsConstructor
@Entity(name = "simulation_coins")
public final class CoinRepository {

    @PrimaryKey
    private final UUID uniqueId;
    private final int coins;

}
