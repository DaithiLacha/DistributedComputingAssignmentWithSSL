package com.distributedComputingCA.clientGUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

public class LoginOrCreateUserGUI extends JFrame {

	private JPanel contentPane;
	/**
	 * Create the frame.
	 */
	public LoginOrCreateUserGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblHeading = new JLabel("Choose Option");
		lblHeading.setFont(new Font("Consolas", Font.BOLD, 28));
		lblHeading.setBounds(114, 11, 223, 33);
		contentPane.add(lblHeading);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Consolas", Font.PLAIN, 18));
		lblLogin.setBounds(61, 92, 74, 28);
		contentPane.add(lblLogin);
		
		JLabel lblCreateUser = new JLabel("Create User");
		lblCreateUser.setFont(new Font("Consolas", Font.PLAIN, 18));
		lblCreateUser.setBounds(61, 144, 120, 27);
		contentPane.add(lblCreateUser);
		
		JButton btnLogin = new JButton("Confirm");
		btnLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                LoginGUI loginGUI = new LoginGUI();
                loginGUI.setVisible(true);
                LoginOrCreateUserGUI.super.setVisible(false);
                LoginOrCreateUserGUI.super.dispose();
            }
        });
		btnLogin.setFont(new Font("Consolas", Font.PLAIN, 18));
		btnLogin.setBounds(260, 87, 126, 33);
		contentPane.add(btnLogin);
		
		JButton btnCreateUser = new JButton("Confirm");
		btnCreateUser.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                CreateUserGUI createUserGUI = new CreateUserGUI();
                LoginOrCreateUserGUI.super.setVisible(false);
                LoginOrCreateUserGUI.super.dispose();
            }
        });
		btnCreateUser.setFont(new Font("Consolas", Font.PLAIN, 18));
		btnCreateUser.setBounds(260, 138, 126, 33);
		contentPane.add(btnCreateUser);
		setVisible(true);
	}
}
