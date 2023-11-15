import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
public class add extends HttpServlet{
    public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        Connection con;
        String animal=request.getParameter("animal");
        String breed=request.getParameter("breed");
        String colour=request.getParameter("colour");
        String no=request.getParameter("no");
        try {
            Class.forName("org.sqlite.JDBC");
            con=DriverManager.getConnection("jdbc:sqlite:C://Program Files//Apache Software Foundation//Tomcat 9.0_Tomcat//webapps//petshop//WEB-INF//classes//petshop.db");      
            String query_getting = "SELECT count FROM pets WHERE animal = ? AND breed = ? AND colour=?";
            String query_update="UPDATE pets SET count = ? WHERE animal = ? AND breed=? AND colour=?";
            PreparedStatement updatestatement=con.prepareStatement(query_update);
            PreparedStatement selectingstatement = con.prepareStatement(query_getting);
            
            selectingstatement.setString(1, animal);
            selectingstatement.setString(2, breed);
            selectingstatement.setString(3, colour);
            ResultSet results = selectingstatement.executeQuery();
            results.next();
            int numAnimals = results.getInt("count");
            results.close();
            int count=numAnimals+Integer.parseInt(no);
            updatestatement.setString(1, String.valueOf(count));
            updatestatement.setString(2, animal);
            updatestatement.setString(3, breed);
            updatestatement.setString(4, colour);
            int i=updatestatement.executeUpdate();
            if(i>0){
                RequestDispatcher rd=request.getRequestDispatcher("admin");
                rd.forward(request, response);
            }else{
                RequestDispatcher rd=request.getRequestDispatcher("add");
                 rd.include(request, response);
            }
        } catch (Exception e) {
            out.println("Something went Wrong-> "+e);
            // TODO: handle exception
        }
    }
}