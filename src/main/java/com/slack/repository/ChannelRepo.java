package com.slack.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.slack.model.Channel;

public interface ChannelRepo extends JpaRepository<Channel, Integer>{

}
