package com.SportMatesChat.Entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.time.LocalDateTime;

@Document(collection = "chatMessage")
@JsonPropertyOrder({"id", "message", "user", "event", "date"})
@Getter
@Setter
public class ChatMessage {

    @Id
    private String id;

    @NotNull
    private String message;

    @NotNull
    private User user;

    @NotNull
    private int event;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime date;

}
