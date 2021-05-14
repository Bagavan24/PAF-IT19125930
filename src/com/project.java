package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Project {
	// DB Connection
				public Connection connect() {
					Connection con = null;

					try {
						Class.forName("com.mysql.jdbc.Driver");
						con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/invest", "root", "");

						// For testing
						System.out.print("DB Successfully connected");
					}

					catch (Exception e) {
						e.printStackTrace();
						System.out.print("DB not connected");
					}

					return con;
					
				}
				
				//insert
				public String insertProject(String name, String cost,String duration,String author) {

					String output = "";

					try {
						
						Connection con = connect();
							if (con == null) {
							return "Error while connecting to the database for inserting";
						}

						// create a prepared statement
						String query = " insert into  values (?, ?, ?, ?, ?)";
						PreparedStatement preparedStmt = con.prepareStatement(query);

						// binding values
						preparedStmt.setInt(1, 0);
						preparedStmt.setString(2, name);
						preparedStmt.setString(3, cost);
						preparedStmt.setString(4, duration);
						preparedStmt.setString(5, author);
						
					

						// execute the statement
						preparedStmt.execute();
						con.close();
						
						//output = "Insertion successful";
						String newItems = readProject(); 
						 output = "{\"status\":\"success\", \"data\": \"" + 
						 newItems + "\"}";
						

					} catch (Exception e) {
						//output = "Insertion failure";
						output = "{\"status\":\"error\", \"data\": \"Error while inserting the item.\"}"; 
						System.err.println(e.getMessage());
					}
					return output;
				}
				
				//read
				public String readProject() {

					String output = "";

					try {
						Connection con = connect();
						if (con == null) {
							return "Error while connecting to the database for reading.";
						}

						// Prepare the html table to be displayed
						output = "<table border='1'><tr><th> name </th>"
								+" <th> cost </th> "
								+" <th> duration</th> " 
								+" <th> author </th></tr> ";

						String query = "select * from project";
						Statement stmt = con.createStatement();
						ResultSet rs = stmt.executeQuery(query);

						// iterate through the rows in the result set
						while (rs.next()) {

							String id  = Integer.toString(rs.getInt(" id"));
							String name = rs.getString("name");
							String cost =Integer.toString(rs.getInt(" cost "));
							String duration = Integer.toString(rs.getInt(" duration "));
							String author = rs.getString("author");
							
						
							

							// Add into the html table
							output += "<tr><td>"+ name + "</td>";
							output += "<td>" + cost + "</td>";
							output += "<td>" + duration + "</td>";
							output += "<td>" + author  + "</td>";
							
							
							//buttons
							output += "<td><input name='btnUpdate' type='button' value='Update' "
									+ "class='btnUpdate btn btn-secondary' data-itemid='" + id + "'></td>"
									+ "<td><input name='btnRemove' type='button' value='Remove' "
									+ "class='btnRemove btn btn-danger' data-itemid='" + id + "'></td></tr>";
						}

						con.close();

						// Complete the html table
						output += "</table>";

					} 
					
					catch (Exception e) {
						output = "Error while reading the investors.";
						System.err.println(e.getMessage());
					}
					return output;
				}
				
				// Update
				public String updateProject(String id, String name, String cost, String duration,String author) {

					String output = "";

					try {
						Connection con = connect();
						if (con == null) {
							return "Error while connecting to the database for updating.";
						}

						// create a prepared statement
						String query = "UPDATE project SET name=?,cost=?,duration=?,author=? WHERE investorID=?";
						PreparedStatement preparedStmt = con.prepareStatement(query);

						// binding values
						
						preparedStmt.setString(1, name);
						preparedStmt.setString(2,Integer.parseInt(cost));
						preparedStmt.setString(3,Integer.parseInt(duration));
						preparedStmt.setString(4, author);
						preparedStmt.setInt(6, Integer.parseInt(id)); 
						
						// execute the statement
						preparedStmt.execute();
						con.close();
						
						//output = " Updated Successfully";
						String newItems = readProject(); 
						 output = "{\"status\":\"success\", \"data\": \"" + 
						 newItems + "\"}";

					} catch (Exception e) {
						//output = "Error while updating the project .";
						output = "{\"status\":\"error\", \"data\":  \"Error while updating the item.\"}"; 
						System.err.println(e.getMessage());
						
					}
					
					return output;
				}
				
				
				// Delete
				public String deleteProject(String id) {
					String output = "";

					try {
						Connection con = connect();
						if (con == null) {
							return "Error while connecting to the database for deleting.";
						}

						// create a prepared statement
						String query = "delete from project where id=?";
						PreparedStatement preparedStmt = con.prepareStatement(query);

						// binding values
						preparedStmt.setInt(1, Integer.parseInt(id));

						// execute the statement
						preparedStmt.execute();
						con.close();
						//output = "Deleted successfully";
						
						String newItems = readProject(); 
						 output = "{\"status\":\"success\", \"data\": \"" + 
						 newItems + "\"}";

					} 
						catch (Exception e) {
							//output = "Error while deleting the investor Details.";
							output = "{\"status\":\"error\", \"data\": \"Error while deleting the item.\"}";
							System.err.println(e.getMessage());
					}
					return output;
				}
}
