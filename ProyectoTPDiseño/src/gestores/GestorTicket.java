package gestores;
import logica.util.*;
import logica.*;
import java.util.List;
<<<<<<< HEAD
import java.util.ArrayList;
=======
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.time.temporal.ChronoUnit;
>>>>>>> Tami

public class GestorTicket {
	
	public GestorTicket() {};
	
	public void registrarTicket(DTOTicket dto, Sesion s){
		Ticket t = new Ticket();
		List<Intervencion> l = new ArrayList<Intervencion>();
		List<HistorialEstadoTicket> e = new ArrayList<HistorialEstadoTicket>();
		List<HistorialClasificacion> c = new ArrayList<HistorialClasificacion>();
		GestorDB gestorDB = new GestorDB();
		
		gestorDB.connectDatabase();
		int secuencia = gestorDB.devolverSecuencia();
		Empleado demand = gestorDB.consultaEmpleado(dto.getNroLegajo()); 
		Clasificacion clas = gestorDB.seleccionarClasificaciones(dto.getClasificacion());
		gestorDB.cerrarConexion();
		
		GestorUsuario gestorUsuario = new GestorUsuario();
		Usuario usr = gestorUsuario.getUser(s);
		
		t.setActorMesa(usr);
		t.setDescripcion(dto.getDescripcion());
		t.setNroTicket(secuencia);
		t.setFechaApertura(dto.getFechaApertura());
		t.setIntervenciones(l);
		t.setHistorialEstadoTicket(e);
		t.setHistorialClasificacion(c);
		t.setDemandante(demand);
		
		HistorialEstadoTicket ht = new HistorialEstadoTicket();
		ht.setFechaInicio(dto.getFechaApertura());
		ht.setEstado(EstadoTicket.abiertoEnMesa);
		ht.setActor(usr);
		
		HistorialClasificacion hc = new HistorialClasificacion();
		hc.setClasificacion(clas);
		hc.setFechaInicio(dto.getFechaApertura());
		hc.setActor(usr);
		
		t.agregarHistorialTicket(ht);
		t.agregarHistorialClasificacion(hc);
		
		
		gestorDB.connectDatabase();
		gestorDB.guardarTicket(t);
		gestorDB.cerrarConexion();
		
	}

	public void setObservaciones(Ticket t, String o) {
		t.setObservaciones(o);
<<<<<<< HEAD
=======
	}
	
	public void cerrarTicket(Ticket t, Sesion s) {
		HistorialEstadoTicket ht = new HistorialEstadoTicket();
		LocalDateTime now = LocalDateTime.now();
		
		GestorUsuario gestorUsuario = new GestorUsuario();
		Usuario usr = gestorUsuario.getUser(s);
		
		//para cerrar el estado anterior
				List<HistorialEstadoTicket> h = t.getHistorialEstadoTicket();
				int n = h.size() - 1;
				HistorialEstadoTicket ha = h.get(n);
				ha.setFechaFinal(now);
				t.agregarHistorialTicket(ha);
				
				
		ht.setActor(usr);
		ht.setEstado(EstadoTicket.cerrado);
		ht.setFechaInicio(now);
		t.agregarHistorialTicket(ht);
	
		GestorDB gestorDB = new GestorDB();
		
		gestorDB.connectDatabase();
		gestorDB.guardarTicket(t);
		gestorDB.cerrarConexion();
	}
	
	public void derivarTicket(Ticket t, GrupoResolucion g, Sesion s) {
		HistorialEstadoTicket ht = new HistorialEstadoTicket();
		LocalDateTime now = LocalDateTime.now();
		
		GestorUsuario gestorUsuario = new GestorUsuario();
		Usuario usr = gestorUsuario.getUser(s);
		
		//para cerrar el estado anterior
				List<HistorialEstadoTicket> h = t.getHistorialEstadoTicket();
				int n = h.size() - 1;
				HistorialEstadoTicket ha = h.get(n);
				ha.setFechaFinal(now);
				t.agregarHistorialTicket(ha);
				
				
		ht.setActor(usr);
		ht.setEstado(EstadoTicket.abiertoDerivado);
		ht.setFechaInicio(now);
		t.agregarHistorialTicket(ht);
		
		Intervencion i = new Intervencion();
		i.setEstado(EstadoIntervencion.asignada);
		i.setFechaAsignacion(now);
		i.setActor(usr);
		i.setTicket(t);
		i.setTiempoTrabajado(0);
	
		t.agregarIntervencion(i);
		
		GestorDB gestorDB = new GestorDB();
		gestorDB.connectDatabase();
		gestorDB.guardarTicket(t);
		gestorDB.cerrarConexion();	
	}
	
	public void setTiempoEnMesa(Ticket t, LocalDateTime n) {
		
		
		LocalDateTime tempDateTime = LocalDateTime.from( t.getFechaApertura() );

		long years = tempDateTime.until( n, ChronoUnit.YEARS);
		tempDateTime = tempDateTime.plusYears( years );

		long months = tempDateTime.until( n, ChronoUnit.MONTHS);
		tempDateTime = tempDateTime.plusMonths( months );

		long days = tempDateTime.until( n, ChronoUnit.DAYS);
		tempDateTime = tempDateTime.plusDays( days );


		long hours = tempDateTime.until( n, ChronoUnit.HOURS);
		tempDateTime = tempDateTime.plusHours( hours );

		long minutes = tempDateTime.until(n, ChronoUnit.MINUTES);
		tempDateTime = tempDateTime.plusMinutes( minutes );

		long seconds = tempDateTime.until( n, ChronoUnit.SECONDS);
		
		long m = 525600*years + 43800*months + 1440*days +60*hours + minutes;
		
		t.setTiempoEnMesa(m);
		
		GestorDB gestorDB = new GestorDB();
		gestorDB.connectDatabase();
		gestorDB.guardarTicket(t);
		gestorDB.cerrarConexion();
>>>>>>> Tami
	}

}
