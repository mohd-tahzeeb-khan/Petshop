import javax.print.attribute.standard.RequestingUserName;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
public class sell extends HttpServlet{
public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        PrintWriter out=response.getWriter();
        Connection con;
        response.setContentType("text/html");
        String animal=request.getParameter("animal");
        String breed=request.getParameter("breed");
        String no=request.getParameter("no");
        try {
            Class.forName("org.sqlite.JDBC");
            con=DriverManager.getConnection("jdbc:sqlite:C://Program Files//Apache Software Foundation//Tomcat 9.0_Tomcat//webapps//petshop//WEB-INF//classes//petshop.db");
            String query_getting = "SELECT * FROM pets WHERE animal = ? AND breed = ?";
            String query_update="UPDATE pets SET count = ? WHERE animal = ? AND breed=?";
            String insertQuery="insert into sells values(?,?,?,?,?);";
            PreparedStatement selectingstatement = con.prepareStatement(query_getting);
            PreparedStatement updatestatement=con.prepareStatement(query_update);
            PreparedStatement insertintosells=con.prepareStatement(insertQuery);
            selectingstatement.setString(1, animal);
            selectingstatement.setString(2, breed);
            ResultSet results = selectingstatement.executeQuery();
            results.next();
            int numAnimals = results.getInt("count");

            // out.println(numAnimals);
            // out.println(no);
            int count=numAnimals-Integer.parseInt(no);
            // out.println(count);
            updatestatement.setString(1, String.valueOf(count));
            updatestatement.setString(2, animal);
            updatestatement.setString(3, breed);
            int i=updatestatement.executeUpdate();
            String animal_get =results.getString("animal");
            String breed_get=results.getString("breed");
            String color=results.getString("colour");
            int price=results.getInt("price");
            insertintosells.setString(1, animal_get);
            insertintosells.setString(2, breed_get);
            insertintosells.setString(3, color);
            insertintosells.setInt(4, price);
            insertintosells.setInt(5,Integer.parseInt(no));
            insertintosells.executeUpdate();
            
            results.close();
            // out.print(i);
             if(i>0){
                 RequestDispatcher rd=request.getRequestDispatcher("admin");
                 rd.forward(request, response);
             }else{
                out.print("System has encountered some error. Please check your database.");
                RequestDispatcher rd=request.getRequestDispatcher("sell");
                rd.include(request, response);
             }
        } catch (Exception e) {
            out.print("Check your database "+e);
        }
    }
}