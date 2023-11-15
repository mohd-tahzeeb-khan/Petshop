import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
public class login extends HttpServlet{
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        String user=request.getParameter("username");
        String pass=request.getParameter("passwords");
        if(user.equals("kritika") && pass.equals("pass")){
            RequestDispatcher rd=request.getRequestDispatcher("admin");
            rd.forward(request, response);
        }
        else{
            out.print("Wrong Credentials. Try Again");
            RequestDispatcher rd=request.getRequestDispatcher("index.html");
            rd.include(request, response);
        }
    }
}