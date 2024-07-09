package com.epam.example.message.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Builder
@Data
//@JsonTypeInfo(use = Id.CLASS, include = As.PROPERTY, property = "ID")
public class MessageDto implements Serializable {
    private Long id;
    private String content;
    private Date date;

    @JsonCreator
    public MessageDto(@JsonProperty("id") long id, @JsonProperty("content") String content, @JsonProperty("date") Date date) {
        this.id = id;
        this.content = content;
        this.date = date;
    }
}
