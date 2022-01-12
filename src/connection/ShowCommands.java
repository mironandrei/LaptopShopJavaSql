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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;

public class ShowCommands extends JFrame {

	private JPanel commands;
	private JTable table;
	private JTextField clnt;
	private JComboBox comboBox;

	public ShowCommands(Connection conn){
		setResizable(false);
		initialize(conn);
	}

	private void initialize(Connection conn) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1000, 700);
		commands = new JPanel();
		commands.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(commands);
		commands.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(291, 51, 691, 601);
		commands.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnShowAll = new JButton("Show all");
		btnShowAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String query = "SELECT b.Username AS Client, c.Firma AS Laptop, c.Model, a.Plata AS Suma, a.Judet, a.Localitate, a.CodPostal From Comenzi a INNER JOIN Client b ON b.ClientID = a.ClientID INNER JOIN ComenziProduse ap ON ap.ComandaID = a.ComandaID INNER JOIN Laptopuri c ON c.ProdusID = ap.ProdusID";
				
				try{
					
					PreparedStatement ps = conn.prepareStatement(query);
					ResultSet rs = ps.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
				}catch(Exception e1){
					e1.printStackTrace();
				}
				
			}
		});
		btnShowAll.setBackground(Color.MAGENTA);
		btnShowAll.setForeground(Color.WHITE);
		btnShowAll.setFont(new Font("Calibri", Font.BOLD, 20));
		btnShowAll.setBounds(291, 13, 691, 35);
		commands.add(btnShowAll);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AdminMenu aad = new AdminMenu(conn);
				aad.setVisible(true);
				dispose();
			}
		});
		btnExit.setForeground(Color.WHITE);
		btnExit.setFont(new Font("Calibri", Font.BOLD, 20));
		btnExit.setBackground(Color.MAGENTA);
		btnExit.setBounds(20, 617, 259, 35);
		commands.add(btnExit);
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("Calibri", Font.BOLD, 17));
		comboBox.setForeground(Color.WHITE);
		comboBox.setBackground(new Color(51, 0, 102));
		comboBox.setBounds(148, 425, 131, 34);
		commands.add(comboBox);
		
		JButton btnShowClientLaptops = new JButton("Show client laptops");
		btnShowClientLaptops.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				ImageIcon refresh = new ImageIcon("src/Images/refresh.png");
				ImageIcon pencil = new ImageIcon("src/Images/pencil.png");
				String numeClt = clnt.getText();
				int ok = 0;
				
				if(numeClt.isEmpty()){
					ok = 1;
				}
				
				try{
					
					
					String sql = "SELECT b.Username From Comenzi a INNER JOIN Client b ON b.ClientID = a.ClientID INNER JOIN ComenziProduse ap ON ap.ComandaID = a.ComandaID INNER JOIN Laptopuri c ON c.ProdusID = ap.ProdusID";
					Statement statements = conn.createStatement();
					ResultSet rsx = statements.executeQuery(sql);
					
					while(rsx.next()){
						if(numeClt.equals(rsx.getString(1))){
							ok = 2;
						}
					}
					
					if(ok == 1){
						JOptionPane.showMessageDialog(null, "PLEASE INSERT A NAME"," ",JOptionPane.INFORMATION_MESSAGE,pencil);
					}else if(ok == 2){
						comboBox.removeAllItems();
						
						String customer = numeClt;
						String queryyy = "SELECT c.Model From Comenzi a INNER JOIN Client b ON b.ClientID = a.ClientID INNER JOIN ComenziProduse ap ON ap.ComandaID = a.ComandaID INNER JOIN Laptopuri c ON c.ProdusID = ap.ProdusID WHERE b.Username = ?";
						
						PreparedStatement psd = conn.prepareStatement(queryyy);
						psd.setString(1, customer);
						ResultSet rss = psd.executeQuery();
						
						while(rss.next()){
							comboBox.addItem(rss.getString(1));	
						}
					}else{
						JOptionPane.showMessageDialog(null, "WRONG NAME"," ",JOptionPane.INFORMATION_MESSAGE,refresh);
					}
					
				}catch(Exception eu){
					eu.printStackTrace();
				}
			}
		});
		btnShowClientLaptops.setForeground(Color.WHITE);
		btnShowClientLaptops.setFont(new Font("Calibri", Font.BOLD, 20));
		btnShowClientLaptops.setBackground(Color.MAGENTA);
		btnShowClientLaptops.setBounds(20, 377, 259, 35);
		commands.add(btnShowClientLaptops);
		
		JButton btnDeleteCommand = new JButton("Delete Command");
		btnDeleteCommand.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				String query1 = "DELETE FROM Comenzi WHERE ComandaID = ?";
				String query2 = "DELETE FROM ComenziProduse WHERE ComandaID = ?";
				
				
				String numeModel = (String)comboBox.getSelectedItem();
				String client = clnt.getText();
				int ok = 0;
				String clientid = null;
				String pret = null;
				String comandaid = null;
				String comandaid1 = null;
				
				String produsid = null;
				
				ImageIcon del = new ImageIcon("src/Images/delete.png");
				ImageIcon refresh = new ImageIcon("src/Images/refresh.png");
				
				
				try {
					
					String sql = "SELECT * FROM Client";
					Statement statement = conn.createStatement();
					ResultSet rs = statement.executeQuery(sql);
					
					String sql1 = "SELECT * FROM Laptopuri";
					Statement statement1 = conn.createStatement();
					ResultSet rs1 = statement1.executeQuery(sql1);
					
					String sql2 = "SELECT * FROM Comenzi";
					Statement statement2 = conn.createStatement();
					ResultSet rs2 = statement2.executeQuery(sql2);
					
					String sql3 = "SELECT * FROM ComenziProduse";
					Statement statement3 = conn.createStatement();
					ResultSet rs3 = statement3.executeQuery(sql3);
					
					while(rs.next()){
						if(client.equals(rs.getString(2))){
							clientid = rs.getString(1);
						}
					}
					
					while(rs1.next()){
						if(numeModel.equals(rs1.getString(3))){
							pret = rs1.getString(4);
							produsid = rs1.getString(1);
							//ok = 2;
						}
					}
					
					while(rs3.next()){
						if(produsid.equals(rs3.getString(1))){
							comandaid = rs3.getString(2);
						}
					}
					
					while(rs2.next()){
						if(comandaid.equals(rs2.getString(1))){
							comandaid1 = comandaid;
							ok = 2;
						}
					}
					
					PreparedStatement ps = conn.prepareStatement(query1);
					ps.setString(1, comandaid);
					
					PreparedStatement ps2 = conn.prepareStatement(query2);
					ps2.setString(1, comandaid1);
					
					if(ok == 2){
						ps2.execute();
						ps.execute();
						JOptionPane.showMessageDialog(null, "COMMAND DECLINED"," ",JOptionPane.INFORMATION_MESSAGE,del);
						
						comboBox.removeAllItems();
						
						String customer = client;
						
						String queryyy = "SELECT c.Model From Comenzi a INNER JOIN Client b ON b.ClientID = a.ClientID INNER JOIN ComenziProduse ap ON ap.ComandaID = a.ComandaID INNER JOIN Laptopuri c ON c.ProdusID = ap.ProdusID WHERE b.Username = ?";
				
						PreparedStatement psd = conn.prepareStatement(queryyy);
						psd.setString(1, customer);
						ResultSet rss = psd.executeQuery();
						
						while(rss.next()){
							comboBox.addItem(rss.getString(1));
						}
						
					}else{
						JOptionPane.showMessageDialog(null, "PLEASE TRY AGAIN"," ",JOptionPane.INFORMATION_MESSAGE,refresh);
						clnt.setText(null);
					}
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});
		btnDeleteCommand.setForeground(Color.WHITE);
		btnDeleteCommand.setFont(new Font("Calibri", Font.BOLD, 20));
		btnDeleteCommand.setBackground(Color.MAGENTA);
		btnDeleteCommand.setBounds(20, 472, 259, 35);
		commands.add(btnDeleteCommand);
		
		clnt = new JTextField();
		clnt.setForeground(Color.WHITE);
		clnt.setFont(new Font("Calibri", Font.BOLD, 17));
		clnt.setColumns(10);
		clnt.setBackground(new Color(51, 0, 102));
		clnt.setBounds(148, 329, 131, 35);
		commands.add(clnt);
		
		JLabel lblMo = new JLabel("Laptop Model:");
		lblMo.setForeground(Color.WHITE);
		lblMo.setFont(new Font("Calibri", Font.BOLD, 18));
		lblMo.setBounds(20, 425, 116, 35);
		commands.add(lblMo);
		
		JLabel lblClient = new JLabel("Client:");
		lblClient.setForeground(Color.WHITE);
		lblClient.setFont(new Font("Calibri", Font.BOLD, 18));
		lblClient.setBounds(20, 329, 116, 35);
		commands.add(lblClient);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(ShowCommands.class.getResource("/Images/DeleteCommands.png")));
		label.setBounds(0, 0, 1016, 686);
		commands.add(label);
		
		
		
	}
}
