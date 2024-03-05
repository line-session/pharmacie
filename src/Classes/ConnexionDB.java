package Classes;
import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;
public class ConnexionDB {
	public static Connection connectDB()
	{
		try {
			Properties p =new Properties ();
			try (FileInputStream file= new  FileInputStream("Config.properties")){
			p.load(file);
			}
			String urlPilote=p.getProperty("jdbc.driver.class");
			Class.forName(urlPilote);
			String urlBD=p.getProperty("jdbc.url");
			String user=p.getProperty("jdbc.login");
			String  password=p.getProperty("jdbc.password");
			Connection con=DriverManager.getConnection(urlBD, user, password);
			return con;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
			}
	}
}
	


