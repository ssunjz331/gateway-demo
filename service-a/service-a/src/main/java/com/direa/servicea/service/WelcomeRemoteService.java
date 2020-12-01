package com.direa.servicea.service;

import org.springframework.cloud.context.config.annotation.RefreshScope;


public interface WelcomeRemoteService {
    String veryWelcome(String username);
}
