package com.venture.suyaho.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.List;

@Component
public class ChatHandler extends TextWebSocketHandler {
    private static List<WebSocketSession> chatList = new ArrayList<>();

    //클라 연결 및 접속
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        chatList.add(session);
        System.out.println("[{" + session + "}] 클라이언트 접속 ");
    }

    //클라 연결 및 해제
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println("[{"+ session + "}] 클라 접속 해제 " );
        chatList.remove(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        System.out.println("payload : {" + payload + "}");
        for(WebSocketSession webSocketSession : chatList) {
            webSocketSession.sendMessage(new TextMessage(payload));
        }
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        System.out.println(exception.getMessage());
    }
}
