package hackbee.controllers;

import hackbee.dao.HackBeeDAOImpl;
import hackbee.dao.HackbeeDAO;
import hackbee.exceptions.DaoException;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletController
 */

public class ServletController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletController() {

	}

	public void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, DaoException {

		String uri = request.getRequestURI();
		String target = "WEB-INF/jsp/home.jsp";
		HackbeeDAO dao = new HackBeeDAOImpl();
		if (uri.endsWith("addInterests.action")) {
			String userid = request.getParameter("user");
			String[] interests = request.getParameterValues("interestCheckbox");
			//TODO Add Interests
			dao.addInterests(userid, Arrays.asList(interests));
			
			target = "WEB-INF/jsp/addAssessment.jsp";

		} else if (uri.endsWith("getEvents.action")) {

			String userid = request.getParameter("user");
			
			target = "WEB-INF/jsp/addScores.jsp";

		}
		RequestDispatcher rd = request.getRequestDispatcher(target);
		rd.forward(request, response);
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (DaoException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (DaoException e) {
		}
	}

}
