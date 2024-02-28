package dev.httpmarco.evelon.demo;

import dev.httpmarco.evelon.common.annotations.PrimaryKey;

public final class SimpleTestRepository {

    @PrimaryKey
    private String username;
    private Integer money;

}
