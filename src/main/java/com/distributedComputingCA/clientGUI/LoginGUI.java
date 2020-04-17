package com.distributedComputingCA.clientGUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.distributedComputingCA.client.Client;
import com.distributedComputingCA.protocol.*;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class LoginGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtUserName;
	private JPasswordField pwdPassword;
	/**
	 * Create the frame.
	 */
	public LoginGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblHeading = new JLabel("Login");
		lblHeading.setFont(new Font("Consolas", Font.BOLD, 28));
		lblHeading.setBounds(171, 27, 112, 33);
		contentPane.add(lblHeading);
		
		JLabel lblUserName = new JLabel("Username");
		lblUserName.setFont(new Font("Consolas", Font.PLAIN, 18));
		lblUserName.setBounds(10, 93, 101, 23);
		contentPane.add(lblUserName);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Consolas", Font.PLAIN, 18));
		lblPassword.setBounds(10, 142, 101, 23);
		contentPane.add(lblPassword);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String response;
                if(txtUserName.getText().length() == 0) {
                    response = Client.loginRequest(ClientProtocol.LOGIN, " ", new String(pwdPassword.getPassword()));
                }else if(pwdPassword.getPassword().length == 0) {
                    response = Client.loginRequest(ClientProtocol.LOGIN, txtUserName.getText(), " ");
                }else {
                    response = Client.loginRequest(ClientProtocol.LOGIN, txtUserName.getText(), new String(pwdPassword.getPassword()));
                }
                loginResponseGUI(response);
            }
        });
		btnSubmit.setFont(new Font("Consolas", Font.PLAIN, 18));
		btnSubmit.setBounds(190, 217, 112, 33);
		contentPane.add(btnSubmit);
		
		JButton btnBack = new JButton("Back");
		btnBack.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                LoginGUI.super.setVisible(false);
                LoginGUI.super.dispose();
                LoginOrCreateUserGUI loginOrCreateUserGUI = new LoginOrCreateUserGUI();
            }
        });
		btnBack.setFont(new Font("Consolas", Font.PLAIN, 18));
		btnBack.setBounds(312, 217, 112, 33);
		contentPane.add(btnBack);
		
		txtUserName = new JTextField();
		txtUserName.setFont(new Font("Consolas", Font.PLAIN, 18));
		txtUserName.setBounds(192, 92, 232, 23);
		contentPane.add(txtUserName);
		txtUserName.setColumns(10);
		
		pwdPassword = new JPasswordField();
		pwdPassword.setFont(new Font("Consolas", Font.PLAIN, 18));
		pwdPassword.setBounds(192, 140, 232, 24);
		contentPane.add(pwdPassword);		
		setVisible(true);
	}
	
	 private void loginResponseGUI(String response) {
	        if(response.equals("502: " + ServerProtocol.LOGIN_SUCCESS)) {
	            JOptionPane.showMessageDialog(null, "Welcome: " + txtUserName.getText(),
	                    response, JOptionPane.INFORMATION_MESSAGE);
	            SelectOptionGUI selectOptionGUI = new SelectOptionGUI();
	            selectOptionGUI.setTitle(txtUserName.getText());
	            LoginGUI.super.setVisible(false);
	            LoginGUI.super.dispose();
	        }else if(response.equals("503: " + ServerProtocol.LOGIN_SUCCESS)) {
	            JOptionPane.showMessageDialog(null, "Invalid Details Entered",
	                    response, JOptionPane.ERROR_MESSAGE);
	            txtUserName.setText("");
	            pwdPassword.setText("");
	        }else {
	            JOptionPane.showMessageDialog(null, "You are already logged in",
	                    response, JOptionPane.ERROR_MESSAGE);
	            txtUserName.setText("");
	            pwdPassword.setText("");
	        }
	    }
}
