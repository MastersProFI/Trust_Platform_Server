package listener;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;
import impl.Sha3Response;
import impl.rdpvalidation.RdpValidation;
import model.Data;

import java.util.Random;

public class EventListener implements Listener {

    private SocketIOServer server;

    public void initListener(SocketIOServer server) {
        this.server=server;
        initEventListener();
    }

    private void initEventListener() {
        server.addEventListener("registration", Data.class, new DataListener<Data>() {
            public void onData(SocketIOClient socketIOClient, Data data, AckRequest ackRequest) throws Exception {
                System.out.println(data.getData());
            }
        });

        server.addEventListener("micro", Data.class, new DataListener<Data>() {
            public void onData(SocketIOClient socketIOClient, Data data, AckRequest ackRequest) throws Exception {

            }
        });

        server.addEventListener("get_sha3", Data.class, new DataListener<Data>() {
            public void onData(SocketIOClient socketIOClient, Data data, AckRequest ackRequest) throws Exception {
                socketIOClient.sendEvent("get_sha3",new Sha3Response().getResponse());
                System.out.println("send to "+socketIOClient.getSessionId());
            }
        });

        server.addEventListener("go_rdp", Data.class, new DataListener<Data>() {
            public void onData(SocketIOClient socketIOClient, Data data, AckRequest ackRequest) throws Exception {
                new RdpValidation().isValid(data.getData(),socketIOClient);
            }
        });



    }
}
