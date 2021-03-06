package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clases.Usuario;
import modelo.BDAImplementacion;
import modelo.ControladorDatos;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Esta clase gestiona el menu de la aplicacion
 * 
 * @author 1dam
 *
 */
public class Menu extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton btnComprar;

	/**
	 * Es el constructor de la ventana
	 * @param ventanaPadre
	 * @param modal
	 * @param usuario
	 */
	
	public Menu(JFrame ventanaPadre, boolean modal, Usuario usuario) {
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
					
					comprar(usuario);

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


					cargarTorneo(usuario);
          
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
					cargarDatos(usuario);
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
					cargarReserva(usuario);
				}
			});
			btnReservar.setFont(new Font("Algerian", Font.PLAIN, 20));
			btnReservar.setBounds(415, 283, 258, 142);
			contentPanel.add(btnReservar);
		}
	}
	/**
	 * El metodo nos envia a la ventana de Comprar
	 * @param usuario
	 */
	protected void comprar(Usuario usuario) {
		VComprar comprar = new VComprar(this, true , usuario);
		comprar.setVisible(true);
		
	}

	/**
	 * El metodo dependiendo de si entra un usuario o un admin enviara 
	 * a la ventana de gestion datos o a la ventana de Registro
	 * @param usuario
	 */
	protected void cargarDatos(Usuario usuario) {
		if(usuario.isEsAdmin()) {
			VGestionDatos ventanaDatos = new VGestionDatos(this,true,usuario);
			ventanaDatos.setVisible(true);
		}else {
			VRegistro ventanaDatos = new VRegistro(this,true,usuario);
			ventanaDatos.setVisible(true);
		}
		
		
	}

	/**
	 * El metodo nos enviara a la ventana de Reserva
	 * @param usuario
	 */
	protected void cargarReserva(Usuario usuario) {
		VReserva ventanaReserva = new VReserva(this,true,usuario);
		ventanaReserva.setVisible(true);
		
	}

	/**
	 * El metodo nos envia a la ventana de Torneo
	 * @param usuario
	 */
	protected void cargarTorneo(Usuario usuario) {
		VTorneo ventanaTorneo = new VTorneo(this,true,usuario);
		ventanaTorneo.setVisible(true);
		
	}

}