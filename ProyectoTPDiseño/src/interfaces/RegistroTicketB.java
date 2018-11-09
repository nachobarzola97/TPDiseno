package interfaces;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.awt.event.ActionEvent;

import gestores.*;
import logica.*;


public class RegistroTicketB extends JPanel {
	private MenuPrincipalMesa frame;
	private JTextField textObservaciones;
	private GestorDB gestorDB;
	private JComboBox<String> comboBoxGrupo;
	private JComboBox<String> JComboBoxClasificacion;

	public RegistroTicketB(MenuPrincipalMesa f) {
		this.frame=f;
		setLayout(null);
		
		this.gestorDB = new GestorDB();
		
		this.gestorDB.connectDatabase();
		List<Clasificacion> clasificacionesTicket = this.gestorDB.seleccionarClasificaciones();
		
		String[] estadosTicket = {"AbiertoEnMesaDeAyuda","AbiertoDerivadoAGrupo","SolucionadoALaEsperaOk","Cerrado"};
		
		JLabel lblObservaciones = new JLabel("Observaciones");
		lblObservaciones.setBounds(28, 11, 145, 14);
		this.add(lblObservaciones);
		
		textObservaciones = new JTextField();
		textObservaciones.setBounds(28, 33, 183, 74);
		this.add(textObservaciones);
		textObservaciones.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Estado");
		lblNewLabel.setBounds(38, 121, 80, 14);
		this.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Clasificaci\u00F3n");
		lblNewLabel_1.setBounds(38, 146, 113, 14);
		this.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Grupo resoluci\u00F3n");
		lblNewLabel_2.setBounds(38, 171, 113, 14);
		this.add(lblNewLabel_2);
		
		JComboBoxClasificacion = new JComboBox<String>();
		for(Clasificacion c : clasificacionesTicket) {
			JComboBoxClasificacion.addItem(c.getNombre());
		}
		JComboBoxClasificacion.setBounds(179, 143, 183, 20);
		
		String nombreClas = (String)JComboBoxClasificacion.getSelectedItem();
		
		List<GrupoResolucion> grupos = new ArrayList<GrupoResolucion>();
		
		for(Clasificacion c : clasificacionesTicket) {
			if(c.getNombre().equals(nombreClas)) {
				grupos = c.getGrupos();
			}
		}
		
		comboBoxGrupo = new JComboBox<String>();
		
		for(GrupoResolucion gr : grupos) {
			comboBoxGrupo.addItem(gr.getNombre());
		}
		
		comboBoxGrupo.setBounds(179, 168, 183, 20);
		
		this.add(comboBoxGrupo);
		
		JComboBox comboBoxEstado = new JComboBox(estadosTicket);
		comboBoxEstado.setBounds(179, 118, 183, 20);
		comboBoxEstado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		this.add(comboBoxEstado);
		
		JComboBoxClasificacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				List<GrupoResolucion> grup = new ArrayList<GrupoResolucion>();
				int i = JComboBoxClasificacion.getSelectedIndex();
				for (Clasificacion c : clasificacionesTicket){
					if (c.getNombre().equals(JComboBoxClasificacion.getSelectedItem())) {
						grup.addAll(c.getGrupos());
						for(GrupoResolucion gr : c.getGrupos()) {
						}
					}
				}
				
				comboBoxGrupo.removeAllItems();
				
				for(GrupoResolucion g : grup) {
					comboBoxGrupo.addItem(g.getNombre());
				}
			}
		});
		
		this.add(JComboBoxClasificacion);
		
		JButton btnNewButton = new JButton("Cancelar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				((MenuPrincipalMesa)frame).cambiarVentanaMenu(3);
			}
		});
		btnNewButton.setBounds(259, 202, 101, 23);
		this.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Aceptar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if( textObservaciones.getText().isEmpty()) {
					JOptionPane.showMessageDialog(frame,
						    "Los campos no pueden ser nulos",
						    "Error",
						    JOptionPane.ERROR_MESSAGE);
				}
				else {
					int nroTicket = frame.getTicketEnProceso().getNroTicket();
					
					Ticket t = gestorDB.recuperarTicket(nroTicket);
					gestorDB.cerrarConexion();
					
					GestorTicket gestorTicket = new GestorTicket();
					gestorTicket.setObservaciones(t, textObservaciones.getText() );
					
					if(comboBoxEstado.getSelectedItem()=="Cerrado") {
						gestorTicket.cerrarTicket(t, frame.getSesion());
					}
					else if(comboBoxEstado.getSelectedItem()=="Abierto derivado grupo"){
						String grupo=comboBoxGrupo.getSelectedItem().toString();
						GrupoResolucion g = gestorDB.recuperarGrupo(grupo);
						
						gestorTicket.derivarTicket(t, g, frame.getSesion());	
					}
					
					LocalDateTime now = LocalDateTime.now();
					gestorTicket.setTiempoEnMesa(t, now);
					
					JOptionPane.showMessageDialog(frame, "Cambios guardados", "Exito", JOptionPane.INFORMATION_MESSAGE);	
				}
			}
		});
		btnNewButton_1.setBounds(146, 202, 101, 23);
		this.add(btnNewButton_1);
	}

}
