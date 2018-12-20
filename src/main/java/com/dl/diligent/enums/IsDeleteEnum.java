package com.dl.diligent.enums;

public enum IsDeleteEnum implements BaseEnum {
    UN_DELETE(0, "未删除"), DELETED(1, "已删除");

    private Integer value;
    private String description;

    IsDeleteEnum(Integer value, String description) {
        this.value = value;
        this.description = description;
    }

    @Override
    public Integer value() {
        return value;
    }

    @Override
    public String description() {
        return description;
    }
}
