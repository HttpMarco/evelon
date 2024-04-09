package dev.httpmarco.evelon.layer.connection.credentials;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
@AllArgsConstructor
public abstract class LayerConnectionCredentials {

    private String id;
    private boolean active;

}
