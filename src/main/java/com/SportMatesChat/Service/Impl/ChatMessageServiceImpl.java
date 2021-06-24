package com.SportMatesChat.Service.Impl;

import com.SportMatesChat.Entities.ChatMessage;
import com.SportMatesChat.Exception.BadRequestException;
import com.SportMatesChat.Feign.EventUser;
import com.SportMatesChat.Repository.ChatMessageRepository;
import com.SportMatesChat.Service.ChatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatMessageServiceImpl implements ChatMessageService {

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @Autowired private EventUser eventUser;

    @Override
    public List<ChatMessage> getByEvent(int eventId, int userId) {
        this.isUserInscriptedInEvent(eventId, userId);
        return this.chatMessageRepository.findByEvent(eventId);
    }

    @Override
    public boolean save(ChatMessage chatMessage) {
        this.chatMessageRepository.save(chatMessage);
        return true;
    }

    private void isUserInscriptedInEvent(int eventId, int userId) {
        boolean isInscripted = this.eventUser.userIsInscriptedToEvent(eventId, userId);
        if(!isInscripted) {
            throw new BadRequestException("No estás inscrito al evento");
        }
    }
}
