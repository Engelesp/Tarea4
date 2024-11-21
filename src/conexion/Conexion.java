package conexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	
	private static final String CONTROLADOR = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/datos";
	private static final String USUARIO = "root";
	private static final String CLAVE = "Espinosa44";
	
	static {
		try {
			Class.forName(CONTROLADOR);
		} catch (Exception e) {
			System.out.println("Error al cargar el controlador");
			e.printStackTrace();
		}
	}
	
	public Connection conectar() {
		Connection conexion = null;
		try {	
			
			conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
			System.out.println("Conexion establecida");
			
			
		} catch (SQLException e) {
			System.out.println("Error en la conexion");
			e.printStackTrace();
		}
		
		return conexion;
	}
	
		
}
	
