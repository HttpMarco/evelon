package dev.httpmarco.evelon.common;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public final class JsonUtils {

    public static final Gson GSON = new GsonBuilder().serializeNulls().setPrettyPrinting().create();

}
