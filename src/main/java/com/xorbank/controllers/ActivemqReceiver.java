package com.xorbank.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.xorbank.model.ActivemqMessage;

@Component
public class ActivemqReceiver {

	private static final Logger log= LoggerFactory.getLogger(ActivemqReceiver.class);
	@JmsListener(destination="Xorbank")
	public void messageListener(ActivemqMessage msg)
	{
		log.info("Message "+ msg.getMessage());
	}
}
