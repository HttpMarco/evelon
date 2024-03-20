package dev.httpmarco.evelon.layer;

import dev.httpmarco.evelon.credentials.Credentials;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
@RequiredArgsConstructor
public abstract class ConnectableLayer<C> extends Layer {

    @Setter
    private boolean active = false;
    private final Credentials templateCredentials;

    public abstract LayerConnection<C> connection();


    /**
     * Initialize the layer: Maybe for connection or something else
     */
    public abstract void initialize();

    /**
     * Give the information about the state of the layer.
     * @return A state if layer is successfully initialized.
     */
    public boolean active() {
        return active;
    }

    /**
     * Close the layer
     */
    public abstract void close();

}