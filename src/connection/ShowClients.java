package connection;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class ShowClients extends JFrame {

	private JPanel showClients;
	private JTable table;

	public ShowClients(Connection conn){
		setResizable(false);
		initialize(conn);
	}

	/**
	 * Create the frame.
	 */
	private void initialize(Connection conn) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		showClients = new JPanel();
		showClients.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(showClients);
		showClients.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(333, 13, 649, 539);
		showClients.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton showCl = new JButton("Show Clients");
		showCl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try{
					
					String query = "SELECT * FROM Client";
					PreparedStatement ps = conn.prepareStatement(query);
					ResultSet rs = ps.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
				}catch(Exception e1){
					e1.printStackTrace();
				}
			}
		});
		showCl.setForeground(Color.WHITE);
		showCl.setBackground(Color.MAGENTA);
		showCl.setFont(new Font("Calibri", Font.BOLD, 20));
		showCl.setBounds(41, 419, 250, 60);
		showClients.add(showCl);
		
		JButton btnGoBack = new JButton("Exit");
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//AdminMenu adm = new AdminMenu(conn);
				//adm.setVisible(true);
				dispose();
			}
		});
		btnGoBack.setForeground(Color.WHITE);
		btnGoBack.setFont(new Font("Calibri", Font.BOLD, 20));
		btnGoBack.setBackground(Color.MAGENTA);
		btnGoBack.setBounds(41, 492, 250, 60);
		showClients.add(btnGoBack);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(ShowClients.class.getResource("/Images/customer.png")));
		label.setBounds(100, 242, 154, 128);
		showClients.add(label);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(ShowClients.class.getResource("/Images/Clients.png")));
		label_1.setBounds(0, 0, 1009, 583);
		showClients.add(label_1);
	}
}
