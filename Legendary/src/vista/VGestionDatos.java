package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Excepciones.ExceptionManager;
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

/**
 * Esta clase sirve para Borrar usuarios
 * @author 1dam
 *
 */
public class VGestionDatos extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private ControladorDatos datos = new BDAImplementacion();
	private JComboBox comboBox;
	private ArrayList<Usuario> cargaremos = new ArrayList<>();
	
	/**
	 * Este el constructor de la ventana
	 * @param ventanaPadre
	 * @param modal
	 * @param usuario
	 */
	public VGestionDatos(Menu ventanaPadre, boolean modal, Usuario usuario) {
		
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

		JLabel lblNewLabel = new JLabel("En esta ventana podras borrar usuarios");
		lblNewLabel.setBounds(33, 50, 414, 24);
		contentPanel.add(lblNewLabel);
		
		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int confirmar = JOptionPane.showConfirmDialog(null,"Estas seguro de que quieres eliminar a este usuario?");
				
				if (JOptionPane.OK_OPTION == confirmar) {
					cerrarVentana();

					
					try {
						datos.eliminarUsuario(comboBox.getSelectedItem().toString().substring(0,9));
					} catch (ExceptionManager e1) {
						excepciones(e1);
					}
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

	protected void excepciones(ExceptionManager e1) {
		JOptionPane.showMessageDialog(this, e1.getMessage(),"error al borrar", JOptionPane.ERROR_MESSAGE);		
	}


/**
 * El Metodo se usa para cerrar la ventana actual
 */
	protected void cerrarVentana() {
		this.dispose();
		
	}

	/**
	 * Este metodo es para cargar la informacion de los usuarios en una comboBox
	 */
	private void cargarUsuarios() {
		try {
			cargaremos = datos.listarUsuarios();
		} catch (ExceptionManager e) {
			JOptionPane.showMessageDialog(this, e.getMessage(),"error al listar", JOptionPane.ERROR_MESSAGE);
		}

		if (!cargaremos.isEmpty()) {
			for (Usuario cargando : cargaremos) {
				comboBox.addItem(cargando.getDni() + "   " + cargando.getNombre());
			}
			comboBox.setSelectedIndex(-1);
		}
	}

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
