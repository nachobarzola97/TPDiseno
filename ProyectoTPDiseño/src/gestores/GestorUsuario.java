package gestores;
import logica.*;

public class GestorUsuario {
	
	public GestorUsuario() {};
	
	public Sesion setUsuarioLogueado(Usuario usr) {
	Sesion sesion = new Sesion(usr);
	return sesion;
	}
	
	public Usuario getUser(Sesion s) {
		return s.getUser();
	}
	
	public Usuario validarUsuario(String userName) {
		GestorDB gestorDB = new GestorDB();
		gestorDB.connectDatabase();
		Usuario usr = gestorDB.seleccionarUsuario(userName);
		gestorDB.cerrarConexion();
		return usr;
	}
	
	
	
}


