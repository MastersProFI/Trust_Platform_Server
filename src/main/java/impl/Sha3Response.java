package impl;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import encryptmsec.security.Security;

public class Sha3Response {

    public String getResponse(){
        Security security = new Security();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("sha3",security.sha.random_to_hash());
        return jsonObject.toString();
    }

}
