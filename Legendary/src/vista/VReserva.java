package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

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

public class VReserva extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFIni;
	private JTextField textFFin;
	private JTextField textField_numPlaza;
	private ControladorDatos datos = new BDAImplementacion();


	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 * @param datos 
	 * @param modal 
	 * @param ventanaPadre 
	 * @param dni 
	 */
	public VReserva(Menu ventanaPadre, boolean modal, ControladorDatos datos, String dni) {
		super(ventanaPadre);
		this.setModal(modal);
		setBounds(100, 100, 534, 376);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lbltexto = new JLabel("El precio de la reserva de la plaza es un total de 24\u20AC");
		lbltexto.setBounds(112, 11, 348, 32);
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
				Comprobar();
				ReservarPlaza(dni);
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
		
		textField_numPlaza = new JTextField();
		textField_numPlaza.setEditable(false);
		textField_numPlaza.setBounds(44, 145, 29, 20);
		contentPanel.add(textField_numPlaza);
		textField_numPlaza.setColumns(10);
		
		JLabel lbl_num_plaza = new JLabel("id plaza");
		lbl_num_plaza.setBounds(41, 165, 44, 14);
		contentPanel.add(lbl_num_plaza);
		rellenarPlaza();
	}

	private void rellenarPlaza() {
		String id_plaza = String.valueOf(datos.traerIDPlaza());
		if(id_plaza.equals(0)) {
			
		}else
		textField_numPlaza.setText(id_plaza);
	}

	protected void Comprobar() {
		if (textFFin.getText().equalsIgnoreCase(textFIni.getText())) {
			JOptionPane.showMessageDialog(null, "Las fechas son idénticas, introduce unas nuevas");
			textFFin.setText(null);
			textFIni.setText(null);
		}
		else if (LocalDate.parse(textFIni.getText()).isBefore( LocalDate.now()) || LocalDate.parse(textFFin.getText()).isBefore( LocalDate.now())) {
			JOptionPane.showMessageDialog(null, "La fecha de inicio y/o la fecha de fin son anteriores a la fecha actual, introduce unas nuevas ");
			textFFin.setText(null);
			textFIni.setText(null);
		}
		else if (LocalDate.parse(textFFin.getText()).isBefore(LocalDate.parse(textFIni.getText()))) {
			JOptionPane.showMessageDialog(null, "La fecha de fin de reserva es anterior a la de inicio, vuelva a introducirlas");
			textFFin.setText(null);
			textFIni.setText(null);
		}
		else if (Integer.valueOf(textField_numPlaza.getText()) == 0) {
			JOptionPane.showMessageDialog(null, "No hay plazas disponibles en estos momentos, lo sentimos");
			textFFin.setText(null);
			textFIni.setText(null);
		}
		BDAImplementacion impl = new BDAImplementacion();
	}
	
	protected void ReservarPlaza(String dni) {

		Reserva rev = new Reserva();
		Date fecha;
		boolean found;

		rev.setId_Plaza(Integer.valueOf(textField_numPlaza.getText()));
		rev.setDni(dni);
		rev.setFecha_ini(Date.valueOf(textFIni.getText()));
		rev.setFecha_fin(Date.valueOf(textFIni.getText()));
		datos.reservarPlaza(rev);
		textFFin.setText(null);
		textFIni.setText(null);
		JOptionPane.showMessageDialog(null, "Reserva completada");
		this.dispose();
	}

	protected void cerrarVentana() {
		this.dispose();		
	}
}
