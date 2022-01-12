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
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DelAccAdm extends JFrame {

	private JPanel DelAcAdm;
	private JTextField UserDel;

	public DelAccAdm(Connection conn){
		setResizable(false);
		initialize(conn);
	}

	/**
	 * Create the frame.
	 */
	private void initialize(Connection conn) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		DelAcAdm = new JPanel();
		DelAcAdm.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(DelAcAdm);
		DelAcAdm.setLayout(null);
		
		UserDel = new JTextField();
		UserDel.setHorizontalAlignment(SwingConstants.CENTER);
		UserDel.setFont(new Font("Calibri", Font.BOLD, 16));
		UserDel.setForeground(Color.WHITE);
		UserDel.setBackground(new Color(102, 51, 204));
		UserDel.setBounds(115, 200, 261, 35);
		DelAcAdm.add(UserDel);
		UserDel.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Insert Username ");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 20));
		lblNewLabel.setBounds(177, 169, 146, 29);
		DelAcAdm.add(lblNewLabel);
		
		JButton delAcc = new JButton("Delete Account");
		delAcc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String query = "DELETE FROM Client WHERE Username = ?";
				int flag = 0;
				String username = UserDel.getText();
				ImageIcon account = new ImageIcon("src/Images/AccountDeleted.png");
				ImageIcon question = new ImageIcon("src/Images/question.png");
				ImageIcon smile = new ImageIcon("src/Images/smile.png");
				ImageIcon incomplet = new ImageIcon("src/Images/puzzle.png");
				ImageIcon refr = new ImageIcon("src/Images/refresh.png");
				int reply;
				
				String query1 = "DELETE FROM Comenzi WHERE ClientID = ?";
				String clientid = null;
				
				try {
					PreparedStatement ps = conn.prepareStatement(query);
					ps.setString(1, username);
					
					String sql = "SELECT * FROM Client";
					Statement statement = conn.createStatement();
					ResultSet rs = statement.executeQuery(sql);
					
					
					while(rs.next())
					{
						if(username.equals(rs.getString(2))){
							flag = 1;
							clientid = rs.getString(1);
						}
	
					}
					
					PreparedStatement ps1 = conn.prepareStatement(query1);
					ps1.setString(1, clientid);
					
					if(flag == 1){
						reply = JOptionPane.showConfirmDialog(null,"ARE YOU SURE?"," ",JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,question);
						if(reply == JOptionPane.YES_OPTION){
							ps1.execute();
							ps.execute();
							JOptionPane.showMessageDialog(null,"ACCOUNT DELETED"," ",JOptionPane.INFORMATION_MESSAGE,account);
							UserDel.setText(null);
						}else{
							JOptionPane.showMessageDialog(null,"OK, HAVE A GOOD DAY"," ",JOptionPane.INFORMATION_MESSAGE,smile);
						}
					}else if(flag == 3){
						JOptionPane.showMessageDialog(null, "Fill all of the fields !","SOMETHING IS MISSING",JOptionPane.INFORMATION_MESSAGE,incomplet);
					}else{
						JOptionPane.showMessageDialog(null,"INCORRECT USERNAME"," ",JOptionPane.INFORMATION_MESSAGE,refr);
						UserDel.setText(null);
					}
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		delAcc.setForeground(Color.WHITE);
		delAcc.setBackground(Color.MAGENTA);
		delAcc.setFont(new Font("Calibri", Font.BOLD, 17));
		delAcc.setBounds(115, 257, 261, 68);
		DelAcAdm.add(delAcc);
		
		JButton mnu = new JButton("Exit");
		mnu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				dispose();
			}
		});
		mnu.setForeground(Color.WHITE);
		mnu.setFont(new Font("Calibri", Font.BOLD, 17));
		mnu.setBackground(Color.MAGENTA);
		mnu.setBounds(115, 328, 261, 43);
		DelAcAdm.add(mnu);
		
		JLabel background = new JLabel("background");
		background.setIcon(new ImageIcon(DelAccAdm.class.getResource("/Images/DelClntAcc.png")));
		background.setBounds(0, 0, 511, 483);
		DelAcAdm.add(background);
	}
}
