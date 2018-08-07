package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

import javafx.scene.control.ToolBar;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.JToolBar;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.JFormattedTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

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
				buildRegisterPanel();
				mainPanel.repaint();
			}
		});
		
		toolBar.add(btnNewProduct);
		
		btnUpdateProduct = new JButton("Atualizar Produto");
		btnUpdateProduct.setEnabled(false);
		btnUpdateProduct.setFont(new Font("SansSerif", Font.BOLD, 11));
		toolBar.add(btnUpdateProduct);
		
		JButton btnRemoveProduct = new JButton("Remover Produto");
		btnRemoveProduct.setEnabled(false);
		btnRemoveProduct.setFont(new Font("SansSerif", Font.BOLD, 11));
		toolBar.add(btnRemoveProduct);
		JLabel titleLabel = new JLabel("HackaPet - Store");
		titleLabel.setForeground(new Color(255, 140, 0));
		titleLabel.setFont(new Font("Arial Black", Font.PLAIN, 35));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(74, 0, 503, 35);
		mainPanel.add(titleLabel);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 2, 648, 32);
		frame.getContentPane().add(menuBar);
		
		JMenuItem mntmProdutos_1 = new JMenuItem("Produtos");
		mntmProdutos_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toolBar.setVisible(true);
			}
		});
		menuBar.add(mntmProdutos_1);
		
		
	}
	
	public void buildRegisterPanel() {
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
				mainPanel.remove(registerPanel);
				mainPanel.repaint();
				
			}
		});
		btnInsertConfirm.setFont(new Font("SansSerif", Font.BOLD, 11));
		btnInsertConfirm.setBounds(272, 190, 89, 23);
		registerPanel.add(btnInsertConfirm);
		
	
	
	}
}
