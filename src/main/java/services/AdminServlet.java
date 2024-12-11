package services;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Admin;
import repository.AdminDao;

@WebServlet("/Admin")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminDao adminDao;

	public void init() {
		adminDao = new AdminDao();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		try {
			switch (action) {
			case "Adminnew":
				showNewForm(request, response);
				break;
			case "Admininsert":
				insertAdmin(request, response);
				break;
			case "Admindelete":
				deleteAdmin(request, response);
				break;
			case "Adminedit":
				showEditForm(request, response);
				break;
			case "Adminupdate":
				updateAdmin(request, response);
				break;
			case "Adminlist":
				listAdmin(request, response);
				break;
			case "AdminAuthenticate":
				try {
					authenticate(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

//  Display Admin Object on views
	private void listAdmin(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		// get all admin detail from database and store as a list of admin
		List<Admin> listAdmin = adminDao.getAllAdmin();
		// setting attribute to display all admin details on admin display page
		request.setAttribute("listAdmin", listAdmin);
		// forward to admin display page
		RequestDispatcher dispatcher = request.getRequestDispatcher("adminDisplay.jsp");
		dispatcher.forward(request, response);
	}

	// show admin registration page
	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// forward to admin index page
		RequestDispatcher dispatcher = request.getRequestDispatcher("adminIndex.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		// get the admin selected id from view to edit its detail
		int id = Integer.parseInt(request.getParameter("id"));
		// get user details using user id
		Admin existingAdmin = adminDao.getAdmin(id);
		// forward to user admin index page
		RequestDispatcher dispatcher = request.getRequestDispatcher("adminIndex.jsp");
		// setting attribute to display specific admin details to edit on admin display

		request.setAttribute("admin", existingAdmin);
		dispatcher.forward(request, response);

	}

	// insert the admin detail
	private void insertAdmin(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		// getting name and password admin detail to insert in database
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		Admin newAdmin = new Admin(name, password);
		// saving user detail
		adminDao.saveAdmin(newAdmin);
		// redirect to ulist
		response.sendRedirect("Admin?action=Adminlist");
	}

	// update the admin detail
	private void updateAdmin(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		// getting admin detail to update database
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String password = request.getParameter("password");

		Admin admin = new Admin(id, name, password);
		// update admin detail
		adminDao.updateAdmin(admin);
		// redirect to ulist
		response.sendRedirect("Admin?action=Adminlist");
	}

	// delete the admin detail
	private void deleteAdmin(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		// getting admin id to delete corresponding admin detail database
		int id = Integer.parseInt(request.getParameter("id"));
		// delete admin detail
		adminDao.deleteAdmin(id);
		// redirect to adminlist
		response.sendRedirect("Admin?action=Adminlist");
	}

	// authenticate the admin detail
	private void authenticate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// getting name and password to authenticate
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		// validating name and password
		if (adminDao.validate(name, password)) {
			// forward admin dashboard
			RequestDispatcher dispatcher = request.getRequestDispatcher("AdminDashboard.html");
			dispatcher.forward(request, response);
		} else {
			System.out.println("Login not successful..");
		}
	}

}
