package com.parfenov;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MainWindow extends JFrame {

  private JEditorPane outputArea;
  private BusinessLayer businessLayer;

  public MainWindow() {
    setTitle("Northwind Customer Data");
    setSize(600, 400);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    initComponents();
  }

  private void initComponents() {
    setLayout(new BorderLayout());

    JButton loadDataButton = new JButton("Load Customer Data");
    outputArea = new JEditorPane();
    outputArea.setEditable(false);
    outputArea.setContentType("text/html");
    add(loadDataButton, BorderLayout.NORTH);
    add(new JScrollPane(outputArea), BorderLayout.CENTER);
    loadDataButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          businessLayer = new BusinessLayer();
          displayData();
        } catch (Exception ex) {
          outputArea.setText("<html><body>Error: " + ex.getMessage() + "</body></html>");
        }
      }
    });
  }

  private void displayData() throws Exception {
    long customerCount = businessLayer.getCustomerCount();
    List<String> customerNames = businessLayer.getCustomerNames();
    List<String> customerLastNames = businessLayer.getCustomerLastNames();
    StringBuilder output = new StringBuilder();
    output.append("<html><body>");
    output.append("<b>Customer count: ").append(customerCount).append("</b><br><br>");
    output.append("<b>Customer Names:</b><br>");
    for (String name : customerNames) {
      output.append(name).append("<br>");
    }
    output.append("<br>");
    output.append("<b>Customer Last Names:</b><br>");
    for (String lastName : customerLastNames) {
      output.append(lastName).append("<br>");
    }

    output.append("</body></html>");
    outputArea.setText(output.toString());
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      MainWindow mainWindow = new MainWindow();
      mainWindow.setVisible(true);
    });
  }
}