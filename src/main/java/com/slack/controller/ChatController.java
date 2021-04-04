package com.slack.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.slack.model.Channel;
import com.slack.model.Message;
import com.slack.service.ChatService;

@RestController
@CrossOrigin("*")
public class ChatController {
	
	
	
	@Autowired
	ChatService service;

	@GetMapping("/channels")
	List<Channel> getChanel() {
		return service.getChannels();
	}

	@GetMapping("/messages/{channelId}")
	List<Message> getMessage(@PathVariable("channelId") Integer id) {
		return service.getMessages(id);
	}
	
	@PostMapping("/add-channel")
	Channel saveChannel(@RequestBody Channel channel) {
		return service.saveChannel(channel);
	}
	
	@PostMapping("/add-message/{channelId}")
	Message saveMessage(@PathVariable("channelId") Integer id, @RequestBody Message mesg) {
		  return null;
	}
	
	@PostMapping("/add-image/{channelId}")
	List<Message> saveImage(@PathVariable("channelId") Integer id, @RequestParam("file") MultipartFile multipartImage,@RequestParam("user") String user) throws IOException {
		return service.saveImage(id,multipartImage,user);
	}

}
