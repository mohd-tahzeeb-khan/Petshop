import javax.servlet.*;
import javax.servlet.http.*;
import javax.xml.transform.Result;

import java.io.*;
import java.sql.*;
public class allanimal extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        PrintWriter out=response.getWriter();
        Connection con;
        String table_deisgn="<td style='border-left:2px solid #000; border-right:2px solid #000;padding:3px 5px;'>";
        String table_close="</td>";
        String title="ALL ANIMALS";
        try {
            Class.forName("org.sqlite.JDBC");
            con=DriverManager.getConnection("jdbc:sqlite:C://Program Files//Apache Software Foundation//Tomcat 9.0_Tomcat//webapps//PetShopSystem//WEB-INF//classes//PetsShop.db");
            Statement state=con.createStatement();
            String query_select="SELECT * FROM report;";
        ResultSet data = state.executeQuery(query_select);
        response.setContentType("text/html");
        out.print("<link rel='stylesheet' href='/report.css'>");
        out.print("<title>" + title+ "</title>");
        out.print("<h1 style='font-size:58px;'>ALL ANIMAL</h1>");
        out.print("<table style='border:2px solid #000; padding:3px 5px;'>");
        out.print("<tr><th style='border:2px solid #000; padding:3px 5px;backgound-color:skyblue;'>Pet</th><th style='border:2px solid #000'>Breed</th><th style='border:2px solid #000'>Colour</th><th style='border:2px solid #000'>Price</th><th style='border:2px solid #000'>Height</th><th style='border:2px solid #000'>Weight</th></tr>");
        while(data.next()){
            String animal=data.getString("pet");
            String breed=data.getString("breed");
            String colour=data.getString("color");
            int height=data.getInt("height");
            int weight=data.getInt("weight");
            int price=data.getInt("price");
            int age=data.getInt("age");
            int count=data.getInt("count");
            out.println("<tr>");
            out.println(table_deisgn + animal + table_close);
            out.println(table_deisgn + breed  + table_close);
            out.println(table_deisgn + colour + table_close);
            out.println(table_deisgn + price  + table_close);
            out.println(table_deisgn + height + table_close);
            out.println(table_deisgn + weight + table_close);
            out.println(table_deisgn +  age   + table_close);
            out.println(table_deisgn + count  + table_close);

            out.println("</tr>");
        }
            out.print("</table>");
    }catch(Exception e){
        out.println(e);
    }
    }
}