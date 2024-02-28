package dev.httpmarco.evelon.common.builder;

/**
 * represent a builder of elements in a data query/update
 */
public interface Builder {

    /**
     * Create a new builder for sub elements, list or maps
     * @return new sub builder
     */
    Builder subBuilder();

}
