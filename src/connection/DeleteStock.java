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

import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

public class DeleteStock extends JFrame {

	private JPanel dltStk;
	private JTextField nume;
	private JTable table;

	public DeleteStock(Connection conn){
		setResizable(false);
		initialize(conn);
	}

	/**
	 * Create the frame.
	 */
	private void initialize(Connection conn){
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 700, 300);
		dltStk = new JPanel();
		dltStk.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(dltStk);
		dltStk.setLayout(null);
		
		nume = new JTextField();
		nume.setForeground(Color.WHITE);
		nume.setBackground(new Color(51, 0, 102));
		nume.setFont(new Font("Calibri", Font.BOLD, 17));
		nume.setHorizontalAlignment(SwingConstants.CENTER);
		nume.setBounds(29, 118, 266, 36);
		dltStk.add(nume);
		nume.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(310, 53, 372, 199);
		dltStk.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Show Laptops");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try{
					String query = "SELECT Firma,Model FROM Laptopuri";
					PreparedStatement ps = conn.prepareStatement(query);
					ResultSet rs = ps.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
				}catch(Exception e1){
					e1.printStackTrace();
				}
			
			}
		});
		btnNewButton.setBackground(Color.MAGENTA);
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("Calibri", Font.BOLD, 18));
		btnNewButton.setBounds(310, 13, 372, 36);
		dltStk.add(btnNewButton);
		
		JButton btnDeleteLaptopFrom = new JButton("Delete Laptop from Stock");
		btnDeleteLaptopFrom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String mod = nume.getText();
				int ok = 0;
				int okk = 0;
				String produs = null;
				int reply;
				ImageIcon incomplet = new ImageIcon("src/Images/puzzle.png");
				ImageIcon refresh = new ImageIcon("src/Images/refresh.png");
				ImageIcon del = new ImageIcon("src/Images/delete.png");
				ImageIcon question = new ImageIcon("src/Images/question.png");
				ImageIcon smile = new ImageIcon("src/Images/smile.png");
				
				String query = "DELETE FROM Laptopuri WHERE Model = ?";
				String que = "DELETE FROM SpecificatiiProduse WHERE ProdusID = ?";
				String query2 = "DELETE FROM ComenziProduse WHERE ProdusID = ?";
				
				if(nume.getText().isEmpty()){
					ok = 1;
				}
				
				try{
					
					
					String sql = "SELECT * FROM Laptopuri";
					Statement statement = conn.createStatement();
					ResultSet rs = statement.executeQuery(sql);
					
					String sql2 = "SELECT * FROM SpecificatiiProduse";
					Statement statement2 = conn.createStatement();
					ResultSet rs2 = statement2.executeQuery(sql2);
					
					while(rs.next()){
						if(mod.equals(rs.getString(3))){
							produs = rs.getString(1);
							ok = 2;
						}
					}
					
					
					PreparedStatement ps = conn.prepareStatement(query);
					ps.setString(1, mod);
					
					PreparedStatement ps2 = conn.prepareStatement(que);
					ps2.setString(1, produs);
					
					PreparedStatement ps3 = conn.prepareStatement(query2);
					ps3.setString(1, produs);
					
					if(ok == 1){
						JOptionPane.showMessageDialog(null, "Fill all of the fields !","SOMETHING IS MISSING",JOptionPane.INFORMATION_MESSAGE,incomplet);
					}else if(ok == 2){
						reply = JOptionPane.showConfirmDialog(null,"ARE YOU SURE?"," ",JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,question);
						if(reply == JOptionPane.YES_OPTION){

							while(rs2.next()){
								if(produs.equals(rs2.getString(2))){
									okk = 2;
									continue;
								}
							}
							if(okk == 2){
								ps2.execute();
							}
							ps3.execute();
							ps.execute();
							JOptionPane.showMessageDialog(null, "DONE"," ",JOptionPane.INFORMATION_MESSAGE,del);
							nume.setText(null);
						}else{
							JOptionPane.showMessageDialog(null,"OK, HAVE A GOOD DAY"," ",JOptionPane.INFORMATION_MESSAGE,smile);
							}
					}else{
						JOptionPane.showMessageDialog(null, "DON'T FIND THIS MODEL"," ",JOptionPane.INFORMATION_MESSAGE,refresh);
						nume.setText(null);
					}
					
				}catch(Exception e2){
					e2.printStackTrace();
				}
				
			}
		});
		btnDeleteLaptopFrom.setForeground(Color.WHITE);
		btnDeleteLaptopFrom.setFont(new Font("Calibri", Font.BOLD, 18));
		btnDeleteLaptopFrom.setBackground(Color.MAGENTA);
		btnDeleteLaptopFrom.setBounds(29, 167, 266, 36);
		dltStk.add(btnDeleteLaptopFrom);
		
		JButton btnNewButton_1_1 = new JButton("Exit");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1_1.setForeground(Color.WHITE);
		btnNewButton_1_1.setFont(new Font("Calibri", Font.BOLD, 18));
		btnNewButton_1_1.setBackground(Color.MAGENTA);
		btnNewButton_1_1.setBounds(29, 216, 266, 36);
		dltStk.add(btnNewButton_1_1);
		
		JLabel lblInsertTheModel = new JLabel("Insert the model");
		lblInsertTheModel.setForeground(Color.WHITE);
		lblInsertTheModel.setFont(new Font("Calibri", Font.BOLD, 18));
		lblInsertTheModel.setHorizontalAlignment(SwingConstants.CENTER);
		lblInsertTheModel.setBounds(29, 88, 266, 30);
		dltStk.add(lblInsertTheModel);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(DeleteStock.class.getResource("/Images/DeleteStock.png")));
		label.setBounds(0, 0, 716, 286);
		dltStk.add(label);
	}
}
