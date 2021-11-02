package com.cobrowsing.api.socket.listner.impl;

import com.cobrowsing.api.dto.CustomerMessageData;
import com.cobrowsing.api.dto.NewAdvisorMessageData;
import com.cobrowsing.api.service.CustomerMaintainService;
import com.cobrowsing.api.socket.listner.SocketListener;
import com.corundumstudio.socketio.HandshakeData;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomerSocketListener implements SocketListener {

    SocketIOServer socketIOServer;

    CustomerMaintainService customerMaintainService;

    public ConnectListener onConnect() {
        return client -> {
            final HandshakeData handshakeData = client.getHandshakeData();
            log.info("Connected with ClientId [{}] and Url [{}]", client.getSessionId().toString(), handshakeData.getUrl());
        };
    }

    @OnDisconnect
    public DisconnectListener onDisconnected() {
        return client -> log.debug("Disconnected with ClientId [{}]", client.getSessionId().toString());
    }

    @OnEvent(value = "join")
    public DataListener<CustomerMessageData> joinMessageDataListener() {
        return (socketIOClient, message, ackRequest) -> {
            socketIOClient.joinRoom(message.getRoomId());
            customerMaintainService.maintainCustomers(message);
            log.debug("Join with ClientId [{}]", socketIOClient.getSessionId().toString());
        };
    }

    public DataListener<NewAdvisorMessageData> advisorConnectedMessageDataListener() {
        return (socketIOClient, message, ackRequest) -> log.debug("AdvisorConnected with ClientId [{}]", socketIOClient.getSessionId().toString());
    }

    @PostConstruct
    public void init() {
        socketIOServer.addConnectListener(onConnect());
        socketIOServer.addDisconnectListener(onDisconnected());
        socketIOServer.addEventListener("join", CustomerMessageData.class, joinMessageDataListener());
        socketIOServer.addEventListener("advisorConnected", NewAdvisorMessageData.class, advisorConnectedMessageDataListener());
    }
}
