
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class KoneksiDB {
    public Connection getKoneksi() throws ClassNotFoundException, SQLException{//utk menangkap yang error
        Class.forName("com.mysql.jdbc.Driver");
        
        String url = "jdbc:mysql://localhost/mahasiswa";
        Connection con = (Connection) DriverManager.getConnection(url, "root", "");
        return con;
    }
}
