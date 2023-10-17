package servlet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dao.RobotDAO;
import dao.impl.RobotDAOImpl;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vo.Robot;

public class HomepageServlet extends HttpServlet {
    private RobotDAO robotDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        robotDAO = new RobotDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int user_id = (int) session.getAttribute("user_id");
        List<Robot> carList = robotDAO.getMyRobots(user_id);

        request.setAttribute("carList", carList);
        //request.getRequestDispatcher("homepage.jsp").forward(request, response);

        List<Robot> carList1=robotDAO.getAllRobots();
        request.setAttribute("carList1", carList1);
        request.getRequestDispatcher("homepage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    /*protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 处理 POST 请求，如果需要的话可以在此方法中进行其他操作

        // 获取表单数据
        String name = request.getParameter("name");
        int id = Integer.parseInt(request.getParameter("id"));
        String color = request.getParameter("color");
        int axes = Integer.parseInt(request.getParameter("axes"));
        int wheels = Integer.parseInt(request.getParameter("wheels"));
        String power = request.getParameter("power");
        int userid = Integer.parseInt(request.getParameter("userid"));
        // 创建 Robot 对象
        Robot robot = new Robot(id, name, color, axes, wheels, power, userid);

        // 调用 RobotDAO 的实现类保存小车数据
        RobotDAO robotDAO = new RobotDAOImpl();

        // 重定向到 homepage 页面显示更新后的小车数据
        response.sendRedirect(request.getContextPath() + "./homepage.jsp");
    }*/
}
