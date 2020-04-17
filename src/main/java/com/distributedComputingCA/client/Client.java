package com.distributedComputingCA.client;

import com.distributedComputingCA.clientGUI.DisplayProtocolsGUI;
import com.distributedComputingCA.protocol.ClientProtocol;
import java.io.*;

/**
 * This module contains the presentation logic of an Echo Client.
 *
 * @author M. L. Liu
 */

public class Client {
    private static ClientHelper clientHelper;

    private String setHostName(String host) {
        if(host.equals("")) {
            return "localhost";
        }else {
            return host;
        }
    }

    private String setPortNumber(String num) {
        if(num.equals("")) {
            return "8055";
        }else {
            return num;
        }
    }

    public void runClient(String host, String port) {
        try {
            clientHelper = new ClientHelper(setHostName(host), setPortNumber(port));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static String createUserRequest(ClientProtocol proto, String username, String password) {
        String clientRequest = "601;" + proto + ";" + username + ";" + password;
        String serverResponse = "";

        try {
            serverResponse = clientHelper.helperSendRequest(clientRequest);
            DisplayProtocolsGUI.displayProtocolsOnScreen(clientRequest, serverResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return serverResponse;
    }

    public static String loginRequest(ClientProtocol proto, String username, String password) {
        String clientRequest = "501;" + proto + ";" + username + ";" + password;
        String serverResponse = "";

        try {
            serverResponse = clientHelper.helperSendRequest(clientRequest);
            DisplayProtocolsGUI.displayProtocolsOnScreen(clientRequest, serverResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return serverResponse;
    }

    public static String downloadMessagesRequest(ClientProtocol proto, String username) {
        String clientRequest = "701;" + proto + ";" + username;
        String serverResponse = "";

        try {
            serverResponse = clientHelper.helperSendRequest(clientRequest);
            DisplayProtocolsGUI.displayProtocolsOnScreen(clientRequest, serverResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return serverResponse;
    }

    public static String uploadMessageRequest(ClientProtocol proto, String username, String message) {
        String clientRequest = "801;" + proto + ";" + username + ";" + message;
        String serverResponse = "";

        try {
            serverResponse = clientHelper.helperSendRequest(clientRequest);
            DisplayProtocolsGUI.displayProtocolsOnScreen(clientRequest, serverResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return serverResponse;
    }

    public static String logOffRequest(ClientProtocol proto, String username) {
        String clientRequest = "901;" + proto + ";" + username;
        String serverResponse = "";

        try {
            serverResponse = clientHelper.helperSendRequest(clientRequest);
            DisplayProtocolsGUI.displayProtocolsOnScreen(clientRequest, serverResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return serverResponse;
    }
}
