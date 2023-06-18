package com.blog.app.exception;

public class ResourceNotFoundException extends RuntimeException{
    private String resource;
    private String fieldName;
    private Integer fieldValue;

    public ResourceNotFoundException(String resource,String fieldName,Integer fieldValue){
        super(String.format("%s is not found with %s : %s",resource,fieldName,fieldValue));
        this.resource = resource;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
