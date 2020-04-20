package com.distributedComputingCA.clientGUI;

import java.awt.EventQueue;
import com.distributedComputingCA.client.Client;
import com.distributedComputingCA.protocol.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UploadMessageGUI extends JFrame {
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public UploadMessageGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblHeading = new JLabel("Upload Message");
		lblHeading.setFont(new Font("Consolas", Font.BOLD, 28));
		lblHeading.setBounds(91, 11, 222, 33);
		contentPane.add(lblHeading);
		
		JLabel lblMessage = new JLabel("Message");
		lblMessage.setFont(new Font("Consolas", Font.PLAIN, 18));
		lblMessage.setBounds(10, 54, 75, 25);
		contentPane.add(lblMessage);
		
		JTextArea txtMessage = new JTextArea();
		txtMessage.setLineWrap(true);
		txtMessage.setFont(new Font("Consolas", Font.PLAIN, 18));
		txtMessage.setBounds(91, 54, 333, 144);
		contentPane.add(txtMessage);
		
		JButton btnUpload = new JButton("Upload");
		btnUpload.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String serverResponse = Client.uploadMessageRequest(ClientProtocol.UPLOAD,
                        UploadMessageGUI.super.getTitle(), txtMessage.getText());
                uploadGUI(serverResponse);
                txtMessage.setText("");
            }
        });
		btnUpload.setFont(new Font("Consolas", Font.PLAIN, 18));
		btnUpload.setBounds(194, 227, 110, 25);
		contentPane.add(btnUpload);
		
		JButton btnBack = new JButton("Back");
		btnBack.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                UploadMessageGUI.super.setVisible(false);
                UploadMessageGUI.super.dispose();
                SelectOptionGUI selectOptionGUI = new SelectOptionGUI();
                selectOptionGUI.setTitle(UploadMessageGUI.super.getTitle());
            }
        });
		btnBack.setFont(new Font("Consolas", Font.PLAIN, 18));
		btnBack.setBounds(314, 227, 110, 25);
		contentPane.add(btnBack);
		setVisible(true);
	}
	
	private void uploadGUI(String response) {
        if(response.equals("802: " + ServerProtocol.UPLOAD_SUCCESS)) {
            JOptionPane.showMessageDialog(null, "Message has been uploaded",
                    response, JOptionPane.INFORMATION_MESSAGE);
        }else {
            JOptionPane.showMessageDialog(null, "Message failed to upload",
                    response, JOptionPane.ERROR_MESSAGE);
        }        
    }	
}
