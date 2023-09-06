package main.java.controller.address;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.java.dao.ConnectionProperty;
import main.java.domain.Address;
import main.java.domain.City;
import main.java.domain.Country;
import main.java.domain.Region;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

@WebServlet("/address")
public class AddressServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ConnectionProperty prop;

	String SELECT_ALL_ADDRESSES = "SELECT id, cityId, person, street, building, office FROM address";
	String SELECT_ALL_CITIES = "SELECT id, regionId, city FROM city";
	String SELECT_ALL_REGIONS = "SELECT id, countryId, region FROM region";
	String SELECT_ALL_COUNTRIES = "SELECT id, countryFull, countryShort FROM country";
	String INSERT_ADDRESS = "INSERT INTO address (cityId, person, street, building, office) VALUES (?, ?, ?, ?, ?)";

	ArrayList<Address> addresses = new ArrayList<>();
	ArrayList<City> cities = new ArrayList<>();
	ArrayList<Region> regions = new ArrayList<>();
	ArrayList<Country> countries = new ArrayList<>();

	public AddressServlet() {
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
							rs.getString("countryShort"),
							rs.getString("countryFull")));
				}
				rs.close();
				request.setAttribute("countries", countries);
			}

			long countryId;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SELECT_ALL_REGIONS);
			if (rs != null) {
				regions.clear();
				while (rs.next()) {
					countryId = rs.getLong("countryId");
					regions.add(new Region(rs.getLong("id"),
							rs.getString("region"),
							countryId,
							findByIdCountry(countryId, countries)));
				}
				rs.close();
				request.setAttribute("regions", regions);
			}

			long regionId;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SELECT_ALL_CITIES);
			if (rs != null) {
				cities.clear();
				while (rs.next()) {
					regionId = rs.getLong("regionId");
					cities.add(new City(rs.getLong("id"),
							rs.getString("city"),
							regionId,
							findByIdRegion(regionId, regions)));
				}
				rs.close();
				request.setAttribute("cities", cities);
			}

			long cityId;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SELECT_ALL_ADDRESSES);
			if (rs != null) {
				addresses.clear();
				while (rs.next()) {
					cityId = rs.getLong("cityId");
					addresses.add(new Address(rs.getLong("id"),
							rs.getString("person"),
							rs.getString("street"),
							rs.getInt("building"),
							rs.getInt("office"),
							cityId,
							findByIdCity(cityId, cities)));
				}
				rs.close();
				request.setAttribute("addresses", addresses);
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		String userPath = request.getServletPath();
		if ("/address".equals(userPath)){
			request.getRequestDispatcher("/WEB-INF/view/address/address.jsp")
					.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ConnectionProperty builder = new ConnectionProperty();

		try (Connection conn = builder.getConnection()){
			String person = request.getParameter("person");
			String street = request.getParameter("street");
			int building = Integer.parseInt(request.getParameter("building"));
			int office = Integer.parseInt(request.getParameter("office"));

			String city = request.getParameter("city");
			int index1 = city.indexOf('=');
			int index2 = city.indexOf(",");
			String r1 = city.substring(index1+1, index2);
			Long cityId = Long.parseLong(r1.trim());

			try (PreparedStatement preparedStatement = conn.prepareStatement(INSERT_ADDRESS)){
			preparedStatement.setLong(1, cityId);
			preparedStatement.setString(2, person);
			preparedStatement.setString(3, street);
			preparedStatement.setInt(4, building);
			preparedStatement.setInt(5, office);

			int rows = preparedStatement.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
	} catch (Exception e) {
		System.out.println(e);
		getServletContext().getRequestDispatcher("/WEB-INF/view/address/address.jsp")
				.forward(request, response);
	}
		doGet(request, response);
	}

	private Country findByIdCountry(Long id, ArrayList<Country> countries) {
		if (countries != null) {
			for (Country r: countries) {
				if ((r.getId()).equals(id)) {
					return r;
				}
			}
		} else {
			return null;
		}
		return null;
	}
	private Region findByIdRegion(Long id, ArrayList<Region> regions) {
		if (regions != null) {
			for (Region r: regions) {
				if ((r.getId()).equals(id)) {
					return r;
				}
			}
		} else {
			return null;
		}
		return null;
	}
	private City findByIdCity(Long id, ArrayList<City> cities) {
		if (cities != null) {
			for (City r: cities) {
				if ((r.getId()).equals(id)) {
					return r;
				}
			}
		} else {
			return null;
		}
		return null;
	}
}
