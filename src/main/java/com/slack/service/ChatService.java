package com.slack.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.slack.model.Channel;
import com.slack.model.Message;
import com.slack.repository.ChannelRepo;
import com.slack.repository.MessageRepo;

@Service
public class ChatService {

	@Autowired
	ChannelRepo channelRepo;
	
	@Autowired
	MessageRepo messageRepo;
	
	@Autowired private SimpMessagingTemplate messagingTemplate;
	
	public List<Channel> getChannels() {
		return channelRepo.findAll();
	}
	
	public List<Message> getMessages(Integer id) {
		return messageRepo.findByChannelId(id);
	}
	
	public Channel saveChannel(Channel channel) {
		channel.setCreatedAt(System.currentTimeMillis());
		return channelRepo.save(channel);
	}
	
	public Message saveMessage(Integer id,Message mesg) {
		Channel channel = channelRepo.findById(id).get();
		mesg.setTimeStamp(System.currentTimeMillis());
		mesg.setChannel(channel);
		
		return messageRepo.save(mesg);
	}

	public List<Message> saveImage(Integer id, MultipartFile multipartImage, String user) throws IOException {
		Channel channel = channelRepo.findById(id).get();
		Message mesg = new Message();
		mesg.setName(user);
		mesg.setTimeStamp(System.currentTimeMillis());
		mesg.setImage(multipartImage.getBytes());
		mesg.setChannel(channel);
		messageRepo.save(mesg);
		messagingTemplate.convertAndSend("/topic/mesg", mesg);
		return messageRepo.findByChannelId(id);
	}
}
