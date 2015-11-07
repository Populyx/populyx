package com.populyx.security;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

import com.populyx.entities.User;

@Component	
public class CustomEventPublisher 
   implements ApplicationEventPublisherAware {
   
   private ApplicationEventPublisher publisher;

   public void setApplicationEventPublisher
              (ApplicationEventPublisher publisher){
      this.publisher = publisher;
   }

   public void publish(User user) {
      publisher.publishEvent(new CustomEvent(this,user));
   }
}