package db;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

public class UpdateVideoURL {

    public static void main(String[] args) throws ClassNotFoundException {
        // 获取文件路径
        String filePath = "D:\\Short_term\\SrcCodeVer6\\testWeb\\src\\db\\image_urls.txt";

        // 获取行数
        int numLines = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            while (reader.readLine() != null) numLines++;
        } catch (IOException e) {
            System.err.println("无法读取文件：" + filePath);
            System.exit(1);
        }

        // 获取用户输入
        Scanner scanner = new Scanner(System.in);
        int recId = 0;
        do {
            System.out.printf("共有%d行URL，请选择1-%d之间的一个整数作为Rec_id：", numLines, numLines);
            try {
                recId = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.err.println("错误：请输入整数！");
                continue;
            }
            if (recId < 1 || recId > numLines) {
                System.err.printf("错误：请输入1-%d之间的整数！\n", numLines);
            }
        } while (recId < 1 || recId > numLines);

        // 选择相应的行
        String line = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            for (int i = 1; i <= recId; i++) line = reader.readLine();
        } catch (IOException e) {
            System.err.println("无法读取文件：" + filePath);
            System.exit(1);
        }

        // 连接到数据库


        String url = "jdbc:mysql://127.0.0.1:3306/javawebdb?useSSL=false&allowPublicKeyRetrieval=true";
        String user = "root";
        String password = "Sun88599879";
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            // 执行更新操作
            String sql = "UPDATE recording SET videoURL = ? WHERE Rec_id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1,line);
                stmt.setInt(2, recId);
                stmt.executeUpdate();
                System.out.println("视频URL已更新！");
            }
        } catch (SQLException e) {
            System.err.println("SQL错误：" + e.getMessage());
            System.exit(1);
        }
    }
}