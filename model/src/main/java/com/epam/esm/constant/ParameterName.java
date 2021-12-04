package com.epam.esm.constant;

public enum ParameterName {

    TAG_NAME("tagName"),
    NAME_OR_DESC_PART("nameOrDescriptionPart"),
    SORT("sort");

    private String name;

    ParameterName(String parameterName) {
        this.name = parameterName;
    }
}
