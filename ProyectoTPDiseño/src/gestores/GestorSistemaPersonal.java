package gestores;

public class GestorSistemaPersonal {
	
	public GestorSistemaPersonal(){};
	
 public boolean obtenerE(String legajo) {
	 GestorDB gestorDB = new GestorDB();
		
		gestorDB.connectDatabase();
		boolean existe = gestorDB.existeEmpleado(nroLegajo.getText());
		gestorDB.cerrarConexion();
		
		return existe;
 }

}
