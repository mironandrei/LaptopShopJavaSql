package connection;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import javax.swing.JScrollBar;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.UIManager;

public class AddStock extends JFrame {

	private JPanel AddStock;
	private JTable table;
	private JTextField firma;
	private JTextField Model;
	private JTextField pret;
	private JTextField nameSpec;
	private JLabel lblModel;
	private JLabel lblPret;
	private JLabel lblCategorieid;
	private JButton btnAddSpecTo;
	private JTable table_1;
	private JTable table_2;
	private JComboBox comboBox;
	private JLabel label;

	public AddStock(Connection conn){
		setResizable(false);
		initialize(conn);
	}

	/**
	 * Create the frame.
	 */
	private void initialize(Connection conn) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		AddStock = new JPanel();
		AddStock.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(AddStock);
		AddStock.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(315, 56, 667, 248);
		AddStock.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		firma = new JTextField();
		firma.setForeground(Color.WHITE);
		firma.setFont(new Font("Calibri", Font.BOLD, 18));
		firma.setBackground(new Color(51, 0, 102));
		firma.setBounds(157, 102, 143, 30);
		AddStock.add(firma);
		firma.setColumns(10);
		
		Model = new JTextField();
		Model.setFont(new Font("Calibri", Font.BOLD, 18));
		Model.setForeground(Color.WHITE);
		Model.setBackground(new Color(51, 0, 102));
		Model.setColumns(10);
		Model.setBounds(157, 145, 143, 30);
		AddStock.add(Model);
		
		pret = new JTextField();
		pret.setForeground(Color.WHITE);
		pret.setFont(new Font("Calibri", Font.BOLD, 18));
		pret.setBackground(new Color(51, 0, 102));
		pret.setColumns(10);
		pret.setBounds(157, 188, 143, 30);
		AddStock.add(pret);
		
		nameSpec = new JTextField();
		nameSpec.setForeground(Color.WHITE);
		nameSpec.setFont(new Font("Calibri", Font.BOLD, 18));
		nameSpec.setColumns(10);
		nameSpec.setBackground(new Color(51, 0, 102));
		nameSpec.setBounds(157, 393, 143, 30);
		AddStock.add(nameSpec);
		
		JLabel lblFirma = new JLabel("Firma:");
		lblFirma.setForeground(Color.WHITE);
		lblFirma.setFont(new Font("Calibri", Font.BOLD, 19));
		lblFirma.setBounds(28, 103, 59, 25);
		AddStock.add(lblFirma);
		
		lblModel = new JLabel("Model:");
		lblModel.setForeground(Color.WHITE);
		lblModel.setFont(new Font("Calibri", Font.BOLD, 19));
		lblModel.setBounds(28, 146, 59, 25);
		AddStock.add(lblModel);
		
		lblPret = new JLabel("Pret:");
		lblPret.setForeground(Color.WHITE);
		lblPret.setFont(new Font("Calibri", Font.BOLD, 19));
		lblPret.setBounds(28, 189, 59, 25);
		AddStock.add(lblPret);
		
		lblCategorieid = new JLabel("CategorieID:");
		lblCategorieid.setForeground(Color.WHITE);
		lblCategorieid.setFont(new Font("Calibri", Font.BOLD, 19));
		lblCategorieid.setBounds(28, 232, 113, 25);
		AddStock.add(lblCategorieid);
		
