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
        String color=request.getParameter("color");
        String no=request.getParameter("no");
        try {
            Class.forName("org.sqlite.JDBC");
            con=DriverManager.getConnection("jdbc:sqlite:C://Program Files//Apache Software Foundation//Tomcat 9.0_Tomcat//webapps//PetShopSystem//WEB-INF//classes//PetsShop.db");
            String query_getting = "SELECT * FROM pets WHERE pet = ? AND breed = ? AND color=?";
            String query_update="UPDATE pets SET count = ? WHERE pet = ? AND breed=? AND color=?";
            String insertQuery="insert into report values(?,?,?,?,?,?);";
            PreparedStatement selectingstatement = con.prepareStatement(query_getting);
            PreparedStatement updatestatement=con.prepareStatement(query_update);
            PreparedStatement insertintosells=con.prepareStatement(insertQuery);
            selectingstatement.setString(1, animal);
            selectingstatement.setString(2, breed);
            selectingstatement.setString(3, color);
            ResultSet results = selectingstatement.executeQuery();
            results.next();
            int numAnimals = results.getInt("count");
            if(numAnimals<=0){
                out.println("No Pet Found!");
                RequestDispatcher rd=request.getRequestDispatcher("sell");
                rd.include(request, response);
            }
            if(numAnimals>=1){
                int count=numAnimals-Integer.parseInt(no);
                out.print(count);
                updatestatement.setString(1, String.valueOf(count));
                updatestatement.setString(2, animal);
                updatestatement.setString(3, breed);
                updatestatement.setString(4, color);
                int i=updatestatement.executeUpdate();
                String animal_get =results.getString("pet");
                String breed_get=results.getString("breed");
                String colour=results.getString("color");
                int price=results.getInt("price");
                int height=results.getInt("height");
                int weight=results.getInt("weight");
                insertintosells.setString(1, animal_get);
                insertintosells.setString(2, colour);
                insertintosells.setString(3, breed_get);
                insertintosells.setInt(4, price);
                insertintosells.setInt(5, height);
                insertintosells.setInt(6,weight);
                insertintosells.executeUpdate();
                
                results.close();
                 if(i>0){
                     RequestDispatcher rd=request.getRequestDispatcher("admin");
                     rd.forward(request, response);
                 }else{
                    out.print("System has encountered some error. Please check your database.");
                    RequestDispatcher rd=request.getRequestDispatcher("sell");
                    rd.include(request, response);
                 }
            }
        } catch (Exception e) {
            out.print("Something Went Wrong, Please Check the Details in the form,  "+e);
           
        }
    }
}