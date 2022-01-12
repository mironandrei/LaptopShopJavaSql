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
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class ChangePrice extends JFrame {

	private JPanel changePricee;
	private JTable table;
	private JTextField model;
	private JTextField pret;

	public ChangePrice(Connection conn){
		setResizable(false);
		initialize(conn);
	}

	/**
	 * Create the frame.
	 */
	private void initialize(Connection conn) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 700, 400);
		changePricee = new JPanel();
		changePricee.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(changePricee);
		changePricee.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(342, 54, 340, 298);
		changePricee.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		model = new JTextField();
		model.setHorizontalAlignment(SwingConstants.CENTER);
		model.setForeground(Color.WHITE);
		model.setFont(new Font("Calibri", Font.BOLD, 18));
		model.setBackground(new Color(51, 0, 102));
		model.setBounds(54, 166, 235, 32);
		changePricee.add(model);
		model.setColumns(10);
		
		pret = new JTextField();
		pret.setHorizontalAlignment(SwingConstants.CENTER);
		pret.setForeground(Color.WHITE);
		pret.setFont(new Font("Calibri", Font.BOLD, 18));
		pret.setColumns(10);
		pret.setBackground(new Color(51, 0, 102));
		pret.setBounds(54, 230, 235, 32);
		changePricee.add(pret);
		
		JLabel lblLaptopModel = new JLabel("Laptop Model:");
		lblLaptopModel.setForeground(Color.WHITE);
		lblLaptopModel.setHorizontalAlignment(SwingConstants.LEFT);
		lblLaptopModel.setFont(new Font("Calibri", Font.BOLD, 18));
		lblLaptopModel.setBounds(54, 135, 235, 31);
		changePricee.add(lblLaptopModel);
		
		JLabel lblNewPrice = new JLabel("New Price:");
		lblNewPrice.setForeground(Color.WHITE);
		lblNewPrice.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewPrice.setFont(new Font("Calibri", Font.BOLD, 18));
		lblNewPrice.setBounds(54, 198, 235, 31);
		changePricee.add(lblNewPrice);
		
		JButton btnchngprc = new JButton("Change Price");
		btnchngprc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String model1 = model.getText();
				String price = pret.getText();
				int flag = 0;
				
				ImageIcon incomplet = new ImageIcon("src/Images/puzzle.png");
				ImageIcon prc = new ImageIcon("src/Images/best-price.png");
				ImageIcon refresh = new ImageIcon("src/Images/refresh.png");
				
				String query = "UPDATE Laptopuri SET Pret = ? WHERE Model = ?";
				
				if(model.getText().isEmpty() || pret.getText().isEmpty()){
					flag = 1;
				}
				
				try{
					
					PreparedStatement ps = conn.prepareStatement(query);
					ps.setString(1, price);
					ps.setString(2, model1);
					
					String sql = "SELECT * FROM Laptopuri";
					Statement statement = conn.createStatement();
					ResultSet rs = statement.executeQuery(sql);
					
					
					
					while(rs.next()){

						if(model.getText().equals(rs.getString(3))){
							flag = 2;
						}
					}
					
					if(flag == 1){
						JOptionPane.showMessageDialog(null, "Fill all of the fields !","SOMETHING IS MISSING",JOptionPane.INFORMATION_MESSAGE,incomplet);
					}else if(flag == 2){
						ps.execute();
						JOptionPane.showMessageDialog(null, "PRICE WAS CHANGED!"," ",JOptionPane.INFORMATION_MESSAGE,prc);
						model.setText(null);
						pret.setText(null);
					}else if(flag == 0){
						JOptionPane.showMessageDialog(null, "MODEL NOT FOUND!"," ",JOptionPane.INFORMATION_MESSAGE,refresh);
						model.setText(null);
						pret.setText(null);
					}
						
				}catch(Exception ee){
					ee.printStackTrace();
				}
			}
		});
		btnchngprc.setForeground(Color.WHITE);
		btnchngprc.setBackground(Color.MAGENTA);
		btnchngprc.setFont(new Font("Calibri", Font.BOLD, 18));
		btnchngprc.setBounds(54, 275, 235, 32);
		changePricee.add(btnchngprc);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnExit.setForeground(Color.WHITE);
		btnExit.setFont(new Font("Calibri", Font.BOLD, 18));
		btnExit.setBackground(Color.MAGENTA);
		btnExit.setBounds(54, 320, 235, 32);
		changePricee.add(btnExit);
		
		JButton btnShowPrices = new JButton("Show Prices");
		btnShowPrices.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			try{
				
				String query = "SELECT Firma,Model,Pret FROM Laptopuri";
				PreparedStatement ps = conn.prepareStatement(query);
				ResultSet rs = ps.executeQuery();
				table.setModel(DbUtils.resultSetToTableModel(rs));
				
			}catch(Exception ee){
				ee.printStackTrace();
			}
			}
		});
		btnShowPrices.setForeground(Color.WHITE);
		btnShowPrices.setFont(new Font("Calibri", Font.BOLD, 18));
		btnShowPrices.setBackground(Color.MAGENTA);
		btnShowPrices.setBounds(342, 13, 340, 32);
		changePricee.add(btnShowPrices);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(ChangePrice.class.getResource("/Images/ChangePrice.png")));
		label.setBounds(0, 0, 710, 386);
		changePricee.add(label);
	}
}
