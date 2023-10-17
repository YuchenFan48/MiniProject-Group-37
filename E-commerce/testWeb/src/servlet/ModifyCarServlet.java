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

public class ModifyCarServlet extends HttpServlet {
    private RobotDAO rDAO;

    public void init() {
        rDAO = new RobotDAOImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 从表单获取要修改的小车信息
        String newCarName = request.getParameter("newCarName");
        String newCarColor = request.getParameter("newCarColor");
        String newCarFreedom = request.getParameter("newCarFreedom");
        String newCarWheelNum = request.getParameter("newCarWheelNum");
        String newCarPower = request.getParameter("newCarPower");

      // 用合适的方式获取小车ID
        HttpSession session = request.getSession();
        int carId = (int) session.getAttribute("user_id");
        // 根据ID获取原始的小车信息
        Robot existingCar = rDAO.getCarById(carId);

        // 更新小车信息
        existingCar.setName(newCarName) ;
        existingCar.setColor(newCarColor);
        existingCar.setNumOfAxes(Integer.valueOf(newCarFreedom));
        existingCar.setNumOfWheel(Integer.valueOf(newCarWheelNum));
        existingCar.setPower(newCarPower);
        existingCar.setId(Integer.valueOf(carId));
        // 保存更新后的小车信息到数据库
        boolean flag = rDAO.updateCar(existingCar,carId);

        if (flag) {
            // 修改成功后重定向到成功页面
            response.sendRedirect("./homepage");
        } else {
            // 修改失败时转发到错误页面
            request.setAttribute("errorMessage", "Failed to modify car information.");
            request.getRequestDispatcher("./homepage").forward(request, response);
        }
    }
}
