package com.distributedComputingCA.clientGUI;

import com.distributedComputingCA.client.*;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextField;
import javax.swing.JButton;

public class ConnectionGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtHostName;
	private JTextField txtPortNo;

	/**
	 * Create the frame.
	 */
	public ConnectionGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblHeading = new JLabel("Connnect to Server");
		lblHeading.setFont(new Font("Consolas", Font.BOLD, 28));
		lblHeading.setBounds(73, 11, 282, 33);
		contentPane.add(lblHeading);
		
		JLabel lblHostName = new JLabel("Host Name");
		lblHostName.setFont(new Font("Consolas", Font.PLAIN, 18));
		lblHostName.setBounds(47, 95, 99, 22);
		contentPane.add(lblHostName);
		
		JLabel lblPortNo = new JLabel("Port No.");
		lblPortNo.setFont(new Font("Consolas", Font.PLAIN, 18));
		lblPortNo.setBounds(47, 145, 99, 22);
		contentPane.add(lblPortNo);
		
		txtHostName = new JTextField();
		txtHostName.setFont(new Font("Consolas", Font.PLAIN, 18));
		txtHostName.setBounds(178, 95, 200, 22);
		contentPane.add(txtHostName);
		txtHostName.setColumns(10);
		
		txtPortNo = new JTextField();
		txtPortNo.setFont(new Font("Consolas", Font.PLAIN, 18));
		txtPortNo.setBounds(178, 143, 200, 22);
		contentPane.add(txtPortNo);
		txtPortNo.setColumns(10);
		
		JButton btnConnect = new JButton("Connect");
		btnConnect.setFont(new Font("Consolas", Font.PLAIN, 18));
		btnConnect.setBounds(116, 217, 149, 33);
		btnConnect.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Client client = new Client();
                client.runClient(txtHostName.getText(), txtPortNo.getText());
                LoginOrCreateUserGUI gui = new LoginOrCreateUserGUI();
                ConnectionGUI.super.setVisible(false);
                ConnectionGUI.super.dispose();
            }
        });
		contentPane.add(btnConnect);
		
		
		JButton btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Consolas", Font.PLAIN, 18));
		btnExit.setBounds(275, 217, 149, 33);
		btnExit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(-1);
            }
        });
		contentPane.add(btnExit);
		setVisible(true);
	}
}
