package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/userServlet")
public class UserExampleController extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        if(req.getSession().getAttribute("userNameSessionName") == null ){
            req.getSession().setAttribute("userNameSessionName", "initialName");
        }

        if(req.getSession().getAttribute("userPasswordSessionName") == null){
            req.getSession().setAttribute("userPasswordSessionName", "initialPassword");
        }

        getServletContext().getRequestDispatcher("/login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        String name = req.getParameter("login");
        String password = req.getParameter("password");

        if(name.isEmpty() || name == null ){
            name = "initialName";
        }

        if(password.isEmpty() || password == null ){
            password="initialPassword";
        }

        req.getSession().setAttribute("userNameSessionName", name);
        req.getSession().setAttribute("userPasswordSessionName", password);

        getServletContext().getRequestDispatcher("/login.jsp").forward(req,resp);

    }

}
