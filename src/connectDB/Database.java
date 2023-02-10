package connectDB;
import java.sql.*;

public class Database {
    public static Connection con = null;
    private static Database instance = new Database();

    public static Database getInstance() {
        if (instance == null)
           instance = new Database();
        return instance;
    }

    /**
     * K?t n?i database
     * @throws SQLException
     */
    public void connect(){
        String severName = "localhost";
        String databaseName = "NhaSachTNL";
        String username = "sa";
        String password = "0123456789";
        String url = "jdbc:sqlserver://" + severName + ":1433;databaseName=" + databaseName;
        try {
			con = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    /**
     * Ng?t k?t n?i database
     */
    public void disconnect() {
        if (con != null)
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    /**
     * L?y ra Connection �ang k?t n?i �?n database
     * @return Connection
     */
    public static Connection getConnection() {
        return con;
    }
}
