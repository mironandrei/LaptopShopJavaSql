package connection;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ClientMenu extends JFrame {

	private JPanel ClientMenu;

	public ClientMenu(Connection conn){
		setResizable(false);
		initialize(conn);
	}

	/**
	 * Create the frame.
	 */
	private void initialize(Connection conn) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 960, 540);
		ClientMenu = new JPanel();
		ClientMenu.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(ClientMenu);
		ClientMenu.setLayout(null);
		
		JButton newPass = new JButton("Change Password");
		newPass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ChangePassword chng = new ChangePassword(conn);
				chng.setVisible(true);
				dispose();
			}
		});
		newPass.setBackground(Color.MAGENTA);
		newPass.setForeground(Color.WHITE);
		newPass.setFont(new Font("Calibri", Font.BOLD, 18));
		newPass.setBounds(36, 393, 178, 44);
		ClientMenu.add(newPass);
		
		JButton delAcc = new JButton("Delete Account");
		delAcc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String query = "DELETE FROM Client WHERE Username = ?";
				int flag = 0;
				String us2 = Login.us1;
				ImageIcon account = new ImageIcon("src/Images/AccountDeleted.png");
				ImageIcon question = new ImageIcon("src/Images/question.png");
				ImageIcon smile = new ImageIcon("src/Images/smile.png");
				int reply;
				
				String query1 = "DELETE FROM Comenzi WHERE ClientID = ?";
				String clientid = null;
				
				try {
					PreparedStatement ps = conn.prepareStatement(query);
					ps.setString(1, us2);
					
					String sql = "SELECT * FROM Client";
					Statement statement = conn.createStatement();
					ResultSet rs = statement.executeQuery(sql);
					
					while(rs.next())
					{
						if(us2.equals(rs.getString(2))){
							clientid = rs.getString(1);
						}
						
						if(us2.isEmpty()){
							flag = 1;
						}else{
							flag = 2;
						}
					}
					
					PreparedStatement ps1 = conn.prepareStatement(query1);
					ps1.setString(1, clientid);
					
					if(flag == 2){
						reply = JOptionPane.showConfirmDialog(null,"ARE YOU SURE?"," ",JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,question);
						if(reply == JOptionPane.YES_OPTION){
							ps1.execute();
							ps.execute();
							JOptionPane.showMessageDialog(null,"ACCOUNT DELETED"," ",JOptionPane.INFORMATION_MESSAGE,account);
							Login log = new Login(conn);
							log.setVisible(true);
							dispose();
						}else{
							JOptionPane.showMessageDialog(null,"OK, HAVE A GOOD DAY"," ",JOptionPane.INFORMATION_MESSAGE,smile);
						}
					}else if(flag == 1){
						JOptionPane.showMessageDialog(null,"error");
					}
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		delAcc.setForeground(Color.WHITE);
		delAcc.setBackground(Color.MAGENTA);
		delAcc.setFont(new Font("Calibri", Font.BOLD, 18));
		delAcc.setBounds(226, 393, 178, 44);
		ClientMenu.add(delAcc);
		
		JButton VerStock = new JButton("Stock Verify");
		VerStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShowStock ss = new ShowStock(conn);
				ss.setVisible(true);
				dispose();
			}
		});
		VerStock.setForeground(Color.WHITE);
		VerStock.setBackground(Color.MAGENTA);
		VerStock.setFont(new Font("Calibri", Font.BOLD, 18));
		VerStock.setBounds(416, 393, 178, 44);
		ClientMenu.add(VerStock);
		
		JButton logOut = new JButton("Log Out");
		logOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login log = new Login(conn);
				log.setVisible(true);
				dispose();
			}
		});
		logOut.setForeground(Color.WHITE);
		logOut.setFont(new Font("Calibri", Font.BOLD, 18));
		logOut.setBackground(Color.MAGENTA);
		logOut.setBounds(799, 393, 116, 44);
		ClientMenu.add(logOut);
		
		JButton src = new JButton("Search by filters");
		src.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				SearchByFilters srcf = new SearchByFilters(conn);
				srcf.setVisible(true);
				dispose();
			}
		});
		src.setForeground(Color.WHITE);
		src.setFont(new Font("Calibri", Font.BOLD, 18));
		src.setBackground(Color.MAGENTA);
		src.setBounds(606, 393, 178, 44);
		ClientMenu.add(src);
		
		JLabel background = new JLabel("");
		background.setIcon(new ImageIcon(ClientMenu.class.getResource("/Images/ClientMenu.png")));
		background.setBounds(0, 0, 971, 545);
		ClientMenu.add(background);
	}
}
