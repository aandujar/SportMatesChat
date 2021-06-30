package com.SportMatesChat.Service.Impl;

import com.SportMatesChat.Entities.ChatMessage;
import com.SportMatesChat.Exception.BadRequestException;
import com.SportMatesChat.Feign.EventUser;
import com.SportMatesChat.Repository.ChatMessageRepository;
import com.SportMatesChat.Service.ChatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
        this.isUserInscriptedInEvent(chatMessage.getEvent(), chatMessage.getUser().getId());
        chatMessage.setDate(LocalDateTime.now());
        this.chatMessageRepository.save(chatMessage);
        return true;
    }

    @Override
    public boolean updateMessage(ChatMessage chatMessage) {
        ChatMessage chatMessageOld = chatMessageRepository.getById(chatMessage.getId());
        if(chatMessage == null) {
            throw new BadRequestException("No se ha encontrado el mensaje");
        }
        this.isUserInscriptedInEvent(chatMessage.getEvent(), chatMessage.getUser().getId());
        chatMessageOld.setMessage(chatMessage.getMessage());
        this.chatMessageRepository.save(chatMessageOld);
        return true;
    }

    @Override
    public boolean deleteMessage(String messageId, int userId) {
        ChatMessage chatMessage = chatMessageRepository.getById(messageId);
        if(chatMessage == null) {
             throw new BadRequestException("No se ha encontrado el mensaje");
        }
        this.isUserInscriptedInEvent(chatMessage.getEvent(), userId);
        if(chatMessage.getUser().getId() != userId) {
            throw new BadRequestException("No puedes borrar un mensaje que no has escrito");
        }
        chatMessageRepository.delete(chatMessage);
        return true;
    }

    private void isUserInscriptedInEvent(int eventId, int userId) {
        try {
            boolean isInscripted = this.eventUser.userIsInscriptedToEvent(eventId, userId);
            if (!isInscripted) {
                throw new BadRequestException("No estás inscrito al evento");
            }
        } catch (Exception ex) {
            throw new BadRequestException("Ha ocurrido un error");
        }
    }
}
