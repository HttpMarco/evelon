package dev.httpmarco.evelon.common.credentials;

import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Getter
@Accessors(fluent = true)
public class CredentialsConfig {

    private final List<Credentials> credentials = new ArrayList<>();

}
