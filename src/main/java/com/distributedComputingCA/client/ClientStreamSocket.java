package com.distributedComputingCA.client;

import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

class ClientStreamSocket {
    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;

    ClientStreamSocket(InetAddress acceptorHost, int acceptorPort) throws IOException {
        System.setProperty("javax.net.ssl.trustStore", "ca.store");
        socket = ((SSLSocketFactory) SSLSocketFactory.getDefault()).createSocket(acceptorHost, acceptorPort);
        setStreams();
    }

    private void setStreams() throws IOException {
        InputStream inStream = socket.getInputStream();
        input = new BufferedReader(new InputStreamReader(inStream));
        OutputStream outStream = socket.getOutputStream();
        output = new PrintWriter(new OutputStreamWriter(outStream));
    }

    String ReceiveResponse() throws IOException {
        return input.readLine();
    }

    void socketSendRequest(String message) throws IOException {
        output.print(message + "\n");
        output.flush();
    }
}