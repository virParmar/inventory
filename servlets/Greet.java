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
@WebServlet(urlPatterns="/greet")
public class Greet extends HttpServlet
{
public void doGet(HttpServletRequest request,HttpServletResponse response)
{
System.out.println("Hello.............");
}
}