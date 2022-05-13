package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import clases.Hardware;
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

public class VComprar extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JComboBox comboBox;
	private DefaultTableModel dtm;
	private JScrollPane scroll = new JScrollPane();
	ArrayList<Hardware> datosHardware;
	private JTable table = null;
	private String dni;
	private String prueba;
	ControladorDatos datos = null;
	DefaultTableModel model;
	

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 * 
	 * @param modal
	 * @param ventanaPadre
	 * @param datos2 
	 * @param dni 
	 */
	public VComprar(JDialog ventanaPadre, boolean modal, String dni, ControladorDatos datos) {
		
		super(ventanaPadre);
		this.setModal(modal);
		this.dni=dni;
		this.datos = datos;
		
		setBounds(100, 100, 672, 520);

		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		comboBox = new JComboBox();
		cargarTipoHardware(datos);
		comboBox.setBounds(60, 22, 326, 46);
		contentPanel.add(comboBox);

		JButton btnNewButton = new JButton("VOLVER");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				volver();
			}
		});
		btnNewButton.setFont(new Font("Algerian", Font.PLAIN, 20));
		btnNewButton.setBounds(50, 408, 161, 46);
		contentPanel.add(btnNewButton);

		JButton btnVer = new JButton("Ver");
		btnVer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				presentarTabla();
			}
		});
		btnVer.setBounds(453, 34, 89, 23);
		contentPanel.add(btnVer);

	}

	protected void volver() {

		this.dispose();

	}

	protected void presentarTabla() {
		//
		// table = new JTable();
		table = this.cargarTabla();
		scroll.setViewportView(table);

		contentPanel.add(scroll);
		scroll.setBounds(50, 171, 544, 186);

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				seleccionHardware(dni);
			}
		});

	}

	protected void seleccionHardware(String dni2) {
		int row = table.getSelectedRow();
		String texto = (String) table.getValueAt(row, 0);
		int idHw = Integer.parseInt(texto);

		Hardware hardw = new Hardware();
		for (Hardware hardware : datosHardware) {
			if (hardware.getIdHW() == idHw) {
				hardw = hardware;
			}
		}
		VCRUDHardware crudhard = new VCRUDHardware(this, true, datos, hardw, dni2);
		crudhard.setVisible(true);

	}

	private JTable cargarTabla() {

		// Columnas
		String[] columnNames = { "id_hardware", "nombre", "precio", "marca", "tipo", "stock", "precio_coste" };
		String[] columna = new String[7];
		model = null;
		model = new DefaultTableModel(null, columnNames);
		model.setRowCount(0);

		datosHardware = datos.listarDatosHardware((String) comboBox.getSelectedItem());

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

	private void cargarTipoHardware(ControladorDatos datos) {
		ArrayList<Hardware> cargaremos;

		cargaremos = datos.listarTipoHardware();
		for (Hardware cargando : cargaremos) {

			comboBox.addItem(cargando.getTipoHW());

		}

		comboBox.setSelectedIndex(-1);

	}
}
