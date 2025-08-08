package com.shop.servlet;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.*;
import java.util.*;
import java.sql.*;
import com.shop.beans.*;
import com.shop.utils.*;
import com.shop.exception.*;
import com.google.gson.*;
@WebServlet(urlPatterns="/getInventory")
public class GetInventory extends HttpServlet
{
public void doGet(HttpServletRequest request,HttpServletResponse response)
{
}
public void doPost(HttpServletRequest request,HttpServletResponse response)
{
GsonBuilder gsonBuilder=new GsonBuilder();
Gson gson=gsonBuilder.create();
try
{
int inventoryId=Integer.parseInt(request.getParameter("orderId"));
Connection connection=DBConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select * from inventory where inventory_id=?");
preparedStatement.setInt(1,inventoryId);
ResultSet resultSet=preparedStatement.executeQuery();
resultSet.next();
String name=resultSet.getString("name");
int quantity=resultSet.getInt("quantity");
resultSet.close();
preparedStatement.close();
connection.close();
JsonResponse jsonResponse;
jsonResponse=new JsonResponse();
jsonResponse.success=true;
jsonResponse.response="Inventory id : "+inventoryId+" Name : "+name+" Quantity : "+quantity;
String responseString;
responseString=gson.toJson(jsonResponse);
response.setContentType("applicantion/json");
PrintWriter printWriter=response.getWriter();
printWriter.print(responseString);
}catch(Exception exception)
{
System.out.println(exception.getMessage());
}
}
}