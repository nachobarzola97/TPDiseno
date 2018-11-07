package gestores;

import logica.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class PruebaGestor {
	public static void main(String[] args) {
		
		Ticket t = new Ticket(56, "aaa", LocalDateTime.of(2018, 10, 12, 12, 15, 43), "obs", 123, new ArrayList<Intervencion>(), null, new ArrayList<HistorialEstadoTicket>(), null, new ArrayList<HistorialClasificacion>());
		GestorDB gestor = new GestorDB();
		gestor.connectDatabase();
		gestor.guardarTicket(t);
		gestor.cerrarConexion();
	}
}
