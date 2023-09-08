/*
 * Copyright 2019-2023 ByteMC team & contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.bytemc.evelon.cradinates;

import net.bytemc.evelon.DatabaseProtocol;
import org.jetbrains.annotations.NotNull;

public record DatabaseCradinates(DatabaseProtocol databaseProtocol, String hostname, String password, String user, String database, int port) {

    public @NotNull String toUrl() {
        return this.defaultUrl().formatted(databaseProtocol.name().toLowerCase(), hostname, port, database, user, password);
    }

    private String defaultUrl() {
        return "jdbc:%s://%s:%d/%s?useUnicode=true&autoReconnect=true&user=%s&password=%s";
    }

}

