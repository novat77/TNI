package com.sincera.intern.dto;

public class EntityDto {
    private String errorMessage;
    private String tableName;

    private String download;

    public String getDownload() {
        return download;
    }

    public void setDownload(String download) {
        this.download = download;
    }

    @Override
    public String toString() {
        return "EntityDto{" +
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
