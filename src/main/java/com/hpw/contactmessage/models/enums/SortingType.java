package com.hpw.contactmessage.models.enums;


public enum SortingType {

    ASC("asc"),
    DESC("desc");

    private String type;

    SortingType(String type) {
        this.type=type;
    }

    public String getType() {
        return type;
    }
}
