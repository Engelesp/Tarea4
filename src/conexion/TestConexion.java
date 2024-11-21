package conexion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestConexion {
	public static void main(String[] args) {
		Conexion conexion = new Conexion();
		Connection cn = null;
		Statement stm = null;
		ResultSet rs = null;
		
		try {
			cn = conexion.conectar();
			stm = cn.createStatement();
			rs = stm.executeQuery("SELECT * FROM datos.usuarios");
			
			while (rs.next()) {
				int idUsuario = rs.getInt(1);
				String nombre_usuario = rs.getString(2);				
				String nombre = rs.getString(3);			
				String apellido = rs.getString(4);
				String telefono = rs.getString(5);
				String correo = rs.getString(6);
				String contraseña = rs.getString(7);
				
				
				System.out.println(idUsuario + "-\n " + nombre_usuario +  " - " + nombre + " - " + apellido + " - " + telefono + " - " + correo + " - " + contraseña);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		}	finally {
			  try {
				  	if (rs!= null) {
				  		rs.close();
				  	}
				  	
				  	if (stm!= null) {
				  		rs.close();
				  	}
				  	
				  	if (cn!= null) {
				  		rs.close();
				  	}
				  	
				} catch (Exception e2) {
					e2.printStackTrace();
				}
		}
		
	}
}
