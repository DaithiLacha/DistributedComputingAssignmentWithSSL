package com.distributedComputingCA.client;

import java.net.*;
import java.io.*;

/**
 * This class is a module which provides the application logic
 * for an Echo client using stream-mode socket.
 *
 * @author M. L. Liu
 */

public class ClientHelper {
    private static ClientStreamSocket mySocket;

    ClientHelper(String hostName, String portNum) throws IOException {
        InetAddress serverHost = InetAddress.getByName(hostName);
        int serverPort = Integer.parseInt(portNum);
        mySocket = new ClientStreamSocket(serverHost, serverPort);
        System.out.println("Connection request made");
    }

    String helperSendRequest(String message) throws IOException {
        String response;
        mySocket.socketSendRequest(message);
        response = mySocket.ReceiveResponse();
        return response;
    }
}
