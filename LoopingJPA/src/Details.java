import javax.persistence.EntityManager;

import customTools.DBLoop;
import customTools.DBUtil;
import model.DemoCustomer;

import java.io.IOException;
import java.util.List;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/details")
public class Details extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Details() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String CustIDstr = request.getParameter("customerID");
		long CustID = Long.parseLong(CustIDstr);
		String message = "";
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		try{
			DemoCustomer cust = DBLoop.getCust(CustID);
			message += "<div class=\"container\">";
			message += "<table class=\"table table-bordered\"><thead><tr><th>Customer ID</th><th>First Name</th><th>Last Name</th><th>Street Address</th><th>City/th><th>State</th><th>ZipCode</th><th>Phone Number</th></tr></thead><tbody>";
			message += "<tr>";
			message += "<td>" + cust.getCustomerId() + "</a></td>";
			message += "<td>" + cust.getCustFirstName() + "</td>";
			message += "<td>" + cust.getCustLastName() + "</td>";
			message += "<td>" + cust.getCustStreetAddress1() + "</td>";
			message += "<td>" + cust.getCustCity() + "</td>";
			message += "<td>" + cust.getCustState() + "</td>";
			message += "<td>" + cust.getCustPostalCode() + "</td>";
			message += "<td>" + cust.getPhoneNumber1() + "</td>";
			message += "</tr>";
		message += "</tbody></table>";
		message += "</div>";
		
		request.setAttribute("message", message);
		getServletContext().getRequestDispatcher("/details.jsp").forward(request, response);
		}catch (Exception e){
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

}
