package gestores;
import logica.util.*;
import logica.*;
import java.util.List;
import java.util.ArrayList;

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
	}

}
