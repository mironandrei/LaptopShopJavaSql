package connection;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class AdminMenu extends JFrame {

	private JPanel contentPane;

	public AdminMenu(Connection conn){
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
		
		JButton DeleteClientAccount = new JButton("Delete Client Account");
		DeleteClientAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DelAccAdm dl = new DelAccAdm(conn);
				dl.setVisible(true);
				//dispose();
			}
		});
		DeleteClientAccount.setForeground(Color.WHITE);
		DeleteClientAccount.setBackground(Color.MAGENTA);
		DeleteClientAccount.setFont(new Font("Calibri", Font.BOLD, 16));
		DeleteClientAccount.setBounds(91, 416, 193, 38);
		contentPane.add(DeleteClientAccount);
		
		JButton logout = new JButton("LogOut");
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Login lg = new Login(conn);
				lg.setVisible(true);
				dispose();
			}
		});
		logout.setForeground(Color.WHITE);
		logout.setFont(new Font("Calibri", Font.BOLD, 16));
		logout.setBackground(Color.MAGENTA);
		logout.setBounds(731, 416, 130, 38);
		contentPane.add(logout);
		
		JButton addstock = new JButton("Add Stock");
		addstock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddStock ad = new AddStock(conn);
				ad.setVisible(true);
				dispose();
			}
		});
		addstock.setForeground(Color.WHITE);
		addstock.setFont(new Font("Calibri", Font.BOLD, 16));
		addstock.setBackground(Color.MAGENTA);
		addstock.setBounds(327, 365, 130, 38);
		contentPane.add(addstock);
		
		JButton allClients = new JButton("All of the Clients");
		allClients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShowClients sh = new ShowClients(conn);
				sh.setVisible(true);
				//dispose();
			}
		});
		allClients.setForeground(Color.WHITE);
		allClients.setFont(new Font("Calibri", Font.BOLD, 16));
		allClients.setBackground(Color.MAGENTA);
		allClients.setBounds(91, 365, 193, 38);
		contentPane.add(allClients);
		
		JButton comands = new JButton("See all the commands");
		comands.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ShowCommands shc = new ShowCommands(conn);
				shc.setVisible(true);
				dispose();
				
			}
		});
		comands.setForeground(Color.WHITE);
		comands.setFont(new Font("Calibri", Font.BOLD, 16));
		comands.setBackground(Color.MAGENTA);
		comands.setBounds(497, 365, 193, 38);
		contentPane.add(comands);
		
		JButton cmplxSrc = new JButton("Statistics");
		cmplxSrc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				StatisticsMenu stc = new StatisticsMenu(conn);
				stc.setVisible(true);
				dispose();
			}
		});
		cmplxSrc.setForeground(Color.WHITE);
		cmplxSrc.setFont(new Font("Calibri", Font.BOLD, 16));
		cmplxSrc.setBackground(Color.MAGENTA);
		cmplxSrc.setBounds(497, 416, 193, 38);
		contentPane.add(cmplxSrc);
		
		JButton btnDeleteStock = new JButton("Delete Stock");
		btnDeleteStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DeleteStock dlt = new DeleteStock(conn);
				dlt.setVisible(true);
				
			}
		});
		btnDeleteStock.setForeground(Color.WHITE);
		btnDeleteStock.setFont(new Font("Calibri", Font.BOLD, 16));
		btnDeleteStock.setBackground(Color.MAGENTA);
		btnDeleteStock.setBounds(327, 416, 130, 38);
		contentPane.add(btnDeleteStock);
		
		JButton btnModifyStock = new JButton("Change Prices");
		btnModifyStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ChangePrice chng = new ChangePrice(conn);
				chng.setVisible(true);
				
			}
		});
		btnModifyStock.setForeground(Color.WHITE);
		btnModifyStock.setFont(new Font("Calibri", Font.BOLD, 16));
		btnModifyStock.setBackground(Color.MAGENTA);
		btnModifyStock.setBounds(731, 365, 130, 38);
		contentPane.add(btnModifyStock);
		
		JLabel lblBackground = new JLabel("background");
		lblBackground.setIcon(new ImageIcon(AdminMenu.class.getResource("/Images/AdminMenu.png")));
		lblBackground.setBounds(0, 0, 970, 546);
		contentPane.add(lblBackground);
	}
}
