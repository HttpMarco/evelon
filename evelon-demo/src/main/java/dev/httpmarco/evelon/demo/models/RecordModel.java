package dev.httpmarco.evelon.demo.models;

import dev.httpmarco.evelon.PrimaryKey;

import java.util.UUID;

public record RecordModel(@PrimaryKey UUID id, long coins, int age) {
}
