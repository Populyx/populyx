package com.populyx.config;

import java.util.List;

import org.springframework.context.annotation.*;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker
@ComponentScan(basePackages = "com.populyx.controller")
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		config.enableSimpleBroker("/queue", "/topic","/user");
		config.setApplicationDestinationPrefixes("/app");
	}

	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/chat").withSockJS();
	}

	@Override
	public void configureClientInboundChannel(ChannelRegistration channelRegistration) {
	}

	@Override
	public void configureClientOutboundChannel(ChannelRegistration channelRegistration) {
	}

	@Override
	public boolean configureMessageConverters(List<MessageConverter> converters) {
		return true;
	}
}
