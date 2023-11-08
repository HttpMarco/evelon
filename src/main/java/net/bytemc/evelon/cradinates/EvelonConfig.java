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

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.bytemc.evelon.DatabaseProtocol;

@AllArgsConstructor
@Getter
public class EvelonConfig {
    public static EvelonConfig DEFAULT = new EvelonConfig(false,
            DatabaseProtocol.MARIADB,
            "hostname",
            "username",
            "password",
            "database",
            3306);

    private boolean useThisConfig;
    private DatabaseProtocol databaseProtocol;
    private String hostname;
    private String username;
    private String password;
    private String database;
    private int port;
}
