package dev.httpmarco.evelon.common.query.response;

import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public final class UpdateResponse extends ResponseResult {

    // only success information
    private int modifiedElements = 0;

    @Override
    public void append(ResponseResult response) {
        super.append(response);
        if (response instanceof UpdateResponse updateResponse) {
            this.modifiedElements += updateResponse.modifiedElements;
        }
    }
}