package dao;

import vo.Robot;
import vo.UserInfo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public interface RobotDAO {
    List<Robot> getAllRobots();
    List<Robot> getMyRobots(int user_id);
    Robot getCarById(int rId);
    boolean updateCar(Robot car,int user_id);
    boolean NewCar (Robot robot);
    boolean DeleteCar(int car_id);
    void writeUserIdToFile( int user_id, String filePath);
}
