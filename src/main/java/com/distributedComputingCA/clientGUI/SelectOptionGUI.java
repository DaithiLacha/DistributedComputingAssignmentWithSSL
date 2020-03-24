package com.distributedComputingCA.clientGUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.distributedComputingCA.client.Client;
import com.distributedComputingCA.protocol.Protocol;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class SelectOptionGUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public SelectOptionGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblHeading = new JLabel("Select Option");
		lblHeading.setFont(new Font("Consolas", Font.BOLD, 28));
		lblHeading.setBounds(100, 11, 212, 33);
		contentPane.add(lblHeading);
		
		JLabel lblUpload = new JLabel("Upload Message");
		lblUpload.setFont(new Font("Consolas", Font.PLAIN, 18));
		lblUpload.setBounds(32, 66, 200, 25);
		contentPane.add(lblUpload);
		
		JLabel lblDownload = new JLabel("Download Messages");
		lblDownload.setFont(new Font("Consolas", Font.PLAIN, 18));
		lblDownload.setBounds(32, 114, 200, 25);
		contentPane.add(lblDownload);
		
		JLabel lblLogOff = new JLabel("Log Off");
		lblLogOff.setFont(new Font("Consolas", Font.PLAIN, 18));
		lblLogOff.setBounds(32, 163, 200, 25);
		contentPane.add(lblLogOff);
		
		JButton btnUpload = new JButton("Confirm");
		btnUpload.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                UploadMessageGUI uploadMessageGUI = new UploadMessageGUI();
                uploadMessageGUI.setTitle(SelectOptionGUI.super.getTitle());
                SelectOptionGUI.super.dispose();
            }
        });
		btnUpload.setFont(new Font("Consolas", Font.PLAIN, 18));
		btnUpload.setBounds(272, 66, 123, 25);
		contentPane.add(btnUpload);
		
		JButton btnDownload = new JButton("Confirm");
		btnDownload.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String serverResponse = Client.downloadMessagesRequest(Protocol.DOWNLOAD, SelectOptionGUI.super.getTitle());
                downloadGUI(serverResponse);
            }
        });
		btnDownload.setFont(new Font("Consolas", Font.PLAIN, 18));
		btnDownload.setBounds(272, 114, 123, 25);
		contentPane.add(btnDownload);
		
		JButton btnLogOff = new JButton("Confirm");
		btnLogOff.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String serverResponse = Client.logOffRequest(Protocol.LOGOFF, SelectOptionGUI.super.getTitle());
                logOffGUI(serverResponse);
                SelectOptionGUI.super.setVisible(false);
                SelectOptionGUI.super.dispose();
                ConnectionGUI connectionGUI = new ConnectionGUI();
            }
        });
		btnLogOff.setFont(new Font("Consolas", Font.PLAIN, 18));
		btnLogOff.setBounds(272, 163, 123, 25);
		contentPane.add(btnLogOff);
		setVisible(true);
	}

	private void downloadGUI(String response) {
        if(response.equals("702: " + Protocol.DOWNLOAD_SUCCESS)) {
            JOptionPane.showMessageDialog(null, "Download Successful",
                    response, JOptionPane.INFORMATION_MESSAGE);
        }else {
            JOptionPane.showMessageDialog(null, "Download UnSuccessful",
                    response, JOptionPane.ERROR_MESSAGE);
        }
    }

    private void logOffGUI(String response) {
        if(response.equals("902: " + Protocol.LOGOFF_SUCCESS)) {
            JOptionPane.showMessageDialog(null, SelectOptionGUI.super.getTitle() +
                    " has been logged off", response, JOptionPane.INFORMATION_MESSAGE);
        }else {
            JOptionPane.showMessageDialog(null, "There was an error in logging off the user",
                    response, JOptionPane.ERROR_MESSAGE);
        }
    }
}
