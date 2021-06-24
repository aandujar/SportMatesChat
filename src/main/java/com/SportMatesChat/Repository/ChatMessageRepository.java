package com.SportMatesChat.Repository;

import com.SportMatesChat.Entities.ChatMessage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMessageRepository extends MongoRepository<ChatMessage, Integer> {

    List<ChatMessage> findByEvent(int event);
}
