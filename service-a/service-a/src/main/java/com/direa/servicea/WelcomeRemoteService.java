package com.direa.servicea;

import org.springframework.cloud.context.config.annotation.RefreshScope;

@RefreshScope
public interface WelcomeRemoteService {
    String veryWelcome(String username);
}