		JButton btnShowAll = new JButton("Show all");
		btnShowAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try{
					
					String query = "SELECT * FROM Laptopuri";
					PreparedStatement ps = conn.prepareStatement(query);
					ResultSet rs = ps.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
					String query1 = "SELECT * FROM Specificatii";
					PreparedStatement ps1 = conn.prepareStatement(query1);
					ResultSet rs1 = ps1.executeQuery();
					table_1.setModel(DbUtils.resultSetToTableModel(rs1));
					
					String query2 = "SELECT * FROM SpecificatiiProduse";
					PreparedStatement ps2 = conn.prepareStatement(query2);
					ResultSet rs2 = ps2.executeQuery();
					table_2.setModel(DbUtils.resultSetToTableModel(rs2));
					
				}catch(Exception e1){
					e1.printStackTrace();
				}
				
			}
		});
		btnShowAll.setBackground(Color.MAGENTA);
		btnShowAll.setForeground(Color.WHITE);
		btnShowAll.setFont(new Font("Calibri", Font.BOLD, 20));
		btnShowAll.setBounds(315, 13, 667, 30);
		AddStock.add(btnShowAll);
		
		JLabel lblNume = new JLabel("Nume:");
		lblNume.setForeground(Color.WHITE);
		lblNume.setFont(new Font("Calibri", Font.BOLD, 19));
		lblNume.setBounds(28, 394, 59, 25);
		AddStock.add(lblNume);
		
		JLabel lblDescriere = new JLabel("Descriere:");
		lblDescriere.setForeground(Color.WHITE);
		lblDescriere.setFont(new Font("Calibri", Font.BOLD, 19));
		lblDescriere.setBounds(28, 437, 100, 25);
		AddStock.add(lblDescriere);
		
		JComboBox catego = new JComboBox();
		catego.addItem("office");
		catego.addItem("gaming");
		catego.setSelectedItem("office");
		catego.setFont(new Font("Calibri", Font.BOLD, 18));
		catego.setForeground(Color.WHITE);
		catego.setBackground(new Color(51, 0, 102));
		catego.setBounds(157, 231, 143, 30);
		AddStock.add(catego);
		
		JButton btnNewButton = new JButton("Add Laptop");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				String firm = firma.getText().toLowerCase();
				String mdl = Model.getText().toLowerCase();
				String price = pret.getText().toLowerCase();
				ImageIcon incomplet = new ImageIcon("src/Images/puzzle.png");
				ImageIcon ad = new ImageIcon("src/Images/added.png");
				ImageIcon ch = new ImageIcon("src/Images/check.png");
				String cat = (String)catego.getSelectedItem();
				String categ = null;
				int ok = 0;
				
				if(cat.equals("office")){
					categ = "1";
				}else if(cat.equals("gaming")){
					categ = "2";
				}
				
				String query = "INSERT INTO Laptopuri(Firma,Model,Pret,CategorieID) VALUES (?,?,?,?)";
				
				try{
					
					PreparedStatement ps = conn.prepareStatement(query);
					ps.setString(1, firm);
					ps.setString(2, mdl);
					ps.setString(3, price);
					ps.setString(4,categ);
					
					String sql = "SELECT * FROM Laptopuri";
					Statement statement = conn.createStatement();
					ResultSet rs = statement.executeQuery(sql);
					
					while(rs.next()){
						if(mdl.equals(rs.getString(3))){
							ok = 1;
						}
					}
					
					
					if(ok == 1){
						JOptionPane.showMessageDialog(null, "WE ALREADY HAVE THIS LAPTOP"," ",JOptionPane.INFORMATION_MESSAGE,ch);
						firma.setText(null);
						Model.setText(null);
						pret.setText(null);
						catego.setSelectedItem("office");
					}else if(firma.getText().isEmpty() || Model.getText().isEmpty() || pret.getText().isEmpty()){
						JOptionPane.showMessageDialog(null, "Fill all of the fields !","SOMETHING IS MISSING",JOptionPane.INFORMATION_MESSAGE,incomplet);
					}
					else{
						
						ps.execute();
						JOptionPane.showMessageDialog(null, "LAPTOP ADDED"," ",JOptionPane.INFORMATION_MESSAGE,ad);
						firma.setText(null);
						Model.setText(null);
						pret.setText(null);
						catego.setSelectedItem("office");
						
					}
					
					
					
				}catch(Exception e5){
					e5.printStackTrace();
				}
				
			}
		});
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(Color.MAGENTA);
		btnNewButton.setFont(new Font("Calibri", Font.BOLD, 18));
		btnNewButton.setBounds(28, 274, 272, 30);
		AddStock.add(btnNewButton);
		
		comboBox = new JComboBox();
		comboBox.addItem("procesor");
		comboBox.addItem("placa video");
		comboBox.addItem("ram");
		comboBox.addItem("ssd");
		comboBox.addItem("culoare");
		comboBox.setSelectedItem("procesor");
		comboBox.setFont(new Font("Calibri", Font.BOLD, 18));
		comboBox.setForeground(Color.WHITE);
		comboBox.setBackground(new Color(51, 0, 102));
		comboBox.setBounds(157, 431, 143, 30);
		AddStock.add(comboBox);
		
		JButton btnAddSpec = new JButton("Add Spec");
		btnAddSpec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String name = nameSpec.getText().toLowerCase();
				//String description = Descriere.getText().toLowerCase();
				String description = (String)comboBox.getSelectedItem();
				ImageIcon incomplet = new ImageIcon("src/Images/puzzle.png");
				ImageIcon ad = new ImageIcon("src/Images/added.png");
				ImageIcon ch = new ImageIcon("src/Images/check.png");
				int okk = 0;
				
				String query = "INSERT INTO Specificatii(Nume,Descriere) VALUES (?,?)"; 
				
				try{
					
					PreparedStatement ps = conn.prepareStatement(query);
					ps.setString(1, name);
					ps.setString(2, description);
					
					String sql1 = "SELECT * FROM Specificatii";
					Statement statement1 = conn.createStatement();
					ResultSet rs1 = statement1.executeQuery(sql1);
					
					while(rs1.next()){
						if(name.equals(rs1.getString(2))){
							okk = 1;
						}
					}
					
					if(okk == 1){
						JOptionPane.showMessageDialog(null, "WE ALREADY HAVE THIS SPEC"," ",JOptionPane.INFORMATION_MESSAGE,ch);
						nameSpec.setText(null);
						comboBox.setSelectedItem("procesor");
					}else if(nameSpec.getText().isEmpty()){
						JOptionPane.showMessageDialog(null, "Fill all of the fields !","SOMETHING IS MISSING",JOptionPane.INFORMATION_MESSAGE,incomplet);
					}else{
						ps.execute();
						JOptionPane.showMessageDialog(null, "SPEC ADDED"," ",JOptionPane.INFORMATION_MESSAGE,ad);
						nameSpec.setText(null);
						comboBox.setSelectedItem("procesor");
					}
					
				}catch(Exception ee){
					ee.printStackTrace();
				}
			}
		});
		btnAddSpec.setForeground(Color.WHITE);
		btnAddSpec.setFont(new Font("Calibri", Font.BOLD, 18));
		btnAddSpec.setBackground(Color.MAGENTA);
		btnAddSpec.setBounds(157, 479, 143, 30);
		AddStock.add(btnAddSpec);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminMenu admm = new AdminMenu(conn);
				admm.setVisible(true);
				dispose();
			}
		});
		
		btnExit.setForeground(Color.WHITE);
		btnExit.setFont(new Font("Calibri", Font.BOLD, 18));
		btnExit.setBackground(Color.MAGENTA);
		btnExit.setBounds(28, 479, 123, 30);
		AddStock.add(btnExit);
		
		btnAddSpecTo = new JButton("Add Spec to Laptop");
		btnAddSpecTo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				AddSpecToLaptop astl = new AddSpecToLaptop(conn);
				astl.setVisible(true);
				
			}
		});
		btnAddSpecTo.setForeground(Color.WHITE);
		btnAddSpecTo.setFont(new Font("Calibri", Font.BOLD, 18));
		btnAddSpecTo.setBackground(Color.MAGENTA);
		btnAddSpecTo.setBounds(28, 522, 272, 30);
		AddStock.add(btnAddSpecTo);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(315, 314, 326, 238);
		AddStock.add(scrollPane_1);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(653, 314, 329, 238);
		AddStock.add(scrollPane_2);
		
		table_2 = new JTable();
		scrollPane_2.setViewportView(table_2);
		
		label = new JLabel("");
		label.setIcon(new ImageIcon(AddStock.class.getResource("/Images/AddStock.png")));
		label.setBounds(0, 0, 1012, 585);
		AddStock.add(label);
		
		
	}
}
