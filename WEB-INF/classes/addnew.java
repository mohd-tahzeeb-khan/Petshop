import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
public class addnew extends HttpServlet{
    public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        Connection con;
        PrintWriter out=response.getWriter();
        response.setContentType("text/html");
        String animal=request.getParameter("pet");
        String breed=request.getParameter("breed");
        String colour=request.getParameter("colour");
        String age=request.getParameter("age");
        String weight=request.getParameter("weight");
        String height=request.getParameter("height");
        String noofanimal=request.getParameter("noofanimal");
        String askingprice=request.getParameter("price");
        try{
            Class.forName("org.sqlite.JDBC");
            con=DriverManager.getConnection("jdbc:sqlite:C://Program Files//Apache Software Foundation//Tomcat 9.0_Tomcat//webapps//PetShopSystem//WEB-INF//classes//PetsShop.db");
            PreparedStatement ps=con.prepareStatement("insert into pets values(?,?,?,?,?,?,?,?);");
            ps.setString(1, animal);
            ps.setString(2, breed);
            ps.setString(3, noofanimal);
            ps.setString(4, askingprice);
            ps.setString(5, colour);
            ps.setString(6, height);
            ps.setString(7, weight);
            ps.setString(8, age);
            int i=ps.executeUpdate();
            if(i>0){
                out.print("New Animal Successully Added");
                RequestDispatcher rd=request.getRequestDispatcher("admin");
                rd.forward(request, response);
            }
            else{
                out.print("Error in Inserting new Animal");
            }
        }catch(Exception e){
            out.print(e);
        }   
    }
}