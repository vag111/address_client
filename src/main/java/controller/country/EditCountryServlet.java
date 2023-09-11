package main.java.controller.country;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.java.dao.ConnectionProperty;
import main.java.domain.Country;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

@WebServlet("/editCountry")
public class EditCountryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    ConnectionProperty prop;
    String userPath;

    String SELECT_ALL_COUNTRIES = "SELECT id, countryFull, countryShort FROM country";
    String SELECT_COUNTRY_BY_ID = "SELECT id, countryFull, countryShort FROM country WHERE id = ?";
    String EDIT_COUNTRY = "UPDATE country SET countryFull = ?, countryShort = ? WHERE id = ?";

    ArrayList<Country> countries = new ArrayList<>();
    ArrayList<Country> editCountry = new ArrayList<>();

    public EditCountryServlet() {
        super();
        prop = new ConnectionProperty();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        ConnectionProperty builder = new ConnectionProperty();

        try (Connection conn = builder.getConnection()) {
            String strId = request.getParameter("id");
            Long idCountrySelected = null;
            if (strId != null) {
                idCountrySelected = Long.parseLong(strId);
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

            try (PreparedStatement preparedStatement = conn.prepareStatement(SELECT_COUNTRY_BY_ID)) {
                preparedStatement.setLong(1, idCountrySelected);
                rs = preparedStatement.executeQuery();
                if (rs != null) {
                    editCountry.clear();
                    while (rs.next()) {
                        editCountry.add(new Country(rs.getLong("id"),
                                rs.getString("countryShort"),
                                rs.getString("countryFull")));
                    }
                    rs.close();
                    request.setAttribute("countryEdit", editCountry);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        String userPath = request.getServletPath();
        if("/editCountry".equals(userPath)){
            request.getRequestDispatcher("/WEB-INF/view/country/editCountry.jsp")
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

            String countryFull = request.getParameter("countryFull");
            String countryShort = request.getParameter("countryShort");

            PreparedStatement preparedStatement = conn.prepareStatement(EDIT_COUNTRY);
            preparedStatement.setString(1, countryFull);
            preparedStatement.setString(2, countryShort);
            preparedStatement.setLong(3, id);

            int rows = preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        doGet(request, response);
    }
}
