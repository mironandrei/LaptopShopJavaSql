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
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChangePassword extends JFrame {

	private JPanel ChangePass;
	private JTextField oldPassword;
	private JTextField newPass1;
	private JTextField newPass2;

	public ChangePassword(Connection conn){
		setResizable(false);
		initialize(conn);
	}

	/**
	 * Create the frame.
	 */
	private void initialize(Connection conn) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		ChangePass = new JPanel();
		ChangePass.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(ChangePass);
		ChangePass.setLayout(null);
		
		oldPassword = new JTextField();
		oldPassword.setFont(new Font("Calibri", Font.BOLD, 14));
		oldPassword.setHorizontalAlignment(SwingConstants.CENTER);
		oldPassword.setBounds(170, 141, 147, 35);
		ChangePass.add(oldPassword);
		oldPassword.setColumns(10);
		
		newPass1 = new JTextField();
		newPass1.setFont(new Font("Calibri", Font.BOLD, 14));
		newPass1.setHorizontalAlignment(SwingConstants.CENTER);
		newPass1.setColumns(10);
		newPass1.setBounds(170, 207, 147, 35);
		ChangePass.add(newPass1);
		
		newPass2 = new JTextField();
		newPass2.setFont(new Font("Calibri", Font.BOLD, 14));
		newPass2.setHorizontalAlignment(SwingConstants.CENTER);
		newPass2.setColumns(10);
		newPass2.setBounds(170, 298, 147, 35);
		ChangePass.add(newPass2);
		
		JLabel oldPass = new JLabel("Old Password");
		oldPass.setForeground(Color.WHITE);
		oldPass.setHorizontalAlignment(SwingConstants.CENTER);
		oldPass.setFont(new Font("Calibri", Font.BOLD, 18));
		oldPass.setBounds(171, 120, 146, 19);
		ChangePass.add(oldPass);
		
		JLabel lblNewPassword = new JLabel("New Password");
		lblNewPassword.setForeground(Color.WHITE);
		lblNewPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewPassword.setFont(new Font("Calibri", Font.BOLD, 18));
		lblNewPassword.setBounds(171, 187, 146, 19);
		ChangePass.add(lblNewPassword);
		
		JLabel lblNewPassword_1 = new JLabel("New Password");
		lblNewPassword_1.setForeground(Color.WHITE);
		lblNewPassword_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewPassword_1.setFont(new Font("Calibri", Font.BOLD, 18));
		lblNewPassword_1.setBounds(170, 275, 146, 19);
		ChangePass.add(lblNewPassword_1);
		
		JLabel lblRepeat = new JLabel("Repeat");
		lblRepeat.setForeground(Color.WHITE);
		lblRepeat.setHorizontalAlignment(SwingConstants.CENTER);
		lblRepeat.setFont(new Font("Calibri", Font.BOLD, 18));
		lblRepeat.setBounds(171, 255, 146, 19);
		ChangePass.add(lblRepeat);
		
		JButton change = new JButton("Change Password");
		change.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String query = "UPDATE Client SET Password = ? WHERE Username = ?";
				String us3 = Login.us1;
				String ps2 = Login.ps1;
				String oldPs = oldPassword.getText();
				String newPs1 = newPass1.getText();
				String newPs2 = newPass2.getText();
				ImageIcon refr = new ImageIcon("src/Images/refresh.png");
				ImageIcon pas = new ImageIcon("src/Images/PasswordChanged.png");
				ImageIcon incomplet = new ImageIcon("src/Images/puzzle.png");
				int flag = 0;
				
				if(oldPassword.getText().isEmpty() || newPass1.getText().isEmpty() || newPass2.getText().isEmpty()){
					flag = 1;
				}
				
				try {
					
					PreparedStatement ps = conn.prepareStatement(query);
					ps.setString(1,newPs1);
					ps.setString(2,us3);
					
					String sql = "SELECT * FROM Client";
					Statement statement = conn.createStatement();
					ResultSet rs = statement.executeQuery(sql);
					
					
					while(rs.next()){
						if(ps2.equals(oldPs) && newPs1.equals(newPs2) && us3.equals(rs.getString(2))){
							flag = 2;
						}
					}
					
					if(flag == 1){
						JOptionPane.showMessageDialog(null, "Fill all of the fields !","SOMETHING IS MISSING",JOptionPane.INFORMATION_MESSAGE,incomplet);
					}else if(flag == 2){
						ps.execute();
						JOptionPane.showMessageDialog(null,"PASSWORD CHANGED"," ",JOptionPane.INFORMATION_MESSAGE,pas);
						Login log = new Login(conn);
						log.setVisible(true);
						dispose();
					}else{
						JOptionPane.showMessageDialog(null,"TRY AGAIN"," ",JOptionPane.INFORMATION_MESSAGE,refr);
						oldPassword.setText(null);
						newPass1.setText(null);
						newPass2.setText(null);
					}
					
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
		
			}
		});
		change.setForeground(Color.WHITE);
		change.setBackground(Color.MAGENTA);
		change.setFont(new Font("Calibri", Font.BOLD, 18));
		change.setBounds(69, 370, 174, 48);
		ChangePass.add(change);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClientMenu cln = new ClientMenu(conn);
				cln.setVisible(true);
				dispose();
			}
		});
		btnCancel.setForeground(Color.WHITE);
		btnCancel.setBackground(Color.MAGENTA);
		btnCancel.setFont(new Font("Calibri", Font.BOLD, 18));
		btnCancel.setBounds(248, 370, 174, 48);
		ChangePass.add(btnCancel);
		
		JLabel back = new JLabel("background");
		back.setIcon(new ImageIcon(ChangePassword.class.getResource("/Images/ChangePassword.png")));
		back.setBounds(0, 0, 505, 500);
		ChangePass.add(back);
	}
}
