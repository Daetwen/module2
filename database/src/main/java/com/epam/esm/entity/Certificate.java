package com.epam.esm.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Certificate {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private int duration;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdateDate;
    private List<Tag> tags;

    public Certificate() {}

    public Certificate(Long id,
                       String name,
                       String description,
                       BigDecimal price,
                       int duration,
                       LocalDateTime createDate,
                       LocalDateTime lastUpdateDate,
                       List<Tag> tags) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.duration = duration;
        this.createDate = createDate;
        this.lastUpdateDate = lastUpdateDate;
        this.tags = new ArrayList<>();
        if (tags != null) {
            Collections.copy(this.tags, tags);
        }
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(LocalDateTime lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public List<Tag> getTags() {
        List<Tag> resultTags = new ArrayList<>();
        if (this.tags != null) {
            Collections.copy(resultTags, this.tags);
        }
        return resultTags;
    }

    public void setTags(List<Tag> tags) {
        if (tags != null) {
            this.tags = new ArrayList<>();
            for (Tag value : tags) {
                Tag tag = new Tag(value);
                this.tags.add(tag);
            }
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Certificate that = (Certificate) obj;
        return id.equals(that.id)
                && name.equals(that.name)
                && Objects.equals(description, that.description)
                && price.equals(that.price)
                && duration == that.duration
                && createDate.equals(that.createDate)
                && lastUpdateDate.equals(that.lastUpdateDate)
                && tags != null ? tags.equals(that.tags) : that.tags == null;
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;

        result = result * prime + (id != null ? id.hashCode() : 0);
        result = result * prime + (name != null ? name.hashCode() : 0);
        result = result * prime + (description != null ? description.hashCode() : 0);
        result = result * prime + (price != null ? price.hashCode() : 0);
        result = result * prime + duration;
        result = result * prime + (createDate != null ? createDate.hashCode() : 0);
        result = result * prime + (lastUpdateDate != null ? lastUpdateDate.hashCode() : 0);
        result = result * prime + (tags != null ? tags.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("GiftCertificate{ ")
                .append("id = '").append(id).append('\'')
                .append(", name = '").append(name).append('\'')
                .append(", description = '").append(description).append('\'')
                .append(", price = '").append(price).append('\'')
                .append(", duration = '").append(duration).append('\'')
                .append(", createDate = '").append(createDate).append('\'')
                .append(", lastUpdateDate = '").append(lastUpdateDate).append('\'');
        stringBuilder.append(", tags = [");
        for(Tag tag : tags) {
            stringBuilder.append(tag).append(", ");
        }
        stringBuilder.append("] }\n");
        return stringBuilder.toString();
    }
}
