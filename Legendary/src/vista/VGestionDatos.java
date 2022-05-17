package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clases.Hardware;
import clases.Usuario;
import modelo.BDAImplementacion;
import modelo.ControladorDatos;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class VGestionDatos extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private ControladorDatos datos = new BDAImplementacion();
	private JComboBox comboBox;
	private ArrayList<Usuario> cargaremos = new ArrayList<>();

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 * @param usuario 
	 * 
	 * @param datos
	 * @param b
	 * @param menu
	 */
<<<<<<< HEAD
	public VGestionDatos(Menu ventanaPadre, boolean modal) {
		
=======
	public VGestionDatos(Menu ventanaPadre, boolean modal, Usuario usuario) {

>>>>>>> 45cbd6c424e1baf33e3b4e1011320ec704041541
		super(ventanaPadre);
		this.setModal(modal);
		setBounds(100, 100, 589, 319);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		comboBox = new JComboBox();

		comboBox.setBounds(43, 105, 326, 46);

		cargarUsuarios();

		contentPanel.add(comboBox);
<<<<<<< HEAD
		
		
		{
			JLabel lblNewLabel = new JLabel("En esta ventana podras borrar usuarios");
			lblNewLabel.setBounds(33, 50, 414, 24);
			contentPanel.add(lblNewLabel);
		}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    
=======

		JLabel lblNewLabel = new JLabel("En esta ventana podras borrar usuarios");
		lblNewLabel.setBounds(33, 50, 414, 24);
		contentPanel.add(lblNewLabel);
>>>>>>> 45cbd6c424e1baf33e3b4e1011320ec704041541
		
		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirmar = JOptionPane.showConfirmDialog(null,"Estas seguro de que quieres eliminar a este usuario?");

				if (JOptionPane.OK_OPTION == confirmar) {
					System.out.println(comboBox.getSelectedItem().toString().substring(0,9));
					datos.eliminarUsuario(comboBox.getSelectedItem().toString().substring(0,9));
					JOptionPane.showMessageDialog(null, "El usuario se ha borrado.");
				} else {
					JOptionPane.showMessageDialog(null, "El usuario no se ha borrado.");
				}
			}
		});
		btnBorrar.setFont(new Font("Algerian", Font.PLAIN, 20));
		btnBorrar.setBounds(95, 212, 129, 37);
		contentPanel.add(btnBorrar);

		JButton btnCancelBorrar = new JButton("Cancelar");
		btnCancelBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cerrarVentana();
			}
		});
		btnCancelBorrar.setFont(new Font("Algerian", Font.PLAIN, 20));
		btnCancelBorrar.setBounds(315, 212, 143, 37);
		contentPanel.add(btnCancelBorrar);
		
	}

	
	protected void cerrarVentana() {
		this.dispose();
		
	}


	private void cargarUsuarios() {
		cargaremos = datos.listarUsuarios();

		if (!cargaremos.isEmpty()) {
			for (Usuario cargando : cargaremos) {
				comboBox.addItem(cargando.getDni() + "   " + cargando.getNombre());
			}
			comboBox.setSelectedIndex(-1);
		}
	}

<<<<<<< HEAD
	protected void cargarRegistro(ControladorDatos datos) {
		String texto = (String) comboBox.getSelectedItem();
		String nombre = texto;
		
		
		ArrayList<Usuario> cargaremos;
		
		cargaremos = datos.listarUsuarios();
		
		Usuario cargando = new Usuario();
		for(Usuario u: cargaremos) {
			if(u.getNombre().equalsIgnoreCase(nombre)) {
				
				cargando = u;
			}
		}
		
		VRegistro ventanaRegistro = new VRegistro(this,true,cargando, datos);
		ventanaRegistro.setVisible(true);
	}
}
=======
//	protected void cargarRegistro() {
//		String texto = (String) comboBox.getSelectedItem();
//		String nombre = texto;
//
//		ArrayList<Usuario> cargaremos;
//
//		cargaremos = datos.listarUsuarios();
//
//		Usuario cargando = new Usuario();
//		for (Usuario u : cargaremos) {
//			if (u.getNombre().equalsIgnoreCase(nombre)) {
//
//				cargando = u;
//			}
//		}
//
//		VRegistro ventanaRegistro = new VRegistro(this, true, cargando);
//		ventanaRegistro.setVisible(true);
//	}
}
>>>>>>> 45cbd6c424e1baf33e3b4e1011320ec704041541
