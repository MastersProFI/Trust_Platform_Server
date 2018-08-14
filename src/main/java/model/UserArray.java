package model;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Scanner;

public class UserArray {

    private ArrayList<UserJson> users = new ArrayList<UserJson>();

    public ArrayList<UserJson> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<UserJson> users) {
        this.users = users;
    }

    public void updateFromFile(){
        users.removeAll(users);
        Gson gson = new Gson();
        StringBuilder stringBuilder=null;
        try {
            stringBuilder = new StringBuilder();
            Scanner scn = new Scanner(new File(System.getProperty("user.dir")+"\\src\\main\\java\\database\\UserBD.txt"));
            while(scn.hasNextLine()){
                stringBuilder.append(scn.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        UserArray tmp=gson.fromJson(stringBuilder.toString(), UserArray.class);
        users=tmp.users;
    }

}
