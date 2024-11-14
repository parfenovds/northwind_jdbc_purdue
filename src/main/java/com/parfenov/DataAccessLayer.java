package com.parfenov;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DataAccessLayer {

  private String connectionString;
  private String username;
  private String password;

  public DataAccessLayer(String server, String database, String username, String password) {
    this.connectionString = "jdbc:sqlserver://" + server + ":1433;databaseName=" + database + ";encrypt=true;trustServerCertificate=true;";
    this.username = username;
    this.password = password;
  }

  private Connection getConnection() throws SQLException {
    return DriverManager.getConnection(connectionString, username, password);
  }

  public long getCustomerCount() throws SQLException {
    String query = "SELECT COUNT(*) FROM Customers";
    try (Connection connection = getConnection();
         PreparedStatement pstmt = connection.prepareStatement(query);
         ResultSet rs = pstmt.executeQuery()) {
      if (rs.next()) {
        return rs.getLong(1);
      }
    }
    return 0;
  }

  public List<String> getCustomerNames() throws SQLException {
    List<String> result = new ArrayList<>();
    String query = "SELECT ContactName FROM Customers";
    try (Connection connection = getConnection();
         PreparedStatement pstmt = connection.prepareStatement(query);
         ResultSet rs = pstmt.executeQuery()) {
      while (rs.next()) {
        result.add(rs.getString("ContactName"));
      }
    }
    return result;
  }

  public long getEmployeeCount() throws SQLException {
    String query = "SELECT COUNT(*) FROM Employees";
    try (Connection connection = getConnection();
         PreparedStatement pstmt = connection.prepareStatement(query);
         ResultSet rs = pstmt.executeQuery()) {
      if (rs.next()) {
        return rs.getLong(1);
      }
    }
    return 0;
  }

  public List<String> getEmployeeNames() throws SQLException {
    List<String> result = new ArrayList<>();
    String query = "SELECT FirstName, LastName FROM Employees";
    try (Connection connection = getConnection();
         PreparedStatement pstmt = connection.prepareStatement(query);
         ResultSet rs = pstmt.executeQuery()) {
      while (rs.next()) {
        String fullName = rs.getString("FirstName") + " " + rs.getString("LastName");
        result.add(fullName);
      }
    }
    return result;
  }

  public long getOrderCount() throws SQLException {
    String query = "SELECT COUNT(*) FROM Orders";
    try (Connection connection = getConnection();
         PreparedStatement pstmt = connection.prepareStatement(query);
         ResultSet rs = pstmt.executeQuery()) {
      if (rs.next()) {
        return rs.getLong(1);
      }
    }
    return 0;
  }

  public List<String> getOrderDetails() throws SQLException {
    List<String> result = new ArrayList<>();
    String query = "SELECT OrderID, ShipName FROM Orders";
    try (Connection connection = getConnection();
         PreparedStatement pstmt = connection.prepareStatement(query);
         ResultSet rs = pstmt.executeQuery()) {
      while (rs.next()) {
        String orderDetail = "OrderID: " + rs.getInt("OrderID") + ", ShipName: " + rs.getString("ShipName");
        result.add(orderDetail);
      }
    }
    return result;
  }
}