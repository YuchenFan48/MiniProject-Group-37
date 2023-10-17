package dao.impl;
import dao.RecDAO;
import db.DBConnect;
import vo.Rec;
import vo.Robot;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecDAOImpl implements dao.RecDAO{
    @Override
    public List<Rec> getAllRecs(int user_id){
        List<Rec> recList = new ArrayList<>();
        ResultSet rs = null;
        int flag = 0;
        String sql = "SELECT * FROM recording WHERE user_id = ?";
        PreparedStatement pstmt = null;
        DBConnect dbc = null;

        //下面是针对数据库的具体操作
        try {
            //连接数据库
            dbc = new DBConnect();
            //Statement stmt = dbc.getConnection().createStatement();
            pstmt = dbc.getConnection().prepareStatement(sql);
            pstmt.setString(1,String.valueOf(user_id));
            //进行数据库查询操作
            rs = pstmt.executeQuery();
            System.out.println(rs);
            while (rs.next()) {
                Rec rec = new Rec();//Robot robot = new Robot();
                rec.setRecId(rs.getInt("Rec_id"));
                rec.setRuntime(rs.getInt("runtime"));
                rec.setUserId(rs.getInt("user_id"));
                rec.setStatus(rs.getInt("status"));
                rec.setRobotId(rs.getInt("robot_id"));
                rec.setVideoURL(rs.getString("videoURL"));
                recList.add(rec);
            }

            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("An error occurred while querying the database: " + e.getMessage());
            e.printStackTrace();
        } finally {
            //关闭数据库连接
            dbc.close();
        }
        return recList;
    };
}
