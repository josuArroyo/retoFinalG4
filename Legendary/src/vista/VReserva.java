package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Excepciones.ExceptionManager;
import clases.Reserva;
import clases.Usuario;
import modelo.BDAImplementacion;
import modelo.ControladorDatos;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;
import java.awt.event.ActionEvent;

/**
 * Esta clase sirve para gestionar las reservas
 * @author 1dam
 *
 */
public class VReserva extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFIni;
	private JTextField textFFin;
	private JTextField textNumPlaza;
	private ControladorDatos datos = new BDAImplementacion();

	
	/**
	 * Este es constructor de la ventana
	 * @param ventanaPadre
	 * @param modal
	 * @param usuario
	 */
	public VReserva(Menu ventanaPadre, boolean modal, Usuario usuario) {
		super(ventanaPadre);
		this.setModal(modal);
		setBounds(100, 100, 534, 376);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lbltexto = new JLabel("El precio de la reserva de la plaza es un total de 24\u20AC");
		lbltexto.setBounds(124, 53, 348, 32);
		contentPanel.add(lbltexto);
		
		JLabel lblFIni = new JLabel("Fecha Inicio");
		lblFIni.setFont(new Font("Algerian", Font.PLAIN, 20));
		lblFIni.setBounds(177, 96, 161, 32);
		contentPanel.add(lblFIni);
		
		textFIni = new JTextField();
		textFIni.setBounds(112, 139, 265, 32);
		contentPanel.add(textFIni);
		textFIni.setColumns(10);
		
		JLabel lblFFin = new JLabel("Fecha Fin");
		lblFFin.setFont(new Font("Algerian", Font.PLAIN, 20));
		lblFFin.setBounds(177, 182, 161, 32);
		contentPanel.add(lblFFin);
		
		textFFin = new JTextField();
		textFFin.setColumns(10);
		textFFin.setBounds(112, 225, 265, 32);
		contentPanel.add(textFFin);
		
		JButton btnReservar = new JButton("RESERVAR");
		btnReservar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean bien;
				bien = comprobar();
				
				if (bien) {
					reservarPlaza(usuario);
				}
				
				
			}
		});
		btnReservar.setFont(new Font("Algerian", Font.PLAIN, 20));
		btnReservar.setBounds(263, 277, 209, 49);
		contentPanel.add(btnReservar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cerrarVentana();
			}
		});
		btnVolver.setFont(new Font("Algerian", Font.PLAIN, 20));
		btnVolver.setBounds(44, 277, 209, 49);
		contentPanel.add(btnVolver);
		
		textNumPlaza = new JTextField();
		textNumPlaza.setEditable(false);
		textNumPlaza.setBounds(33, 139, 39, 26);
		contentPanel.add(textNumPlaza);
		textNumPlaza.setColumns(10);
		
		JLabel lblplaza = new JLabel("id plaza");
		lblplaza.setBounds(33, 176, 46, 14);
		contentPanel.add(lblplaza);
		
		rellenarPlaza();
	}

	/**
	 * Es un metodo que nos enseña el id de la plaza disponible
	 */
	private void rellenarPlaza() {
		String id_Plaza = "1";
		try {
			id_Plaza = String.valueOf(datos.traerIDPlaza());
		} catch (ExceptionManager e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(id_Plaza.equals(0)) {
			
		}else {
			textNumPlaza.setText(id_Plaza);
		}
		
	}

	/**
	 * El metodo lo usaremos para realizar la reserva de una plaza en nuestro local
	 * @param usuario
	 */
	protected void reservarPlaza(Usuario usuario) {
		
		Reserva rev = new Reserva();
		Date fecha;
		boolean found;
		
		rev.setId_Plaza(Integer.valueOf(textNumPlaza.getText()));
		rev.setDni(usuario.getDni());
		rev.setFecha_ini(Date.valueOf(textFIni.getText()));
		rev.setFecha_fin(Date.valueOf(textFFin.getText()));
		try {
			datos.reservarPlaza(rev);
		} catch (ExceptionManager e) {
			JOptionPane.showMessageDialog(this, e.getMessage(),"error al reservar la  plaza", JOptionPane.ERROR_MESSAGE);
		}
		textFFin.setText(null);
		textFIni.setText(null);
		JOptionPane.showMessageDialog(null, "Reserva completada");
		this.dispose();
		
	}

	/**
	 * El metodo comprueba si las fechas son identicas, si hay alguna fecha que sea anterior al dia actual, si la fecha de fin es anterior a la fecha inicio y si hay plazas disponibles
	 * @return boolean
	 */
	protected boolean comprobar() {
		boolean bien = true;
		
		if (textFFin.getText().equalsIgnoreCase(textFIni.getText())) {
			JOptionPane.showMessageDialog(null, "Las fechas son idénticas, introduce unas nuevas");
			textFFin.setText(null);
			textFIni.setText(null);
			bien = false;
		}
		else if (LocalDate.parse(textFIni.getText()).isBefore( LocalDate.now()) || LocalDate.parse(textFFin.getText()).isBefore( LocalDate.now())) {
			JOptionPane.showMessageDialog(null, "La fecha de inicio y/o la fecha de fin son anteriores a la fecha actual, introduce unas nuevas ");
			textFFin.setText(null);
			textFIni.setText(null);
			bien = false;
		}
		else if (LocalDate.parse(textFFin.getText()).isBefore(LocalDate.parse(textFIni.getText()))) {
			JOptionPane.showMessageDialog(null, "La fecha de fin de reserva es anterior a la de inicio, vuelva a introducirlas");
			textFFin.setText(null);
			textFIni.setText(null);
			bien = false;
		}else if(Integer.valueOf(textNumPlaza.getText()) == 0){
			JOptionPane.showMessageDialog(null, "No hay plazas disponible es estos momentos, lo sentimos");
			textFFin.setText(null);
			textFIni.setText(null);
			bien = false;
		}
		BDAImplementacion impl = new BDAImplementacion();
		return bien;
	}

	protected void cerrarVentana() {
		this.dispose();		
	}
}
