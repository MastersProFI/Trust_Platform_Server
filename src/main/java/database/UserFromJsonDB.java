package database;

import model.UserArray;
import model.UserJson;

public class UserFromJsonDB {

    private UserArray userArray = new UserArray();

    public UserJson getUserByIP(String ip){
        userArray.updateFromFile();
        for (UserJson userJson : userArray.getUsers()) {
            if(userJson.getIp_server().equals(ip)){
                return userJson;
            }
        }
        return null;
    }

    public UserJson getUserByPublic(){
        userArray.updateFromFile();
        return null;
    }


}
