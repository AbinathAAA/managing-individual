package model;

import java.sql.*;
public class Project
{ //A common method to connect to the DB
	private Connection connect()
	 {
	 
		Connection con = null;
	
	try{
	    Class.forName("com.mysql.jdbc.Driver");

	    //Provide the correct details: DBServer/DBName, username, password
	    con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/projectpaf", "root", "");
	    
	 }catch (Exception e){
		 e.printStackTrace();}
	     return con;
	 } 
	
	public String insertProject(String name, String description, String language, String Devname,String Price)
	 {
	 
		String output = "";
	 
		try{
	 
			Connection con = connect();
	
			if (con == null){
				return "Error while connecting to the database for inserting.";
				}
	 
			// create a prepared statement
	
			String query = " INSERT INTO `project management`(`Id`, `Name`, `Description`, `Language`, `Developername`, `Price`) VALUES  (?, ?, ?,?, ?, ?)";
	 
			PreparedStatement preparedStmt = con.prepareStatement(query);
	        // binding values
	        preparedStmt.setInt(1, 0);
	        preparedStmt.setString(2, name);
	        preparedStmt.setString(3, description);
	        preparedStmt.setString(4, language);
	        preparedStmt.setString(5, Devname);
	        preparedStmt.setString(6, Price);
	        // execute the statement
	
	        preparedStmt.execute();
	        con.close();
	        String newItems = readProject();
	        output = "{\"status\":\"success\", \"data\": \"" +newItems + "\"}";
	        }
	        catch (Exception e)
	        {
	        output = "{\"status\":\"error\", \"data\"\"Error while inserting the item.\"}";
	        System.err.println(e.getMessage());
	        }
	        return output;
	        } 
	
	public String readProject(){
	 
		String output = "";
	 
		try{
	
			Connection con = connect();
	 
			if (con == null){
				return "Error while connecting to the database for reading."; }
	 

			 
			// Prepare the html table to be displayed
	
			output = "<table border='1'><tr><th>Project Name</th><th>Project Descrption</th>" +
	                "<th>Language</th>" +
	                "<th>Developer Name</th>" +
	                "<th>Price</th><th>Update</th><th>Remove</th></tr>";

	 
			String query = "SELECT `Id`,`Name`, `Description`, `Language`, `Developername`, `Price` FROM `project management` ";
	        Statement stmt = con.createStatement();
	        ResultSet rs = stmt.executeQuery(query);
	        // iterate through the rows in the result set
	     
	        while (rs.next()){
	         
	            String ID = Integer.toString(rs.getInt("Id"));
	        	String Name = rs.getString("Name");
	            String Description = rs.getString("Description");
	            String Language = rs.getString("Language");
	            String Developername = rs.getString("Developername");
	            String Price = rs.getString("Price");
	            
	            // Add into the html table
	            output += "<tr><td>"+ Name + "</td>";
	            output += "<td>" + Description + "</td>";
	            output += "<td>" + Language + "</td>";
	            output += "<td>" + Developername + "</td>";
	            output += "<td>" + Price + "</td>";
	
	          
	         // buttons
	            output += "<td><input name='btnUpdate' type='button' value='Update' "
	            + "class='btnUpdate btn btn-secondary' data-itemid='" + ID + "'></td>"
	            + "<td><input name='btnRemove' type='button' value='Remove' "
	            + "class='btnRemove btn btn-danger' data-itemid='" + ID + "'></td></tr>";
	 
	        }
	 
	        con.close();
	        // Complete the html table
	        output += "</table>";
	 
		}catch (Exception e){
	 
			output = "Error while reading the items.";
	        System.err.println(e.getMessage());
	 
		}
	
		return output;
	 } 
	
	
	
	public String buyProject(){
		 
		String output = "";
	 
		try{
	//read
			Connection con = connect();
	 
			if (con == null){
				return "Error while connecting to the database for reading."; }
	 
			// Prepare the html table to be displayed
	
			output = "<table border='1'><tr><th>Project Name</th><th>Project Descrption</th>" +
	                "<th>Language</th>" +
	                "<th>Developer Name</th>" +
	                "<th>Price</th><th>BUY</th></tr>";

	 
			String query = "SELECT `Id`,`Name`, `Description`, `Language`, `Developername`, `Price` FROM `project management` ";
	        Statement stmt = con.createStatement();
	        ResultSet rs = stmt.executeQuery(query);
	        // iterate through the rows in the result set
	     
	        while (rs.next()){
	            String ID=rs.getString("Id");
	        	String Name = rs.getString("Name");
	            String Description = rs.getString("Description");
	            String Language = rs.getString("Language");
	            String Developername = rs.getString("Developername");
	            String Price = rs.getString("Price");
	            
	            // Add into the html table
	
	            output += "<tr><td>" + Name + "</td>";
	            output += "<td>" + Description + "</td>";
	            output += "<td>" + Language + "</td>";
	            output += "<td>" + Developername + "</td>";
	            output += "<td>" + Price + "</td>";
	
	            // buttons
	           output +="<td><input name='btnUpdate' type='button' value='BUY' class='btn btn-success'></td>"
	        			 + "<td><form method='post' action='items.jsp'>"
	        			 +"</tr>";
	 
	        }
	 
	        con.close();
	        // Complete the html table
	        output += "</table>";
	 
		}catch (Exception e){
	 
			output = "Error while reading the items.";
	        System.err.println(e.getMessage());
	 
		}
	
		return output;
	 } 	
	
	
	public String updateProject(String ID,String name, String description, String language, String Devname,String Price){
	 
		String output = "";
	 
		try{
	 
			Connection con = connect();
	 
			if (con == null){
				return "Error while connecting to the database for updating.";
				}
	 
			// create a prepared statement
	        String query = "UPDATE `project management` SET `Name`=?,`Description`=?,`Language`=?,`Developername`=?,`Price`=? WHERE `Id`=?";
	        PreparedStatement preparedStmt = con.prepareStatement(query);
	 
	        // binding values
	        preparedStmt.setString(1, name);
	        preparedStmt.setString(2, description);
	        preparedStmt.setString(3, language);
	        preparedStmt.setString(4, Devname);
	        preparedStmt.setString(5, Price);
	        preparedStmt.setInt(6, Integer.parseInt(ID));
	
	        // execute the statement
	        preparedStmt.execute();
	        con.close();
	        String newItems = readProject();
	        output = "{\"status\":\"success\", \"data\": \"" + newItems + "\"}";
	        }
	        catch (Exception e)
	        {
	        output = "{\"status\":\"error\", \"data\":\"Error while updating the item.\"}";
	        System.err.println(e.getMessage());
	        }
	        return output;
	        }
	
	public String deleteProject(String ID){
	 
		String output = "";
	
		try {
	 
			Connection con = connect();
	
			if (con == null){
				return "Error while connecting to the database for deleting.";
				}
	
			 // create a prepared statement
	         String query = "DELETE FROM `project management` WHERE `Id`=?";
	         PreparedStatement preparedStmt = con.prepareStatement(query);
	 
	         // binding values
	         preparedStmt.setInt(1, Integer.parseInt(ID));
	 
	         // execute the statement
	         preparedStmt.execute();
	         System.out.println("working");
	         con.close();
	         String newItems = readProject();
	         output = "{\"status\":\"success\", \"data\": \"" +newItems + "\"}";
	         }
	         catch (Exception e)
	         {
	         output = "{\"status\":\"error\", \"data\":\"Error while deleting the item.\"}";
	         System.err.println(e.getMessage());
	         }
	         return output;
	         }
	 } 

