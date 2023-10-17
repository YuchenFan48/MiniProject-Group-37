package servlet;
import java.io.IOException;
import java.util.List;

import dao.RecDAO;
import dao.RobotDAO;
import dao.impl.RecDAOImpl;
import dao.impl.RobotDAOImpl;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import dao.impl.UserDAOImpl;
import vo.*;
import dao.UserDAO;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
public class CarpageServlet extends HttpServlet{

    private RecDAO recdao;
    private RobotDAO robotDAO;
    @Override
    public void init() throws ServletException {
        super.init();
        recdao = (RecDAO) new RecDAOImpl();
        robotDAO = (RobotDAO) new RobotDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int user_id = (int) session.getAttribute("user_id");
        String filePath = "D:\\Short_term\\SrcCodeVer6\\testWeb\\web\\Videos\\user.txt";

        robotDAO.writeUserIdToFile(user_id,filePath);
        System.out.println("user id is");
        System.out.println(user_id);
        List<Rec> recList = recdao.getAllRecs(user_id);
        System.out.println(recList);
        if (recList != null)
            request.setAttribute("recList", recList);
        else
            System.out.println("no list");
        request.getRequestDispatcher("Carpage.jsp").forward(request, response);
    }
}