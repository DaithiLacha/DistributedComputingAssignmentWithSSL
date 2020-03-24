package com.distributedComputingCA.clientGUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextArea;
import javax.swing.JButton;

public class DisplayProtocolsGUI extends JFrame {

	private JPanel contentPane;
	private static JTextArea txtServer;
	private static JTextArea txtClient;
	/**
	 * Create the frame.
	 */
	public DisplayProtocolsGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 752, 539);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblHeading = new JLabel("Protocol Traffic");
		lblHeading.setFont(new Font("Consolas", Font.BOLD, 28));
		lblHeading.setBounds(250, 11, 262, 33);
		contentPane.add(lblHeading);
		
		JLabel lblServerTraffic = new JLabel("Server Traffic");
		lblServerTraffic.setFont(new Font("Consolas", Font.PLAIN, 18));
		lblServerTraffic.setBounds(10, 55, 177, 25);
		contentPane.add(lblServerTraffic);
		
		JLabel lblClientTraffic = new JLabel("Client Traffic");
		lblClientTraffic.setFont(new Font("Consolas", Font.PLAIN, 18));
		lblClientTraffic.setBounds(388, 55, 165, 25);
		contentPane.add(lblClientTraffic);
		
		txtServer = new JTextArea();
		txtServer.setFont(new Font("Consolas", Font.PLAIN, 14));
		txtServer.setBounds(10, 91, 338, 321);
		contentPane.add(txtServer);
		
		txtClient = new JTextArea();
		txtClient.setFont(new Font("Consolas", Font.PLAIN, 14));
		txtClient.setBounds(388, 91, 338, 321);
		contentPane.add(txtClient);
		
		JButton btnClose = new JButton("Close");
		btnClose.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.exit(-1);
            }
        });
		btnClose.setFont(new Font("Consolas", Font.PLAIN, 18));
		btnClose.setBounds(593, 456, 133, 33);
		contentPane.add(btnClose);
		setVisible(true);
	}

	public static void displayProtocolsOnScreen(String client, String server) {
        txtClient.append("Request Sent: " + client + "\n");
        txtServer.append("Request Received: " + client + "\n");
        txtClient.append("Response Received: " + server + "\n");
        txtServer.append("Response Sent: " + server + "\n");

    }
}
