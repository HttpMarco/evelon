package dev.httpmarco.evelon.common.credentials;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
@AllArgsConstructor
public class AbstractCredentials implements Credentials {

    private String id;
    private boolean enabled;

    public AbstractCredentials(String id) {
        this.id = id;
        this.enabled = false;
    }
}