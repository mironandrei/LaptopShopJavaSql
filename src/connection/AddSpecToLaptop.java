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
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddSpecToLaptop extends JFrame {

	private JPanel addSpecToLaptop;
	private JTextField specName;
	private JTextField modelName;

	public AddSpecToLaptop(Connection conn){
		setResizable(false);
		initialize(conn);
	}

	/**
	 * Create the frame.
	 */
	private void initialize(Connection conn) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 300);
		addSpecToLaptop = new JPanel();
		addSpecToLaptop.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(addSpecToLaptop);
		addSpecToLaptop.setLayout(null);
		
		specName = new JTextField();
		specName.setHorizontalAlignment(SwingConstants.CENTER);
		specName.setBackground(new Color(51, 0, 102));
		specName.setForeground(Color.WHITE);
		specName.setFont(new Font("Calibri", Font.BOLD, 18));
		specName.setBounds(60, 121, 221, 38);
		addSpecToLaptop.add(specName);
		specName.setColumns(10);
		
		modelName = new JTextField();
		modelName.setHorizontalAlignment(SwingConstants.CENTER);
		modelName.setBackground(new Color(51, 0, 102));
		modelName.setForeground(Color.WHITE);
		modelName.setFont(new Font("Calibri", Font.BOLD, 18));
		modelName.setColumns(10);
		modelName.setBounds(316, 121, 221, 38);
		addSpecToLaptop.add(modelName);
		
		JLabel lblSpecificationName = new JLabel("Specification Name");
		lblSpecificationName.setForeground(Color.WHITE);
		lblSpecificationName.setHorizontalAlignment(SwingConstants.CENTER);
		lblSpecificationName.setFont(new Font("Calibri", Font.BOLD, 18));
		lblSpecificationName.setBounds(60, 93, 221, 26);
		addSpecToLaptop.add(lblSpecificationName);
		
		JLabel lblLaptopModel = new JLabel("Laptop Model");
		lblLaptopModel.setForeground(Color.WHITE);
		lblLaptopModel.setHorizontalAlignment(SwingConstants.CENTER);
		lblLaptopModel.setFont(new Font("Calibri", Font.BOLD, 18));
		lblLaptopModel.setBounds(316, 93, 221, 26);
		addSpecToLaptop.add(lblLaptopModel);
		
		JButton btnNewButton = new JButton("Add Spec To Laptop");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String name = specName.getText();
				String model = modelName.getText();
				int flag = 0;
				int flag1 = 0;
				int ok = 0;
				
				String laptopid = null;
				String specid = null;
				
				ImageIcon refresh = new ImageIcon("src/Images/refresh.png");
				ImageIcon ad = new ImageIcon("src/Images/added.png");
				ImageIcon ch = new ImageIcon("src/Images/check.png");
				ImageIcon incomplet = new ImageIcon("src/Images/puzzle.png");
				
				String query = "INSERT INTO SpecificatiiProduse(SpecificatiiID, ProdusID) VALUES (?,?)";
				
				if(name.isEmpty() || model.isEmpty()){
					flag = 1;
				}
				
				try{

					String sql = "SELECT * FROM Laptopuri";
					Statement statement = conn.createStatement();
					ResultSet rs = statement.executeQuery(sql);
					
					String sql1 = "SELECT * FROM Specificatii";
					Statement statement1 = conn.createStatement();
					ResultSet rs1 = statement1.executeQuery(sql1);
					
					String sql2 = "SELECT * FROM SpecificatiiProduse";
					Statement statement2 = conn.createStatement();
					ResultSet rs2 = statement2.executeQuery(sql2);
					
					while(rs.next()){
						if(modelName.getText().equals(rs.getString(3))){
							laptopid = rs.getString(1);
							flag = 3;
						}
					}

					while(rs1.next()){
						if(specName.getText().equals(rs1.getString(2))){
							specid = rs1.getString(1);
							flag1 = 4;
						}
					}

					PreparedStatement ps = conn.prepareStatement(query);
					ps.setString(1, specid);
					ps.setString(2, laptopid);
	
					if(flag == 1){
						JOptionPane.showMessageDialog(null, "Fill all of the fields !","SOMETHING IS MISSING",JOptionPane.INFORMATION_MESSAGE,incomplet);
					}else if(flag == 0 || flag == 3 && flag1 != 4 || flag != 3 && flag1 == 4){
						JOptionPane.showMessageDialog(null, "Model or spec name doesn't match","ERROR", JOptionPane.INFORMATION_MESSAGE,refresh);
						specName.setText(null);
						modelName.setText(null);
					}else if(flag == 3 && flag1 == 4){
						
						while(rs2.next()){
							if(laptopid.equals(rs2.getString(2)) && specid.equals(rs2.getString(1))){
								JOptionPane.showMessageDialog(null, "This laptop already has this specification"," ",JOptionPane.INFORMATION_MESSAGE,ch);
								specName.setText(null);
								modelName.setText(null);
								ok = 1;
								continue;
							}
						}
						if(ok == 0){
							ps.execute();
							JOptionPane.showMessageDialog(null, "SPEC ADDED TO LAPTOP"," ",JOptionPane.INFORMATION_MESSAGE,ad);
							specName.setText(null);
							modelName.setText(null);
						}
					}
					
					
					
				}catch(Exception e1){
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(Color.MAGENTA);
		btnNewButton.setFont(new Font("Calibri", Font.BOLD, 18));
		btnNewButton.setBounds(60, 172, 477, 38);
		addSpecToLaptop.add(btnNewButton);
		
		JLabel lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon(AddSpecToLaptop.class.getResource("/Images/AddSpecToLaptop.png")));
		lblBackground.setBounds(0, 0, 611, 282);
		addSpecToLaptop.add(lblBackground);
	}
}
