package com.sincera.intern.dto;

public class TruncateDto {
    private String errorMessage;
    private String tableName;

    @Override
    public String toString() {
        return "TruncateDto{" +
                "errorMessage='" + errorMessage + '\'' +
                ", tableName='" + tableName + '\'' +
                '}';
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
