package com.epam.esm.builder;

import com.epam.esm.entity.Certificate;
import com.epam.esm.entity.Tag;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public class CertificateBuilder {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private int duration;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdateDate;
    private List<Tag> tags;

    public Certificate build() {
        Certificate certificate = new Certificate(id, name, description, price, duration, createDate, lastUpdateDate, tags);
        this.id = null;
        this.name = null;
        this.description = null;
        this.price = null;
        this.createDate = null;
        this.lastUpdateDate = null;
        this.tags = null;
        return certificate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public void setLastUpdateDate(LocalDateTime lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public void setTags(List<Tag> tags) {
        Collections.copy(this.tags, tags);
    }
}
