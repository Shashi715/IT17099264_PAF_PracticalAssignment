
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@page import="model.Payment"%>
 <%@ page import="java.util.*"%> 
 <%@ page import="java.sql.*"%> 
 <%@ page import="javax.servlet.*"%> 
 <%@ page import="javax.servlet.http.*"%>  
 
<%
//SAVE
if(request.getParameter("AppoinmentCode") != null)
{
	Payment paymentObj = new Payment();
	String stsMsg = "";

	
	//INSERT
	if(request.getParameter("hidCustomerIdSave") == "")
	{
		stsMsg = paymentObj.insertPayment(request.getParameter("AppoinmentCode"),
											request.getParameter("cardNo"),
											request.getParameter("Price"),
											request.getParameter("paymentDesc"));	
	}
	//update
	else
	{
		stsMsg = paymentObj.updatePayment(request.getParameter("hidCustomerIdSave"),
										request.getParameter("AppoinmentCode"),
										request.getParameter("cardNo"),
										request.getParameter("price"),
										request.getParameter("paymentDesc"));
	}
	session.setAttribute("ststusMsg", stsMsg);
	
}

//Delete
if(request.getParameter("hidCustomerIdDelete") != null)
{
	Payment paymentObj = new Payment();
	String stsMsg = paymentObj.deletePayment(request.getParameter("hidCustomerIdDelete"));
	session.setAttribute("statusMsg", stsMsg);
}
	
%>

    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Payment Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/payment.js"></script>
</head>
<body>
<div class="container">
<div class="row">
<div class="col-6">
	<h1>Payment Management</h1>
	<form id="formPayment" name="formPayment" method="post" action="index.jsp">
		Appointment Code:
		<input id="appoinmentCode" name="appoinmentCode" type="text" class="form-control form-control-sm">
		
		<br> Card Number:
		<input id="cardNumber" name="cardNumber" type="number" class="form-control form-control-sm">
		
		<br> Price(Rs):
		<input id="price" name="price" type="number" class="form-control form-control-sm">
		
		<br> Payment Description:
		<input id="paymentDesc" name="paymentDesc" type="text" class="form-control form-control-sm">
	
		
		<br>
		<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
		<input type="hidden" id="hidCustomerIDSave" name="hidCustomerIDSave" value="">
		
	</form>

	<div id="alertSuccess" class="alert alert-success"></div>
	<div id="alertError" class="alert alert-danger"></div>
	
	<br>
	<div id="divPaymenttGrid">
		<%
			Payment paymentObj = new Payment();
			out.print(paymentObj.readPayment());
		%>
	</div>
</div>
</div>
</div>

</body>
</html>