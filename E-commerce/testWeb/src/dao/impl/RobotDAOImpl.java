package dao.impl;

import dao.RobotDAO;
import db.DBConnect;
import vo.Robot;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RobotDAOImpl implements RobotDAO {

    @Override
    public List<Robot> getAllRobots() {
        List<Robot> robotList = new ArrayList<>();
        ResultSet rs = null;

        int flag = 0;
        String sql = "SELECT e3.avgruntime,e3.robot_name,e3.robot_color,e3.robot_numOfAxes,e3.robot_numOfWheel,e3.robot_power,e4.user_name FROM(SELECT " +
                "e2.avgruntime,e1.robot_name,e1.robot_color, e1.robot_numOfAxes, e1.robot_numOfWheel, e1.robot_power, e1.user_id FROM \n" +
                " robot e1 JOIN (SELECT AVG(runtime) as avgruntime, robot_id FROM recording GROUP BY robot_id) e2 ON e1.robot_id = e2.robot_id) e3\n" +
                "JOIN userinfo e4 ON e3.user_id = e4.user_id ORDER BY e3.avgruntime;";
        ;
        PreparedStatement pstmt = null;
        DBConnect dbc = null;

        //下面是针对数据库的具体操作
        try {
            //连接数据库
            dbc = new DBConnect();
            pstmt = dbc.getConnection().prepareStatement(sql);
            //进行数据库查询操作
            rs = pstmt.executeQuery();
            System.out.println(rs);
            while (rs.next()) {
                Robot robot = new Robot();

                robot.setAvgruntime(Double.valueOf(rs.getString("avgruntime")));
                robot.setName(rs.getString("robot_name"));
                robot.setColor(rs.getString("robot_color"));
                robot.setNumOfAxes(rs.getInt("robot_numOfAxes"));
                robot.setNumOfWheel(rs.getInt("robot_numOfWheel"));
                robot.setPower(rs.getString("robot_power"));
                robot.setUserName(rs.getString("user_name"));

                robotList.add(robot);

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
        return robotList;
    }

    @Override
    public List<Robot> getMyRobots(int user_id) {
        List<Robot> robotList = new ArrayList<>();
        ResultSet rs = null;

        int flag = 0;
        String sql = "SELECT * FROM robot where user_id=?";//
        PreparedStatement pstmt = null;
        DBConnect dbc = null;

        //下面是针对数据库的具体操作
        try {
            //连接数据库
            dbc = new DBConnect();
            pstmt = dbc.getConnection().prepareStatement(sql);
            pstmt.setInt(1, user_id);

            //进行数据库查询操作
            rs = pstmt.executeQuery();
            System.out.println(rs);
            while (rs.next()) {
                Robot robot = new Robot();

                robot.setId(rs.getInt("robot_id"));
                robot.setName(rs.getString("robot_name"));
                robot.setColor(rs.getString("robot_color"));
                robot.setNumOfAxes(rs.getInt("robot_numOfAxes"));
                robot.setNumOfWheel(rs.getInt("robot_numOfWheel"));
                robot.setPower(rs.getString("robot_power"));
                robot.setUserId(rs.getInt("user_id"));

                robotList.add(robot);

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
        return robotList;
    }
    @Override
    public boolean updateCar(Robot car,int user_id) {
        boolean flag = false;
        String sql = "UPDATE robot SET robot_name=?, robot_color=?, robot_numOfAxes=?, robot_numOfWheel=?, robot_power=? WHERE robot_id=?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = new DBConnect().getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, car.getName());
            preparedStatement.setString(2, car.getColor());
            preparedStatement.setInt(3, car.getNumOfAxes());
            preparedStatement.setInt(4, car.getNumOfWheel());
            preparedStatement.setString(5, car.getPower());
            preparedStatement.setInt(6, car.getUserId());

            int row = preparedStatement.executeUpdate();
            if (row > 0) {
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return flag;
    }

    @Override
    public Robot getCarById(int rId) {
        Robot car = null;
        String sql = "SELECT * FROM robot WHERE robot_id=?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = new DBConnect().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, rId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                car = new Robot();
                car.setId(resultSet.getInt("robot_id"));
                car.setName(resultSet.getString("robot_name"));
                car.setColor(resultSet.getString("robot_color"));
                car.setNumOfAxes(resultSet.getInt("robot_numOfAxes"));
                car.setNumOfWheel(resultSet.getInt("robot_numOfWheel"));
                car.setPower(resultSet.getString("robot_power"));
                car.setUserId(resultSet.getInt("user_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return car;
    }
    //小车注册

    public boolean NewCar (Robot robot){
        boolean flag = false;
        String sql = "insert into robot VALUES (?,?,?,?,?,?,?)";
        String sql2 = "SELECT COUNT(robot_id) AS count FROM robot";
        DBConnect dbc = null;
        PreparedStatement pstmt = null ;

        int countRobotID=-1;
        try{
            // 连接数据库
            dbc = new DBConnect() ;
            Statement stmt = dbc.getConnection().createStatement();

            ResultSet rs = stmt.executeQuery(sql2);
            if(rs.next())
                countRobotID = rs.getInt("count");
            pstmt = dbc.getConnection().prepareStatement(sql);

            pstmt.setInt(1,robot.getUserId());
            pstmt.setString (2,robot.getName());
            pstmt.setString(3,robot.getColor());
            pstmt.setInt(4,robot.getNumOfAxes());
            pstmt.setInt(5,robot.getNumOfWheel());
            pstmt.setString(6,robot.getPower());
            pstmt.setInt(7,robot.getUserId());

            int row = pstmt.executeUpdate();
            if (row > 0) {
                flag = true;
            }
            pstmt.close() ;
            pstmt.close() ;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally{
            // 关闭数据库连接
            dbc.close() ;
        }

        return flag;
    }


    @Override
    public boolean DeleteCar(int car_id){
        boolean flag=false;
        String sql="DELETE FROM robot where robot_id=?";
        DBConnect dbc = null;
        PreparedStatement pstmt = null ;
        try{
            // 连接数据库
            dbc = new DBConnect() ;
            pstmt = dbc.getConnection().prepareStatement(sql);

                pstmt.setInt(1,car_id);
                pstmt.executeUpdate();
                pstmt.close() ;
                flag = true;

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally{
            // 关闭数据库连接
            dbc.close() ;
        }

        return flag;
    }
    @Override
    public void writeUserIdToFile( int user_id, String filePath)
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(Integer.toString(user_id));
            System.out.println("UserId has been written to the file.");
        } catch (IOException e) {
            System.err.println("An error occurred while writing the userId to the file.");
            e.printStackTrace();
        }
    }
}
