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

public class NewCarServlet extends HttpServlet {
    public NewCarServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RobotDAO rd = new RobotDAOImpl();

        boolean flag = false;

        Robot robot = new Robot();
        robot.setName(request.getParameter("newCarName"));
        robot.setColor(request.getParameter("newCarColor"));
        robot.setNumOfAxes(Integer.valueOf(request.getParameter("newCarFreedom")));
        robot.setNumOfWheel(Integer.valueOf(request.getParameter("newCarWheelNum")));
        robot.setPower(request.getParameter("newCarPower"));

        HttpSession session = request.getSession();
        int user_id = (int) session.getAttribute("user_id");
        robot.setId(user_id);
        robot.setUserId(user_id);
        flag = rd.NewCar(robot);

        if(flag) {
            //注册成功后跳转
            response.sendRedirect("./homepage");
        }else{
            System.out.println("Wrong");
            response.sendRedirect("./homepage");
        }
    }
}