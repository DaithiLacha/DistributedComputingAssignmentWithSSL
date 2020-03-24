package com.distributedComputingCA.client;

import java.awt.EventQueue;

import com.distributedComputingCA.clientGUI.*;

public class ClientDriver {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConnectionGUI connectionGUI = new ConnectionGUI();
					DisplayProtocolsGUI frame = new DisplayProtocolsGUI();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
}
