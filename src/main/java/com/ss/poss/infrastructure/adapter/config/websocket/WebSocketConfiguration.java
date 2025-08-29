package com.ss.poss.infrastructure.adapter.config.websocket;

import com.ss.poss.infrastructure.adapter.config.auth.JwtHandshakeInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

@Configuration
@EnableWebSocket
public class WebSocketConfiguration implements WebSocketConfigurer {
    private final OrderWebSocketHandler orderWebSocketHandler;
    private final JwtHandshakeInterceptor jwtHandshakeInterceptor;

    public WebSocketConfiguration(OrderWebSocketHandler orderWebSocketHandler, JwtHandshakeInterceptor jwtHandshakeInterceptor) {
        this.orderWebSocketHandler = orderWebSocketHandler;
        this.jwtHandshakeInterceptor = jwtHandshakeInterceptor;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(orderWebSocketHandler, "/ws/orders")
                .addInterceptors(new HttpSessionHandshakeInterceptor(), jwtHandshakeInterceptor)
                .setAllowedOrigins("*");
    }
}


