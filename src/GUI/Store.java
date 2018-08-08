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
		frame.setResizable(false);
		mainPanel = new JPanel();
		mainPanel.setBounds(10, 70, 638, 324);
		mainPanel.setBackground(SystemColor.menu);
		frame.getContentPane().add(mainPanel);
		mainPanel.setLayout(null);
		try {
			buildTable();
		} catch (Exception e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(0, 33, 658, 26);
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
		menuBar.setBounds(0, 4, 658, 31);
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
		registerPanel = new RegisterPanel();
		mainPanel.add(registerPanel);
		((RegisterPanel) registerPanel).getBtnInsertConfirm().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Validade, save
				try {
					Product prod = ((RegisterPanel) registerPanel).buildProduct();
					if(((RegisterPanel) registerPanel).validateProduct(prod)) {
						if(flag == 1) {
							store.getInstance().insertProduct(prod);
							JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
							mainPanel.remove(registerPanel);
							mainPanel.repaint();
						}else if(flag == 2){
							store.getInstance().updateProduct(prod);
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
		
		((RegisterPanel) registerPanel).getBtnCancelar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainPanel.remove(registerPanel);
				mainPanel.repaint();
			}
		});
	
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
		table.setBounds(0, 57, 638, 267);
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
}
