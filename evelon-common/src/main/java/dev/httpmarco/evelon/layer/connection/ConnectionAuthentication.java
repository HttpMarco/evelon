package dev.httpmarco.evelon.layer.connection;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
@AllArgsConstructor
public abstract class ConnectionAuthentication {

    private String id;
    private boolean active;

}
