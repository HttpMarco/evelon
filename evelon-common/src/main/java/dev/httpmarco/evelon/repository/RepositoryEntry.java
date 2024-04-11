package dev.httpmarco.evelon.repository;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Getter
@Accessors(fluent = true)
@RequiredArgsConstructor
public class RepositoryEntry {

    private final String id;
    // the original class of the entry
    private final Class<?> clazz;
    // current ordered type of the entry
    private final RepositoryEntryType orderedType;
    // the constants of the entry
    private final Map<RepositoryConstant<?>, ?> constants = new ConcurrentHashMap<>();

}