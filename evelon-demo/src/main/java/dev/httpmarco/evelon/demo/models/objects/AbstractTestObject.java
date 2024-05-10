package dev.httpmarco.evelon.demo.models.objects;

import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.UUID;

@Getter
@Accessors(fluent = true)
public final class AbstractTestObject extends TestObject1{

    private final UUID test;

    public AbstractTestObject(String permission, long expire, UUID test) {
        super(permission, expire);
        this.test = test;
    }
}
