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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class SearchByFilters extends JFrame {

	private JPanel filters;
	private JTable table;
	private JTextField low;
	private JTextField max;

	public SearchByFilters(Connection conn){
		setResizable(false);
		initialize(conn);
	}
	
	
	private void initialize(Connection conn) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1100, 750);
		filters = new JPanel();
		filters.setBackground(Color.LIGHT_GRAY);
		filters.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(filters);
		filters.setLayout(null);
		
		JButton btnBackToMain = new JButton("Back to Main Menu");
		btnBackToMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClientMenu clnt = new ClientMenu(conn);
				clnt.setVisible(true);
				dispose();
			}
		});
		btnBackToMain.setBackground(Color.MAGENTA);
		btnBackToMain.setForeground(Color.WHITE);
		btnBackToMain.setFont(new Font("Calibri", Font.BOLD, 18));
		btnBackToMain.setBounds(603, 668, 479, 34);
		filters.add(btnBackToMain);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(603, 174, 479, 481);
		filters.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JComboBox procesor = new JComboBox();
		
		String query = "SELECT Nume FROM Specificatii WHERE Descriere = 'procesor'";
		
		try{
			
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				procesor.addItem(rs.getString(1));
			}
			
		}catch(Exception ez){
			ez.printStackTrace();
		}
		
		
		procesor.setBackground(new Color(51, 0, 102));
		procesor.setForeground(Color.WHITE);
		procesor.setFont(new Font("Calibri", Font.BOLD, 17));
		procesor.setBounds(164, 184, 158, 34);
		filters.add(procesor);
		
		JComboBox placa_video = new JComboBox();
		
		String query1 = "SELECT Nume FROM Specificatii WHERE Descriere = 'placa video'";
		
		try{
			
			PreparedStatement ps1 = conn.prepareStatement(query1);
			ResultSet rs1 = ps1.executeQuery();
			
			while(rs1.next()){
				placa_video.addItem(rs1.getString(1));
			}
			
		}catch(Exception e1){
			e1.printStackTrace();
		}
		
		placa_video.setForeground(Color.WHITE);
		placa_video.setFont(new Font("Calibri", Font.BOLD, 17));
		placa_video.setBackground(new Color(51, 0, 102));
		placa_video.setBounds(164, 231, 158, 34);
		filters.add(placa_video);
		
		JComboBox ram = new JComboBox();
		
		String query2 = "SELECT Nume FROM Specificatii WHERE Descriere = 'ram'";
		
		try{
			
			PreparedStatement ps2 = conn.prepareStatement(query2);
			ResultSet rs2 = ps2.executeQuery();
			
			while(rs2.next()){
				ram.addItem(rs2.getString(1));
			}
			
		}catch(Exception e2){
			e2.printStackTrace();
		}
		
		ram.setForeground(Color.WHITE);
		ram.setFont(new Font("Calibri", Font.BOLD, 17));
		ram.setBackground(new Color(51, 0, 102));
		ram.setBounds(164, 278, 158, 34);
		filters.add(ram);
		
		JComboBox ssd = new JComboBox();
		
		String query3 = "SELECT Nume FROM Specificatii WHERE Descriere = 'ssd'";
		
		try{
			
			PreparedStatement ps3 = conn.prepareStatement(query3);
			ResultSet rs3 = ps3.executeQuery();
			
			while(rs3.next()){
				ssd.addItem(rs3.getString(1));
			}
			
		}catch(Exception e3){
			e3.printStackTrace();
		}
		
		ssd.setForeground(Color.WHITE);
		ssd.setFont(new Font("Calibri", Font.BOLD, 17));
		ssd.setBackground(new Color(51, 0, 102));
		ssd.setBounds(164, 325, 158, 34);
		filters.add(ssd);
		
		JComboBox culoare = new JComboBox();
		
		String query4 = "SELECT Nume FROM Specificatii WHERE Descriere = 'culoare'";
		
		try{
			
			PreparedStatement ps4 = conn.prepareStatement(query4);
			ResultSet rs4 = ps4.executeQuery();
			
			while(rs4.next()){
				culoare.addItem(rs4.getString(1));
			}
			
		}catch(Exception e4){
			e4.printStackTrace();
		}
		
		culoare.setForeground(Color.WHITE);
		culoare.setFont(new Font("Calibri", Font.BOLD, 17));
		culoare.setBackground(new Color(51, 0, 102));
		culoare.setBounds(164, 372, 158, 34);
		filters.add(culoare);
		
		JLabel lblProcesor = new JLabel("Processor:");
		lblProcesor.setForeground(Color.WHITE);
		lblProcesor.setFont(new Font("Calibri", Font.BOLD, 20));
		lblProcesor.setBounds(22, 183, 138, 34);
		filters.add(lblProcesor);
		
		JLabel lblPlacaVideo = new JLabel("Graphics:");
		lblPlacaVideo.setForeground(Color.WHITE);
		lblPlacaVideo.setFont(new Font("Calibri", Font.BOLD, 20));
		lblPlacaVideo.setBounds(22, 230, 138, 34);
		filters.add(lblPlacaVideo);
		
		JLabel lblMemorieRam = new JLabel("RAM Memory:");
		lblMemorieRam.setForeground(Color.WHITE);
		lblMemorieRam.setFont(new Font("Calibri", Font.BOLD, 20));
		lblMemorieRam.setBounds(22, 277, 138, 34);
		filters.add(lblMemorieRam);
		
		JLabel lblMemorie = new JLabel("Storage:");
		lblMemorie.setForeground(Color.WHITE);
		lblMemorie.setFont(new Font("Calibri", Font.BOLD, 20));
		lblMemorie.setBounds(22, 324, 138, 34);
		filters.add(lblMemorie);
		
		JLabel lblCuloare = new JLabel("Colour:");
		lblCuloare.setForeground(Color.WHITE);
		lblCuloare.setFont(new Font("Calibri", Font.BOLD, 20));
		lblCuloare.setBounds(22, 371, 138, 34);
		filters.add(lblCuloare);
		
		JButton btnSearch = new JButton("Search by Processor");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String proc = (String)procesor.getSelectedItem();
				
				String sql = "SELECT a.Firma AS Laptop, a.Model, a.Pret, ctg.Tip AS Intrebuintare FROM Laptopuri a INNER JOIN Categorii_laptopuri ctg ON ctg.CategorieID = a.CategorieID INNER JOIN SpecificatiiProduse sp ON a.ProdusID = sp.ProdusID INNER JOIN Specificatii s ON sp.SpecificatiiID = s.SpecificatiiID WHERE s.Nume = ?";
				
				try{
					PreparedStatement ps5 = conn.prepareStatement(sql);
					ps5.setString(1, proc);
					ResultSet rs5 = ps5.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs5));
					
				}catch(Exception e5){
					e5.printStackTrace();
				}
				
			}
		});
		btnSearch.setForeground(Color.WHITE);
		btnSearch.setFont(new Font("Calibri", Font.BOLD, 18));
		btnSearch.setBackground(Color.MAGENTA);
		btnSearch.setBounds(353, 183, 238, 34);
		filters.add(btnSearch);
		
		JButton btnSearchByGraphics = new JButton("Search by Graphics");
		btnSearchByGraphics.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String graph = (String)placa_video.getSelectedItem();
				
				String sql1 = "SELECT a.Firma AS Laptop, a.Model, a.Pret, ctg.Tip AS Intrebuintare FROM Laptopuri a INNER JOIN Categorii_laptopuri ctg ON ctg.CategorieID = a.CategorieID INNER JOIN SpecificatiiProduse sp ON a.ProdusID = sp.ProdusID INNER JOIN Specificatii s ON sp.SpecificatiiID = s.SpecificatiiID WHERE s.Nume = ?";
				
				try{
					PreparedStatement ps6 = conn.prepareStatement(sql1);
					ps6.setString(1, graph);
					ResultSet rs6 = ps6.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs6));
					
				}catch(Exception e6){
					e6.printStackTrace();
				}
				
			}
		});
		btnSearchByGraphics.setForeground(Color.WHITE);
		btnSearchByGraphics.setFont(new Font("Calibri", Font.BOLD, 18));
		btnSearchByGraphics.setBackground(Color.MAGENTA);
		btnSearchByGraphics.setBounds(353, 230, 238, 34);
		filters.add(btnSearchByGraphics);
		
		JButton btnSearchByRam = new JButton("Search by RAM");
		btnSearchByRam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String rami = (String)ram.getSelectedItem();
				
				String sql2 = "SELECT a.Firma AS Laptop, a.Model, a.Pret, ctg.Tip AS Intrebuintare FROM Laptopuri a INNER JOIN Categorii_laptopuri ctg ON ctg.CategorieID = a.CategorieID INNER JOIN SpecificatiiProduse sp ON a.ProdusID = sp.ProdusID INNER JOIN Specificatii s ON sp.SpecificatiiID = s.SpecificatiiID WHERE s.Nume = ?";
				
				try{
					PreparedStatement ps7 = conn.prepareStatement(sql2);
					ps7.setString(1, rami);
					ResultSet rs7 = ps7.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs7));
					
				}catch(Exception e7){
					e7.printStackTrace();
				}
				
			}
		});
		btnSearchByRam.setForeground(Color.WHITE);
		btnSearchByRam.setFont(new Font("Calibri", Font.BOLD, 18));
		btnSearchByRam.setBackground(Color.MAGENTA);
		btnSearchByRam.setBounds(353, 277, 238, 34);
		filters.add(btnSearchByRam);
		
		JButton btnSearchBy = new JButton("Search by Storage");
		btnSearchBy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String storage = (String)ssd.getSelectedItem();
				
				String sql3 = "SELECT a.Firma AS Laptop, a.Model, a.Pret, ctg.Tip AS Intrebuintare FROM Laptopuri a INNER JOIN Categorii_laptopuri ctg ON ctg.CategorieID = a.CategorieID INNER JOIN SpecificatiiProduse sp ON a.ProdusID = sp.ProdusID INNER JOIN Specificatii s ON sp.SpecificatiiID = s.SpecificatiiID WHERE s.Nume = ?";
				
				try{
					PreparedStatement ps8 = conn.prepareStatement(sql3);
					ps8.setString(1, storage);
					ResultSet rs8 = ps8.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs8));
					
				}catch(Exception e8){
					e8.printStackTrace();
				}
				
			}
		});
		btnSearchBy.setForeground(Color.WHITE);
		btnSearchBy.setFont(new Font("Calibri", Font.BOLD, 18));
		btnSearchBy.setBackground(Color.MAGENTA);
		btnSearchBy.setBounds(353, 325, 238, 34);
		filters.add(btnSearchBy);
		
		JButton btnSearchByColour = new JButton("Search by Colour");
		btnSearchByColour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String colour = (String)culoare.getSelectedItem();
				
				String sql4 = "SELECT a.Firma AS Laptop, a.Model, a.Pret, ctg.Tip AS Intrebuintare FROM Laptopuri a INNER JOIN Categorii_laptopuri ctg ON ctg.CategorieID = a.CategorieID INNER JOIN SpecificatiiProduse sp ON a.ProdusID = sp.ProdusID INNER JOIN Specificatii s ON sp.SpecificatiiID = s.SpecificatiiID WHERE s.Nume = ?";
				
				try{
					PreparedStatement ps9 = conn.prepareStatement(sql4);
					ps9.setString(1, colour);
					ResultSet rs9 = ps9.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs9));
					
				}catch(Exception e9){
					e9.printStackTrace();
				}
			}
		});
		btnSearchByColour.setForeground(Color.WHITE);
		btnSearchByColour.setFont(new Font("Calibri", Font.BOLD, 18));
		btnSearchByColour.setBackground(Color.MAGENTA);
		btnSearchByColour.setBounds(353, 372, 238, 34);
		filters.add(btnSearchByColour);
		
		JButton btnShowAllLaptops = new JButton("Show All Laptops");
		btnShowAllLaptops.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try{
					String queryy = "SELECT a.Firma, a.Model, a.Pret, b.Tip AS Utilizare FROM Laptopuri a INNER JOIN Categorii_laptopuri b ON a.CategorieID = b.CategorieID";

					PreparedStatement pss = conn.prepareStatement(queryy);
					ResultSet rss = pss.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rss));
				}catch(Exception e1s){
					e1s.printStackTrace();
				}
				
			}
		});
		btnShowAllLaptops.setForeground(Color.WHITE);
		btnShowAllLaptops.setFont(new Font("Calibri", Font.BOLD, 18));
		btnShowAllLaptops.setBackground(Color.MAGENTA);
		btnShowAllLaptops.setBounds(603, 127, 479, 34);
		filters.add(btnShowAllLaptops);
		
		low = new JTextField();
		low.setHorizontalAlignment(SwingConstants.CENTER);
		low.setForeground(Color.WHITE);
		low.setFont(new Font("Calibri", Font.BOLD, 17));
		low.setBackground(new Color(51, 0, 102));
		low.setBounds(119, 526, 104, 34);
		filters.add(low);
		low.setColumns(10);
		
		max = new JTextField();
		max.setHorizontalAlignment(SwingConstants.CENTER);
		max.setForeground(Color.WHITE);
		max.setFont(new Font("Calibri", Font.BOLD, 17));
		max.setColumns(10);
		max.setBackground(new Color(51, 0, 102));
		max.setBounds(277, 526, 104, 34);
		filters.add(max);
		
		JLabel lblBetween = new JLabel("Between");
		lblBetween.setHorizontalAlignment(SwingConstants.CENTER);
		lblBetween.setForeground(Color.WHITE);
		lblBetween.setFont(new Font("Calibri", Font.BOLD, 20));
		lblBetween.setBounds(22, 526, 96, 34);
		filters.add(lblBetween);
		
		JLabel lblAnd = new JLabel("and");
		lblAnd.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnd.setForeground(Color.WHITE);
		lblAnd.setFont(new Font("Calibri", Font.BOLD, 20));
		lblAnd.setBounds(223, 526, 53, 34);
		filters.add(lblAnd);
		
		JButton btnSearchByPrice = new JButton("Search by Price");
		btnSearchByPrice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String valMin = low.getText();
				String valMax = max.getText();
				int flag = 0;
				ImageIcon incomplet = new ImageIcon("src/Images/puzzle.png");
				
				String queryz = "SELECT a.Firma AS Laptop, a.Model, a.Pret, cl.Tip AS Utilizare FROM  Laptopuri a INNER JOIN Categorii_laptopuri cl ON cl.CategorieID = a.CategorieID WHERE a.Pret > ? AND a.Pret < ? ORDER BY a.Pret";
				
				if(valMin.isEmpty() || valMax.isEmpty()){
					flag = 1;
				}
				
				try{
					
					if(flag == 1){
						JOptionPane.showMessageDialog(null, "Fill all of the fields !","SOMETHING IS MISSING",JOptionPane.INFORMATION_MESSAGE,incomplet);
					}else{
						PreparedStatement pss = conn.prepareStatement(queryz);
						pss.setString(1,valMin);
						pss.setString(2, valMax);
						ResultSet rss = pss.executeQuery();
						table.setModel(DbUtils.resultSetToTableModel(rss));
					}
					
				}catch(Exception ep){
					ep.printStackTrace();
				}
			}
		});
		btnSearchByPrice.setForeground(Color.WHITE);
		btnSearchByPrice.setFont(new Font("Calibri", Font.BOLD, 18));
		btnSearchByPrice.setBackground(Color.MAGENTA);
		btnSearchByPrice.setBounds(393, 526, 198, 34);
		filters.add(btnSearchByPrice);
		
		JButton btnMost = new JButton("Best-Selling Laptops");
		btnMost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String laptopid = null;
				String nrVanzari = null;
				String laptop = null;
				
				String queryh = "SELECT cp.Firma AS Laptop, cp.Model, cp.Pret, COUNT(*) AS NumarVanzari FROM ComenziProduse a INNER JOIN Laptopuri cp ON cp.ProdusID = a.ProdusID GROUP BY a.ProdusID,cp.Model,cp.Firma,cp.Pret ORDER BY COUNT(*) DESC";
				
				try{
					
					PreparedStatement psz = conn.prepareStatement(queryh);
					ResultSet rsz = psz.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rsz));
					
				}catch(Exception e1){
					e1.printStackTrace();
				}
			
			}
		});
		btnMost.setForeground(Color.WHITE);
		btnMost.setFont(new Font("Calibri", Font.BOLD, 18));
		btnMost.setBackground(Color.MAGENTA);
		btnMost.setBounds(22, 668, 569, 34);
		filters.add(btnMost);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(SearchByFilters.class.getResource("/Images/SearchByFilters.png")));
		label.setBounds(0, 0, 1112, 729);
		filters.add(label);
	}
}
