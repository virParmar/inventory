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
@WebServlet(urlPatterns="/tempInventory")
public class TempInventory extends HttpServlet
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
JsonResponse jsonResponse;
jsonResponse=new JsonResponse();
jsonResponse.success=true;
jsonResponse.response="Inventory created with inventory id : ";
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