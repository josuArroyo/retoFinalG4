package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Excepciones.ExceptionManager;
import clases.Torneo;
import clases.TorneoNoOficial;
import clases.TorneoOficial;
import clases.Usuario;
import modelo.BDAImplementacion;
import modelo.ControladorDatos;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class VTorneo extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JComboBox comboBox;
	private JButton btnCrear;
	private JScrollPane scroll = new JScrollPane();
	private ControladorDatos datos = new BDAImplementacion();
	private DefaultTableModel model;
	private ArrayList<Torneo> listaTorneos = new ArrayList<>();

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
	 * 
	 * @param ventanaPadre
	 * @param modal
	 * @param usuario
	 * @param dni
	 */

	//

	public VTorneo(Menu ventanaPadre, boolean modal, Usuario usuario) {
		super(ventanaPadre);
		this.setModal(modal);
		setBounds(100, 100, 684, 464);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel(
				"Haz doble click sobre un torneo para ver mas inforamacion e inscribirte al mismo");
		lblNewLabel.setFont(new Font("Algerian", Font.PLAIN, 11));
		lblNewLabel.setBounds(50, 51, 567, 38);
		contentPanel.add(lblNewLabel);

		comboBox = new JComboBox();
		comboBox.setBounds(60, 106, 333, 38);
		contentPanel.add(comboBox);
		cargarCombo();

		// Variable para compobar si es admin o no para deshabilitar o habilitar el
		// boton de crear
		boolean found;

		JButton btnCrear = new JButton("Crear");
		// found = comprobarUsu(usuario);
		if (!usuario.isEsAdmin()) {
			btnCrear.setEnabled(usuario.isEsAdmin());
		}
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarAniadirTorneo();
			}
		});
		btnCrear.setFont(new Font("Algerian", Font.PLAIN, 20));
		btnCrear.setBounds(426, 371, 168, 31);
		contentPanel.add(btnCrear);

		JButton btnNewButton = new JButton("Ver");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				presentarTabla(usuario);
			}
		});
		btnNewButton.setBounds(421, 114, 89, 23);
		contentPanel.add(btnNewButton);

	}

	protected void presentarTabla(Usuario usuario) {

		table = this.cargarTabla();
		scroll.setViewportView(table);

		contentPanel.add(scroll);
		scroll.setBounds(50, 171, 544, 186);

		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				seleccionarTorneo(usuario);
			}
		});

	}

	protected void seleccionarTorneo(Usuario usuario) {
		int row = table.getSelectedRow();
		String text = (String) table.getValueAt(row, 0);
		int id = Integer.parseInt(text);

		Torneo torne = new Torneo();
		for (Torneo tor : listaTorneos) {
			if (tor.getIdTorneo() == id) {
				torne = tor;
			}
		}

		int confirmar = JOptionPane.showConfirmDialog(null, "Estas seguro de que quieres inscribirte a este torneo?");

		if (JOptionPane.OK_OPTION == confirmar) {

			try {
				datos.inscribirse(usuario, torne);
			} catch (ExceptionManager e) {
				JOptionPane.showMessageDialog(this, e.getMessage(),"error al inscribirse torneo", JOptionPane.ERROR_MESSAGE);
			}
			JOptionPane.showMessageDialog(null, "Te has inscrito al torneo.");
			// sdf

		} else {
			JOptionPane.showMessageDialog(null, "No te has inscrito al torneo.");
		}

	}

	private JTable cargarTabla() {

		String[] columnNames = { "id_torneo", "nombre", "aforo", "juego", "direccion", "fecha", "tipo",
				"Regla/Premio" };
		String[] columna = new String[8];
		model = null;
		model = new DefaultTableModel(null, columnNames);
		model.setRowCount(0);

		try {
			listaTorneos = datos.listarDatosTorneos((String) comboBox.getSelectedItem());
		} catch (ExceptionManager e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "error al cargar torneo", JOptionPane.ERROR_MESSAGE);
		}

		for (Torneo tor : listaTorneos) {

			columna[0] = String.valueOf(tor.getIdTorneo());
			columna[1] = tor.getNombre();
			columna[2] = String.valueOf(tor.getAforo());
			columna[3] = tor.getJuego();
			columna[4] = tor.getDir();
			columna[5] = String.valueOf(tor.getFecha());
			columna[6] = tor.getTipo();
			if (tor instanceof TorneoOficial) {
				columna[7] = ((TorneoOficial) tor).getPremio();
			} else {
				columna[7] = ((TorneoNoOficial) tor).getRegla();
			}

			model.addRow(columna);

		}

		return new JTable(model);
	}

	protected void cargarAniadirTorneo() {

		VAniadirTorneo ventanaAniadirTor = new VAniadirTorneo(this, true);
		ventanaAniadirTor.setVisible(true);

	}

	private void cargarCombo() {
		ArrayList<Torneo> listaTorneo = new ArrayList<>();

		try {
			listaTorneo = datos.listarJuegoTorneo();
		} catch (ExceptionManager e) {
			JOptionPane.showMessageDialog(this, e.getMessage(),"error al cargar torneo", JOptionPane.ERROR_MESSAGE);
		}

		for (Torneo tor : listaTorneo) {
			comboBox.addItem(tor.getJuego());
		}

		comboBox.setSelectedIndex(-1);

	}
}
