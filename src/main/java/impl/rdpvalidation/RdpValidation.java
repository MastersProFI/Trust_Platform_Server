package impl.rdpvalidation;

import com.corundumstudio.socketio.SocketIOClient;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import database.UserFromJsonDB;
import encryptmsec.security.Security;
import model.UserJson;

import java.math.BigInteger;

public class RdpValidation {

    private Gson gson=new Gson();
    private Security security = new Security();

    public boolean isValid(String json, SocketIOClient socketIOClient){
        RdpJsonModel rdpJsonModel = gson.fromJson(json, RdpJsonModel.class);
        BigInteger[] pub = getBigPub(rdpJsonModel);
        BigInteger[] sign = getBigSign(rdpJsonModel);
        if(security.get_verification_hash(rdpJsonModel.getSha_id(),sign,pub)&&
                isCredentialValid(rdpJsonModel)){

        }else{
            return validationFalse(socketIOClient);
        }

        return false;
    }

    private boolean isCredentialValid(RdpJsonModel rdpJsonModel) {
        UserFromJsonDB userFromJsonDB = new UserFromJsonDB();
        String pub_key=rdpJsonModel.getPublic_key().split("&")[0]+rdpJsonModel.getPublic_key().split("&")[1];
        UserJson userByIP = userFromJsonDB.getUserByIP(rdpJsonModel.getIp_server());
        if(userByIP.getIp_server().equals(rdpJsonModel.getIp_server())&&
                userByIP.getIp_server().equals(pub_key)&&
                userByIP.getUser_name().equals(rdpJsonModel.getUser())){
            return true;
        }
        return false;
    }

    private boolean validationFalse(SocketIOClient socketIOClient) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("rdp","false");
        socketIOClient.sendEvent("go_rdp",jsonObject.toString());
        return false;
    }

    private BigInteger[] getBigSign(RdpJsonModel rdpJsonModel) {
        BigInteger[] tmp = new BigInteger[2];
        tmp[0]= new BigInteger(rdpJsonModel.getSign().split("&")[0]);
        tmp[1]= new BigInteger(rdpJsonModel.getSign().split("&")[1]);
        return tmp;
    }

    private BigInteger[] getBigPub(RdpJsonModel rdpJsonModel) {
        BigInteger[] tmp = new BigInteger[2];
        tmp[0]= new BigInteger(rdpJsonModel.getPublic_key().split("&")[0]);
        tmp[1]= new BigInteger(rdpJsonModel.getPublic_key().split("&")[1]);
        return tmp;
    }

}
