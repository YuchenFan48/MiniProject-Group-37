package servlet;

import dao.UserDAO;
import dao.impl.UserDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vo.UserInfo;

import java.io.IOException;

public class ModifyUserServlet extends HttpServlet {
    private UserDAO dao; // 提前声明一个UserDAO实例


    public void init() {
        dao = new UserDAOImpl(); // 在init方法中进行实例化
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //使用 init 方法中创建的 dao 实例
        boolean flag = false;
        //从表单获取要修改的用户密码
        String newPassword=request.getParameter("user_password1");

        HttpSession session=request.getSession();
        int userId = (int) session.getAttribute("user_id");
        String userName = (String) session.getAttribute("user_name");

        // 根据需要使用 id 进行操作
        //userinfo.setUser_id(userId);

        //当密码1，密码2有一个不为空时执行以下操作
        if (!request.getParameter("user_password1").isEmpty() && !request.getParameter("user_password2").isEmpty() ) {
            if (dao.compareRegPassword(request.getParameter("user_password1"), request.getParameter("user_password2"))) {
                UserInfo userinfo=new UserInfo();
                userinfo.setPassword(newPassword);
                userinfo.setUser_id(userId);
                userinfo.setUsername(userName);
                flag = dao.updatePassword(userinfo);

            } else {
                request.setAttribute("errorMessage", "Your password inputs do not match");
                response.sendRedirect("./error2.jsp");
                return; // 不再继续执行下面的代码
            }
        }

        if(flag) {
            //修改成功后重新登录
            System.out.println("Successfully Modified!");
            response.sendRedirect("./login.jsp");
        }else {
            // 如果更新不成功或没有任何修改，则转发到 homepage.jsp
            System.out.println("Not Modified!");
            request.getRequestDispatcher("homepage.jsp").forward(request, response);
        }
    }
}