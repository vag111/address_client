package main.java.controller.country;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.java.dao.ConnectionProperty;
import main.java.domain.Country;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

@WebServlet("/country")
public class CountryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ConnectionProperty prop;
	String userPath;

	String SELECT_ALL_COUNTRIES = "SELECT id, countryFull, countryShort FROM country";
	String INSERT_COUNTRY = "INSERT INTO country (countryFull, countryShort) VALUES (?, ?)";

	ArrayList<Country> countries = new ArrayList<>();

	public CountryServlet() {
		prop = new ConnectionProperty();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		ConnectionProperty builder = new ConnectionProperty();

		try (Connection conn = builder.getConnection()) {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(SELECT_ALL_COUNTRIES);
			if (rs != null) {
				countries.clear();
				while (rs.next()) {
					countries.add(new Country(rs.getLong("id"),
							rs.getString("countryFull"),
							rs.getString("countryShort")));
				}
				rs.close();
				request.setAttribute("countries", countries);
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		userPath = request.getServletPath();
		if("/country".equals(userPath)){
			request.getRequestDispatcher("/WEB-INF/view/country/country.jsp")
					.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ConnectionProperty builder = new ConnectionProperty();

		try (Connection conn = builder.getConnection()){
			String countryFull = request.getParameter("countryFull");
			String countryShort = request.getParameter("countryShort");
			Country newCountry = new Country(countryFull, countryShort);

			try (PreparedStatement preparedStatement = conn.prepareStatement(INSERT_COUNTRY)){
				preparedStatement.setString(1, newCountry.getCountryFull());
				preparedStatement.setString(2, newCountry.getCountryShort());

				int result = preparedStatement.executeUpdate();
			} catch (Exception e) {
				System.out.println(e);
			}
		} catch (Exception e) {
			System.out.println(e);
			getServletContext().getRequestDispatcher("/WEB-INF/view/country/country.jsp")
					.forward(request, response);
		}
		doGet(request, response);
	}
}