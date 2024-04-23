package dev.httpmarco.evelon.process;

public interface ProcessReference<R extends ProcessReference<R>> {

    void bind(R reference);

}
