package dev.httpmarco.evelon.common.layers;

public interface EvelonLayerSession {

    /**
     * Initialize the layer: Maybe for connection or something else
     */
    void initialize();

    /**
     * Give the information about the state of the layer.
     * @return A state if layer is successfully initialized.
     */
    boolean active();

    /**
     * Close the layer
     */
    void close();

}
