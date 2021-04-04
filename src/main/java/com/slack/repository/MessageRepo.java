package com.slack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.slack.model.Message;

public interface MessageRepo  extends JpaRepository<Message, Integer>{
	List<Message> findByChannelId(Integer id);
}
