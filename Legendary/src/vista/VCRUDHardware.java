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
	private JLabel lblCantidad;
	private boolean tiene = false;
	private Usuario usu;
	private JLabel lblprecioCoste;
	private JLabel lblId;
	private JLabel lblNombre;
	private JLabel lblPrecio;
	private JLabel lblMarca;
	private JLabel lblTipo;
	private JLabel lblStock;
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
	public VCRUDHardware(VComprar ventanaPadre, boolean modal, Hardware hardw,Usuario usuario) {
		super(ventanaPadre);
		this.setModal(modal);
		this.hardw = hardw;
		this.usu = usuario; 
		cargarVentana();
		
		
		if (usuario.isEsAdmin()) {
			
			
			lblprecioCoste.setVisible(false);
			textPrecioCoste.setVisible(false);
			btnAniadir.setVisible(false);
			
			//hacemos que los siguientes campos no sean editables ya sea mientras este comprando o modificando el admin
			textId.setEnabled(false);
			textNombre.setEnabled(false);
			textMarca.setEnabled(false);
			textTipo.setEnabled(false);
			
		}else {
			
			btnAniadir.setVisible(false);
			btnModificar.setVisible(false);
			textPrecioCoste.setVisible(false);
			textNombre.setEnabled(false);
			textId.setEnabled(false);
			textMarca.setEnabled(false);
			textPrecio.setEnabled(false);
			textTipo.setEnabled(false);
			textStock.setEnabled(false);
			lblprecioCoste.setVisible(false);
		}
	}

	public VCRUDHardware(VComprar ventanaPadre, boolean modal, Usuario usuario) {
		super(ventanaPadre);
		this.setModal(modal);
		cargarVentana();
		btnComprar.setEnabled(false);
		btnModificar.setEnabled(false);
		lblCantidad.setEnabled(false);
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

		lblId = new JLabel("ID");
		lblId.setFont(new Font("Algerian", Font.PLAIN, 20));
		lblId.setBounds(48, 43, 46, 14);
		contentPanel.add(lblId);

		textId = new JTextField();
		textId.setBounds(48, 68, 207, 31);
		contentPanel.add(textId);
		textId.setColumns(10);

		lblNombre = new JLabel("NOMBRE");
		lblNombre.setFont(new Font("Algerian", Font.PLAIN, 20));
		lblNombre.setBounds(338, 43, 86, 14);
		contentPanel.add(lblNombre);

		lblPrecio = new JLabel("PRECIO");
		lblPrecio.setFont(new Font("Algerian", Font.PLAIN, 20));
		lblPrecio.setBounds(48, 126, 86, 14);
		contentPanel.add(lblPrecio);

		textNombre = new JTextField();
		textNombre.setBounds(338, 68, 207, 31);
		contentPanel.add(textNombre);
		textNombre.setColumns(10);

		textPrecio = new JTextField();
		textPrecio.setColumns(10);
		textPrecio.setBounds(48, 162, 207, 31);
		contentPanel.add(textPrecio);

		lblMarca = new JLabel("MARCA");
		lblMarca.setFont(new Font("Algerian", Font.PLAIN, 20));
		lblMarca.setBounds(338, 126, 86, 14);
		contentPanel.add(lblMarca);

		textMarca = new JTextField();
		textMarca.setColumns(10);
		textMarca.setBounds(338, 162, 207, 31);
		contentPanel.add(textMarca);

		lblTipo = new JLabel("TIPO");
		lblTipo.setFont(new Font("Algerian", Font.PLAIN, 20));
		lblTipo.setBounds(48, 222, 86, 14);
		contentPanel.add(lblTipo);

		lblStock = new JLabel("sTOCK");
		lblStock.setFont(new Font("Algerian", Font.PLAIN, 20));
		lblStock.setBounds(338, 222, 86, 14);
		contentPanel.add(lblStock);


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
					JOptionPane.showMessageDialog(null, "ERROR NO HAY STOCK DISPONIBLE", "OWO",JOptionPane.ERROR_MESSAGE);
				} else
					comprar();
					JOptionPane.showMessageDialog(null, "Compra realizada con exito.");
					cerrarVentana();
			}
		});
		btnComprar.setBounds(408, 451, 160, 38);
		contentPanel.add(btnComprar);

		btnModificar = new JButton("MODIFICAR");
		btnModificar.setFont(new Font("Algerian", Font.PLAIN, 20));
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificar();
			}
		});
		btnModificar.setBounds(218, 451, 160, 38);
		contentPanel.add(btnModificar);

		lblCantidad = new JLabel("Cantidad");
		lblCantidad.setFont(new Font("Algerian", Font.PLAIN, 20));
		lblCantidad.setBounds(48, 330, 125, 14);
		contentPanel.add(lblCantidad);

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
		btnAniadir.setBounds(36, 451, 154, 38);
		contentPanel.add(btnAniadir);

		lblprecioCoste = new JLabel("PrecioCoste");
		lblprecioCoste.setFont(new Font("Algerian", Font.PLAIN, 20));
		lblprecioCoste.setBounds(338, 326, 136, 23);
		contentPanel.add(lblprecioCoste);

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

	protected void cerrarVentana() {
		this.dispose();
		
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

	private void modificar() {

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

	private void comprar() {

		LocalDate fecha;
		Factura fak = new Factura();

		fak.setNombre(textNombre.getText());
		fak.setCantidad(Integer.valueOf(textCantidad.getText()));
		fak.setDni(usu.getDni());
		fak.setFechaFactura(LocalDate.now());
		datos.comprarHardware(fak, usu.getDni());

	}

		
}