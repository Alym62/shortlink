package com.github.shortlink.core.domain;

import com.github.shortlink.core.domain.vo.UtmTags;

import java.time.LocalDateTime;
import java.util.UUID;

public class Link {
    private String linkId;
    private String urlOriginal;
    private UtmTags utmTags;
    private UUID userId;
    private boolean active;
    private LocalDateTime expirationDateTime;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Link() {}

    public Link(String linkId, String urlOriginal, LocalDateTime expirationDateTime) {
        this.linkId = linkId;
        this.urlOriginal = urlOriginal;
        this.active = Boolean.TRUE;
        this.expirationDateTime = expirationDateTime;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public String getLinkId() {
        return linkId;
    }

    public void setLinkId(String linkId) {
        this.linkId = linkId;
    }

    public String getUrlOriginal() {
        return urlOriginal;
    }

    public void setUrlOriginal(String urlOriginal) {
        this.urlOriginal = urlOriginal;
    }

    public UtmTags getUtmTags() {
        return utmTags;
    }

    public void setUtmTags(UtmTags utmTags) {
        this.utmTags = utmTags;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDateTime getExpirationDateTime() {
        return expirationDateTime;
    }

    public void setExpirationDateTime(LocalDateTime expirationDateTime) {
        this.expirationDateTime = expirationDateTime;
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
