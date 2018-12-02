package gestores;
import java.util.ArrayList;
import java.util.List;

import logica.Clasificacion;

public class GestorClasificacion {
	
	public GestorClasificacion() {};
	
	public Clasificacion obtenerClasificacion(String clas) {
		Clasificacion c;
		GestorDB gestorDB = new GestorDB();
		gestorDB.connectDatabase();
		c = gestorDB.seleccionarClasificaciones(clas);
		gestorDB.cerrarConexion();
		return c;
		
	}
	
	public List<String> obtenerNombreClasificaciones(){
		GestorDB gestorDB = new GestorDB();
		List<String> lista = new ArrayList<String>();
		
		gestorDB.connectDatabase();
		lista = gestorDB.getNombreClasificaciones();
		gestorDB.cerrarConexion();
		
		return lista;
	}

}
