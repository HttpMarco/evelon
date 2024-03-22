package dev.httpmarco.evelon.credentials;

import lombok.Getter;
import lombok.experimental.Accessors;
import java.util.HashSet;
import java.util.Set;

@Accessors(fluent = true)
@Getter
public class CredentialsConfiguration {

    private final Set<Credentials> credentials = new HashSet<>();

}
