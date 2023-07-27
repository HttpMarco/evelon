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

package net.bytemc.evelon.sql.analyze;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public final class AnalyseResult {

    // collect all detections and warnings
    private List<String> detections = new ArrayList<>();
    private List<String> warnings = new ArrayList<>();


    /**
     * @param warning the warning to add as a message
     */
    public void addWarning(String warning) {
        this.warnings.add(warning);
    }


    /**
     * @param detection the detection to add as a message
     */
    public void addDetections(String detection) {
        this.detections.add(detection);
    }

    /**
     * @return true if the result is successfully
     * Only if not detections and warnings are found
     */
    public boolean isSuccessfully() {
        return this.warnings.isEmpty() && this.detections.isEmpty();
    }

}
