package gestores;

import logica.*;
import logica.util.EstadoTicket;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PruebaGestor {
	public static void main(String[] args) {
		GestorDB gest = new GestorDB();
		gest.connectDatabase();
		gest.recuperarGrupo("Redes LAN");
		gest.cerrarConexion();
	}
}
