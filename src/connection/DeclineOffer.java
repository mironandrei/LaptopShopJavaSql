package connection;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class DeclineOffer extends JFrame {

	private JPanel decline;
	private JComboBox comboBox;

	public DeclineOffer(Connection conn){
		setResizable(false);
		initialize(conn);
	}

	/**
	 * Create the frame.
	 */
	private void initialize(Connection conn) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		decline = new JPanel();
		decline.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(decline);
		decline.setLayout(null);
		
		
		comboBox = new JComboBox();
		comboBox.setForeground(Color.WHITE);
		comboBox.setFont(new Font("Calibri", Font.BOLD, 18));
		comboBox.setBackground(new Color(51, 0, 102));
		comboBox.setBounds(82, 85, 274, 42);
		decline.add(comboBox);
		
		try{
				
			String customer = Login.us1;
			String queryyy = "SELECT c.Model From Comenzi a INNER JOIN Client b ON b.ClientID = a.ClientID INNER JOIN ComenziProduse ap ON ap.ComandaID = a.ComandaID INNER JOIN Laptopuri c ON c.ProdusID = ap.ProdusID WHERE b.Username = ?";
			PreparedStatement psd = conn.prepareStatement(queryyy);
			psd.setString(1, customer);
		
			ResultSet rss = psd.executeQuery();
				
			while(rss.next()){
				comboBox.addItem(rss.getString(1));
			}
				
			}catch(Exception ez){
				ez.printStackTrace();
		}
		
		JButton dec = new JButton("Decline");
		dec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String query1 = "DELETE FROM Comenzi WHERE ComandaID = ?";
				String query2 = "DELETE FROM ComenziProduse WHERE ComandaID = ?";
				
				
				String numeModel = (String)comboBox.getSelectedItem();
				int ok = 0;
				String client = Login.us1;
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
						
						String customer = Login.us1;
						String queryyy = "SELECT c.Model From Comenzi a INNER JOIN Client b ON b.ClientID = a.ClientID INNER JOIN ComenziProduse ap ON ap.ComandaID = a.ComandaID INNER JOIN Laptopuri c ON c.ProdusID = ap.ProdusID WHERE b.Username = ?";
						
						PreparedStatement psd = conn.prepareStatement(queryyy);
						psd.setString(1, customer);
						ResultSet rss = psd.executeQuery();
						
						while(rss.next()){
							comboBox.addItem(rss.getString(1));
						}
						
					}else{
						JOptionPane.showMessageDialog(null, "PLEASE TRY AGAIN"," ",JOptionPane.INFORMATION_MESSAGE,refresh);
					}
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
				
			}
		});
		dec.setForeground(Color.WHITE);
		dec.setFont(new Font("Calibri", Font.BOLD, 18));
		dec.setBackground(Color.MAGENTA);
		dec.setBounds(226, 140, 130, 42);
		decline.add(dec);
		
		JButton ext = new JButton("Exit");
		ext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		ext.setForeground(Color.WHITE);
		ext.setFont(new Font("Calibri", Font.BOLD, 18));
		ext.setBackground(Color.MAGENTA);
		ext.setBounds(82, 192, 274, 42);
		decline.add(ext);
		
		JLabel lblInsertLaptopModel = new JLabel("Insert Laptop Model");
		lblInsertLaptopModel.setForeground(Color.WHITE);
		lblInsertLaptopModel.setHorizontalAlignment(SwingConstants.CENTER);
		lblInsertLaptopModel.setFont(new Font("Calibri", Font.BOLD, 18));
		lblInsertLaptopModel.setBounds(82, 55, 274, 29);
		decline.add(lblInsertLaptopModel);
		
		JButton refr = new JButton("Refresh");
		refr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try{
					
					comboBox.removeAllItems();
					
					String customer = Login.us1;
					String queryyy = "SELECT c.Model From Comenzi a INNER JOIN Client b ON b.ClientID = a.ClientID INNER JOIN ComenziProduse ap ON ap.ComandaID = a.ComandaID INNER JOIN Laptopuri c ON c.ProdusID = ap.ProdusID WHERE b.Username = ?";
					
					PreparedStatement psds = conn.prepareStatement(queryyy);
					psds.setString(1, customer);
					ResultSet rsss = psds.executeQuery();
					
					while(rsss.next()){
						comboBox.addItem(rsss.getString(1));
					}
					
				}catch(Exception ep){
					ep.printStackTrace();
				}
			}
		});
		refr.setForeground(Color.WHITE);
		refr.setFont(new Font("Calibri", Font.BOLD, 18));
		refr.setBackground(Color.MAGENTA);
		refr.setBounds(82, 140, 130, 42);
		decline.add(refr);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(DeclineOffer.class.getResource("/Images/Decline.png")));
		label.setBounds(0, 0, 463, 285);
		decline.add(label);
		
		
	}
}
