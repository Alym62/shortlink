package com.github.shortlink.core.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class LinkAnalytics {
    private String linkId;
    private LocalDate date;
    private Long clicks;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public LinkAnalytics() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public String getLinkId() {
        return linkId;
    }

    public void setLinkId(String linkId) {
        this.linkId = linkId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getClicks() {
        return clicks;
    }

    public void setClicks(Long click) {
        this.clicks = click;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
