package dao.impl;
import java.sql.Connection;
import java.sql.*;

import dao.UserDAO;
import db.DBConnect;
import vo.UserInfo;

public class UserDAOImpl implements dao.UserDAO {

    @Override
    public boolean save(UserInfo user) {
        boolean flag = false;
        String sql1 = "insert into userinfo(user_id, user_name, user_password) values(?,?,?)";
        String sql2 = "SELECT COUNT(user_id) AS count FROM userinfo";
        PreparedStatement pst = null;
        DBConnect connection = null;
        int userid = -1;
        try {
            connection = new DBConnect();
            Statement stmt = connection.getConnection().createStatement();

            // 执行查询
            ResultSet rs = stmt.executeQuery(sql2);
            if(rs.next())
                userid = rs.getInt("count")+1;
            pst = connection.getConnection().prepareStatement(sql1);
            pst.setString(1, String.valueOf(userid));
            pst.setString(2, user.getUsername());
            pst.setString(3, user.getPassword());
            int row = pst.executeUpdate();
            if (row > 0) {
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return flag;
    }
    public int queryByUserInfo(UserInfo userinfo) throws Exception {
        int flag = 0;
        String sql = "select * from userinfo where user_name=?";
        PreparedStatement pstmt = null;
        DBConnect dbc = null;

        //下面是针对数据库的具体操作
        try {
            //连接数据库
            dbc = new DBConnect();
            pstmt = dbc.getConnection().prepareStatement(sql);
            pstmt.setString(1, userinfo.getUsername());
            //进行数据库查询操作
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                //查询出内容，将其与用户提交的内容对比
                if (rs.getString("user_password").equals(userinfo.getPassword())) {
                    flag = 1;
                }
            }

            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            //关闭数据库连接
            dbc.close();
        }

        return flag;
    }
    public int queryUserID(String user_name) throws Exception {
        int flag = 0;
        String sql = "SELECT * FROM userinfo WHERE user_name = ?";
        System.out.println(user_name);
        PreparedStatement pstmt = null;
        DBConnect dbc = null;
        int user_id = 0;
        //下面是针对数据库的具体操作
        try {
            //连接数据库
            dbc = new DBConnect();
            pstmt = dbc.getConnection().prepareStatement(sql);
            pstmt.setString(1, user_name);
            //进行数据库查询操作
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            user_id = rs.getInt(1);
            System.out.println("query user id ");
            System.out.println(user_id);
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            //关闭数据库连接
            dbc.close();
        }

        return user_id;
    }
    public boolean compareRegPassword(String password1, String password2) {
        if (password2.equals(password1)) {
            return true;
        } else {
            return false;
        }
    }
    @Override
    public boolean updateUsername(UserInfo user) {
        boolean flag=false;
        String sql="update userinfo set user_name=? where user_id=?";

        PreparedStatement pst = null;
        DBConnect connection = null;

        try {
            connection = new DBConnect();
            pst = connection.getConnection().prepareStatement(sql);
            pst.setString(1, user.getUsername());
            pst.setString(2, String.valueOf(user.getUserID()));

            int row = pst.executeUpdate();
            if (row > 0) {
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                connection.close();
            }
        }
        return flag;
    }

    public boolean updatePassword(UserInfo user) {
        boolean flag = false;
        String sql = "update userinfo set user_password=? where user_id=?";

        PreparedStatement pst = null;
        DBConnect connection = null;

        try {
            connection = new DBConnect();
            pst = connection.getConnection().prepareStatement(sql);
            pst.setString(1, user.getPassword());
            pst.setString(2, String.valueOf(user.getUserID()));
            //System.out.println("id"+getId());

            int row = pst.executeUpdate();
            if (row > 0) {
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                connection.close();
            }
        }
        return flag;
    }
}