package com.distributedComputingCA.clientGUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.distributedComputingCA.client.Client;
import com.distributedComputingCA.protocol.*;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CreateUserGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtUserName;
	private JPasswordField pwdPassword;
	private JPasswordField pwdConfirm;

	/**
	 * Create the frame.
	 */
	public CreateUserGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblHeading = new JLabel("Enter User Details");
		lblHeading.setFont(new Font("Consolas", Font.BOLD, 28));
		lblHeading.setBounds(73, 11, 294, 33);
		contentPane.add(lblHeading);
		
		JLabel lblUserName = new JLabel("Username");
		lblUserName.setFont(new Font("Consolas", Font.PLAIN, 18));
		lblUserName.setBounds(28, 66, 144, 22);
		contentPane.add(lblUserName);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Consolas", Font.PLAIN, 18));
		lblPassword.setBounds(28, 119, 163, 22);
		contentPane.add(lblPassword);
		
		JLabel lblConfirm = new JLabel("Confirm Password");
		lblConfirm.setFont(new Font("Consolas", Font.PLAIN, 18));
		lblConfirm.setBounds(28, 166, 163, 22);
		contentPane.add(lblConfirm);
		
		txtUserName = new JTextField();
		txtUserName.setFont(new Font("Consolas", Font.PLAIN, 18));
		txtUserName.setBounds(210, 63, 214, 25);
		contentPane.add(txtUserName);
		txtUserName.setColumns(10);
		
		pwdPassword = new JPasswordField();
		pwdPassword.setFont(new Font("Consolas", Font.PLAIN, 18));
		pwdPassword.setBounds(210, 116, 214, 25);
		contentPane.add(pwdPassword);
		
		pwdConfirm = new JPasswordField();
		pwdConfirm.setFont(new Font("Consolas", Font.PLAIN, 18));
		pwdConfirm.setBounds(210, 163, 214, 25);
		contentPane.add(pwdConfirm);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String username = txtUserName.getText();
                String pass = new String(pwdPassword.getPassword());
                String confirm = new String(pwdConfirm.getPassword());
                if(areFieldsValid(username, pass, confirm)) {
                    String serverResponse = Client.createUserRequest(ClientProtocol.CREATE_USER, username, pass);
                    createUserResponseGUI(serverResponse);
                }

            }
        });
		btnSubmit.setFont(new Font("Consolas", Font.PLAIN, 18));
		btnSubmit.setBounds(182, 219, 105, 31);
		contentPane.add(btnSubmit);
		
		JButton btnBack = new JButton("Back");
		btnBack.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                CreateUserGUI.super.setVisible(false);
                CreateUserGUI.super.dispose();
                LoginOrCreateUserGUI loginOrCreateUserScreen = new LoginOrCreateUserGUI();
            }
        });
		btnBack.setFont(new Font("Consolas", Font.PLAIN, 18));
		btnBack.setBounds(297, 219, 105, 31);
		contentPane.add(btnBack);
		setVisible(true);
	}
	
	private boolean areFieldsValid(String username, String pass, String confirm) {
        if(username.length() > 0) {
            if(pass.equals(confirm)) {
                return true;
            }else {
                JOptionPane.showMessageDialog(null, "Passwords do not Match. Please Re-Enter details",
                        "Invalid Details", JOptionPane.ERROR_MESSAGE);
                pwdPassword.setText("");
                pwdConfirm.setText("");
                return false;
            }
        }else {
            JOptionPane.showMessageDialog(null, "Please enter a username",
                    "Invalid Details", JOptionPane.ERROR_MESSAGE);
            pwdPassword.setText("");
            pwdConfirm.setText("");
            return false;
        }
    }

    private void createUserResponseGUI(String serverResponse) {
        if(serverResponse.equals("603: " + ServerProtocol.CREATE_USER_FAILURE)) {
            JOptionPane.showMessageDialog(null, "Username already exists in the system please choose another",
                    serverResponse, JOptionPane.ERROR_MESSAGE);
            txtUserName.setText("");
            pwdPassword.setText("");
            pwdConfirm.setText("");
        }else if(serverResponse.equals("602: " + ServerProtocol.CREATE_USER_SUCCESS)) {
            JOptionPane.showMessageDialog(null, "User: " + txtUserName.getText() + " created",
                    serverResponse, JOptionPane.INFORMATION_MESSAGE);
            txtUserName.setText("");
            pwdPassword.setText("");
            pwdConfirm.setText("");
        }
    }

}
