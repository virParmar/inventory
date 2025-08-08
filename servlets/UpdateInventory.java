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
@WebServlet(urlPatterns="/updateInventory")
public class UpdateInventory extends HttpServlet
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
BufferedReader bufferedReader=request.getReader();
String line="";
StringBuilder stringBuilder=new StringBuilder();
while(true)
{
line=bufferedReader.readLine();
if(line==null) break;
stringBuilder.append(line);
}
String requestBodyText=stringBuilder.toString();
InventoryBean inventoryBean=gson.fromJson(requestBodyText,InventoryBean.class);
Connection connection=DBConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("update inventory set name=?,quantity=? where inventoryId=?)");
preparedStatement.setString(1,inventoryBean.name);
preparedStatement.setInt(2,inventoryBean.quantity);
preparedStatement.setInt(3,inventoryBean.inventoryId);
preparedStatement.executeUpdate();
preparedStatement.close();
connection.close();
JsonResponse jsonResponse;
jsonResponse=new JsonResponse();
jsonResponse.success=true;
jsonResponse.response="Inventory updated";
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