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
@WebServlet(urlPatterns="/deleteInventory")
public class DeleteInventory extends HttpServlet
{
public void doGet(HttpServletRequest request,HttpServletResponse response)
{
}
public void doPost(HttpServletRequest request,HttpServletResponse response)
{
try
{
int inventoryId=Integer.parseInt(request.getParameter("orderId"));
System.out.println("Inventory id : "+inventoryId);
Connection connection=DBConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("delete from inventory where inventory_id=?");
preparedStatement.setInt(1,inventoryId);
preparedStatement.executeUpdate();
preparedStatement.close();
connection.close();
JsonResponse jsonResponse;
jsonResponse=new JsonResponse();
jsonResponse.success=true;
jsonResponse.response="Inventory deleted with inventory id : "+inventoryId;
String responseString;
Gson gson=new Gson();
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