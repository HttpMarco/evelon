package net.bytemc.evelon.test;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import net.bytemc.evelon.repository.annotations.Entity;
import net.bytemc.evelon.repository.annotations.PrimaryKey;
import java.util.Map;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@Entity(name = "polus")
public class TestRepository {

    @PrimaryKey
    private String name;
    private UUID uniqueId;
    private Map<String, Long> connectedStates;

}
