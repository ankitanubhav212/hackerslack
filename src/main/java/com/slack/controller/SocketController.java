package com.slack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.slack.model.Message;
import com.slack.service.ChatService;

@Controller
@CrossOrigin("*")
public class SocketController {
	
	@Autowired
	ChatService service;

    @MessageMapping("/chat/{channelId}")
    @SendTo("/topic/mesg")
    public Message send(@Payload Message message,@DestinationVariable Integer channelId) {
        return service.saveMessage(channelId,message);
    }
}
