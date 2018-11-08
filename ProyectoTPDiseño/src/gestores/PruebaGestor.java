package gestores;

import logica.*;
import logica.util.EstadoTicket;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PruebaGestor {
	public static void main(String[] args) {
		
		Usuario user = new Usuario("FrancoBrechbuhl", "abc", null);
		HistorialEstadoTicket het = new HistorialEstadoTicket(LocalDateTime.of(2018, 10, 25, 15, 23, 11), EstadoTicket.AbiertoEnMesaDeAyuda, user);
		List<HistorialEstadoTicket> l1 = new ArrayList<HistorialEstadoTicket>();
		l1.add(het);
		Clasificacion clas = new Clasificacion(5, "General");
		HistorialClasificacion hct = new HistorialClasificacion(LocalDateTime.of(2018, 11, 8, 11, 23, 55), clas, user);
		List<HistorialClasificacion> l2 = new ArrayList<HistorialClasificacion>();
		l2.add(hct);
		Empleado emp = new Empleado("24088", "Franco Brechbuhl");
		
		Ticket t = new Ticket(56, "aaa", LocalDateTime.of(2018, 10, 12, 12, 15, 43), "obs", 123, new ArrayList<Intervencion>(), user, l1, emp, l2);
		GestorDB gestor = new GestorDB();
		gestor.connectDatabase();
		gestor.guardarTicket(t);
		gestor.cerrarConexion();
	}
}
