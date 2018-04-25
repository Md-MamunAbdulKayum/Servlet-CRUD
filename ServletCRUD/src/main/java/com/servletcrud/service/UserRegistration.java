/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servletcrud.service;

import com.servletcrud.util.DBConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Sourav
 */
public class UserRegistration extends HttpServlet {
DBConnection dBConnection;
Connection con;

@Override
public void init() {
  dBConnection = new DBConnection();
  con = dBConnection.checkUser(); 
}
@Override
   public void doPost(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
  
response.setContentType("text/html");  
PrintWriter out = response.getWriter();  
          
  
String name=request.getParameter("name");  
String pass=request.getParameter("password");  

    System.out.println("Username: "+name+" Pass: "+pass);
          
try{  
     
PreparedStatement ps=con.prepareStatement(  
"insert into users (name,password) values(?,?)");  
  
 
ps.setString(1,name);  
ps.setString(2,pass);  
 
          
int i=ps.executeUpdate();  
if(i>0)  
out.print("You are successfully registered...");  
      
          
}catch (Exception e2) { e2.printStackTrace();;}  
          
out.close();  
}  

}
