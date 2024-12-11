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

import entities.College;
import repository.CollegeDao;

@WebServlet("/college")
public class CollegeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CollegeDao collegeDao;

	public void init() {
		collegeDao = new CollegeDao();
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
			case "new":
				showNewForm(request, response);
				break;
			case "insert":
				insertCollege(request, response);
				break;
			case "delete":
				deleteCollege(request, response);
				break;
			case "edit":
				showEditForm(request, response);
				break;
			case "update":
				updateCollege(request, response);
				break;
			case "list":
				listCollege(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	// Display College Object on views
	private void listCollege(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		// get all college detail from database and store as a list of college
		List<College> listCollege = collegeDao.getAllCollege();
		// setting attribute to display all college details on college display page
		request.setAttribute("listCollege", listCollege);
		// forward to college display page
		RequestDispatcher dispatcher = request.getRequestDispatcher("collegeDisplay.jsp");
		dispatcher.forward(request, response);
	}

	// show college registration page
	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// forward to collge index page
		RequestDispatcher dispatcher = request.getRequestDispatcher("collegeIndex.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		// get the college selected id from view to edit its detail
		int id = Integer.parseInt(request.getParameter("id"));
		// get college details using college id
		College existingCollege = collegeDao.getCollege(id);
		// forward to college index page
		RequestDispatcher dispatcher = request.getRequestDispatcher("collegeIndex.jsp");
		// setting attribute to display specific college details to edit on
		request.setAttribute("college", existingCollege);
		dispatcher.forward(request, response);

	}

	// insert the college detail
	private void insertCollege(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		// getting collegeadmin,collegename and location college detail to insert in
		// database
		String collegedmin = request.getParameter("collegedmin");
		String collegename = request.getParameter("collegename");
		String location = request.getParameter("location");
		College newCollege = new College(collegedmin, collegename, location);
		// saving college detail
		collegeDao.saveCollege(newCollege);
		// redirect to certificatelist
		response.sendRedirect("college?action=list");
	}

	// update the college detail
	private void updateCollege(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		// getting collegeadmin,collegename and location college detail to insert in
		// database
		int id = Integer.parseInt(request.getParameter("id"));
		String collegedmin = request.getParameter("collegedmin");
		String collegename = request.getParameter("collegename");
		String location = request.getParameter("location");
		College college = new College(id, collegedmin, collegename, location);
		// saving college update detail
		collegeDao.updateCollege(college);
		// redirect to certificatelist
		response.sendRedirect("college?action=list");
	}

	// delete the college detail
	private void deleteCollege(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		// getting college id to delete corresponding college detail database
		int id = Integer.parseInt(request.getParameter("id"));
		// save delete college detail
		collegeDao.deleteCollege(id);
		response.sendRedirect("college?action=list");
	}
}
