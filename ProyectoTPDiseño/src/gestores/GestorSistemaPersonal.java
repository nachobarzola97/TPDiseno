package gestores;

public class GestorSistemaPersonal {
	
	public GestorSistemaPersonal(){};
	
 public boolean obtenerE(String legajo) {
	 GestorDB gestorDB = new GestorDB();
		
		gestorDB.connectDatabase();
		//boolean existe = gestorDB.existeEmpleado(legajo); //TODO gestor DB metodo
		gestorDB.cerrarConexion();
		
		return true;
 }

}
