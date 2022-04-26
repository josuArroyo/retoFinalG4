package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;

public class Menu extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Menu dialog = new Menu();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Menu() {
		setBounds(100, 100, 768, 538);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JButton btnComprar = new JButton("Comprar");
			btnComprar.setFont(new Font("Algerian", Font.PLAIN, 20));
			btnComprar.setBounds(84, 66, 258, 142);
			contentPanel.add(btnComprar);
		}
		{
			JButton btnTorneo = new JButton("Torneo");
			btnTorneo.setFont(new Font("Algerian", Font.PLAIN, 20));
			btnTorneo.setBounds(415, 66, 258, 142);
			contentPanel.add(btnTorneo);
		}
		{
			JButton btnDatos = new JButton("Datos");
			btnDatos.setFont(new Font("Algerian", Font.PLAIN, 20));
			btnDatos.setBounds(84, 283, 258, 142);
			contentPanel.add(btnDatos);
		}
		{
			JButton btnReservar = new JButton("Reservar");
			btnReservar.setFont(new Font("Algerian", Font.PLAIN, 20));
			btnReservar.setBounds(415, 283, 258, 142);
			contentPanel.add(btnReservar);
		}
	}

}
