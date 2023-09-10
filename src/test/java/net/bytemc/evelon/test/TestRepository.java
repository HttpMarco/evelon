package net.bytemc.evelon.test;

import lombok.*;
import net.bytemc.evelon.repository.annotations.Entity;
import net.bytemc.evelon.repository.annotations.PrimaryKey;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "polus")
public class TestRepository {

    @PrimaryKey
    private String name;
    private UUID uniqueId;
    private HashMap<String, Long> connectedStates;

}
