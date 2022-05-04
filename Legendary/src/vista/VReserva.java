package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

public class VReserva extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFIni;
	private JTextField textFFin;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public VReserva() {
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
		btnReservar.setFont(new Font("Algerian", Font.PLAIN, 20));
		btnReservar.setBounds(263, 277, 209, 49);
		contentPanel.add(btnReservar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Algerian", Font.PLAIN, 20));
		btnVolver.setBounds(44, 277, 209, 49);
		contentPanel.add(btnVolver);
	}

}
