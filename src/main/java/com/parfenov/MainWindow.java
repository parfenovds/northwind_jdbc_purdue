package com.parfenov;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class MainWindow extends JFrame {

  private JTextField serverField;
  private JTextField databaseField;
  private JTextField usernameField;
  private JPasswordField passwordField;
  private JTextArea outputArea;

  public MainWindow() {
    setTitle("Northwind Database Access");
    setSize(600, 400);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    initComponents();
  }

  private void initComponents() {
    setLayout(new BorderLayout());
    JPanel loginPanel = new JPanel();
    loginPanel.setLayout(new GridLayout(5, 2));

    serverField = new JTextField();
    databaseField = new JTextField();
    usernameField = new JTextField();
    passwordField = new JPasswordField();

    loginPanel.add(new JLabel("Server:"));
    loginPanel.add(serverField);
    loginPanel.add(new JLabel("Database:"));
    loginPanel.add(databaseField);
    loginPanel.add(new JLabel("Username:"));
    loginPanel.add(usernameField);
    loginPanel.add(new JLabel("Password:"));
    loginPanel.add(passwordField);

    JButton connectButton = new JButton("Connect");
    loginPanel.add(connectButton);

    add(loginPanel, BorderLayout.NORTH);

    outputArea = new JTextArea();
    outputArea.setEditable(false);

    add(new JScrollPane(outputArea), BorderLayout.CENTER);

    connectButton.addActionListener(e -> {
      String server = serverField.getText();
      String database = databaseField.getText();
      String username = usernameField.getText();
      String password = new String(passwordField.getPassword());

      outputArea.setText("");

      try {
        // Создаем бизнес-слой
        BusinessLayer businessLayer = new BusinessLayer(server, database, username, password);
        outputArea.append("Connected successfully!\n");
        displayData(businessLayer, username);
      } catch (Exception ex) {
        outputArea.append("An unexpected error occurred: " + ex.getMessage() + "\n");
        ex.printStackTrace();
      }
    });
  }

  private void displayData(BusinessLayer businessLayer, String username) throws Exception {
    StringBuilder output = new StringBuilder();

    if (username.equals("User_CEO")) {
      output.append("CEO Access:\n");
      output.append("Total Customers: ").append(businessLayer.getCustomerCount()).append("\n");
      output.append("Customer Names:\n").append(String.join("\n", businessLayer.getCustomerNames())).append("\n");
      output.append("Total Employees: ").append(businessLayer.getEmployeeCount()).append("\n");
      output.append("Order Details:\n").append(String.join("\n", businessLayer.getOrderDetails())).append("\n");
    } else if (username.equals("User_HR")) {
      output.append("HR Access:\n");
      output.append("Total Employees: ").append(businessLayer.getEmployeeCount()).append("\n");
      output.append("Employee Names:\n").append(String.join("\n", businessLayer.getEmployeeNames())).append("\n");
    } else if (username.equals("User_Sales")) {
      output.append("Sales Access:\n");
      output.append("Total Customers: ").append(businessLayer.getCustomerCount()).append("\n");
      output.append("Customer Names:\n").append(String.join("\n", businessLayer.getCustomerNames())).append("\n");
      output.append("Order Details:\n").append(String.join("\n", businessLayer.getOrderDetails())).append("\n");
    }

    outputArea.setText(output.toString());
  }


  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      MainWindow mainWindow = new MainWindow();
      mainWindow.setVisible(true);
    });
  }
}
