package com.epam.esm.entity;

public class Tag {

    private Long id;
    private String name;

    public Tag() {}

    public Tag(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Tag(Tag tag) {
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Tag tag = (Tag) obj;
        return id.equals(tag.id)
                && name.equals(tag.name);
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;

        result = result * prime + (id != null ? id.hashCode() : 0);
        result = result * prime + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Tag{ ")
                .append("id = '").append(id).append('\'')
                .append(", name = '").append(name).append('\'')
                .append(" }\n");
        return stringBuilder.toString();
    }
}
