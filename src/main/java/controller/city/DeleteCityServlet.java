package main.java.controller.city;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.java.dao.ConnectionProperty;
import main.java.domain.City;
import main.java.domain.Country;
import main.java.domain.Region;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

@WebServlet("/deleteCity")
public class DeleteCityServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    ConnectionProperty prop;

    String SELECT_ALL_CITIES = "SELECT id, regionId, city FROM city";
    String SELECT_ALL_REGIONS = "SELECT id, countryId, region FROM region";
    String SELECT_ALL_COUNTRIES = "SELECT id, countryFull, countryShort FROM country";
    String SELECT_CITY_BY_ID = "SELECT id, regionId, city FROM city WHERE id = ?";
    String DELETE_CITY = "DELETE FROM city WHERE id = ?";

    ArrayList<City> cities = new ArrayList<>();
    ArrayList<City> deleteCity = new ArrayList<>();
    ArrayList<Region> regions = new ArrayList<>();
    ArrayList<Country> countries = new ArrayList<>();

    public DeleteCityServlet() {
        super();
        prop = new ConnectionProperty();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        ConnectionProperty builder = new ConnectionProperty();

        try (Connection conn = builder.getConnection()) {
            String strId = request.getParameter("id");
            Long idCitySelected = null;
            if (strId != null) {
                idCitySelected = Long.parseLong(strId);
            }

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

            try (PreparedStatement preparedStatement = conn.prepareStatement(SELECT_CITY_BY_ID)) {
                preparedStatement.setLong(1, idCitySelected);
                rs = preparedStatement.executeQuery();
                if (rs != null) {
                    deleteCity.clear();
                    Long idRegion = null;
                    while (rs.next()) {
                        deleteCity.add(new City(rs.getLong("id"),
                                rs.getString("city"),
                                idRegion,
                                findByIdRegion(idRegion, regions)));
                    }
                    rs.close();
                    request.setAttribute("cityDelete", deleteCity);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        String userPath = request.getServletPath();
        if("/deleteCity".equals(userPath)){
            request.getRequestDispatcher("/WEB-INF/view/city/deleteCity.jsp")
                    .forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ConnectionProperty builder = new ConnectionProperty();

        try (Connection conn = builder.getConnection()){
            String strId = request.getParameter("id");
            Long id = null;
            if (strId != null) {
                id = Long.parseLong(strId);
            }

            try (PreparedStatement preparedStatement = conn.prepareStatement(DELETE_CITY)) {
                preparedStatement.setLong(1, id);

                int result = preparedStatement.executeUpdate();
            } catch (Exception e) {
                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println(e);
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
}
