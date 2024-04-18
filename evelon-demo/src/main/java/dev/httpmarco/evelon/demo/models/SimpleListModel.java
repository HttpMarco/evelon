package dev.httpmarco.evelon.demo.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
public final class SimpleListModel {

    private final UUID uuid;
    private final List<String> permissions;
    private final List<String> groups;

}
