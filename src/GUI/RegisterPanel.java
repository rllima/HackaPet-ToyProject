package GUI;

import java.awt.Color;
import java.awt.Font;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

import Business.Product;
import Business.StoreFacade;
import Business.exception.ProductFieldsEmpty;





public class RegisterPanel extends JPanel {
	private JTextField textFieldProductName;
	private JTextField textFieldProductDescription;
	private JTextField textFieldProductCode;
	private JFormattedTextField formattedTextFieldProductPrice;
	private JButton btnInsertConfirm;
	private JButton btnCancelar;
	/**
	 * Create the panel.
	 */
	public RegisterPanel() {
		setBackground(new Color(255, 255, 255));
		setVisible(false);
		setBounds(0, 74, 648, 247);
		
		setLayout(null);
		setVisible(true);
		
		JLabel lblProductName = new JLabel("Nome:");
		lblProductName.setBounds(10, 24, 46, 14);
		add(lblProductName);
		
		JLabel lblProductDescription = new JLabel("Descri\u00E7\u00E3o:");
		lblProductDescription.setBounds(10, 49, 67, 22);
		add(lblProductDescription);
		
		JLabel lblProductPrice = new JLabel("Pre\u00E7o R$ :");
		lblProductPrice.setBounds(10, 87, 67, 14);
		add(lblProductPrice);
		
		JLabel lblProdutCode = new JLabel("C\u00F3digo");
		lblProdutCode.setBounds(10, 120, 46, 14);
		add(lblProdutCode);
		
		
		JTextField textFieldProductName = new JTextField();
		textFieldProductName.setBounds(87, 21, 226, 20);
		add(textFieldProductName);
		textFieldProductName.setColumns(10);
		
		JTextField textFieldProductDescription = new JTextField();
		textFieldProductDescription.setBounds(87, 49, 226, 22);
		add(textFieldProductDescription);
		textFieldProductDescription.setColumns(10);
		
		DecimalFormat decimal = new DecimalFormat("#,##0.00");
        NumberFormatter numFormatter = new NumberFormatter(decimal);
        numFormatter.setFormat(decimal);
        numFormatter.setAllowsInvalid(false);
        DefaultFormatterFactory dfFactory = new DefaultFormatterFactory(numFormatter);
		
		JFormattedTextField formattedTextFieldProductPrice = new JFormattedTextField(dfFactory);
		formattedTextFieldProductPrice.setBounds(88, 83, 113, 22);
		add(formattedTextFieldProductPrice);
		
		JTextField textFieldProductCode = new JTextField();
		textFieldProductCode.setBounds(88, 117, 113, 20);
		add(textFieldProductCode);
		textFieldProductCode.setColumns(10);
		btnInsertConfirm = new JButton("Confirmar");
		btnInsertConfirm.setFont(new Font("SansSerif", Font.BOLD, 11));
		btnInsertConfirm.setBounds(218, 188, 95, 23);
		add(btnInsertConfirm);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("SansSerif", Font.BOLD, 11));
		btnCancelar.setBounds(317, 188, 95, 23);
		add(btnCancelar);

	}
	public Product buildProduct() {
		String name = textFieldProductName.getText();
		String code = textFieldProductCode.getText();
		String description = textFieldProductDescription.getText();
		Object o = formattedTextFieldProductPrice.getValue();
		double price = 0;
		if(o != null)
			price = new Double(((Number) o).doubleValue());
		Product prod = new Product(name,description, price, code);
		return prod;
	}
	public boolean validateProduct(Product prod) {
		if(prod.getName().equalsIgnoreCase("") || prod.getCod().equalsIgnoreCase("") || prod.getPrice() == 0) {
			try {
				throw new ProductFieldsEmpty();
			} catch (ProductFieldsEmpty e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null,e.getMessage());
				return false;
			}
		}
		return true;
	}
	
	public JButton getBtnInsertConfirm() {
		return btnInsertConfirm;
	}
	public void setBtnInsertConfirm(JButton btnInsertConfirm) {
		this.btnInsertConfirm = btnInsertConfirm;
	}
	public JButton getBtnCancelar() {
		return btnCancelar;
	}
	public void setBtnCancelar(JButton btnCancelar) {
		this.btnCancelar = btnCancelar;
	}
	

}
