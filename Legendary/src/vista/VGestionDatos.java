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
	private ControladorDatos datos= new BDAImplementacion();
	private JComboBox comboBox;
	private ArrayList<Usuario> cargaremos = new ArrayList();
	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 * @param datos 
	 * @param b 
	 * @param menu 
	 */
	public VGestionDatos(Menu ventanaPadre, boolean modal) {
		
		super(ventanaPadre);
		this.setModal(modal);
		setBounds(100, 100, 589, 319);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		comboBox = new JComboBox();

		cargarUsuarios(datos);

		comboBox.setBounds(33, 97, 326, 46);

		contentPanel.add(comboBox);
		
		
		{
			JLabel lblNewLabel = new JLabel("En esta ventana podras borrar usuarios");
			lblNewLabel.setBounds(33, 50, 414, 24);
			contentPanel.add(lblNewLabel);
		}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    
		
		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirmar = JOptionPane.showConfirmDialog(null,"Estas seguro de que quieres eliminar a este usuario?");
				
				if(JOptionPane.OK_OPTION == confirmar) {
					JOptionPane.showMessageDialog(null, "El usuario se ha borrado.");
				}else {
					JOptionPane.showMessageDialog(null, "El usuario no se ha borrado.");
				}
			}
		});
		btnBorrar.setFont(new Font("Algerian", Font.PLAIN, 20));
		btnBorrar.setBounds(33, 212, 129, 37);
		contentPanel.add(btnBorrar);
		
		JButton btnCancelBorrar = new JButton("Cancelar");
		btnCancelBorrar.setFont(new Font("Algerian", Font.PLAIN, 20));
		btnCancelBorrar.setBounds(391, 212, 143, 37);
		contentPanel.add(btnCancelBorrar);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarRegistro(datos);
			}
		});
		btnModificar.setFont(new Font("Algerian", Font.PLAIN, 20));
		btnModificar.setBounds(200, 212, 143, 37);
		contentPanel.add(btnModificar);
	}

	private void cargarUsuarios(ControladorDatos datos) {
		ArrayList<Usuario> cargaremos;

		cargaremos = datos.listarUsuarios();

		if (!cargaremos.isEmpty()) {
			for (Usuario cargando : cargaremos) {
				comboBox.addItem(cargando.getNombre());
			}
			comboBox.setSelectedIndex(-1);
		}
	}

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