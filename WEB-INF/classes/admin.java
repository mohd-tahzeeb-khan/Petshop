import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
public class admin extends HttpServlet{
    public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        response.setContentType("text/html");
        RequestDispatcher rd=request.getRequestDispatcher("admin.html");
        rd.forward(request, response);
    }
}