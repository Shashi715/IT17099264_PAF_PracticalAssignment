$(document).ready(function(){
	if($("#alertSuccess").text().trim() == "")
		{
			$("#alertSuccess").hide();
		}
	$("#alertError").hide();
});

//SAVE 
$(document).on("click", "#btnSave", function(event) 
{
			$("#alertSuccess").text("");
			$("#alertSuccess").hide("");
			$("#alertError").text("");
			$("#alertError").hide("");
		
			//form validation
			var status = validatePaymentForm();
			if (status != true)
				{
					$("#alertError").text(status);
					$("#alertError").show();
					return;
				}

			$("#formPayment").submit();
			
			var type = ($("#hidCustomerIdSave").val() == "") ? "POST" : "PUT"; 
			$.ajax
				({  
					url : "PaymentAPI",  
					type : type,  
					data : $("#formPayment").serialize(),  
					dataType : "text",  
					complete : function(response, status)  
					{   
						onPaymentSaveComplete(response.responseText, status);
					} 
				});
			
});

function onPaymentSaveComplete(response, status) 
{  
	if (status == "success")  
	{   
		var resultSet = JSON.parse(response); 

		if (resultSet.status.trim() == "success")   
		{    
			$("#alertSuccess").text("Successfully saved.");    
			$("#alertSuccess").show(); 

			$("#divPaymenttGrid").html(resultSet.data);   
			} 
		else if (resultSet.status.trim() == "error")   
		{    
			$("#alertError").text(resultSet.data);    
			$("#alertError").show();   
		} 

	} 
	else if (status == "error")  
	{   
		$("#alertError").text("Error while saving.");   
		$("#alertError").show();  
		} else  
		{   
			$("#alertError").text("Unknown error while saving..");   
			$("#alertError").show();  
		} 
	
	$("#hidCustomerIdSave").val(""); 
	$("#formPayment")[0].reset();

 
}

//Update
$(document).on("click", ".btnUpdate", function(event) {
	 $("#hidCustomerIDSave").val($(this).closest("tr").find('#hidCustomerIdUpdate').val());
	 $("#AppoinmentCode").val($(this).closest("tr").find('td:eq(0)').text()); 
	 $("#cardNo").val($(this).closest("tr").find('td:eq(1)').text()); 
	 $("#Price").val($(this).closest("tr").find('td:eq(2)').text()); 
	 $("#paymentDesc").val($(this).closest("tr").find('td:eq(3)').text()); 
	
	 
})

//DELETE
$(document).on("click", ".btnRemove", function(event) 
{  
	$.ajax(  
	{   
		url : "PaymentAPI",   
		type : "DELETE",   
		data : "CustomerId=" + $(this).data("CustomerID"),   
		dataType : "text",   
		complete : function(response, status)   
		{    
			onPaymentDeleteComplete(response.responseText, status);   
		}  
	}); 
});
 

function onPaymentntDeleteComplete(response, status) 
{  

	
	if (status == "success")  
	{   
		var resultSet = JSON.parse(response); 
		

		if (resultSet.status.trim() == "success")   
		{    
			$("#alertSuccess").text("Successfully deleted.");    
			$("#alertSuccess").show(); 

			$("#divPaymentGrid").html(resultSet.data);   
		} else if (resultSet.status.trim() == "error")   
		{    
			$("#alertError").text(resultSet.data);    
			$("#alertError").show();   
		} 

	} else if (status == "error")  
	{   
		$("#alertError").text("Error while deleting.");   
		$("#alertError").show();  
	} else  
	{   
		$("#alertError").text("Unknown error while deleting..");   
		$("#alertError").show();  
	} 
	$("#hidCustomerIdSave").val("");  
	$("#formPayment")[0].reset();
	
	
}



//FORM VALIDATE
function validatePaymentForm()
{
	//AppoinmentCode
	if($("#AppoinmentCode").val().trim() == ""){
		return "insert Appointment Code.";
	}
	
	//cardNo
	var tmpcardNo = $("#cardNo").val().trim();
	if (!$.isNumeric(tmpcardNo)) {
		return "Insert a numerical value for Card Number.";
	}
	
	//Price
	if($("#Price").val().trim() == ""){
		return "Insert Price.";
	}
		  
	
	
	//paymentDesc
	if($("#paymentDesc").val().trim() == ""){
		return "Insert Payment Description.";
	}
	
	
	return true;

	
}



  









