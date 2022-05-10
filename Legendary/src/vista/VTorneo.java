package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.ControladorDatos;

import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VTorneo extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			VTorneo dialog = new VTorneo();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 * @param ventanaPadre 
	 * @param datos 
	 * @param modal 
	 * @param dni 
	 */
	
	//
	
	public VTorneo(Menu ventanaPadre, boolean modal, ControladorDatos datos, String dni) {
		super(ventanaPadre);
		this.setModal(modal);
		setBounds(100, 100, 684, 464);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
			table = new JTable();
			table.setBounds(50, 171, 544, 186);
			contentPanel.add(table);
		
		
			JLabel lblNewLabel = new JLabel("Haz doble click sobre un torneo para ver mas inforamacion e inscribirte al mismo");
			lblNewLabel.setFont(new Font("Algerian", Font.PLAIN, 11));
			lblNewLabel.setBounds(50, 51, 567, 38);
			contentPanel.add(lblNewLabel);
		
		
			JComboBox comboBox = new JComboBox();
			comboBox.setBounds(60, 106, 333, 38);
			contentPanel.add(comboBox);
		
		
		//Variable para compobar si es admin o no para deshabilitar o habilitar el boton de crear
		boolean found;
		
		JButton btnCrear = new JButton("Crear");
		found = comprobarUsu(dni);
		if(!found) {
			btnCrear.setEnabled(found);
		}
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VAniadirTorneo ventanaAniadirTor = new VAniadirTorneo();
				ventanaAniadirTor.setVisible(true);
			}	
		});
		btnCrear.setFont(new Font("Algerian", Font.PLAIN, 20));
		btnCrear.setBounds(426, 371, 168, 31);
		contentPanel.add(btnCrear);
			
	}
	
	
	//Metodo que devuelve un boolean que comprueba si el usuario logeado es un admin o un usuario diferente
	private boolean comprobarUsu(String dni) {
		boolean found;
		
		if(dni.equalsIgnoreCase("Admin")) {
			found = true;
		}else {
			found = false;
		}
		
		return found;
		
	}
}
