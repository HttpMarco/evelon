package dev.httpmarco.evelon.demo.models.maps;

import dev.httpmarco.evelon.PrimaryKey;
import dev.httpmarco.evelon.demo.models.objects.EnumObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter
@Accessors(fluent = true)
@AllArgsConstructor
public final class MapEnumBooleanModel {

    @PrimaryKey
    private final UUID uuid;
    private final Map<EnumObject, Boolean> propertyPlayer = new HashMap<>();

}
