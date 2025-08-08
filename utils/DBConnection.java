package com.shop.utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.shop.exception.InventoryException;
public class DBConnection {
private DBConnection()
{
}
public static Connection getConnection() throws InventoryException 
{
try
{
Connection connection;
Class.forName("com.mysql.cj.jdbc.Driver");
connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/shop_db?useSSL=false&allowPublicKeyRetrieval=true","shop_user","shop_user");
return connection;
}catch(SQLException sqlException) {
throw new InventoryException(sqlException.getMessage());
}catch(ClassNotFoundException classNotFoundException) {
throw new InventoryException(classNotFoundException.getMessage());
}
}
}
