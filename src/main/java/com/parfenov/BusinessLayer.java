package com.parfenov;

import java.util.List;

public class BusinessLayer {

  private DataAccessLayer dataAccessLayer;

  public BusinessLayer() throws Exception {
    this.dataAccessLayer = new DataAccessLayer();
  }

  public long getCustomerCount() throws Exception {
    return dataAccessLayer.getCustomerCount();
  }

  public List<String> getCustomerNames() throws Exception {
    return dataAccessLayer.getCustomerNames();
  }

  public List<String> getCustomerLastNames() throws Exception {
    return dataAccessLayer.getCustomerLastNames();
  }
}