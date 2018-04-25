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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Sourav
 */
public class UserLogin extends HttpServlet{
    DBConnection dBConnection;
    Connection con;

    @Override
    public void init() {
      dBConnection = new DBConnection();
      con = dBConnection.checkUser(); 
    }
    
   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String email = request.getParameter("name");
        String pass = request.getParameter("password");
        
        try {
            if(checkUser(con, email, pass))
            {
                RequestDispatcher rs = request.getRequestDispatcher("Welcome");
                rs.forward(request, response);
            }
            else
            {
                out.println("Username or Password incorrect");
                RequestDispatcher rs = request.getRequestDispatcher("login.html");
                rs.include(request, response);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }  
   
   public boolean checkUser(Connection con, String name, String pass) throws SQLException{
            PreparedStatement ps =con.prepareStatement
                             ("select * from users where name=? and password=?");
         ps.setString(1, name);
         ps.setString(2, pass);
         ResultSet rs =ps.executeQuery();
         return rs.next();
   }
}
