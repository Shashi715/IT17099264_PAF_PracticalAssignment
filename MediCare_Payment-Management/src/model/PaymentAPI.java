package model;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet
public class PaymentAPI extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	Payment paymentObj = new Payment();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
	{
			
		String output = paymentObj.insertPayment(request.getParameter("AppoinmentCode"),
				request.getParameter("cardNo"),
				request.getParameter("Price"),
				request.getParameter("paymentDesc"));
		
		response.getWriter().write(output);
	}
	
	
	private static Map<String, String> getParasMap(HttpServletRequest request)
	{
		Map<String, String> map = new HashMap<String, String>();
		try 
		{
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			String queryString = scanner.hasNext() ? scanner.useDelimiter("\\A").next() : "";
			scanner.close();
			
			String[] params = queryString.split("&");
			for(String param : params)
			{
				String[] p = param.split("=");
				map.put(p[0], p[1]);
			}
			
		} catch (Exception e) {
			
		}
		
		return map;
		
	}
	
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		Map<String, String> paras = getParasMap(request);
		
		String output = paymentObj.updatePayment(paras.get("hidCustomerIdSave").toString(), 
			paras.get("AppoinmentCode").toString(),
			paras.get("cardNo").toString(),
			paras.get("Price").toString(),
			paras.get("paymentDesc").toString());
		response.getWriter().write(output);
	}
	

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		Map<String, String> paras = getParasMap(request);
		
		String output = paymentObj.deletePayment(paras.get("CustomerId").toString());
		
		response.getWriter().write(output);
	}
}
