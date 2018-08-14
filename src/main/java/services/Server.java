package services;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;
import listener.*;

public class Server implements Service {

    private Configuration configuration;
    private SocketIOServer server;

    /*Listeners*/
    private Listener connectListener;
    private Listener disconnectListener;
    private Listener eventListener;

    public void launch(){
        configServer();
        initListeners();
        server.start();
    }

    private void configServer() {
        configuration = new Configuration();
        configuration.setHostname("192.168.243.105");
        configuration.setPort(2580);
        configuration.setExceptionListener(new DefExceptionListener());
        server = new SocketIOServer(configuration);
    }

    private void initListeners() {
        connectListener = new ConnectListener();
        connectListener.initListener(server);

        disconnectListener = new DisconnectListener();
        disconnectListener.initListener(server);

        eventListener = new EventListener();
        eventListener.initListener(server);
    }

}
