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
