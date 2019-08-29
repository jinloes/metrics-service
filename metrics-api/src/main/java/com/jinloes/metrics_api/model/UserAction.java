package com.jinloes.metrics_api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.Instant;

@Data
@Document(indexName = "user_actions")
public class UserAction {
    @Id
    private String id;
    @Field(type = FieldType.Date, store = true, format = DateFormat.custom, pattern = "yyyy-MM-dd'T'HH:mm:ssZZ")
    private final Instant timestamp;
    @Field(type = FieldType.Keyword, store = true)
    private final String userId;
    @Field(type = FieldType.Keyword, store = true)
    private final String orgId;
    @Field(type = FieldType.Keyword, store = true)
    private final String feature;
    @Field(type = FieldType.Keyword, store = true)
    private final String action;
    @Field(type = FieldType.Keyword, store = true)
    private final String targetId;

    @JsonCreator
    public UserAction(@JsonProperty("timestamp") Instant timestamp, @JsonProperty("userId") String userId,
                      @JsonProperty("orgId") String orgId, @JsonProperty("feature") String feature,
                      @JsonProperty("action") String action, @JsonProperty("targetId") String targetId) {
        this.timestamp = timestamp;
        this.userId = userId;
        this.orgId = orgId;
        this.feature = feature;
        this.action = action;
        this.targetId = targetId;
    }
}
