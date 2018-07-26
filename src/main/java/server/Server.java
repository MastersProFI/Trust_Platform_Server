package server;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DefaultExceptionListener;
import io.netty.channel.ChannelHandlerContext;
import listener.ConnectListener;
import listener.DisconnectListener;
import listener.EventListener;
import listener.Listener;

import java.util.List;

public class Server {

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
        configuration.setHostname("localhost");
        configuration.setPort(2580);
        configuration.setExceptionListener(new DefaultExceptionListener(){
            @Override
            public void onDisconnectException(Exception e, SocketIOClient client) {
                System.out.println("onDisconnectException");
            }

            @Override
            public void onEventException(Exception e, List<Object> args, SocketIOClient client) {
                System.out.println("onEventException");
            }

            @Override
            public void onConnectException(Exception e, SocketIOClient client) {
                System.out.println("onConnectException");
            }

            @Override
            public void onPingException(Exception e, SocketIOClient client) {
                System.out.println("onPingException");
            }

            @Override
            public boolean exceptionCaught(ChannelHandlerContext ctx, Throwable e) throws Exception {
                System.out.println("exceptionCaught");
                return true;
            }
        });
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
