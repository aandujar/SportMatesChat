package com.SportMatesChat.Service;

import com.SportMatesChat.Entities.ChatMessage;

import java.util.List;

public interface ChatMessageService {

    List<ChatMessage> getByEvent(int eventId, int userId);

    boolean save(ChatMessage chatMessage);
}
