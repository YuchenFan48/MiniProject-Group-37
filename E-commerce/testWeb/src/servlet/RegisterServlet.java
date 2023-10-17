package servlet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dao.UserDAO;
import dao.impl.UserDAOImpl;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vo.UserInfo;

public class RegisterServlet extends HttpServlet {
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub


        UserDAO ud = new UserDAOImpl();

        boolean flag = false;
        if(!request.getParameter("passwordinput1").isEmpty() && !request.getParameter("passwordinput2").isEmpty() && !request.getParameter("user_name").isEmpty()) {
            if (ud.compareRegPassword(request.getParameter("passwordinput1"), request.getParameter("passwordinput2"))) {
                UserInfo userinfo = new UserInfo();
                userinfo.setUsername(request.getParameter("user_name"));
                userinfo.setPassword(request.getParameter("passwordinput1"));
                flag = ud.save(userinfo);
                //response.sendRedirect("./test.jsp");

            } else {
                request.setAttribute("errorMessage", "your password input are different");
                response.sendRedirect("./error2.jsp");
            }
        }
        else{
            request.setAttribute("errorMessageNull", "your probably input nothing");
            request.getRequestDispatcher("Register.jsp").forward(request,response);
            response.sendRedirect("./login.jsp");
        }
        if(flag) {
            //注册成功后跳转
            response.sendRedirect("./login.jsp");
        }
}}