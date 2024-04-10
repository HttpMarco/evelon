package dev.httpmarco.evelon.process;

public interface ProcessResolver<Q> {

    Process<Q> render();

}
