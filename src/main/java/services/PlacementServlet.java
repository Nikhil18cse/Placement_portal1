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

import com.google.gson.Gson;
import entities.Placement;
import repository.PlacementDao;

@WebServlet("/placement")
public class PlacementServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private PlacementDao placementDao = new PlacementDao();

    public void init() {
        placementDao = new PlacementDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("placements".equals(action)) {
            try {
                listPlacementsAsHtml(response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else {
        try {
			switch (action) {
			case "placementnew":
				showNewForm(request, response);
				break;
			case "placementinsert":
				insertPlacement(request, response);
				break;
			case "placementdelete":
				deletePlacement(request, response);
				break;
			case "placementedit":
				showEditForm(request, response);
				break;
			case "placementupdate":
				updatePlacement(request, response);
				break;
			default:
				listPlacement(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
        }
	
    }
    
    private void listPlacement(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		// get all placement detail from database and store as a list of placement
		List<Placement> listPlacement = placementDao.getAllPlacement();
		// setting attribute to display all placement details on placement display page
		request.setAttribute("listPlacement", listPlacement);
		// forward to placement display page
		RequestDispatcher dispatcher = request.getRequestDispatcher("placementDisplay.jsp");
		dispatcher.forward(request, response);
	}

	// show placement registration page
	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("placementIndex.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		// get the placement selected id from view to edit its detail
		int id = Integer.parseInt(request.getParameter("id"));
		// get placement details using user id
		//Placement existingPlacement = placementDao.getPlacement(id);
		// forward to placement index page
		RequestDispatcher dispatcher = request.getRequestDispatcher("placementIndex.jsp");
		// setting attribute to display specific placement details to edit on placement
		// display
		//request.setAttribute("placement", existingPlacement);
		dispatcher.forward(request, response);

	}

	// insert the placement detail
	private void insertPlacement(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		// getting name and password placement detail to insert in database
		String name = request.getParameter("name");
		String college = request.getParameter("college");
		String date = request.getParameter("date");
		String qualification = request.getParameter("qualification");
		String year = request.getParameter("year");
		Placement newPlacement = new Placement(name, college, date, qualification, year);
		// saving placement detail
		placementDao.savePlacement(newPlacement);
		// redirect to placementlist
		response.sendRedirect("placement?action=placementlist");
	}

	// update the placement detail
	private void updatePlacement(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		// getting placement detail to update database
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String college = request.getParameter("college");
		String date = request.getParameter("date");
		String qualification = request.getParameter("qualification");
		String year = request.getParameter("year");
		Placement placement = new Placement(id, name, college, date, qualification, year);
		// update placement detail
		placementDao.updatePlacement(placement);
		// redirect to placementlist
		response.sendRedirect("placement?action=placementlist");
	}

	// delete the placement detail
	private void deletePlacement(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		// getting placement id to delete corresponding placement detail database
		int id = Integer.parseInt(request.getParameter("id"));
		// delete placement detail
		placementDao.deletePlacement(id);
		// redirect to placementlist
		response.sendRedirect("placement?action=placementlist");
	}

    private void listPlacementsAsHtml(HttpServletResponse response) throws IOException, SQLException {
        List<Placement> placements = placementDao.getAllPlacement();

        StringBuilder htmlResponse = new StringBuilder();
        for (Placement placement : placements) {
            htmlResponse.append("<tr>")
                        .append("<td>").append(placement.getName()).append("</td>")
                        .append("<td>").append(placement.getCollege()).append("</td>")
                        .append("<td>").append(placement.getDate()).append("</td>")
                        .append("<td>").append(placement.getQualification()).append("</td>")
                        .append("<td>").append(placement.getYear()).append("</td>")
                        .append("<td>")
                        .append("<button onclick='apply()'>Apply</button>")
                        .append("<button onclick='reject()'>Reject</button>")
                        .append("</td>")
                        .append("</tr>");
        }

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(htmlResponse.toString());
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int userId = Integer.parseInt(request.getParameter("userId"));
            int jobId = Integer.parseInt(request.getParameter("jobId"));

            boolean isApplied = placementDao.applyForJob(userId, jobId);
            if (isApplied) {
                response.getWriter().write("Application submitted successfully!");
            } else {
                response.getWriter().write("Failed to apply for the job.");
            }
        } catch (NumberFormatException e) {
            handleError(response, e);
        }
    }

    

    private void handleError(HttpServletResponse response, Exception e) throws IOException {
        e.printStackTrace();
        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while processing your request.");
    }
}
