package com.github.shortlink.adapter.out.persistence;

import com.github.shortlink.core.commons.Constants;
import com.github.shortlink.core.domain.annotations.DynamoDbTableName;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.*;

import java.time.LocalDateTime;
import java.util.UUID;


@DynamoDbBean
@DynamoDbTableName(name = "tb_links")
public class LinkPersistence {
    private String linkId;
    private String urlOriginal;
    private String utmSource;
    private String utmCampaign;
    private String utmMedium;
    private String utmContent;
    private UUID userId;
    private boolean active;
    private LocalDateTime expirationDateTime;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public LinkPersistence() {}

    @DynamoDbPartitionKey
    @DynamoDbAttribute(value = "link_id")
    @DynamoDbSecondarySortKey(indexNames = Constants.FK_TB_USERS_LINK_USER_ID)
    public String getLinkId() {
        return linkId;
    }

    public void setLinkId(String linkId) {
        this.linkId = linkId;
    }

    @DynamoDbAttribute(value = "url_original")
    public String getUrlOriginal() {
        return urlOriginal;
    }

    public void setUrlOriginal(String urlOriginal) {
        this.urlOriginal = urlOriginal;
    }

    @DynamoDbAttribute(value = "utm_source")
    public String getUtmSource() {
        return utmSource;
    }

    public void setUtmSource(String utmSource) {
        this.utmSource = utmSource;
    }

    @DynamoDbAttribute(value = "utm_campaign")
    public String getUtmCampaign() {
        return utmCampaign;
    }

    public void setUtmCampaign(String utmCampaign) {
        this.utmCampaign = utmCampaign;
    }

    @DynamoDbAttribute(value = "utm_medium")
    public String getUtmMedium() {
        return utmMedium;
    }

    public void setUtmMedium(String utmMedium) {
        this.utmMedium = utmMedium;
    }

    @DynamoDbAttribute(value = "utm_content")
    public String getUtmContent() {
        return utmContent;
    }

    public void setUtmContent(String utmContent) {
        this.utmContent = utmContent;
    }

    @DynamoDbSecondaryPartitionKey(indexNames = Constants.FK_TB_USERS_LINK_USER_ID)
    @DynamoDbAttribute(value = "user_id")
    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    @DynamoDbAttribute(value = "active")
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @DynamoDbAttribute(value = "expiration_date")
    public LocalDateTime getExpirationDateTime() {
        return expirationDateTime;
    }

    public void setExpirationDateTime(LocalDateTime expirationDateTime) {
        this.expirationDateTime = expirationDateTime;
    }

    @DynamoDbAttribute(value = "created_at")
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @DynamoDbAttribute(value = "updated_at")
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
