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

import entities.Student;
import repository.StudentDao;

@WebServlet("/student")
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentDao studentDao;

	public void init() {
		studentDao = new StudentDao();
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
			case "studentnew":
				showNewForm(request, response);
				break;
			case "studentinsert":
				insertStudent(request, response);
				break;
			case "studentdelete":
				deleteStudent(request, response);
				break;
			case "studentedit":
				showEditForm(request, response);
				break;
			case "studentupdate":
				updateStudent(request, response);
				break;
			case "studentlist":
				listStudent(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	// Display Student Object on views
	private void listStudent(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		// get all student detail from database and store as a list of student
		List<Student> listStudent = studentDao.getAllStudent();
		// setting attribute to display all student details on student display page
		request.setAttribute("listStudent", listStudent);
		// forward to student display page
		RequestDispatcher dispatcher = request.getRequestDispatcher("studentDisplay.jsp");
		dispatcher.forward(request, response);
	}

	// show student registration page
	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// forward to student index page
		RequestDispatcher dispatcher = request.getRequestDispatcher("studentIndex.jsp");
		dispatcher.forward(request, response);
	}

//show  student edit form 
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		// get the student selected id from view to edit its detail
		int id = Integer.parseInt(request.getParameter("id"));
		Student existingStudent = studentDao.getStudent(id);
		// setting attribute to display specific student details to edit on student
		// index page
		RequestDispatcher dispatcher = request.getRequestDispatcher("studentIndex.jsp");
		request.setAttribute("student", existingStudent);
		dispatcher.forward(request, response);

	}

	// insert the student detail
	private void insertStudent(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		// getting student detail to insert in database
		String name = request.getParameter("name");
		String college = request.getParameter("college");
		String roll = request.getParameter("roll");
		String qualification = request.getParameter("qualification");
		String course = request.getParameter("course");
		String year = request.getParameter("year");
		String certificate = request.getParameter("certificate");
		String hallTicketNo = request.getParameter("hallTicketNo");
		Student newStudent = new Student(name, college, roll, qualification, course, year, certificate, hallTicketNo);
		// saving student detail
		studentDao.saveStudent(newStudent);
		// redirect to ulist
		response.sendRedirect("student?action=studentlist");
	}

	// update the student detail
	private void updateStudent(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		// getting student detail to update database
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String college = request.getParameter("college");
		String roll = request.getParameter("roll");
		String qualification = request.getParameter("qualification");
		String course = request.getParameter("course");
		String year = request.getParameter("year");
		String certificate = request.getParameter("certificate");
		String hallTicketNo = request.getParameter("hallTicketNo");

		Student student = new Student(id, name, college, roll, qualification, course, year, certificate, hallTicketNo);
		studentDao.updateStudent(student);
		// redirect to ulist
		response.sendRedirect("student?action=studentlist");
	}

	// delete the student detail
	private void deleteStudent(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		// getting student id to delete corresponding student detail database
		int id = Integer.parseInt(request.getParameter("id"));
		studentDao.deleteStudent(id);
		// redirect to ulist
		response.sendRedirect("student?action=studentlist");
	}
}
