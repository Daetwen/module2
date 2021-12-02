package com.epam.esm.dto;

public class TagDto {

    private Long id;
    private String name;

    public TagDto() {}

    public TagDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public TagDto(TagDto tag) {
        this.id = tag.getId();
        this.name = tag.getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
