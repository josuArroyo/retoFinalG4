package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Excepciones.ExceptionManager;
import clases.Usuario;

import modelo.BDAImplementacion;

import modelo.ControladorDatos;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Color;

/**
 * Esta clase es para gestionar el login
 * @author 1dam
 *
 */
public class VLogin extends JFrame {

	private JPanel contentPane;
	private JTextField textDni;
	private JPasswordField password;
	private JButton btnLogin;
	private JButton btnRegis;
	private String dni;
	private String pass;
	private Usuario usuario;

	private ControladorDatos datos = new BDAImplementacion();
	
	
	/**
	 * Este es el constructor de la ventana
	 */
	public VLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 482, 334);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDni = new JLabel("DNI");
		lblDni.setFont(new Font("Algerian", Font.PLAIN, 23));
		lblDni.setBounds(24, 21, 46, 40);
		contentPane.add(lblDni);
		
		textDni = new JTextField();
		textDni.setBounds(24, 72, 364, 31);
		contentPane.add(textDni);
		textDni.setColumns(10);
		
		JLabel lblContra = new JLabel("Contrase\u00F1a");
		lblContra.setFont(new Font("Algerian", Font.PLAIN, 23));
		lblContra.setBounds(24, 112, 150, 40);
		contentPane.add(lblContra);
		
		btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Algerian", Font.PLAIN, 20));
		btnLogin.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 dni = textDni.getText().toString();
				 pass = String.valueOf(password.getPassword());
				 comprobar(dni,pass);
			}
		});
		btnLogin.setBounds(272, 225, 140, 59);
		contentPane.add(btnLogin);
		
		btnRegis = new JButton("REGISTRARSE");
		btnRegis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarRegistro();
			}
		});
		btnRegis.setFont(new Font("Algerian", Font.PLAIN, 20));
		btnRegis.setBounds(42, 226, 180, 59);
		contentPane.add(btnRegis);
		
		password = new JPasswordField();
		password.setBounds(24, 163, 364, 31);
		contentPane.add(password);
	}


	/**
	 * Este metodo nos cargara la ventana de Registro
	 */
	protected void cargarRegistro() {
		VRegistro registro= new VRegistro(this,true,usuario);
		registro.setVisible(true);
		
	}

	/**
	 * Este metodo comprueba que el usuario exista en la base de datos
	 * @param dni
	 * @param pass
	 */
	protected void comprobar( String dni, String pass) {
		
		boolean Registro = false;
		 dni = textDni.getText().toString();
		 pass = String.valueOf(password.getPassword());
		
		try {
			usuario = datos.buscarUsuario(dni, pass);
		} catch (ExceptionManager e) {
			JOptionPane.showMessageDialog(this, e.getMessage(),"Datos incorrectos", JOptionPane.ERROR_MESSAGE);
		}
		if( usuario != null) {
			
			Menu menu = new Menu(this, true, usuario);

			menu.setVisible(true);
			textDni.setText("");
			password.setText("");
			
		}else {

			JOptionPane.showMessageDialog(null, "Usuario o Contrase?a incorrectas.","Datos incorrectos", JOptionPane.ERROR_MESSAGE);
			textDni.setText("");
			password.setText("");	
		}
		
		
	}
}
