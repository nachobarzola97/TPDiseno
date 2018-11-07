package gestores;

import java.sql.*;
import logica.*;
import logica.util.EstadoClasificacion;

import java.util.List;
import java.util.ArrayList;

public class GestorDB {
	private Connection connection;
	public GestorDB() {
		this.connection = null;
	}
	
	public void connectDatabase() {
			try {	
				// We register the PostgreSQL driver
				// Registramos el driver de PostgresSQL
				try { 
				    Class.forName("org.postgresql.Driver");
				} catch (ClassNotFoundException ex) {
				    System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
				}
				
				// Database connect
				// Conectamos con la base de datos
				connection = DriverManager.getConnection(
				        "jdbc:postgresql://localhost/postgres",
				        "postgres", "TF135");
				boolean valido = connection.isValid(50000);
				if(valido) {
					System.out.println("Test OK");
				}
				else {
					System.out.println("Test fail");
				}
			}
			catch (java.sql.SQLException sqle) {
				System.out.println("Error");
				System.out.println(sqle.getSQLState());
				System.out.println(sqle.getErrorCode());
				sqle.printStackTrace();
			}
				
	}
	
	public void cerrarConexion() {
		try {
			this.connection.close();
		}
		catch (java.sql.SQLException sqle) {
			System.out.println("Error al cerrar la conexion");
		}
	}
	
	public List<Clasificacion> seleccionarClasificaciones() {
		List<Clasificacion> lista = new ArrayList<Clasificacion>();
			try{
				String sql = "SELECT * FROM Clasificacion;";
				ResultSet resultadoClasificaciones;
				Statement sentencia = this.connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);				
				resultadoClasificaciones = sentencia.executeQuery(sql);
				
				while(resultadoClasificaciones.next()) {
					Clasificacion aux = new Clasificacion();
					aux.setIdClasificacion(Integer.valueOf((resultadoClasificaciones.getString(1))));
					System.out.println("id: "+resultadoClasificaciones.getString(1));
					aux.setNombre(resultadoClasificaciones.getString(2));
					System.out.println("nombre: "+resultadoClasificaciones.getString(2));
					aux.setEstado(EstadoClasificacion.valueOf(resultadoClasificaciones.getString(3)));
					System.out.println("estado: "+resultadoClasificaciones.getString(3));
					aux.setDescripcion(resultadoClasificaciones.getString(4));
					System.out.println("descripcion: "+resultadoClasificaciones.getString(4));
					
					lista.add(aux);
				}
				System.out.println("Lo hizo bien");
				
				this.cerrarConexion();
			}
			catch(java.sql.SQLException sqle) {
				System.out.println("Error al seleccionar");
				sqle.printStackTrace();
			}
		return lista;
	}
	
	public void seleccionar() {
		try{
			String sql = "SELECT nroTicket FROM Ticket;";
			ResultSet resultado;
			ResultSetMetaData res;
			Statement sentencia = this.connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);				
			resultado = sentencia.executeQuery(sql);
			res = resultado.getMetaData();
			
			while(resultado.next()) {
				System.out.println("Dato: "+resultado.getString(1));
			}
			
		
			System.out.println("Salio bien");
		}
		catch(java.sql.SQLException sqle) {
			System.out.println("Error al seleccionar");
			sqle.printStackTrace();
		}
		
	}
	
	public void insertar() {
		try {
			PreparedStatement sent = this.connection.prepareStatement("INSERT INTO TICKET VALUES (1, 'Ticket1', 'ObsTicket1', '2018-10-30', null, 24088);");
			sent.execute();
			sent.close();
			
			System.out.println("Salio bien");
		}
		catch(java.sql.SQLException sqle) {
			System.out.println("Error al insertar");
			sqle.printStackTrace();
		}
	}
}

