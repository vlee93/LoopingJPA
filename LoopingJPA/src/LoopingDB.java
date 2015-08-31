import javax.persistence.EntityManager;

import customTools.DBLoop;
import customTools.DBUtil;
import model.DemoCustomer;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/LoopingDB")
public class LoopingDB extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public LoopingDB() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String message = "";
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		try{
			List<DemoCustomer> customers = DBLoop.getCustomers();
			message += "<div class=\"container\">";
			message += "<table class=\"table table-bordered\"><thead><tr><th>Customer ID</th><th>First Name</th><th>Last Name</th></tr></thead><tbody>";
		for (DemoCustomer cust : customers)
		{
			message += "<tr>";
			message += "<td><a href=\"details?customerID=" + cust.getCustomerId() + "\">" +
			cust.getCustomerId() + "</a></td>";
			message += "<td>" + cust.getCustFirstName() + "</td>";
			message += "<td>" + cust.getCustLastName() + "</td>";
			message += "</tr>";
		}
		message += "</tbody></table>";
		message += "</div>";
		
		request.setAttribute("message", message);
		getServletContext().getRequestDispatcher("/Home.jsp").forward(request, response);
		}catch (Exception e){
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

}
