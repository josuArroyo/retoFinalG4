package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Excepciones.ExceptionManager;
import clases.Hardware;
import clases.Usuario;
import modelo.BDAImplementacion;

import modelo.ControladorDatos;

import javax.swing.JTable;
import javax.swing.JComboBox;
import java.awt.Font;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;

/**
 * Esta clase gestiona la compra de Hardware
 * 
 * @author 1dam
 *
 */
public class VComprar extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JComboBox comboBox;
	private DefaultTableModel dtm;
	private JScrollPane scroll = new JScrollPane();
	ArrayList<Hardware> datosHardware;
	private JTable table = null;
	// private String dni;
	private String prueba;
	ControladorDatos datos = null;
	DefaultTableModel model;
	private JButton btnAniadir;
	private JButton btnNewButton;
	private JButton btnVer;

	/**
	 * Este es el constructor de la ventana
	 * 
	 * @param ventanaPadre
	 * @param modal
	 * @param usuario
	 */
	public VComprar(JDialog ventanaPadre, boolean modal, Usuario usuario) {
		super(ventanaPadre);
		this.setModal(modal);

		setBounds(100, 100, 672, 520);

		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		comboBox = new JComboBox();
		cargarTipoHardware();
		comboBox.setBounds(60, 22, 326, 46);
		contentPanel.add(comboBox);
		comboBox.setSelectedIndex(-1);

		btnNewButton = new JButton("VOLVER");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				volver();
			}
		});
		btnNewButton.setFont(new Font("Algerian", Font.PLAIN, 20));
		btnNewButton.setBounds(50, 408, 161, 46);
		contentPanel.add(btnNewButton);

		btnVer = new JButton("Ver");
		btnVer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				presentarTabla(usuario);
			}
		});
		btnVer.setBounds(453, 34, 89, 23);
		contentPanel.add(btnVer);

		btnAniadir = new JButton("A\u00D1ADIR");
		if (usuario.isEsAdmin()) {
			btnAniadir.setEnabled(true);
		} else
			btnAniadir.setEnabled(false);

		btnAniadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				cargarVCRUD(usuario);

			}
		});
		btnAniadir.setFont(new Font("Algerian", Font.PLAIN, 20));
		btnAniadir.setBounds(438, 408, 161, 46);
		contentPanel.add(btnAniadir);

	}

	/**
	 * Este metodo nos envia a la ventana de CRUDHardware
	 */
	protected void cargarVCRUD(Usuario usuario) {
		VCRUDHardware vcru = new VCRUDHardware(this, true, usuario);
		vcru.setVisible(true);

	}

	/**
	 * Este metodo nos cierra la ventana actual
	 */
	protected void volver() {

		this.dispose();

	}

	/**
	 * El metodo sirve para mostrar la tabla en la que se mostraran los datos
	 * @param usuario
	 */
	protected void presentarTabla(Usuario usuario) {
		//
		// table = new JTable();
		table = this.cargarTabla();
		scroll.setViewportView(table);

		contentPanel.add(scroll);
		scroll.setBounds(50, 171, 544, 186);

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				seleccionHardware(usuario);
			}
		});

	}

	/**
	 * El metodo nos envia a la ventana VCRUDHardware cuando seleccionas algun dato de la tabla
	 * @param usuario
	 */
	protected void seleccionHardware(Usuario usuario) {
		int row = table.getSelectedRow();
		String texto = (String) table.getValueAt(row, 0);
		int idHw = Integer.parseInt(texto);

		Hardware hardw = new Hardware();
		for (Hardware hardware : datosHardware) {
			if (hardware.getIdHW() == idHw) {
				hardw = hardware;
			}
		}
		VCRUDHardware crudhard = new VCRUDHardware(this, true, hardw, usuario);
		crudhard.setVisible(true);

	}

	/**
	 * Este metodo sirve para crear la tabla que mostraremos en la ventana
	 * @return JTable
	 */
	private JTable cargarTabla() {

		// Columnas
		String[] columnNames = { "id_hardware", "nombre", "precio", "marca", "tipo", "stock", "precio_coste" };
		String[] columna = new String[7];
		model = new DefaultTableModel(null, columnNames);
		model.setRowCount(0);

		try {
			datosHardware = datos.listarDatosHardware((String) comboBox.getSelectedItem());
		} catch (ExceptionManager e) {
			JOptionPane.showMessageDialog(this, e.getMessage(),"error al listar hardware", JOptionPane.ERROR_MESSAGE);
		}

		for (Hardware hard : datosHardware) {

			columna[0] = String.valueOf(hard.getIdHW());
			columna[1] = hard.getNombreHW();
			columna[2] = String.valueOf(hard.getPrecioHW());
			columna[3] = hard.getMarcaHW();
			columna[4] = hard.getTipoHW();
			columna[5] = String.valueOf(hard.getStockHW());
			columna[6] = String.valueOf(hard.getPrecioCosteHW());

			model.addRow(columna);
		}

		return new JTable(model);

	}

	/**
	 * El metodo sirva para cargar los datos de la base de datos en una comboBox
	 */
	private void cargarTipoHardware() {
		ArrayList<Hardware> cargaremos = null;
		datos = new BDAImplementacion();

		try {
			cargaremos = datos.listarTipoHardware();
		} catch (ExceptionManager e) {
			JOptionPane.showMessageDialog(this, e.getMessage(),"error al listar tipo hardware", JOptionPane.ERROR_MESSAGE);
		}
		for (Hardware cargando : cargaremos) {

			comboBox.addItem(cargando.getTipoHW());

		}

	}
}
