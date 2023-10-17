package servlet;
import java.io.IOException;

import dao.impl.RobotDAOImpl;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import dao.impl.UserDAOImpl;
import vo.*;
import dao.UserDAO;
import dao.RobotDAO;

public class UserLoginServlet extends HttpServlet {
    UserInfo userinfo = new UserInfo();
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {
        //UserInfo userinfo = new UserInfo();
        req.getSession();
        userinfo.setUsername((String) req.getParameter("username"));
        userinfo.setPassword((String) req.getParameter("password"));
        UserDAO dao = new UserDAOImpl();
        RobotDAO rdao=new RobotDAOImpl();
        try {
            userinfo.setUser_id(dao.queryUserID(userinfo.getUsername()) );
            System.out.println("running?");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        int flag = 0;
        try {
            flag = dao.queryByUserInfo(userinfo);
        } catch (Exception e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (flag == 1) {
            HttpSession session = req.getSession();
            session.setAttribute("user_name", userinfo.getUsername());
            session.setAttribute("user_id", userinfo.getUserID());
            String filePath = "D:\\Short_term\\SrcCodeVer6\\testWeb\\web\\Videos\\user.txt";

            rdao.writeUserIdToFile(userinfo.getUserID(),filePath);
            System.out.println(session.getAttribute("user_id"));
            System.out.println("System.out.println(userinfo.getUserID());");
            System.out.println(userinfo.getUserID());
            res.sendRedirect("./homepage");
        } else {
            res.sendRedirect("./error1.jsp");
        }
    }
}