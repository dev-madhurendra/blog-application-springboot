package com.blog.application.apis.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    String resourceName;
    Long field;
    public ResourceNotFoundException(String resourceName,Long field) {
        super(resourceName + " with id " + field + " not found !");
    }
}
