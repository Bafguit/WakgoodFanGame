package com.fastcat.labyrintale.handlers.resourcetype;

import com.fastcat.labyrintale.handlers.ResourceHandler;
import java.util.HashMap;
import lombok.Getter;

public class MultipleResourceRequest<R> extends ResourceHandler.ResourceRequest<R> {
    @Getter
    private final HashMap<String, String> resourceNames;

    public MultipleResourceRequest(
            HashMap<String, String> resourceNames, Class<R> resourceType, ResourceHandler.ResourceCallback callback) {
        super(null, resourceType, callback, resourceNames);
        this.resourceNames = resourceNames;
    }
}
