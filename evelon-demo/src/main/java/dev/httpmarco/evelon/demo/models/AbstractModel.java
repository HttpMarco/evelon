package dev.httpmarco.evelon.demo.models;

import dev.httpmarco.evelon.demo.models.objects.TestObject1;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.UUID;

@Getter
@Accessors(fluent = true)
@ToString
public final class AbstractModel extends TestObject1 {

    private final UUID test;

    public AbstractModel(String permission, int expire, UUID test) {
        super(permission, expire);
        this.test = test;
    }
}
