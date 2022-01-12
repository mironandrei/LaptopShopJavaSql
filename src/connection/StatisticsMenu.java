package connection;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class StatisticsMenu extends JFrame {

	private JPanel statistic;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton mostCmd;
	private JComboBox micMare;
	private JComboBox categorie;
	private JComboBox lpt;
	private JButton lptMDLL;

	public StatisticsMenu(Connection conn){
		setResizable(false);
		initialize(conn);
	}
	
	private void initialize(Connection conn) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1100, 750);
		statistic = new JPanel();
		statistic.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(statistic);
		statistic.setLayout(null);
		
		JButton btnBackToMain = new JButton("Back to Main Menu");
		btnBackToMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				AdminMenu adm = new AdminMenu(conn);
				adm.setVisible(true);
				dispose();
			}
		});
		btnBackToMain.setBackground(Color.MAGENTA);
		btnBackToMain.setForeground(Color.WHITE);
		btnBackToMain.setFont(new Font("Calibri", Font.BOLD, 17));
		btnBackToMain.setBounds(552, 664, 530, 38);
		statistic.add(btnBackToMain);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(552, 135, 530, 516);
		statistic.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		mostCmd = new JButton("Month With Most Commands");
		mostCmd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String que = "SELECT MONTH(a.Data) AS LunaComenzii, YEAR(a.Data) AS AnulComenzii, COUNT(*) AS NumarComenzi FROM Comenzi a WHERE MONTH(a.Data) = (SELECT TOP 1 MONTH(a2.Data) FROM Comenzi a2 GROUP BY MONTH(a2.Data) ORDER BY COUNT(*) DESC) GROUP BY MONTH(a.Data),YEAR(a.Data)";
				
				try{
					
					PreparedStatement ps1 = conn.prepareStatement(que);
					ResultSet rs1 = ps1.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs1));

				}catch(Exception e2){
					e2.printStackTrace();
				}
				
			}
		});
		mostCmd.setForeground(Color.WHITE);
		mostCmd.setFont(new Font("Calibri", Font.BOLD, 17));
		mostCmd.setBackground(Color.MAGENTA);
		mostCmd.setBounds(12, 664, 530, 38);
		statistic.add(mostCmd);
		
		JComboBox howMany = new JComboBox();
		howMany.addItem("No command");
		howMany.addItem("At least one command");
		howMany.setSelectedItem("No command");
		howMany.setBackground(new Color(51, 0, 102));
		howMany.setForeground(Color.WHITE);
		howMany.setFont(new Font("Calibri", Font.BOLD, 17));
		howMany.setBounds(12, 186, 259, 38);
		statistic.add(howMany);
		
		JButton showClt = new JButton("Show Clients");
		showClt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String query1 = "SELECT a.Nume, a.Prenume FROM Client a WHERE a.ClientID NOT IN(SELECT a2.ClientID FROM Comenzi a2, ComenziProduse cp WHERE a2.ComandaID = cp.ComandaID)";
				String query2 = "SELECT a.Nume, a.Prenume FROM Client a WHERE a.ClientID IN(SELECT a2.ClientID FROM Comenzi a2, ComenziProduse cp WHERE a2.ComandaID = cp.ComandaID)";
				
				String aleg = (String)howMany.getSelectedItem();
				int flag = 0;
				
				if(aleg.equals("No command")){
					flag = 1;
				}
				
				try{
					PreparedStatement ps1 = conn.prepareStatement(query1);
					PreparedStatement ps2 = conn.prepareStatement(query2);
					
					if(flag == 1){
						ResultSet rs1 = ps1.executeQuery();
						table.setModel(DbUtils.resultSetToTableModel(rs1));
					}else{
						ResultSet rs2 = ps2.executeQuery();
						table.setModel(DbUtils.resultSetToTableModel(rs2));
					}
					
				}catch(Exception e2){
					e2.printStackTrace();
				}
			
			}
		});
		showClt.setForeground(Color.WHITE);
		showClt.setFont(new Font("Calibri", Font.BOLD, 17));
		showClt.setBackground(Color.MAGENTA);
		showClt.setBounds(281, 186, 259, 38);
		statistic.add(showClt);
		
		JButton shwAll = new JButton("Show All Clients");
		shwAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try{
					
					String query = "SELECT Nume,Prenume,Username,Gmail,Telefon FROM Client";
					PreparedStatement ps = conn.prepareStatement(query);
					ResultSet rs = ps.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
				}catch(Exception e1){
					e1.printStackTrace();
				}
			}
		});
		shwAll.setForeground(Color.WHITE);
		shwAll.setFont(new Font("Calibri", Font.BOLD, 17));
		shwAll.setBackground(Color.MAGENTA);
		shwAll.setBounds(552, 84, 530, 38);
		statistic.add(shwAll);
		
		micMare = new JComboBox();
		micMare.addItem("Lower Price");
		micMare.addItem("Max Price");
		micMare.setSelectedItem("Lower Price");
		micMare.setForeground(Color.WHITE);
		micMare.setFont(new Font("Calibri", Font.BOLD, 17));
		micMare.setBackground(new Color(51, 0, 102));
		micMare.setBounds(12, 319, 259, 38);
		statistic.add(micMare);
		
		categorie = new JComboBox();
		categorie.addItem("Office");
		categorie.addItem("Gaming");
		categorie.setSelectedItem("Office");
		categorie.setForeground(Color.WHITE);
		categorie.setFont(new Font("Calibri", Font.BOLD, 17));
		categorie.setBackground(new Color(51, 0, 102));
		categorie.setBounds(12, 370, 259, 38);
		statistic.add(categorie);
		
		JButton showLpt = new JButton("Show Laptops");
		showLpt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String categ = (String)categorie.getSelectedItem();
				String lowMax = (String)micMare.getSelectedItem();
				int ok = 0;
				
				String min = "SELECT a.Firma AS Laptop, a.Model, a.Pret, b.Tip AS Intrebuintare FROM Laptopuri a INNER JOIN Categorii_laptopuri b ON b.CategorieID = a.CategorieID WHERE a.Pret IN(SELECT MIN(a1.Pret) FROM Laptopuri a1 WHERE a1.CategorieID = a.CategorieID AND CategorieID = ? GROUP BY CategorieID)";
				String max = "SELECT a.Firma AS Laptop, a.Model, a.Pret, b.Tip AS Intrebuintare FROM Laptopuri a INNER JOIN Categorii_laptopuri b ON b.CategorieID = a.CategorieID WHERE a.Pret IN(SELECT MAX(a1.Pret) FROM Laptopuri a1 WHERE a1.CategorieID = a.CategorieID AND CategorieID = ? GROUP BY CategorieID)";
				
				if(categ.equals("Office") && lowMax.equals("Lower Price")){
					ok = 1;
				}else if(categ.equals("Office") && lowMax.equals("Max Price")){
					ok = 2;
				}else if(categ.equals("Gaming") && lowMax.equals("Lower Price")){
					ok = 3;
				}else if(categ.equals("Gaming") && lowMax.equals("Max Price")){
					ok = 4;
				}
				
				try{
					
					PreparedStatement ps1 = conn.prepareStatement(min);
					PreparedStatement ps2 = conn.prepareStatement(max);
					
					if(ok == 1){
						ps1.setString(1, "1");
						ResultSet rs1 = ps1.executeQuery();
						table.setModel(DbUtils.resultSetToTableModel(rs1));
					}else if(ok == 2){
						ps2.setString(1, "1");
						ResultSet rs2 = ps2.executeQuery();
						table.setModel(DbUtils.resultSetToTableModel(rs2));
					}else if(ok == 3){
						ps1.setString(1, "2");
						ResultSet rs1 = ps1.executeQuery();
						table.setModel(DbUtils.resultSetToTableModel(rs1));
					}else{
						ps2.setString(1, "2");
						ResultSet rs2 = ps2.executeQuery();
						table.setModel(DbUtils.resultSetToTableModel(rs2));
					}
					
					
					
				}catch(Exception e3){
					e3.printStackTrace();
				}
				
			}
		});
		showLpt.setForeground(Color.WHITE);
		showLpt.setFont(new Font("Calibri", Font.BOLD, 17));
		showLpt.setBackground(Color.MAGENTA);
		showLpt.setBounds(281, 319, 259, 89);
		statistic.add(showLpt);
		
		lpt = new JComboBox();
		lpt.setForeground(Color.WHITE);
		lpt.setFont(new Font("Calibri", Font.BOLD, 17));
		lpt.setBackground(new Color(51, 0, 102));
		lpt.setBounds(12, 512, 259, 38);
		statistic.add(lpt);
		
		try{
			
			String queryyy = "SELECT Model FROM Laptopuri";
			PreparedStatement psd = conn.prepareStatement(queryyy);
		
			ResultSet rss = psd.executeQuery();
			
			while(rss.next()){
				lpt.addItem(rss.getString(1));
			}
				
			}catch(Exception ez){
				ez.printStackTrace();
		}
		
		lptMDLL = new JButton("Show Clients");
		lptMDLL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String model = (String)lpt.getSelectedItem();
				
				String sql = "SELECT a.Nume, a.Prenume FROM Client a INNER JOIN Comenzi c ON c.ClientID = a.ClientID INNER JOIN ComenziProduse cp ON cp.ComandaID = c.ComandaID INNER JOIN Laptopuri lp ON lp.ProdusID = cp.ProdusID WHERE lp.ProdusID = (SELECT ProdusID FROM Laptopuri WHERE Model = ?)";
				
				try {
				
					PreparedStatement psx = conn.prepareStatement(sql);
					psx.setString(1, model);
					ResultSet rsx = psx.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rsx));
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		lptMDLL.setForeground(Color.WHITE);
		lptMDLL.setFont(new Font("Calibri", Font.BOLD, 17));
		lptMDLL.setBackground(Color.MAGENTA);
		lptMDLL.setBounds(281, 512, 259, 38);
		statistic.add(lptMDLL);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(StatisticsMenu.class.getResource("/Images/StatisticsMenu.png")));
		label.setBounds(0, 0, 1113, 736);
		statistic.add(label);
	}
}
