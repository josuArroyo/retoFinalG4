package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.BDAImplementacion;
import modelo.ControladorDatos;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Menu extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private ControladorDatos datos = new BDAImplementacion();

	/**
	 * Launch the application.
	 */
	/**
	 * Create the dialog.


	 * @param modal 
	 * @param datos 
	 * @param dni 
	 */


	private JButton btnComprar;
	public Menu(JFrame ventanaPadre, boolean modal, ControladorDatos datos) {
		super(ventanaPadre);
		this.setModal(modal);
		
		setBounds(100, 100, 768, 538);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{

			btnComprar = new JButton("Comprar");
			btnComprar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					comprar(datos);

				}
			});
			btnComprar.setFont(new Font("Algerian", Font.PLAIN, 20));
			btnComprar.setBounds(84, 66, 258, 142);
			contentPanel.add(btnComprar);
		}
		{
			JButton btnTorneo = new JButton("Torneo");
			btnTorneo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {


					cargarTorneo(dni);
          
				}
			});
			btnTorneo.setFont(new Font("Algerian", Font.PLAIN, 20));
			btnTorneo.setBounds(415, 66, 258, 142);
			contentPanel.add(btnTorneo);
		}
		{
			JButton btnDatos = new JButton("Datos");
			btnDatos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cargarDatos();
				}
			});
			btnDatos.setFont(new Font("Algerian", Font.PLAIN, 20));
			btnDatos.setBounds(84, 283, 258, 142);
			contentPanel.add(btnDatos);
		}
		{
			JButton btnReservar = new JButton("Reservar");
			btnReservar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cargarReserva();
				}
			});
			btnReservar.setFont(new Font("Algerian", Font.PLAIN, 20));
			btnReservar.setBounds(415, 283, 258, 142);
			contentPanel.add(btnReservar);
		}
	}
	protected void comprar(ControladorDatos datos) {
		VComprar comprar = new VComprar(this, true, datos);
		comprar.setVisible(true);
		
	}

	protected void cargarDatos() {
		VGestionDatos ventanaDatos = new VGestionDatos(this,true,datos);
		ventanaDatos.setVisible(true);
		
	}

	protected void cargarReserva() {
		VReserva ventanaReserva = new VReserva(this,true,datos);
		ventanaReserva.setVisible(true);
		
	}

	protected void cargarTorneo(String dni) {
		VTorneo ventanaTorneo = new VTorneo(this,true, datos,dni);
		ventanaTorneo.setVisible(true);
		
	}

}
