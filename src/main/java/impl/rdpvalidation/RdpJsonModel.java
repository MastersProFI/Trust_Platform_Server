package impl.rdpvalidation;

public class RdpJsonModel {

    private String public_key;
    private String sign;
    private String sha_id;
    private String ip_server;
    private String user;

    public String getSha_id() {
        return sha_id;
    }

    public void setSha_id(String sha_id) {
        this.sha_id = sha_id;
    }

    public String getPublic_key() {
        return public_key;
    }

    public void setPublic_key(String public_key) {
        this.public_key = public_key;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getIp_server() {
        return ip_server;
    }

    public void setIp_server(String ip_server) {
        this.ip_server = ip_server;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

}
