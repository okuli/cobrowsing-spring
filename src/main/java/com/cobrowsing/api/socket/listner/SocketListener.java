package com.cobrowsing.api.socket.listner;

import com.cobrowsing.api.dto.CustomerMessageData;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;


public interface SocketListener {

    ConnectListener onConnect();

    DisconnectListener onDisconnected();

    DataListener<CustomerMessageData> joinMessageDataListener();

}
