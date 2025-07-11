import java.sql.*;

public class FirDAO {

    public void addFir(Fir fir) {
        String sql = "INSERT INTO firs (citizen_id, date, time, location, description) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, fir.getCitizenId());
            stmt.setString(2, fir.getDate());
            stmt.setString(3, fir.getTime());
            stmt.setString(4, fir.getLocation());
            stmt.setString(5, fir.getDescription());
            stmt.executeUpdate();
            System.out.println("FIR registered successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteFir(int id) {
        String sql = "DELETE FROM firs WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("FIR deleted successfully.");
            } else {
                System.out.println("FIR not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Fir getFirById(int id) {
        String sql = "SELECT * FROM firs WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Fir(
                        rs.getInt("id"),
                        rs.getInt("citizen_id"),
                        rs.getString("date"),
                        rs.getString("time"),
                        rs.getString("location"),
                        rs.getString("description"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
