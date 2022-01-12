package connection;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class Register extends JFrame {

	private JPanel contentPane;
	private JTextField UsernameField;
	private JTextField PasswordField;
	private JTextField NameField;
	private JTextField PrenumeField;
	private JTextField TelefonField;
	private JTextField GmailField;

	
	public Register(Connection conn){
		setResizable(false);
		initialize(conn);
	}
	/**
	 * Create the frame.
	 */
	private void initialize(Connection conn) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 960, 540);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		UsernameField = new JTextField();
		UsernameField.setFont(new Font("Calibri", Font.BOLD, 17));
		UsernameField.setBounds(384, 113, 176, 31);
		contentPane.add(UsernameField);
		UsernameField.setColumns(10);
		
		PasswordField = new JTextField();
		PasswordField.setFont(new Font("Calibri", Font.BOLD, 17));
		PasswordField.setColumns(10);
		PasswordField.setBounds(384, 171, 176, 31);
		contentPane.add(PasswordField);
		
		NameField = new JTextField();
		NameField.setFont(new Font("Calibri", Font.BOLD, 17));
		NameField.setColumns(10);
		NameField.setBounds(384, 228, 176, 31);
		contentPane.add(NameField);
		
		PrenumeField = new JTextField();
		PrenumeField.setFont(new Font("Calibri", Font.BOLD, 17));
		PrenumeField.setColumns(10);
		PrenumeField.setBounds(384, 286, 176, 31);
		contentPane.add(PrenumeField);
		
		TelefonField = new JTextField();
		TelefonField.setFont(new Font("Calibri", Font.BOLD, 17));
		TelefonField.setColumns(10);
		TelefonField.setBounds(384, 347, 176, 31);
		contentPane.add(TelefonField);
		
		GmailField = new JTextField();
		GmailField.setFont(new Font("Calibri", Font.BOLD, 17));
		GmailField.setColumns(10);
		GmailField.setBounds(384, 403, 176, 31);
		contentPane.add(GmailField);
		
		JLabel UsernameLabel = new JLabel("Username:");
		UsernameLabel.setForeground(Color.WHITE);
		UsernameLabel.setFont(new Font("Calibri", Font.BOLD, 17));
		UsernameLabel.setBounds(293, 114, 88, 31);
		contentPane.add(UsernameLabel);
		
		JLabel PasswordLabel = new JLabel("Password:");
		PasswordLabel.setForeground(Color.WHITE);
		PasswordLabel.setFont(new Font("Calibri", Font.BOLD, 17));
		PasswordLabel.setBounds(293, 172, 88, 31);
		contentPane.add(PasswordLabel);
		
		JLabel NameLabel = new JLabel("Nume:");
		NameLabel.setForeground(Color.WHITE);
		NameLabel.setFont(new Font("Calibri", Font.BOLD, 17));
		NameLabel.setBounds(293, 229, 88, 31);
		contentPane.add(NameLabel);
		
		JLabel PrenumeLabel = new JLabel("Prenume:");
		PrenumeLabel.setForeground(Color.WHITE);
		PrenumeLabel.setFont(new Font("Calibri", Font.BOLD, 17));
		PrenumeLabel.setBounds(293, 287, 88, 31);
		contentPane.add(PrenumeLabel);
		
		JLabel PhoneLabel = new JLabel("Telefon:");
		PhoneLabel.setForeground(Color.WHITE);
		PhoneLabel.setFont(new Font("Calibri", Font.BOLD, 17));
		PhoneLabel.setBounds(293, 348, 88, 31);
		contentPane.add(PhoneLabel);
		
		JLabel GmailLabel = new JLabel("Gmail:");
		GmailLabel.setForeground(Color.WHITE);
		GmailLabel.setFont(new Font("Calibri", Font.BOLD, 17));
		GmailLabel.setBounds(293, 404, 88, 31);
		contentPane.add(GmailLabel);
		
		JButton RegisterButton = new JButton("Register");
		RegisterButton.setForeground(Color.WHITE);
		RegisterButton.setBackground(Color.MAGENTA);
		RegisterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String query = "INSERT INTO Client(Username,Password,Nume,Prenume,Telefon,Gmail) Values(?,?,?,?,?,?)";
				String user = UsernameField.getText();
				int flag = 0;
				ImageIcon incomplet = new ImageIcon("src/Images/puzzle.png");
				ImageIcon welcome = new ImageIcon("src/Images/welcome.png");
				ImageIcon refresh = new ImageIcon("src/Images/refresh.png");
				
				try {
					PreparedStatement ps = conn.prepareStatement(query);
					ps.setString(1, UsernameField.getText());
					ps.setString(2, PasswordField.getText());
					ps.setString(3, NameField.getText());
					ps.setString(4, PrenumeField.getText());
					ps.setString(5, TelefonField.getText());
					ps.setString(6, GmailField.getText());
					
					
					String sql = "SELECT * FROM Client";
					Statement statement = conn.createStatement();
					ResultSet rs = statement.executeQuery(sql);
					
					while(rs.next()){
						if(user.equals(rs.getString(2))){
							flag = 1;
						}
						if(UsernameField.getText().isEmpty() || PasswordField.getText().isEmpty() || NameField.getText().isEmpty() || PrenumeField.getText().isEmpty() || TelefonField.getText().isEmpty() || GmailField.getText().isEmpty()){
							flag = 2;
						}
					}
					
					if(flag == 1){
						JOptionPane.showMessageDialog(null, "The user already exist","TRY ANOTHER USERNAME",JOptionPane.INFORMATION_MESSAGE,refresh);
						UsernameField.setText(null);
						PasswordField.setText(null);
						NameField.setText(null);
						PrenumeField.setText(null);
						TelefonField.setText(null);
						GmailField.setText(null);
					}else if(flag == 2){
						JOptionPane.showMessageDialog(null, "Fill all of the fields !","SOMETHING IS MISSING",JOptionPane.INFORMATION_MESSAGE,incomplet);
					}else{
						ps.execute();
						JOptionPane.showMessageDialog(null, "Register Complete","SUCCESS",JOptionPane.INFORMATION_MESSAGE,welcome);
					}
					}catch (SQLException e1) {
					e1.printStackTrace();
					}
				
			}
		});
		RegisterButton.setFont(new Font("Calibri", Font.BOLD, 16));
		RegisterButton.setBounds(255, 455, 136, 25);
		contentPane.add(RegisterButton);
		
		JButton ClearButton = new JButton("Clear");
		ClearButton.setForeground(Color.WHITE);
		ClearButton.setBackground(Color.MAGENTA);
		ClearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UsernameField.setText(null);
				PasswordField.setText(null);
				NameField.setText(null);
				PrenumeField.setText(null);
				TelefonField.setText(null);
				GmailField.setText(null);
			}
		});
		ClearButton.setFont(new Font("Calibri", Font.BOLD, 16));
		ClearButton.setBounds(417, 455, 143, 25);
		contentPane.add(ClearButton);
		
		JLabel userPhoto = new JLabel("");
		userPhoto.setIcon(new ImageIcon(Register.class.getResource("/Images/userNameIcon.png")));
		userPhoto.setBounds(255, 109, 33, 38);
		contentPane.add(userPhoto);
		
		JLabel PassPhoto = new JLabel("");
		PassPhoto.setIcon(new ImageIcon(Register.class.getResource("/Images/passwordIcon.png")));
		PassPhoto.setBounds(255, 171, 38, 31);
		contentPane.add(PassPhoto);
		
		JLabel phonePhoto = new JLabel("");
		phonePhoto.setIcon(new ImageIcon(Register.class.getResource("/Images/phone-call.png")));
		phonePhoto.setBounds(250, 342, 38, 36);
		contentPane.add(phonePhoto);
		
		JLabel gmailPhoto = new JLabel("");
		gmailPhoto.setIcon(new ImageIcon(Register.class.getResource("/Images/gmail.png")));
		gmailPhoto.setBounds(250, 391, 38, 36);
		contentPane.add(gmailPhoto);
		
		JLabel NumePhoto = new JLabel("");
		NumePhoto.setIcon(new ImageIcon(Register.class.getResource("/Images/name.png")));
		NumePhoto.setBounds(250, 228, 38, 31);
		contentPane.add(NumePhoto);
		
		JLabel PrenumePhoto = new JLabel("");
		PrenumePhoto.setIcon(new ImageIcon(Register.class.getResource("/Images/name.png")));
		PrenumePhoto.setBounds(250, 286, 38, 31);
		contentPane.add(PrenumePhoto);
		
		JButton BackToLoginBtn = new JButton("Back to Login");
		BackToLoginBtn.setForeground(Color.WHITE);
		BackToLoginBtn.setBackground(Color.MAGENTA);
		BackToLoginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login log = new Login(conn);
				log.setVisible(true);
				dispose();
			}
		});
		BackToLoginBtn.setFont(new Font("Calibri", Font.BOLD, 16));
		BackToLoginBtn.setBounds(584, 455, 136, 25);
		contentPane.add(BackToLoginBtn);
		
		JLabel Background = new JLabel("");
		Background.setIcon(new ImageIcon(Register.class.getResource("/Images/RegisterMenu.png")));
		Background.setBounds(0, 0, 960, 509);
		contentPane.add(Background);
	}
}
