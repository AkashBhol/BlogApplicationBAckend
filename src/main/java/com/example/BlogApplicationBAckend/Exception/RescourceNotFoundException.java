package com.example.BlogApplicationBAckend.Exception;

public class RescourceNotFoundException extends RuntimeException{

    private String rescorceName;
    private String fieldName;

    private String fieldValue;

    public RescourceNotFoundException(String rescorceName, String fieldName, String fieldValue) {
        super(String.format("%s not with %s :%s",rescorceName,fieldName,fieldValue));
        this.rescorceName = rescorceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
