package dev.httpmarco.evelon.layer;

import dev.httpmarco.evelon.credentials.Credentials;
import dev.httpmarco.evelon.process.common.InitializeProcess;
import dev.httpmarco.evelon.repository.Repository;
import dev.httpmarco.evelon.stage.Stage;
import dev.httpmarco.evelon.stage.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;

@Getter
@Accessors(fluent = true)
@AllArgsConstructor
public abstract class ConnectableLayer extends Layer {

    private final Credentials templateCredentials;

}