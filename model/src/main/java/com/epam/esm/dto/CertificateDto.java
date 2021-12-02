package com.epam.esm.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CertificateDto {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private int duration;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdateDate;
    private List<TagDto> tags;

    public CertificateDto() {}

    public CertificateDto(Long id,
                       String name,
                       String description,
                       BigDecimal price,
                       int duration,
                       LocalDateTime createDate,
                       LocalDateTime lastUpdateDate,
                       List<TagDto> tags) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.duration = duration;
        this.createDate = createDate;
        this.lastUpdateDate = lastUpdateDate;
        this.tags = new ArrayList<>();
        if (tags != null && !tags.isEmpty()) {
            for (TagDto tagDto : tags) {
                TagDto tag = new TagDto(tagDto);
                this.tags.add(tag);
            }
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

    public List<TagDto> getTags() {
        List<TagDto> resultTags = new ArrayList<>();
        if (this.tags != null) {
            for (TagDto tagDto : this.tags) {
                TagDto tag = new TagDto(tagDto);
                resultTags.add(tag);
            }
        }
        return resultTags;
    }

    public void setTags(List<TagDto> tags) {
        if (tags != null && !tags.isEmpty()) {
            for (TagDto tagDto : tags) {
                TagDto tag = new TagDto(tagDto);
                this.tags.add(tag);
            }
        }
    }
}
