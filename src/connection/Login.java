package connection;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.*;
import java.sql.*;
import java.awt.event.ActionListener;

public class Login extends JFrame {

	protected static String us1 = null;
	protected static String ps1 = null;
	private JPanel ConnectionMenu;
	private JPasswordField passwordField;
	private static JTextField UsernameField;
	private JTextField AdminPass;
	private JTextField usernameField;

	/**
	 * Launch the application.
	 */

	public Login(Connection conn){
		setResizable(false);
		initialize(conn);
	}
	
	/**
	 * Create the frame.
	 */
	private void initialize(Connection conn) {
		setBackground(Color.LIGHT_GRAY);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 960, 540);
		ConnectionMenu = new JPanel();
		
		//ConnectionMenu.setVisible(true);
		ConnectionMenu.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(ConnectionMenu);
		ConnectionMenu.setLayout(null);
		
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Calibri", Font.BOLD, 17));
		passwordField.setBounds(670, 272, 195, 36);
		ConnectionMenu.add(passwordField);
		
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setForeground(Color.WHITE);
		lblUsername.setFont(new Font("Calibri", Font.BOLD, 17));
		lblUsername.setBounds(574, 208, 84, 36);
		ConnectionMenu.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Calibri", Font.BOLD, 17));
		lblPassword.setBounds(574, 271, 84, 36);
		ConnectionMenu.add(lblPassword);
		
		AdminPass = new JTextField();
		AdminPass.setFont(new Font("Calibri", Font.BOLD, 17));
		AdminPass.setBounds(46, 379, 107, 36);
		ConnectionMenu.add(AdminPass);
		AdminPass.setColumns(10);
		
		JButton LoginAdmin = new JButton("Login as Admin");
		LoginAdmin.setForeground(Color.WHITE);
		LoginAdmin.setBackground(Color.MAGENTA);
		LoginAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String username = usernameField.getText();
				String password = passwordField.getText();
				String pass = AdminPass.getText();
				int ok = 0;
				ImageIcon incomplet = new ImageIcon("src/Images/puzzle.png");
				ImageIcon connection = new ImageIcon("src/Images/connection.png");
				ImageIcon cancel = new ImageIcon("src/Images/cancel.png");
				
				try{
					String sql = "SELECT * FROM Administrator";
					Statement statement = conn.createStatement();
					
					ResultSet rs = statement.executeQuery(sql);
					
					while(rs.next()){
						if(username.equals(rs.getString(2)) && password.equals(rs.getString(3))&& pass.equals("1234")){
							ok = 1;
						}
						if(username.isEmpty() || password.isEmpty() || pass.isEmpty()){
							ok = 2;
						}
					}
					if(ok == 1){
						JOptionPane.showMessageDialog(null, "Login Successful as Admin","WELCOME",JOptionPane.INFORMATION_MESSAGE,connection);
						usernameField.setText(null);
						passwordField.setText(null);
						AdminPass.setText(null);
						AdminMenu adm = new AdminMenu(conn);
						adm.setVisible(true);
						dispose();
						}else if(ok == 2){
							JOptionPane.showMessageDialog(null, "Fill all of the fields !","SOMETHING IS MISSING",JOptionPane.INFORMATION_MESSAGE,incomplet);
						}else{
						JOptionPane.showMessageDialog(null,"Incorect username, password or pass code","PLEASE TRY AGAIN",JOptionPane.INFORMATION_MESSAGE,cancel);
						usernameField.setText(null);
						passwordField.setText(null);
						AdminPass.setText(null);
					}
			
				}catch(Exception e2){
					e2.printStackTrace();
				}
				
			}
		});
		LoginAdmin.setFont(new Font("Calibri", Font.BOLD, 16));
		LoginAdmin.setBounds(165, 379, 153, 36);
		ConnectionMenu.add(LoginAdmin);
		
		JButton LoginButton = new JButton("Login");
		LoginButton.setForeground(Color.WHITE);
		LoginButton.setBackground(Color.MAGENTA);
		LoginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String username = usernameField.getText();
				String password = passwordField.getText();
				int ok = 0;
				ImageIcon incomplet = new ImageIcon("src/Images/puzzle.png");
				ImageIcon connection = new ImageIcon("src/Images/connection.png");
				ImageIcon cancel = new ImageIcon("src/Images/cancel.png");
				
				try{
					String sql = "SELECT * FROM Client";
					Statement statement = conn.createStatement();
					
					ResultSet rs = statement.executeQuery(sql);
					
					while(rs.next()){
						if(username.equals(rs.getString(2)) && password.equals(rs.getString(3))){
							ok = 1;
						}
						if(username.isEmpty() || password.isEmpty()){
							ok = 2;
						}
					}
					if(ok == 1){
						JOptionPane.showMessageDialog(null, "Login Successful","WELCOME",JOptionPane.INFORMATION_MESSAGE,connection);
						us1 = username;
						ps1 = password;
						usernameField.setText(null);
						passwordField.setText(null);
						ClientMenu clnt = new ClientMenu(conn);
						clnt.setVisible(true);
						dispose();
					}else if(ok == 2){
						JOptionPane.showMessageDialog(null, "Fill all of the fields !","SOMETHING IS MISSING",JOptionPane.INFORMATION_MESSAGE,incomplet);
					}else{
						JOptionPane.showMessageDialog(null,"Incorect username or password","PLEASE TRY AGAIN",JOptionPane.INFORMATION_MESSAGE,cancel);
						usernameField.setText(null);
						passwordField.setText(null);
					}
			
				}catch(Exception e1){
					e1.printStackTrace();
				}
				
						
			}
		});
		LoginButton.setFont(new Font("Calibri", Font.BOLD, 16));
		LoginButton.setBounds(738, 379, 127, 36);
		ConnectionMenu.add(LoginButton);
		
		JLabel UserIcon = new JLabel("");
		UserIcon.setIcon(new ImageIcon(Login.class.getResource("/Images/userNameIcon.png")));
		UserIcon.setBounds(530, 202, 44, 43);
		ConnectionMenu.add(UserIcon);
		
		JLabel PasswordIcon = new JLabel("");
		PasswordIcon.setIcon(new ImageIcon(Login.class.getResource("/Images/passwordIcon.png")));
		PasswordIcon.setBounds(530, 264, 32, 43);
		ConnectionMenu.add(PasswordIcon);
		
		JButton RegisterButton = new JButton("Register");
		RegisterButton.setForeground(Color.WHITE);
		RegisterButton.setBackground(Color.MAGENTA);
		RegisterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Register reg = new Register(conn);
				reg.setVisible(true);
				dispose();
			}
		});
		RegisterButton.setFont(new Font("Calibri", Font.BOLD, 16));
		RegisterButton.setBounds(599, 379, 127, 36);
		ConnectionMenu.add(RegisterButton);
		
		usernameField = new JTextField();
		usernameField.setFont(new Font("Calibri", Font.BOLD, 17));
		usernameField.setBounds(670, 207, 195, 36);
		ConnectionMenu.add(usernameField);
		usernameField.setColumns(10);
		
		JLabel background = new JLabel("");
		background.setIcon(new ImageIcon(Login.class.getResource("/Images/BackgroundLogin.png")));
		background.setBounds(0, 0, 970, 522);
		ConnectionMenu.add(background);
	}
}

