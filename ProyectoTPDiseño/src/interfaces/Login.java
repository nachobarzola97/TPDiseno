package interfaces;

import interfaces.image.*;
import interfaces.MenuPrincipalMesa;
import logica.*;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import gestores.GestorDB;
import gestores.GestorUsuario;

import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;



public class Login extends JFrame {
	private JPanel contentPane;
	private JPasswordField passwordField;
	private JFrame frame=this;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 600);
		this.setLocationRelativeTo(null);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel textUsers = new JLabel("Usuario: ");
		textUsers.setBounds(31, 240, 160, 34);
		contentPane.add(textUsers);
		
		
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(191, 281, 169, 23);
		contentPane.add(textPane);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a: ");
		lblContrasea.setBounds(31, 343, 188, 48);
		contentPane.add(lblContrasea);
		
		passwordField = new JPasswordField();
		passwordField.setToolTipText("");
		passwordField.setEchoChar('*');
		passwordField.setBounds(195, 409, 150, 20);
		passwordField.setBorder(null);
		contentPane.add(passwordField);
		
		JLabel label = new JLabel("");
		label.setBounds(165, 405, 24, 34);
		contentPane.add(label);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(133, 51, 138, 149);
		contentPane.add(lblNewLabel);
		
		
		JLabel lblNewLabel_1 = new JLabel("");
		contentPane.add(lblNewLabel_1);
		
		JButton btnIniciarSeccin = new JButton("Iniciar sesi\u00F3n");
		btnIniciarSeccin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GestorUsuario gestorUsuario = new GestorUsuario();
				Usuario usr;
				
				/*TODO: deberia ser validarUsuario(textPane.getText()), si se quiere autenticar
				con el nombre del usuario*/
				usr=gestorUsuario.validarUsuario("IgnacioBarzola");			
				//TODO: Una implementacion que no se necesita hacer.
				//TODO: esta el if en falso 
				//Validamos el usuario 
				if(false) {
					JOptionPane.showMessageDialog(frame,
							"Usuario inv�lido",
						    "Error de autenticaci�n",
						    JOptionPane.ERROR_MESSAGE);
					textPane.setText("");
					passwordField.setText("");
				}
				else {
					Sesion s= gestorUsuario.setUsuarioLogueado(usr);
					MenuPrincipalMesa menu = new MenuPrincipalMesa(s);
					menu.setVisible(true);
					frame.dispose();
				}
				
				
			}
		});
		btnIniciarSeccin.setBounds(89, 521, 222, 23);
		contentPane.add(btnIniciarSeccin);
	}
}