package servlet;

import dao.RobotDAO;
import dao.impl.RobotDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vo.Robot;

import java.io.IOException;

public class DeleteCarServlet extends HttpServlet {
    public DeleteCarServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RobotDAO rb = new RobotDAOImpl();
        boolean flag = false;
        HttpSession session = request.getSession();
        int user_id = (int) session.getAttribute("user_id");
        if (user_id != 0) {
            flag = rb.DeleteCar(user_id);
            System.out.println("deleted");
        } else {
            // 如果carId为空，则返回一个错误响应
            request.setAttribute("errorMessageNull", "your probably input nothing");
            response.sendRedirect("./NewCar.jsp");
        }
        if (flag) {
            response.sendRedirect("./NewCar.jsp");
        }
    }
}