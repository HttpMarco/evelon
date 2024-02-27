package dev.httpmarco.evelon.common.credentials;

import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Accessors(fluent = true)
@Getter
public class CredentialsConfiguration {

    private final List<Credentials> credentials = new ArrayList<>();

}
