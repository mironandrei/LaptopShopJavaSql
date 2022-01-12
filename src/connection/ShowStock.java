package connection;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTable;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;

public class ShowStock extends JFrame {

	private JPanel showStock;
	private JTable table;
	private JTextField modelName;
	private JTextField judet;
	private JTextField localitate;
	private JTextField codPostal;
	private JTextField model;

	public ShowStock(Connection conn){
		setResizable(false);
		initialize(conn);
	}

	
	private void initialize(Connection conn){
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		showStock = new JPanel();
		showStock.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(showStock);
		showStock.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(358, 63, 624, 489);
		showStock.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setFont(new Font("Calibri", Font.BOLD, 15));
		
		JButton showLaptops = new JButton("Show all Laptops");
		showLaptops.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try{
					String query = "SELECT a.Firma, a.Model, a.Pret, b.Tip AS Utilizare FROM Laptopuri a INNER JOIN Categorii_laptopuri b ON a.CategorieID = b.CategorieID";

					PreparedStatement ps = conn.prepareStatement(query);
					ResultSet rs = ps.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
				}catch(Exception e1){
					e1.printStackTrace();
				}
			}
		});
		showLaptops.setForeground(Color.WHITE);
		showLaptops.setBackground(Color.MAGENTA);
		showLaptops.setFont(new Font("Calibri", Font.BOLD, 18));
		showLaptops.setBounds(12, 63, 334, 43);
		showStock.add(showLaptops);
		
		modelName = new JTextField();
		modelName.setHorizontalAlignment(SwingConstants.CENTER);
		modelName.setForeground(Color.WHITE);
		modelName.setFont(new Font("Calibri", Font.BOLD, 18));
		modelName.setBackground(new Color(51, 0, 102));
		modelName.setBounds(502, 13, 156, 43);
		showStock.add(modelName);
		modelName.setColumns(10);
		
		JLabel lblModelName = new JLabel("Model Name:");
		lblModelName.setForeground(Color.WHITE);
		lblModelName.setHorizontalAlignment(SwingConstants.CENTER);
		lblModelName.setFont(new Font("Calibri", Font.BOLD, 18));
		lblModelName.setBounds(361, 13, 139, 43);
		showStock.add(lblModelName);
		
		JButton specModel = new JButton("Show Specs for this model");
		specModel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String model = modelName.getText();
				String prodid = null;
				int ok = 0;
				
				ImageIcon incomplet = new ImageIcon("src/Images/puzzle.png");
				ImageIcon refresh = new ImageIcon("src/Images/refresh.png");
				
				String query1 = "SELECT s.Nume AS Specificatii, s.Descriere FROM Laptopuri a INNER JOIN Categorii_laptopuri b ON a.CategorieID = b.CategorieID INNER JOIN SpecificatiiProduse sp ON sp.ProdusID = a.ProdusID INNER JOIN Specificatii s ON s.SpecificatiiID = sp.SpecificatiiID WHERE a.ProdusID = ?";
						/*+ "INNER JOIN Categorii_laptopuri b ON a.CategorieID = b.CategorieID"
						+ "INNER JOIN SpecificatiiProduse sp ON sp.ProdusID = a.ProdusID"
						+ "INNER JOIN Specificatii s ON s.SpecificatiiID = sp.SpecificatiiID WHERE a.ProdusID = ?";*/
				
				if(model.isEmpty()){
					ok = 1;
				}
				
				try {
					
					
					String sql = "SELECT * FROM Laptopuri";
					Statement statement = conn.createStatement();
					ResultSet rs = statement.executeQuery(sql);
					
					while(rs.next()){
						if(model.equals(rs.getString(3))){
							ok = 2;
							prodid = rs.getString(1);
						}
					}
					
					PreparedStatement ps = conn.prepareStatement(query1);
					ps.setString(1, prodid);
					
					if(ok == 1){
						JOptionPane.showMessageDialog(null, "Fill all of the fields !","SOMETHING IS MISSING",JOptionPane.INFORMATION_MESSAGE,incomplet);
					}else if(ok == 2){
						ResultSet rs1 = ps.executeQuery();
						table.setModel(DbUtils.resultSetToTableModel(rs1));
					}else{
						JOptionPane.showMessageDialog(null, "DON'T FIND THIS MODEL"," ",JOptionPane.INFORMATION_MESSAGE,refresh);
						modelName.setText(null);
					}
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				
		
			}
		});
		specModel.setForeground(Color.WHITE);
		specModel.setFont(new Font("Calibri", Font.BOLD, 18));
		specModel.setBackground(Color.MAGENTA);
		specModel.setBounds(682, 13, 300, 43);
		showStock.add(specModel);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ClientMenu clien = new ClientMenu(conn);
				clien.setVisible(true);
				dispose();
			}
		});
		btnExit.setForeground(Color.WHITE);
		btnExit.setFont(new Font("Calibri", Font.BOLD, 18));
		btnExit.setBackground(Color.MAGENTA);
		btnExit.setBounds(12, 509, 334, 43);
		showStock.add(btnExit);
		
		JComboBox tipTranzactie = new JComboBox();
		tipTranzactie.setForeground(Color.WHITE);
		tipTranzactie.addItem("ramburs");
		tipTranzactie.addItem("card");
		tipTranzactie.setSelectedItem("ramburs");
		tipTranzactie.setFont(new Font("Calibri", Font.BOLD, 18));
		tipTranzactie.setBackground(new Color(51, 0, 102));
		tipTranzactie.setBounds(178, 173, 168, 43);
		showStock.add(tipTranzactie);
		
		JButton btnMakeACommand = new JButton("Add to Cart");
		btnMakeACommand.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String modelName = model.getText();
				String judetName = judet.getText();
				String localitateName = localitate.getText();
				String postalCode = codPostal.getText();
				String client = Login.us1;
				String clientid = null;
				String pret = null;
				String produsid = null;
				int comandaid = 0;
				int temp = 0;
				int temp2 = 0;
				String cat = (String)tipTranzactie.getSelectedItem();
				
				ImageIcon incomplet = new ImageIcon("src/Images/puzzle.png");
				ImageIcon shop = new ImageIcon("src/Images/shopping-cart.png");
				ImageIcon refresh = new ImageIcon("src/Images/refresh.png");
				
				String query = "INSERT INTO Comenzi(Data, ClientID, TipTranzactie, Plata, Judet, Localitate, CodPostal) VALUES(?,?,?,?,?,?,?)";
				
				
				if(modelName.isEmpty() || judetName.isEmpty() || localitateName.isEmpty() || postalCode.isEmpty()){
					temp = 1;
				}
				LocalDate dataLocala = LocalDate.now();
				String data = dataLocala.toString();
				
				try{
					
					String sql = "SELECT * FROM Client";
					Statement statement = conn.createStatement();
					ResultSet rs = statement.executeQuery(sql);
					
					String sql1 = "SELECT * FROM Laptopuri";
					Statement statement1 = conn.createStatement();
					ResultSet rs1 = statement1.executeQuery(sql1);
					
					String sql2 = "SELECT * FROM Comenzi";
					Statement statement2 = conn.createStatement();
					ResultSet rs2 = statement2.executeQuery(sql2);
					
					
					while(rs.next()){
						if(client.equals(rs.getString(2))){
							clientid = rs.getString(1);
						}
					}
					
					while(rs1.next()){
						if(modelName.equals(rs1.getString(3))){
							pret = rs1.getString(4);
							produsid = rs1.getString(1);
							temp2 = 1;
						}
					}
					
					while(rs2.next()){
						comandaid = Integer.parseInt(rs2.getString(1));
					}
					
					
					PreparedStatement ps = conn.prepareStatement(query);
					ps.setString(1, data);
					ps.setString(2, clientid);
					ps.setString(3, cat);
					ps.setString(4, pret);
					ps.setString(5, judetName);
					ps.setString(6, localitateName);
					ps.setString(7, postalCode);
					
					
					if(temp == 1 ){
						JOptionPane.showMessageDialog(null, "Fill all of the fields !","SOMETHING IS MISSING",JOptionPane.INFORMATION_MESSAGE,incomplet);
					}else if(temp2 == 1){
						ps.execute();
						JOptionPane.showMessageDialog(null, "ADDED TO CART! PRESS Order NOW"," ",JOptionPane.INFORMATION_MESSAGE,shop);
						
					}else{
						JOptionPane.showMessageDialog(null, "PLEASE TRY AGAIN"," ",JOptionPane.INFORMATION_MESSAGE,refresh);
						model.setText(null);
						judet.setText(null);
						localitate.setText(null);
						codPostal.setText(null);
						tipTranzactie.setSelectedItem("ramburs");
					}
					
				}catch(Exception e1){
					e1.printStackTrace();
				}
				
			}
		});
		btnMakeACommand.setForeground(Color.WHITE);
		btnMakeACommand.setFont(new Font("Calibri", Font.BOLD, 18));
		btnMakeACommand.setBackground(Color.MAGENTA);
		btnMakeACommand.setBounds(12, 397, 196, 43);
		showStock.add(btnMakeACommand);
		
		JButton btnOrder = new JButton("Order");
		btnOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String com = "INSERT INTO ComenziProduse(ProdusID, ComandaID, NumarProduse) VALUES (?,?,?)";
				String produsid = null;
				int comandaid = 0;
				int nrProd = 1;
				int flag = 0;
				ImageIcon truck = new ImageIcon("src/Images/truck.png");
				ImageIcon pencil = new ImageIcon("src/Images/pencil.png");
				
				try{
					
					String sql1 = "SELECT * FROM Laptopuri";
					Statement statement1 = conn.createStatement();
					ResultSet rs1 = statement1.executeQuery(sql1);
					
					String sql2 = "SELECT * FROM Comenzi";
					Statement statement2 = conn.createStatement();
					ResultSet rs2 = statement2.executeQuery(sql2);
					
					
					while(rs1.next()){
						if(model.getText().equals(rs1.getString(3))){
							produsid = rs1.getString(1);
							flag = 10;
						}
					}
					
					while(rs2.next()){
						comandaid = Integer.parseInt(rs2.getString(1));
					}
					
					
					PreparedStatement ps2 = conn.prepareStatement(com);
					ps2.setString(1, produsid);
					ps2.setInt(2, comandaid);
					ps2.setInt(3, nrProd);
					
					if(flag == 10){
						ps2.execute();
						JOptionPane.showMessageDialog(null, "SUCCESSFUL COMMAND"," ",JOptionPane.INFORMATION_MESSAGE,truck);
						model.setText(null);
						judet.setText(null);
						localitate.setText(null);
						codPostal.setText(null);
						tipTranzactie.setSelectedItem("ramburs");
					}else{
						JOptionPane.showMessageDialog(null, "MAKE A COMMAND FIRST"," ",JOptionPane.INFORMATION_MESSAGE,pencil);
					}
					
				}catch(Exception ez){
					ez.printStackTrace();
				}
				
			}
		});
		btnOrder.setForeground(Color.WHITE);
		btnOrder.setFont(new Font("Calibri", Font.BOLD, 18));
		btnOrder.setBackground(Color.MAGENTA);
		btnOrder.setBounds(219, 397, 127, 43);
		showStock.add(btnOrder);
		
		
		
		judet = new JTextField();
		judet.setForeground(Color.WHITE);
		judet.setFont(new Font("Calibri", Font.BOLD, 18));
		judet.setHorizontalAlignment(SwingConstants.LEFT);
		judet.setBackground(new Color(51, 0, 102));
		judet.setBounds(178, 229, 168, 43);
		showStock.add(judet);
		judet.setColumns(10);
		
		localitate = new JTextField();
		localitate.setHorizontalAlignment(SwingConstants.LEFT);
		localitate.setForeground(Color.WHITE);
		localitate.setFont(new Font("Calibri", Font.BOLD, 18));
		localitate.setColumns(10);
		localitate.setBackground(new Color(51, 0, 102));
		localitate.setBounds(178, 285, 168, 43);
		showStock.add(localitate);
		
		codPostal = new JTextField();
		codPostal.setHorizontalAlignment(SwingConstants.LEFT);
		codPostal.setForeground(Color.WHITE);
		codPostal.setFont(new Font("Calibri", Font.BOLD, 18));
		codPostal.setColumns(10);
		codPostal.setBackground(new Color(51, 0, 102));
		codPostal.setBounds(178, 341, 168, 43);
		showStock.add(codPostal);
		
		model = new JTextField();
		model.setHorizontalAlignment(SwingConstants.LEFT);
		model.setForeground(Color.WHITE);
		model.setFont(new Font("Calibri", Font.BOLD, 18));
		model.setColumns(10);
		model.setBackground(new Color(51, 0, 102));
		model.setBounds(178, 117, 168, 43);
		showStock.add(model);
		
		JButton placeOrdr = new JButton("Decline a command");
		placeOrdr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				DeclineOffer deco = new DeclineOffer(conn);
				deco.setVisible(true);
				
			}
		});
		placeOrdr.setForeground(Color.WHITE);
		placeOrdr.setFont(new Font("Calibri", Font.BOLD, 18));
		placeOrdr.setBackground(Color.MAGENTA);
		placeOrdr.setBounds(12, 453, 334, 43);
		showStock.add(placeOrdr);
		
		JLabel lblModel = new JLabel("Model:");
		lblModel.setForeground(Color.WHITE);
		lblModel.setHorizontalAlignment(SwingConstants.LEFT);
		lblModel.setFont(new Font("Calibri", Font.BOLD, 20));
		lblModel.setBounds(49, 119, 123, 41);
		showStock.add(lblModel);
		
		JLabel lblJudet = new JLabel("Judet:");
		lblJudet.setForeground(Color.WHITE);
		lblJudet.setHorizontalAlignment(SwingConstants.LEFT);
		lblJudet.setFont(new Font("Calibri", Font.BOLD, 20));
		lblJudet.setBounds(49, 229, 123, 41);
		showStock.add(lblJudet);
		
		JLabel lblLocalitate = new JLabel("Localitate:");
		lblLocalitate.setForeground(Color.WHITE);
		lblLocalitate.setHorizontalAlignment(SwingConstants.LEFT);
		lblLocalitate.setFont(new Font("Calibri", Font.BOLD, 20));
		lblLocalitate.setBounds(49, 285, 123, 41);
		showStock.add(lblLocalitate);
		
		JLabel lblCodPostal = new JLabel("Cod Postal:");
		lblCodPostal.setForeground(Color.WHITE);
		lblCodPostal.setHorizontalAlignment(SwingConstants.LEFT);
		lblCodPostal.setFont(new Font("Calibri", Font.BOLD, 20));
		lblCodPostal.setBounds(49, 343, 123, 41);
		showStock.add(lblCodPostal);
		
		JLabel lblTranzictie = new JLabel("Tranzactie");
		lblTranzictie.setHorizontalAlignment(SwingConstants.LEFT);
		lblTranzictie.setForeground(Color.WHITE);
		lblTranzictie.setFont(new Font("Calibri", Font.BOLD, 20));
		lblTranzictie.setBounds(49, 175, 123, 41);
		showStock.add(lblTranzictie);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(ShowStock.class.getResource("/Images/ShowLaptops.png")));
		label.setBounds(0, 0, 1016, 588);
		showStock.add(label);
		
		
	}
}
