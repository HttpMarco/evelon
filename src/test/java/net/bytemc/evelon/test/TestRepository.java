package net.bytemc.evelon.test;

import lombok.*;
import net.bytemc.evelon.repository.annotations.Entity;
import net.bytemc.evelon.repository.annotations.Ignore;
import net.bytemc.evelon.repository.annotations.PrimaryKey;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
@ToString
@AllArgsConstructor
@Entity(name = "polus2")
public class TestRepository {

    @PrimaryKey
    private String name;
    private UUID uniqueId;
    private List<String> usernames;

    @Ignore
    private final String ignoreField = "test";

}
