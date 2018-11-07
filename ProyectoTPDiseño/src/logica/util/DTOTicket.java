package logica.util;

import java.time.LocalDateTime;

public class DTOTicket {
	int nroTicket;
	String nroLegajo;
	String descripcion;
	String clasificacion;
	String observacion;
	Long tiempoEnMesa;
	LocalDateTime fechaApertura;
	
	public DTOTicket(String nroLegajo, String descripcion, String clasificacion, LocalDateTime fechaApertura) {
		super();
		this.nroLegajo = nroLegajo;
		this.descripcion = descripcion;
		this.clasificacion = clasificacion;
		this.fechaApertura = fechaApertura;
	}
	
	public String getNroLegajo() {
		return nroLegajo;
	}

	public void setNroLegajo(String nroLegajo) {
		this.nroLegajo = nroLegajo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getClasificacion() {
		return clasificacion;
	}

	public void setClasificacion(String clasificacion) {
		this.clasificacion = clasificacion;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Long getTiempoEnMesa() {
		return tiempoEnMesa;
	}

	public void setTiempoEnMesa(Long tiempoEnMesa) {
		this.tiempoEnMesa = tiempoEnMesa;
	}

	public LocalDateTime getFechaApertura() {
		return fechaApertura;
	}

	public void setFechaApertura(LocalDateTime fechaApertura) {
		this.fechaApertura = fechaApertura;
	}
	public int getNroTicket() {
		return nroTicket;
	}

	public void setNroTicket(int nroTicket) {
		this.nroTicket = nroTicket;
	}

}
