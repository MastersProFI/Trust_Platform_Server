package model;

public class UserJson {

    private String ip_server;
    private String public_key;
    private String user_name;
    private String user_pass;

    public String getIp_server() {
        return ip_server;
    }

    public void setIp_server(String ip_server) {
        this.ip_server = ip_server;
    }

    public String getPublic_key() {
        return public_key;
    }

    public void setPublic_key(String public_key) {
        this.public_key = public_key;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_pass() {
        return user_pass;
    }

    public void setUser_pass(String user_pass) {
        this.user_pass = user_pass;
    }
}
