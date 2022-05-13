package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import clases.Torneo;
import clases.TorneoNoOficial;
import clases.TorneoOficial;
import modelo.BDAImplementacion;
import modelo.ControladorDatos;

import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class VTorneo extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JComboBox comboBox;
	private JButton btnCrear;
	private JScrollPane scroll = new JScrollPane();
	private ControladorDatos datos = null;
	private DefaultTableModel model;
	private ArrayList<Torneo> listaTorneos = new ArrayList<>();
	private String dni;

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
		this.datos = datos;
		this.dni = dni;
		setBounds(100, 100, 684, 464);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		

			

			JLabel lblNewLabel = new JLabel("Haz doble click sobre un torneo para ver mas inforamacion e inscribirte al mismo");
			lblNewLabel.setFont(new Font("Algerian", Font.PLAIN, 11));
			lblNewLabel.setBounds(50, 51, 567, 38);
			contentPanel.add(lblNewLabel);
		
		

			comboBox = new JComboBox();
			comboBox.setBounds(60, 106, 333, 38);
			contentPanel.add(comboBox);
			cargarCombo(datos);

		
		//Variable para compobar si es admin o no para deshabilitar o habilitar el boton de crear
		boolean found;
		
		btnCrear = 	new JButton("Crear");
		found = comprobarUsu(dni);
		if(!found) {
			btnCrear.setEnabled(found);
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
				presentarTabla();
			}
		});
		btnNewButton.setBounds(421, 114, 89, 23);
		contentPanel.add(btnNewButton);
			
	}
	
	
	protected void presentarTabla() {
		
		table = this.cargarTabla();
		scroll.setViewportView(table);
		
		contentPanel.add(scroll);
		scroll.setBounds(50, 171, 544, 186);
		
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				seleccionarTorneo(dni);
			}
		});
		
	}


	protected void seleccionarTorneo(String dni2) {
		int row =  table.getSelectedRow();
		String text = (String) table.getValueAt(row, 0);
		int id = Integer.parseInt(text);
		
		Torneo torne = new Torneo();
		for(Torneo tor : listaTorneos) {
			if(tor.getIdTorneo() == id) {
				torne = tor;
			}
		}
		
		int confirmar = JOptionPane.showConfirmDialog(null,"Estas seguro de que quieres inscribirte a este torneo?");
		
		if(JOptionPane.OK_OPTION == confirmar) {
			
			JOptionPane.showMessageDialog(null, "El usuario se ha borrado.");
			
		}else {
			JOptionPane.showMessageDialog(null, "El usuario no se ha borrado.");
		}
		
		
	}


	private JTable cargarTabla() {
		
		String[] columnNames = { "id_torneo", "nombre", "aforo", "juego", "direccion", "fecha", "tipo","Regla/Premio" };
		String[] columna = new String[8];
		model = null;
		model = new DefaultTableModel(null, columnNames);
		model.setRowCount(0);
		
		
		
		listaTorneos = datos.listarDatosTorneos((String)comboBox.getSelectedItem());
		
		for(Torneo tor : listaTorneos) {
			
			columna[0] = String.valueOf(tor.getIdTorneo());
			columna[1] = tor.getNombre();
			columna[2] = String.valueOf(tor.getAforo());
			columna[3] = tor.getJuego();
			columna[4] = tor.getDir();
			columna[5] = String.valueOf(tor.getFecha());
			columna[6] = tor.getTipo();
			if(tor instanceof TorneoOficial) {
				columna[7] = ((TorneoOficial) tor).getPremio();
			}else{
				columna [7] = ((TorneoNoOficial) tor).getReglas();
			}
			
			model.addRow(columna);
			
		}
		
		return new JTable(model);
	}


	protected void cargarAniadirTorneo() {
		
		VAniadirTorneo ventanaAniadirTor = new VAniadirTorneo(this,true,datos);
		ventanaAniadirTor.setVisible(true);
		
	}


	private void cargarCombo(ControladorDatos datos) {
		ArrayList<Torneo> listaTorneo = new ArrayList<>();
		
		listaTorneo = datos.listarJuegoTorneo();
		for(Torneo tor : listaTorneo) {
			comboBox.addItem(tor.getJuego());
		}
		
		comboBox.setSelectedIndex(-1);
		
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
