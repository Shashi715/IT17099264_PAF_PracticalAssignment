package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Payment {
	// private Payment paymentObj;

	//A common method to connect to the DB
	private Connection connect()
	 {
	 Connection con = null;
	 try
	 {
	 Class.forName("com.mysql.jdbc.Driver");

	 //Provide the correct details: DBServer/DBName, username, password
	 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/shashi", "root", "");
	 System.out.print("Successfully connected");
	 }
	 catch (Exception e)
	 {e.printStackTrace();}
	 return con;
	 }
	public String insertPayment(String AppoinmentCode, String cardNo, String Price, String paymentDesc)
	 {
	 String output = "";
	 try
		{
			Connection con = connect();
			
			if(con == null)
			{return "Error while connecting to the database for inserting";}
			
			String query = " insert into pay (`CustomerId`,`AppoinmentCode`,`cardNo`,`Price`,`paymentDesc`)" + " values (?, ?, ?, ?, ?)";

			
			PreparedStatement preparedstmt = con.prepareStatement(query);
			
			preparedstmt.setInt(1,0);
			preparedstmt.setString(2, AppoinmentCode);
			preparedstmt.setString(3, cardNo);
			preparedstmt.setString(4, Price);
			preparedstmt.setString(5, paymentDesc);
			
			preparedstmt.execute();
			con.close();
			
			output = "Insert successfully";
		}
	 catch (Exception e)
	 {
	 output = "Error while inserting the payment.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	public String readPayment()
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for reading."; }
	 // Prepare the html table to be displayed
	 output = "<table border=\"1\"><tr><th>AppoinmentCode</th><th>cardNo</th><th>Price</th><th>paymentDesc</th><th>Update</th><th>Remove</th></tr>";
	 String query = "select * from pay";
	 Statement stmt = con.createStatement();
	 ResultSet rs = stmt.executeQuery(query);
	 // iterate through the rows in the result set
	 while (rs.next())
	 {
	 String CustomerId = Integer.toString(rs.getInt("CustomerId"));
	 String AppoinmentCode = rs.getString("AppoinmentCode");
	 String cardNo = rs.getString("cardNo");
	 String Price = rs.getString("Price");
	 String paymentDesc = rs.getString("paymentDesc");
	 // Add into the html table
	 output += "<tr><td>><input id=\"hidCustomerIDUpdate\" name=\"hidCustomerIDUpdate\" type=\"hidden\" value=\"" + CustomerId + "\">" + AppoinmentCode + "</td>";
	 output += "<td>" + cardNo + "</td>";
	 output += "<td>" + Price + "</td>"; 
	 output += "<td>" + paymentDesc + "</td>";
	 // buttons
	 output += "<td><input name=\"btnUpdate\" type=\"button\"value=\"Update\" class=\"btn btn-secondary\"></td>"+ "<td><form method=\"post\" action=\"index.jsp\">"+"<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"class=\"btn btn-danger\">"+ "<input name=\"hidCustomerIdDelete\" type=\"hidden\" value=\"" + CustomerId+ "\">" + "</form></td></tr>";}
	 con.close();
	 // Complete the html table
	 output += "</table>";
	 }
	 catch (Exception e)
	 {
	 output = "Error while reading the payments.";
	 System.err.println(e.getMessage());
	 }
	 return output; 

	 }
	
	public String updatePayment(String ID, String code, String name, String price, String desc)
	{
	String output = "";
	
	try
	{
		Connection con = connect();
		
		if (con == null)
		{return "Error while connecting to the database for updating."; }
	
		// create a prepared statement
		String query = "UPDATE pay SET AppoinmentCode=?,cardNo=?,Price=?,paymentDesc=? WHERE CustomerID=?";
		PreparedStatement preparedStmt = con.prepareStatement(query);
	
		// binding values
		preparedStmt.setString(1, code);
		preparedStmt.setString(2, name);
		preparedStmt.setString(3, price);
		preparedStmt.setString(4, desc);
		preparedStmt.setInt(5, Integer.parseInt(ID));
	
		// execute the statement
		preparedStmt.execute();
		con.close();
	
		output = "Updated successfully";
	}
	catch (Exception e)
	{
		output = "Error while updating the hospital.";
		System.err.println(e.getMessage());
	}
		return output;
}
	public String deletePayment(String CustomerId)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for deleting."; }
	 // create a prepared statement
	 String query = "delete from pay where CustomerID=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(CustomerId));
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Deleted successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while deleting the payment.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	


} 