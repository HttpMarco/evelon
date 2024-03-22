package dev.httpmarco.evelon.query.response;

import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public final class UpdateResponse extends ResponseResult<UpdateResponse> {

    // only success information
    private int modifiedElements = 0;

    @Override
    public void append(UpdateResponse response) {
        super.append(response);
        this.modifiedElements += response.modifiedElements;
    }

    public void modifiedElements(int amount) {
        this.modifiedElements += amount;
    }
}