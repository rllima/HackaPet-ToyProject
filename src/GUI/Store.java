package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

import Business.Product;
import Business.StoreFacade;
import Business.exception.ProductFieldsEmpty;
import Business.exception.ProductNotFoundException;
import javafx.scene.control.ToolBar;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.JFormattedTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Store {

	private JFrame frame;
	private JTextField textFieldProductName;
	private JTextField textFieldProductDescription;
	private JTextField textFieldProductCode;
	private boolean registerPanelVisibility = false;
	private JPanel registerPanel;
	private JFormattedTextField formattedTextFieldProductPrice;
	private JPanel mainPanel;
	private JButton btnUpdateProduct;
	private static StoreFacade store;
	private JTable table;
	private JButton btnRemoveProduct;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Store window = new Store();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Store() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 664, 434);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		mainPanel = new JPanel();
		mainPanel.setBounds(0, 70, 648, 325);
		mainPanel.setBackground(SystemColor.menu);
		frame.getContentPane().add(mainPanel);
		mainPanel.setLayout(null);
		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(0, 33, 648, 26);
		frame.getContentPane().add(toolBar);
		toolBar.setVisible(false);
		JButton btnNewProduct = new JButton("Novo Produto");
		btnNewProduct.setFont(new Font("SansSerif", Font.BOLD, 11));
		btnNewProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mainPanel.remove(table);
				mainPanel.repaint();
				buildRegisterPanel(1);
				mainPanel.repaint();

			}
		});
		try {
			
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		toolBar.add(btnNewProduct);
		btnUpdateProduct = new JButton("Atualizar Produto");
		btnUpdateProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String code = table.getValueAt(table.getSelectedRow(), 1).toString();
				try {
					Product aux = store.getInstance().searchProduct(code);
					mainPanel.remove(table);
					buildRegisterPanel(2);
					textFieldProductName.setText(aux.getName());
					textFieldProductCode.setText(aux.getCod());
					textFieldProductDescription.setText(aux.getDescription());
					formattedTextFieldProductPrice.setValue(aux.getPrice());
					mainPanel.repaint();
				} catch (ProductNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
		btnUpdateProduct.setEnabled(false);
		btnUpdateProduct.setFont(new Font("SansSerif", Font.BOLD, 11));
		toolBar.add(btnUpdateProduct);
		
		btnRemoveProduct = new JButton("Remover Produto");
		btnRemoveProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String code = table.getValueAt(table.getSelectedRow(), 1).toString();
				try {
					store.getInstance().removeProduct(code);
					mainPanel.remove(table);
					buildTable();
					mainPanel.repaint();
				} catch (ProductNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		btnRemoveProduct.setEnabled(false);
		btnRemoveProduct.setFont(new Font("SansSerif", Font.BOLD, 11));
		toolBar.add(btnRemoveProduct);
		JLabel titleLabel = new JLabel("HackaPet - Store");
		titleLabel.setForeground(new Color(255, 140, 0));
		titleLabel.setFont(new Font("Arial Black", Font.PLAIN, 25));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(74, 0, 503, 34);
		mainPanel.add(titleLabel);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 2, 648, 32);
		frame.getContentPane().add(menuBar);
		
		JMenuItem mntmProdutos_1 = new JMenuItem("Produtos");
		mntmProdutos_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toolBar.setVisible(true);
				try {
					buildTable();
					mainPanel.repaint();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		menuBar.add(mntmProdutos_1);
		
		
	}
	
	public void buildRegisterPanel(int flag) {
		registerPanel = new JPanel();
		registerPanel.setBackground(new Color(255, 255, 255));
		registerPanel.setVisible(false);
		registerPanel.setBounds(0, 74, 648, 247);
		mainPanel.add(registerPanel);
		registerPanel.setLayout(null);
		registerPanel.setVisible(true);
		
		JLabel lblProductName = new JLabel("Nome:");
		lblProductName.setBounds(10, 24, 46, 14);
		registerPanel.add(lblProductName);
		
		JLabel lblProductDescription = new JLabel("Descri\u00E7\u00E3o:");
		lblProductDescription.setBounds(10, 49, 67, 22);
		registerPanel.add(lblProductDescription);
		
		JLabel lblProductPrice = new JLabel("Pre\u00E7o R$ :");
		lblProductPrice.setBounds(10, 87, 67, 14);
		registerPanel.add(lblProductPrice);
		
		JLabel lblProdutCode = new JLabel("C\u00F3digo");
		lblProdutCode.setBounds(10, 120, 46, 14);
		registerPanel.add(lblProdutCode);
		
		
		textFieldProductName = new JTextField();
		textFieldProductName.setBounds(87, 21, 226, 20);
		registerPanel.add(textFieldProductName);
		textFieldProductName.setColumns(10);
		
		textFieldProductDescription = new JTextField();
		textFieldProductDescription.setBounds(87, 49, 226, 22);
		registerPanel.add(textFieldProductDescription);
		textFieldProductDescription.setColumns(10);
		
		DecimalFormat decimal = new DecimalFormat("#,##0.00");
        NumberFormatter numFormatter = new NumberFormatter(decimal);
        numFormatter.setFormat(decimal);
        numFormatter.setAllowsInvalid(false);
        DefaultFormatterFactory dfFactory = new DefaultFormatterFactory(numFormatter);
		
		formattedTextFieldProductPrice = new JFormattedTextField(dfFactory);
		formattedTextFieldProductPrice.setBounds(88, 83, 113, 22);
		registerPanel.add(formattedTextFieldProductPrice);
		
		textFieldProductCode = new JTextField();
		textFieldProductCode.setBounds(88, 117, 113, 20);
		registerPanel.add(textFieldProductCode);
		textFieldProductCode.setColumns(10);
		
		JButton btnInsertConfirm = new JButton("Confirmar");
		btnInsertConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Validade, save
				try {
					Product prod = buildProduct();
					if(validateProduct(prod)) {
						if(flag == 1) {
							store.getInstance().insertProduct(buildProduct());
							JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
							mainPanel.remove(registerPanel);
							mainPanel.repaint();
						}else if(flag == 2){
							store.getInstance().updateProduct(buildProduct());
							JOptionPane.showMessageDialog(null, "Produto atualizado com sucesso!");
							mainPanel.remove(registerPanel);
							mainPanel.repaint();
							buildTable();
							
						}
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		btnInsertConfirm.setFont(new Font("SansSerif", Font.BOLD, 11));
		btnInsertConfirm.setBounds(218, 188, 95, 23);
		registerPanel.add(btnInsertConfirm);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainPanel.remove(registerPanel);
				mainPanel.repaint();
			}
		});
		btnCancelar.setFont(new Font("SansSerif", Font.BOLD, 11));
		btnCancelar.setBounds(317, 188, 95, 23);
		registerPanel.add(btnCancelar);
		
	}
	public void buildTable() throws Exception {
		String [] cols = {"Nome", "Código", "Preço", "Descrição"}; 
		DefaultTableModel dtm = new DefaultTableModel(0, 0);
		dtm.setColumnIdentifiers(cols);
		ArrayList<Product> stock = store.getInstance().getProducts();
		table = new JTable();
		table.setModel(dtm);
		if(stock != null)
			for(Product prod : stock) {
				dtm.addRow(new Object[] {prod.getName(), prod.getCod(), prod.getPrice(), prod.getDescription()});
			}
		table.setBounds(10, 57, 628, 257);
		mainPanel.add(table);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				btnUpdateProduct.setEnabled(true);
				btnRemoveProduct.setEnabled(true);
			}
		});
		table.setFillsViewportHeight(true);
		table.setVisible(true);
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
}
