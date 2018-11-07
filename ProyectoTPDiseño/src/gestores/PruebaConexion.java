package gestores;

import java.util.*;
import logica.*;

public class PruebaConexion {

	public static void main(String[] args) {
		GestorDB con = new GestorDB(); 
		con.connectDatabase();
		//con.insertar();
		List<Clasificacion> li = con.seleccionarClasificaciones();
		con.cerrarConexion();
	}


}
