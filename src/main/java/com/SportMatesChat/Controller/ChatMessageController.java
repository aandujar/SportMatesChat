package com.SportMatesChat.Controller;

import com.SportMatesChat.Entities.ChatMessage;
import com.SportMatesChat.Service.ChatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("chatMessage")
public class ChatMessageController {

    @Autowired
    private ChatMessageService chatMessageService;

    @GetMapping("/{eventId}/{userId}")
    public Object getByEvent(@PathVariable int eventId, @PathVariable int userId) {
        return chatMessageService.getByEvent(eventId, userId);
    }

    @PostMapping
    public Object saveChatMessage(@RequestBody ChatMessage chatMessage) {
        return chatMessageService.save(chatMessage);
    }

    @PutMapping
    public Object updateChatMessage(@RequestBody ChatMessage chatMessage) {
        return chatMessageService.updateMessage(chatMessage);
    }

    @DeleteMapping("/{messageId}/{userId}")
    public Object deleteMessage(@PathVariable String messageId, @PathVariable int userId) {
        return chatMessageService.deleteMessage(messageId, userId);
    }


}
