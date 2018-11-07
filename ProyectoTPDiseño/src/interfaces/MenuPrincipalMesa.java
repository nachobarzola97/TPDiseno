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
	private CardLayout cardLayout= new CardLayout();
	private DTOTicket ticketEnProceso;



	/**
	 * Create the frame.
	 */
	public MenuPrincipalMesa() {
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
		
		contentPane.add(registroA,"1");
		contentPane.add(registroB,"2");
		
		
		JButton btnNewButton = new JButton("Registrar Ticket");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardLayout.show(contentPane, "1");
			}
		});
		btnNewButton.setBounds(130, 48, 149, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Consultar Ticket");
		btnNewButton_1.setBounds(130, 95, 147, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Cerrar Ticket");
		btnNewButton_2.setBounds(130, 143, 147, 23);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Derivar Ticket");
		btnNewButton_3.setBounds(130, 189, 147, 23);
		contentPane.add(btnNewButton_3);
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
		
		public void refreshVentana(List<GrupoResolucion> grupos, List<Clasificacion> clas, int i) {
			JComboBox combo = new JComboBox();
			for (GrupoResolucion g : grupos) {
				combo.addItem(g);
			}
			registroB.setComboBoxGrupo(combo, clas);
			registroB.keepSelectedClass(i);
			cardLayout.show(contentPane, "2");
			};
		
	
	public DTOTicket getTicketEnProceso() {
		return ticketEnProceso;
	}

	public void setTicketEnProceso(DTOTicket ticketEnProceso) {
		this.ticketEnProceso = ticketEnProceso;
	}
	
	
	
}
