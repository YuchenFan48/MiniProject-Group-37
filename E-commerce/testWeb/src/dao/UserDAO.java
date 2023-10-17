package dao;
import vo.*;
public interface UserDAO {
    public boolean save(UserInfo user);
    public int queryByUserInfo(UserInfo userinfo) throws Exception;
    public boolean compareRegPassword(String password1,String password2);
    public int queryUserID(String user_name) throws Exception;

    boolean updateUsername(UserInfo userinfo);
    boolean updatePassword(UserInfo userinfo);
}
