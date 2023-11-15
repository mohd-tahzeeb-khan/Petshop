import javax.servlet.*;
import javax.servlet.http.*;
import javax.xml.transform.Result;

import java.io.*;
import java.sql.*;
public class report extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        PrintWriter out=response.getWriter();
        Connection con;
        try {
            Class.forName("org.sqlite.JDBC");
            con=DriverManager.getConnection("jdbc:sqlite:C://Program Files//Apache Software Foundation//Tomcat 9.0_Tomcat//webapps//petshop//WEB-INF//classes//petshop.db");
            Statement state=con.createStatement();
            String query_select="SELECT * FROM sells;";
        ResultSet data = state.executeQuery(query_select);
        response.setContentType("text/html");
        out.print("<h1>Report</h1>");
        out.print("<table style='border:2px solid #000; padding:3px 5px;'>");
        out.print("<tr><th style='border:2px solid #000; padding:3px 5px;backgound-color:skyblue;'>Animal</th><th style='border:2px solid #000'>Breed</th><th style='border:2px solid #000'>Colour</th><th style='border:2px solid #000'>Count</th><th style='border:2px solid #000'>Price</th></tr>");
        while(data.next()){
            String animal=data.getString("animal");
            String breed=data.getString("breed");
            String colour=data.getString("color");
            int count=data.getInt("count");
            int price=data.getInt("price");
            out.println("<tr>");
            out.println("<td style='border:2px solid #000; padding:3px 5px;'>" + animal + "</td>");
            out.println("<td style='border:2px solid #000; padding:3px 5px;'>" + breed + "</td>");
            out.println("<td style='border:2px solid #000; padding:3px 5px;'>" + colour + "</td>");
            out.println("<td style='border:2px solid #000; padding:3px 5px;'>" + count + "</td>");
            out.println("<td style='border:2px solid #000; padding:3px 5px;'>" + price + "</td>");
            out.println("</tr>");
        }
            out.print("</table>");
    }catch(Exception e){
        out.println(e);
    }
    }
}