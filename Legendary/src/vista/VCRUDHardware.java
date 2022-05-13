package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;

import clases.Factura;
import clases.Hardware;
import clases.Usuario;
import modelo.BDAImplementacion;
import modelo.ControladorDatos;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;

public class VCRUDHardware extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textId;
	private JTextField textNombre;
	private JTextField textPrecio;
	private JTextField textMarca;
	private JTextField textTipo;
	private JTextField textStock;
	ControladorDatos datos = new BDAImplementacion();
	private JTextField textCantidad;
	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the dialog.
	 * @param datos 
	 * @param hardw 
	 * @param b 
	 * @param vComprar 
	 */
	public VCRUDHardware(VComprar ventanaPadre, boolean modal, ControladorDatos datos, Hardware hardw,String dni) {
		super(ventanaPadre); 
		this.setModal(modal);
		setBounds(100, 100, 613, 551);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "HARDWARE", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setFont(new Font("Algerian", Font.PLAIN, 20));
		lblNewLabel.setBounds(48, 43, 46, 14);
		contentPanel.add(lblNewLabel);
		
		textId = new JTextField();
		textId.setBounds(48, 68, 207, 31);
		contentPanel.add(textId);
		textId.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("NOMBRE");
		lblNewLabel_1.setFont(new Font("Algerian", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(338, 43, 86, 14);
		contentPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("PRECIO");
		lblNewLabel_2.setFont(new Font("Algerian", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(48, 126, 86, 14);
		contentPanel.add(lblNewLabel_2);
		
		textNombre = new JTextField();
		textNombre.setBounds(338, 68, 207, 31);
		contentPanel.add(textNombre);
		textNombre.setColumns(10);
		
		textPrecio = new JTextField();
		textPrecio.setColumns(10);
		textPrecio.setBounds(48, 162, 207, 31);
		contentPanel.add(textPrecio);
		
		JLabel lblNewLabel_2_1 = new JLabel("MARCA");
		lblNewLabel_2_1.setFont(new Font("Algerian", Font.PLAIN, 20));
		lblNewLabel_2_1.setBounds(338, 126, 86, 14);
		contentPanel.add(lblNewLabel_2_1);
		
		textMarca = new JTextField();
		textMarca.setColumns(10);
		textMarca.setBounds(338, 162, 207, 31);
		contentPanel.add(textMarca);
		
		JLabel lblNewLabel_2_2 = new JLabel("TIPO");
		lblNewLabel_2_2.setFont(new Font("Algerian", Font.PLAIN, 20));
		lblNewLabel_2_2.setBounds(48, 222, 86, 14);
		contentPanel.add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_2_2_1 = new JLabel("STOCK");
		lblNewLabel_2_2_1.setFont(new Font("Algerian", Font.PLAIN, 20));
		lblNewLabel_2_2_1.setBounds(338, 222, 86, 14);
		contentPanel.add(lblNewLabel_2_2_1);
		
		textTipo = new JTextField();
		textTipo.setColumns(10);
		textTipo.setBounds(48, 257, 207, 31);
		contentPanel.add(textTipo);
		
		textStock = new JTextField();
		textStock.setColumns(10);
		textStock.setBounds(338, 257, 207, 31);
		contentPanel.add(textStock);
		
		JButton btnNewButton = new JButton("COMPRAR");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comprar(dni);
			}
		});
		btnNewButton.setBounds(13, 451, 160, 38);
		contentPanel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("A\u00D1ADIR");
		btnNewButton_1.setBounds(218, 451, 160, 38);
		contentPanel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("MODIFICAR");
		btnNewButton_2.setBounds(424, 451, 160, 38);
		contentPanel.add(btnNewButton_2);
		
		JLabel lblNewLabel_3 = new JLabel("Cantidad");
		lblNewLabel_3.setFont(new Font("Algerian", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(48, 330, 125, 14);
		contentPanel.add(lblNewLabel_3);
		
		textCantidad = new JTextField();
		textCantidad.setBounds(48, 363, 207, 31);
		contentPanel.add(textCantidad);
		textCantidad.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Introduce la cantidad de cuantos vas a comprar");
		lblNewLabel_4.setBounds(265, 330, 259, 76);
		contentPanel.add(lblNewLabel_4);
		
		if (hardw != null ) {
			
			textId.setText(String.valueOf(hardw.getIdHW()));
			textNombre.setText(hardw.getNombreHW().toString());
			textMarca.setText(hardw.getMarcaHW().toString());
			textTipo.setText(hardw.getTipoHW().toString());
			textPrecio.setText(String.valueOf(hardw.getPrecioHW()));
			textStock.setText(String.valueOf(hardw.getStockHW()));
			
		}
	}
	protected void comprar(String dni) {
		
		LocalDate fecha;
		Factura fak = new Factura();
		
		fak.setNombre(textNombre.getText());
		fak.setCantidad(Integer.valueOf(textCantidad.getText()));
		fak.setDni(dni);
		fak.setFechaFactura(LocalDate.now());   
		datos.comprarHardware(fak);

		
		
	}
}
