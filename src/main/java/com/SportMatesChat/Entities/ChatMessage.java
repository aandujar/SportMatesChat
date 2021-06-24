package com.SportMatesChat.Entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "chatMessage")
@JsonPropertyOrder({"message", "user", "event", "date"})
@Getter
@Setter
public class ChatMessage {

    @NotNull
    private String message;

    @NotNull
    private int user;

    @NotNull
    private int event;

    @NotNull
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime date;

}
