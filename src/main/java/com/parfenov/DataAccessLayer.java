package com.parfenov;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DataAccessLayer {

  private String connectionString = "jdbc:sqlserver://localhost:1433;databaseName=Northwind;encrypt=true;trustServerCertificate=true;";
  private String username = "sa";
  private String password = "NotPostgres123!";

  public DataAccessLayer() {
  }

  private Connection getConnection() throws Exception {
    return DriverManager.getConnection(connectionString, username, password);
  }

  public long getCustomerCount() throws Exception {
    String query = "SELECT COUNT(*) FROM Customers";
    try (Connection connection = getConnection();
         Statement stmt = connection.createStatement();
         ResultSet rs = stmt.executeQuery(query)) {
      if (rs.next()) {
        return rs.getLong(1);
      }
    }
    return 0;
  }

  public List<String> getCustomerNames() throws Exception {
    List<String> result = new ArrayList<>();
    String query = "SELECT ContactName FROM Customers";
    try (Connection connection = getConnection();
         Statement stmt = connection.createStatement();
         ResultSet rs = stmt.executeQuery(query)) {
      while (rs.next()) {
        result.add(rs.getString("ContactName"));
      }
    }
    return result;
  }

  public List<String> getCustomerLastNames() throws Exception {
    List<String> result = new ArrayList<>();
    String query = "SELECT ContactName FROM Customers";
    try (Connection connection = getConnection();
         Statement stmt = connection.createStatement();
         ResultSet rs = stmt.executeQuery(query)) {
      while (rs.next()) {
        String fullName = rs.getString("ContactName");
        String lastName = fullName.split(" ")[1]; // Предполагаем, что фамилия идет вторым словом
        result.add(lastName);
      }
    }
    return result;
  }
}

