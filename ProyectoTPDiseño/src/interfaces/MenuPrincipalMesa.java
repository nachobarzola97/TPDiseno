package interfaces;

import java.awt.*;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import logica.util.*;
import logica.*;
import java.util.ArrayList;
import java.util.List;


public class MenuPrincipalMesa extends JFrame {



	private JPanel contentPane;
	private RegistroTicketA registroA;
	private RegistroTicketB registroB;
	private Menu menu;
	private CardLayout cardLayout= new CardLayout();
	private DTOTicket ticketEnProceso;
	private boolean guiSeleccionada;
	private Sesion sesion;


	/**
	 * Create the frame.
	 */
	public MenuPrincipalMesa(Sesion s) {
		sesion = s;
		
		setTitle("Mesa de ayuda");
		this.setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(cardLayout);
		
		registroA = new RegistroTicketA(this);
		registroB = new RegistroTicketB(this);
		menu = new Menu(this); 
		
		contentPane.add(menu, "3");
		contentPane.add(registroA,"1");
		contentPane.add(registroB,"2");
	}
	
	public void cambiarVentana(int n,DTOTicket dtoTicket) {
		this.ticketEnProceso=dtoTicket;
		switch(n) {
			case 1:
				cardLayout.show(contentPane, "1");
				break;
			case 2:
				cardLayout.show(contentPane, "2");
				break;
		}
	}
	
	public void cambiarVentanaMenu(int n) {
		switch(n) {
		case 1: //Registrar Ticket
			cardLayout.show(contentPane, "1");
			break;
		case 3: //volver al menu
			cardLayout.show(contentPane, "3");
		}
	}
	
	public DTOTicket getTicketEnProceso() {
		return ticketEnProceso;
	}

	public void setTicketEnProceso(DTOTicket ticketEnProceso) {
		this.ticketEnProceso = ticketEnProceso;
	}

	public Sesion getSesion() {
		return sesion;
	}

	public void setSesion(Sesion sesion) {
		this.sesion = sesion;
	}
	
}