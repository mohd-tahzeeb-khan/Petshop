import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
public class delete extends HttpServlet{
    public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        PrintWriter out=response.getWriter();
        Connection con;
        response.setContentType("text/html");
        String animal=request.getParameter("animal");
        String breed=request.getParameter("breed");
        String colour=request.getParameter("colour");
        try {
            Class.forName("org.sqlite.JDBC");
            con=DriverManager.getConnection("jdbc:sqlite:C://Program Files//Apache Software Foundation//Tomcat 9.0_Tomcat//webapps//petshop//WEB-INF//classes//petshop.db");
            PreparedStatement ps=con.prepareStatement("delete from pets where animal=? and breed=? and colour=?;");
            ps.setString(1, animal);
            ps.setString(2, breed);
            ps.setString(3, colour);
            int i=ps.executeUpdate();
            if(i>0){
                out.println("Animal Deleted");
                RequestDispatcher rd=request.getRequestDispatcher("admin");
                rd.forward(request, response);
            }
            else{
                out.print("Something went wrong");
                RequestDispatcher rd=request.getRequestDispatcher("delete");
                rd.include(request,response);
            }


        } catch (Exception e) {
            out.print("Something went wrong, Database error-> "+e);
        }
    }
}

