package vo;

public class UserInfo {
    private String username;
    private String password;

    private int user_id;
    public String getUsername() {
        return username;
    }
    public int getUserID() { return user_id; }
    public void setUsername (String username) {
        this. username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this. password = password;
    }
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
