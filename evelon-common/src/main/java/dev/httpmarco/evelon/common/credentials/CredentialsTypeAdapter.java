package dev.httpmarco.evelon.common.credentials;

import com.google.gson.*;
import dev.httpmarco.osgan.reflections.Reflections;
import dev.httpmarco.osgon.files.json.JsonDocument;
import dev.httpmarco.osgon.files.json.JsonTypeAdapter;
import dev.httpmarco.osgon.files.json.JsonUtils;
import lombok.SneakyThrows;

import java.lang.reflect.Type;

public class CredentialsTypeAdapter extends JsonTypeAdapter<Credentials> {

    public CredentialsTypeAdapter() {
        super(Credentials.class);
    }

    @SneakyThrows
    @Override
    public Credentials deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        var object = jsonElement.getAsJsonObject();
        return (Credentials) JsonUtils.fromJson(object.toString(), Class.forName(object.get("class").getAsString()));
    }

    @Override
    public JsonElement serialize(Credentials credentials, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject element = JsonUtils.getPrettyGson().toJsonTree(credentials).getAsJsonObject();

        // todo find a better way to do this (remove class)
        element.addProperty("class", credentials.getClass().getName());
        return element;
    }
}
