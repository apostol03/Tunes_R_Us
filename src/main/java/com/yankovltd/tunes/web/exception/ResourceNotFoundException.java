package com.yankovltd.tunes.web.exception;

public class ResourceNotFoundException extends RuntimeException {

    private final Long resourceId;

    public ResourceNotFoundException(Long resourceId) {
        super("Resource with id: " + resourceId);
        this.resourceId = resourceId;
    }

    public Long getResourceId() {
        return resourceId;
    }
}
