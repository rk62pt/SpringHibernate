package main.ryan.authority.presentation.interceptor;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class AuthorityHandler extends TextWebSocketHandler{
	@Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
		System.out.println("connecting......");
    }
}
