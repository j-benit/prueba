

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompanyDao {
    private String jdbcURL = "jdbc:mysql://localhost:3306/demo";
    private String jdbcUsername = "your_username";
    private String jdbcPassword = "your_password";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
    }

    public void createCompany(company company) {
        String sql = "INSERT INTO company (name, code) VALUES (?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, company.getName());
            pstmt.setString(2, company.getCode());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public company getCompanyByCode(String companyCode) {
        String sql = "SELECT * FROM company WHERE code = ?";
        company company = null;
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, companyCode);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                company = new company(rs.getInt("company_id"), rs.getString("company_name"), rs.getString("company_code"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return company;
    }

    public void updateCompany(company company) {
        String sql = "UPDATE company SET company_name = ?, company_code = ? WHERE company_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, company.getName());
            pstmt.setString(2, company.getCode());
            pstmt.setInt(3, company.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCompany(int companyId) {
        String sql = "DELETE FROM company WHERE company_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, companyId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<company> getAllCompanies() {
        List<company> companies = new ArrayList<>();
        String sql = "SELECT * FROM company";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                company company = new company(rs.getInt("company_id"), rs.getString("company_name"), rs.getString("company_code"));
                companies.add(company);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return companies;
    }
}
