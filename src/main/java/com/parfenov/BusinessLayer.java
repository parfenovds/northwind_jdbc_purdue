package com.parfenov;

import java.util.List;

public class BusinessLayer {

  private DataAccessLayer dataAccessLayer;

  public BusinessLayer(String server, String database, String username, String password) throws Exception {
    this.dataAccessLayer = new DataAccessLayer(server, database, username, password);
  }

  public long getCustomerCount() throws Exception {
    return dataAccessLayer.getCustomerCount();
  }

  public List<String> getCustomerNames() throws Exception {
    return dataAccessLayer.getCustomerNames();
  }

  public long getEmployeeCount() throws Exception {
    return dataAccessLayer.getEmployeeCount();
  }

  public List<String> getEmployeeNames() throws Exception {
    return dataAccessLayer.getEmployeeNames();
  }

  public long getOrderCount() throws Exception {
    return dataAccessLayer.getOrderCount();
  }

  public List<String> getOrderDetails() throws Exception {
    return dataAccessLayer.getOrderDetails();
  }
}