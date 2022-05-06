package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import clases.Hardware;
import modelo.ControladorDatos;

import javax.swing.JTable;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Set;
import java.awt.Cursor;

public class VComprar extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JComboBox comboBox;
	private ArrayList <Hardware> datosHardware;
	private DefaultTableModel dtm;
	private JScrollPane scroll;
	

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the dialog.
	 * @param datos 
	 */
	public VComprar(ControladorDatos datos) {
		setBounds(100, 100, 696, 513);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		table = new JTable();
		table.setBounds(50, 150, 591, 247);
		
		
		
		comboBox = new JComboBox();
		comboBox.setBounds(71, 70, 326, 46);
		contentPanel.add(comboBox);
		CargarTipoHW(datos);
		
		JButton btnNewButton = new JButton("VOLVER");
		btnNewButton.setFont(new Font("Algerian", Font.PLAIN, 20));
		btnNewButton.setBounds(50, 408, 161, 46);
		contentPanel.add(btnNewButton);
		cargarTabla(datos);
	}

	
	
	private void cargarTabla(ControladorDatos datos) {
		
		String[] cabeceras = { "id_hardware", "nombre", "precio", "marca", "tipo", "stock", "precio_coste" };
		String[] fila = new String[7];
		String tipo;

		dtm = new DefaultTableModel(null, cabeceras);
		
		
		
		

		datosHardware =datos.listarDatosHardware((String) comboBox.getSelectedItem());
	
		for (int i = 0; i < datosHardware.size(); i++) {
			fila[0] = String.valueOf(datosHardware.get(i).getIdHW());
			fila[1] = datosHardware.get(i).getNombreHW();
			fila[2] = String.valueOf(datosHardware.get(i).getPrecioHW());
			fila[3] = datosHardware.get(i).getMarcaHW();
			fila[4] = datosHardware.get(i).getTipoHW();
			fila[5] = String.valueOf(datosHardware.get(i).getStockHW());
			fila[6] = String.valueOf(datosHardware.get(i).getPrecioCosteHW());

			dtm.addRow(fila);
		}

		table = new JTable(dtm);
//		table.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//
//				// aqui hacemos lo que quiera cuando clique
//			}
//		});
		
		scroll = new JScrollPane(table);
		scroll.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		table.setBounds(50, 150, 591, 247);
		scroll.setViewportView(table);
		scroll.setBounds(50, 150, 591, 247);

		contentPanel.add(scroll);

	}



	private void CargarTipoHW(ControladorDatos datos) {
		ArrayList<Hardware> cargar;
		
		cargar = datos.listarTipoHardWare();
		
		for(Hardware cargando : cargar) {
			comboBox.addItem(cargando.getTipoHW());
		}
		
		comboBox.setSelectedIndex(1);
	}
}
