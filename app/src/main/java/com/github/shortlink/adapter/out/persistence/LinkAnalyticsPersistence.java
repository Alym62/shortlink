package com.github.shortlink.adapter.out.persistence;

import com.github.shortlink.core.commons.Constants;
import com.github.shortlink.core.domain.annotations.DynamoDbTableName;
import software.amazon.awssdk.enhanced.dynamodb.extensions.annotations.DynamoDbAtomicCounter;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@DynamoDbBean
@DynamoDbTableName(name = "tb_links_analytics")
public class LinkAnalyticsPersistence {
    private String linkId;
    private LocalDate date;
    private Long clicks;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public LinkAnalyticsPersistence() {}

    @DynamoDbPartitionKey
    @DynamoDbAttribute(value = "link_id")
    @DynamoDbSecondarySortKey(indexNames = Constants.FK_TB_USERS_LINK_USER_ID)
    public String getLinkId() {
        return linkId;
    }

    public void setLinkId(String linkId) {
        this.linkId = linkId;
    }

    @DynamoDbAttribute(value = "date")
    @DynamoDbSortKey
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @DynamoDbAttribute(value = "clicks")
    @DynamoDbAtomicCounter
    public Long getClicks() {
        return clicks;
    }

    public void setClicks(Long click) {
        this.clicks = click;
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
