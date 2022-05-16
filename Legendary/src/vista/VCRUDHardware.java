package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;

import org.w3c.dom.Text;

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
	private JButton btnComprar;
	private JButton btnModificar;
	private String dni;
	private Hardware hardw;
	private JTextField textPrecioCoste;
	private JButton btnAniadir;
	private JLabel lblNewLabel_3;
	private boolean tiene = false;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 * 
	 * @param datos
	 * @param hardw
	 * @param b
	 * @param vComprar
	 * @wbp.parser.constructor
	 */
	public VCRUDHardware(VComprar ventanaPadre, boolean modal, ControladorDatos datos, Hardware hardw,
			Usuario usuario) {
		super(ventanaPadre);
		this.setModal(modal);
		this.hardw = hardw;
		cargarVentana();
		textPrecioCoste.setEnabled(false);
		btnAniadir.setEnabled(false);
		btnModificar.setEnabled(false);
	}

	public VCRUDHardware(VComprar ventanaPadre, boolean modal, ControladorDatos datos2, Usuario usuario) {
		super(ventanaPadre);
		this.setModal(modal);
		cargarVentana();

		btnComprar.setEnabled(false);
		btnModificar.setEnabled(false);
		lblNewLabel_3.setEnabled(false);
		textCantidad.setEnabled(false);

	}

	private void cargarVentana() {

		setBounds(100, 100, 613, 551);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "HARDWARE",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
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

		JLabel lblNewLabel_2_2_1 = new JLabel("sTOCK");
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

		btnComprar = new JButton("COMPRAR");
		btnComprar.setFont(new Font("Algerian", Font.PLAIN, 20));
		btnComprar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Integer.parseInt(textStock.getText()) == 0) {
					JOptionPane.showMessageDialog(null, "ERROR NO HAY STOCK DISPONIBLE", "OWO",
							JOptionPane.ERROR_MESSAGE);
				} else
					comprar(dni);
			}
		});
		btnComprar.setBounds(13, 451, 160, 38);
		contentPanel.add(btnComprar);

		btnModificar = new JButton("MODIFICAR");
		btnModificar.setFont(new Font("Algerian", Font.PLAIN, 20));
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificar(datos);
			}
		});
		btnModificar.setBounds(424, 451, 160, 38);
		contentPanel.add(btnModificar);

		lblNewLabel_3 = new JLabel("Cantidad");
		lblNewLabel_3.setFont(new Font("Algerian", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(48, 330, 125, 14);
		contentPanel.add(lblNewLabel_3);

		textCantidad = new JTextField();
		textCantidad.setBounds(48, 363, 207, 31);
		contentPanel.add(textCantidad);
		textCantidad.setColumns(10);

		btnAniadir = new JButton("A\u00D1ADIR");
		btnAniadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textId.getText().equalsIgnoreCase("")) {
					JOptionPane.showMessageDialog(null, "ERROR campos no rellenos", "OWO",
							JOptionPane.ERROR_MESSAGE);
				}else {
					aniadirHardware();
				}
				
			}
		});
		btnAniadir.setFont(new Font("Algerian", Font.PLAIN, 20));
		btnAniadir.setBounds(220, 451, 154, 38);
		contentPanel.add(btnAniadir);

		JLabel lblNewLabel_4 = new JLabel("PrecioCoste");
		lblNewLabel_4.setFont(new Font("Algerian", Font.PLAIN, 20));
		lblNewLabel_4.setBounds(338, 326, 136, 23);
		contentPanel.add(lblNewLabel_4);

		textPrecioCoste = new JTextField();
		textPrecioCoste.setBounds(338, 364, 194, 29);
		contentPanel.add(textPrecioCoste);
		textPrecioCoste.setColumns(10);

		if (hardw != null) {

			textId.setText(String.valueOf(hardw.getIdHW()));
			textNombre.setText(hardw.getNombreHW().toString());
			textMarca.setText(hardw.getMarcaHW().toString());
			textTipo.setText(hardw.getTipoHW().toString());
			textPrecio.setText(String.valueOf(hardw.getPrecioHW()));
			textStock.setText(String.valueOf(hardw.getStockHW()));

		}
	}

	private void aniadirHardware() {

		Hardware hardw = AniadirPantallaHardw();
		
		datos.aniadirHardware(hardw);
		JOptionPane.showMessageDialog(this, "Hardware dado de alta");



		textId.setText("");
		textNombre.setText("");
		textMarca.setText("");
		textTipo.setText("");
		textPrecio.setText("");
		textStock.setText("");
		textPrecioCoste.setText("");
	}

	private Hardware AniadirPantallaHardw() {

		Hardware hardw = new Hardware();
		
		
		hardw.setIdHW(Integer.parseInt(textId.getText()));
		hardw.setNombreHW(textNombre.getText());
		hardw.setPrecioHW(Float.parseFloat(textPrecio.getText()));
		hardw.setMarcaHW(textMarca.getText());
		hardw.setTipoHW(textTipo.getText());
		hardw.setStockHW(Integer.parseInt(textStock.getText()));
		hardw.setPrecioCosteHW(Float.parseFloat(textPrecioCoste.getText()));
		
			
		
		return hardw;
		
	}

	private void modificar(ControladorDatos datos2) {

		Hardware hw = ModiPantallaHardw();
		datos.modificarHardware(hw);
		JOptionPane.showMessageDialog(this, "Hardware modificado con éxito");

	}

	private Hardware ModiPantallaHardw() {

		Hardware hardw = new Hardware();

		hardw.setIdHW(Integer.parseInt(textId.getText()));
		hardw.setPrecioHW(Float.parseFloat(textPrecio.getText()));
		hardw.setStockHW(Integer.parseInt(textStock.getText()));

		return hardw;
	}

	private void comprar(String dni) {

		LocalDate fecha;
		Factura fak = new Factura();

		fak.setNombre(textNombre.getText());
		fak.setCantidad(Integer.valueOf(textCantidad.getText()));
		fak.setDni(dni);
		fak.setFechaFactura(LocalDate.now());
		datos.comprarHardware(fak, dni);

	}

		
}
