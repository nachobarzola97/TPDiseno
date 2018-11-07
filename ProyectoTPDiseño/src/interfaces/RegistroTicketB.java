package interfaces;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import gestores.*;
import logica.*;


public class RegistroTicketB extends JPanel {
	private MenuPrincipalMesa frame;
	private JTextField textObservaciones;
	private GestorDB gestorDB;

	public RegistroTicketB(MenuPrincipalMesa f) {
		this.frame=f;
		setLayout(null);
		
		this.gestorDB = new GestorDB();
		
		//TODO: ObtenerGrupoResolucionBD
		
		this.gestorDB.connectDatabase();
		List<Clasificacion> clasificacionesTicket = this.gestorDB.seleccionarClasificaciones();
		this.gestorDB.cerrarConexion();
		
		String[] grupos= {""};
		String[] estadosTicket= {"Abierto en mesa de ayuda","Abierto derivado a grupo","Solucionado a la espera ok","Cerrado"};
		
		
		
		
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
		
		JComboBox comboBoxEstado = new JComboBox(estadosTicket);
		comboBoxEstado.setBounds(179, 118, 183, 20);
		this.add(comboBoxEstado);
		
		JComboBox JComboBoxClasificacion = new JComboBox();
		for(Clasificacion c : clasificacionesTicket) {
			JComboBoxClasificacion.addItem(c.getNombre());
		}
		JComboBoxClasificacion.setBounds(179, 143, 183, 20);
		this.add(JComboBoxClasificacion);
		
		JComboBox comboBoxGrupo = new JComboBox(grupos);
		comboBoxGrupo.setBounds(179, 168, 183, 20);
		this.add(comboBoxGrupo);
		
		JButton btnNewButton = new JButton("Cancelar");
		btnNewButton.setBounds(259, 202, 101, 23);
		this.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Aceptar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if( textObservaciones.getText().isEmpty()) {
					//TODO: Reveer mensaje de errorB
					//JOptionPane.showMessageDialog(this,"Los campos no pueden ser nulos.","Error",JOptionPane.ERROR_MESSAGE);
				}
				else {
					int nroTicket = frame.getTicketEnProceso().getNroTicket();
					//TODO: recuperarTicket(nroTicket) 
					//TODO: setearObservaciones
					if(comboBoxEstado.getSelectedItem()=="Cerrado") {
						//TODO: Cerrar-Gestorn(nroTicket)
					}
					else if(comboBoxEstado.getSelectedItem()=="Abierto derivado grupo"){
						String grupo=comboBoxGrupo.getSelectedItem().toString();
						//TODO: Derivar-Gestor(grupo,nroTicket)	
					}
					
					
					
				}
			}
		});
		btnNewButton_1.setBounds(146, 202, 101, 23);
		this.add(btnNewButton_1);
	}

}
