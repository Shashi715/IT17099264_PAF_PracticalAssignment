package com;

import model.Payment;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;


@Path("/Payment")
public class PaymentService
{
Payment paymentObj = new Payment();
@GET
@Path("/")
@Produces(MediaType.TEXT_HTML)
public String readPayment() {
	return paymentObj.readPayment();
}

@POST
@Path("/")
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Produces(MediaType.TEXT_PLAIN)
public String insertPayment(@FormParam("AppoinmentCode") String AppoinmentCode,
 @FormParam("cardNo") String cardNo,
 @FormParam("Price") String Price,
 @FormParam("paymentDesc") String paymentDesc)
{
 String output = paymentObj.insertPayment(AppoinmentCode, cardNo, Price, paymentDesc);
return output;
}

@PUT
@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.TEXT_PLAIN)
public String updatePayment(String paymentData)
{
	//Convert the input string to a JSON object
	JsonObject hospitalObject = new JsonParser().parse(paymentData).getAsJsonObject();
	//Read the values from the JSON object
	String CustomerID = hospitalObject.get("CustomerID").getAsString();
	String AppoinmentCode = hospitalObject.get("AppoinmentCode").getAsString();
	String cardNo = hospitalObject.get("cardNo").getAsString();
	String Price = hospitalObject.get("Price").getAsString();
	String paymentDesc = hospitalObject.get("paymentDesc").getAsString();
	
	String output = paymentObj.updatePayment(CustomerID, AppoinmentCode, cardNo, Price, paymentDesc);

	return output;
}
@DELETE
@Path("/")
@Consumes(MediaType.APPLICATION_XML)
@Produces(MediaType.TEXT_PLAIN)
public String deletePayment(String paymentData)
{
//Convert the input string to an XML document
 Document doc = Jsoup.parse(paymentData, "", Parser.xmlParser());

//Read the value from the element <itemID>
 String CustomerID = doc.select("CustomerID").text();
 String output = paymentObj.deletePayment(CustomerID);
return output;
}



}



