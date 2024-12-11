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

import entities.User;
import repository.UserDao;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao;

	public void init() {
		userDao = new UserDao();
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
			case "unew":
				showNewForm(request, response);
				break;
			case "uinsert":
				insertUser(request, response);
				break;
			case "udelete":
				deleteUser(request, response);
				break;
			case "uedit":
				showEditForm(request, response);
				break;
			case "uupdate":
				updateUser(request, response);
				break;
			case "ulist":
				listUser(request, response);
				break;
			case "uAuthenticate":
				try {
					userAuthenticate(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}

				break;

			default:
				listUser(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}
   //  Display User Object on views
	private void listUser(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		//get all user detail from database and store as a list of user
		List<User> listUser = userDao.getAllUser();
		//setting attribute to display all user details on user display page
		request.setAttribute("listUser", listUser);
		//forward to user display page 
		RequestDispatcher dispatcher = request.getRequestDispatcher("userDisplay.jsp");
		dispatcher.forward(request, response);
	}
        // show user registration page
	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//forward to user index page 
		RequestDispatcher dispatcher = request.getRequestDispatcher("userIndex.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		//get the user selected id from view to edit its detail
		int id = Integer.parseInt(request.getParameter("id"));
		//get user details using user id
		User existingUser = userDao.getUser(id);
		//forward to user index page
		RequestDispatcher dispatcher = request.getRequestDispatcher("userIndex.jsp");
		//setting attribute to display specific user details to edit on user display page
		request.setAttribute("user", existingUser);
		dispatcher.forward(request, response);

	}
//insert the user  detail
	private void insertUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		//getting user detail to insert in database
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String type = request.getParameter("type");
		User newUser = new User(name, password, type);
		//saving user detail
		userDao.saveUser(newUser);
		//redirect to ulist
		response.sendRedirect("user?action=ulist");
	}
  //update the user  detail
	private void updateUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		//getting user detail to update database
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String type = request.getParameter("type");
        User user = new User(id, name, password, type);
        //save update user detail
		userDao.updateUser(user);
		//redirect to ulist
		response.sendRedirect("user?action=ulist");
	}
      //delete the user  detail
	private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		//getting user id to delete corresponding user detail database
		int id = Integer.parseInt(request.getParameter("id"));
		//save delete user detail
		userDao.deleteUser(id);
		// redirect to ulist
		response.sendRedirect("user?action=ulist");
	}
	 //authenticate the user  detail
	private void userAuthenticate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//getting name and password to authenticate
		String name = request.getParameter("name");
		String password = request.getParameter("password");
   //validating name and password
		if (userDao.userValidate(name, password)) {
			//forward user dashboard
			RequestDispatcher dispatcher = request.getRequestDispatcher("UserDashboard.html");
			dispatcher.forward(request, response);
		} else {
			System.out.println("Login not successful..");
		}
	}



}
