package main.java.controller.region;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.java.dao.ConnectionProperty;
import main.java.domain.Country;
import main.java.domain.Region;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

@WebServlet("/deleteRegion")
public class DeleteRegionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    ConnectionProperty prop;
    String userPath;

    String SELECT_ALL_REGIONS = "SELECT id, countryId, region FROM region";
    String SELECT_ALL_COUNTRIES = "SELECT id, countryFull, countryShort FROM country";
    String SELECT_REGION_BY_ID = "SELECT id, countryId, region FROM region WHERE id = ?";
    String DELETE_REGION = "DELETE FROM region WHERE id = ?";

    ArrayList<Region> regions = new ArrayList<>();
    ArrayList<Region> deleteRegion = new ArrayList<>();
    ArrayList<Country> countries = new ArrayList<>();

    public DeleteRegionServlet() {
        super();
        prop = new ConnectionProperty();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        ConnectionProperty builder = new ConnectionProperty();

        try (Connection conn = builder.getConnection()) {
            String strId = request.getParameter("id");
            Long idRegionSelected = null;
            if (strId != null) {
                idRegionSelected = Long.parseLong(strId);
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

            try (PreparedStatement preparedStatement = conn.prepareStatement(SELECT_REGION_BY_ID)) {
                preparedStatement.setLong(1, idRegionSelected);
                rs = preparedStatement.executeQuery();
                if (rs != null) {
                    deleteRegion.clear();
                    Long idCountry = null;
                    while (rs.next()) {
                        deleteRegion.add(new Region(rs.getLong("id"),
                                rs.getString("region"),
                                idCountry,
                                findByIdCountry(idCountry, countries)));
                    }
                    rs.close();
                    request.setAttribute("regionDelete", deleteRegion);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        userPath = request.getServletPath();
        if ("/deleteRegion".equals(userPath)) {
            request.getRequestDispatcher("/WEB-INF/view/region/deleteRegion.jsp")
                    .forward(request, response);
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ConnectionProperty builder = new ConnectionProperty();

        try (Connection conn = builder.getConnection()) {
            Long id = Long.parseLong(request.getParameter("id"));
            try (PreparedStatement preparedStatement = conn.prepareStatement(DELETE_REGION)) {
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
}
