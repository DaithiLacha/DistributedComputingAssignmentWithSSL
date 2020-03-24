package com.distributedComputingCA.server;

import java.net.*;
import java.io.*;


public class ServerStreamSocket extends Socket {
    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;

    ServerStreamSocket(Socket socket) throws IOException {
        this.socket = socket;
        setStreams();
    }

    private void setStreams() throws IOException {
        InputStream inStream = socket.getInputStream();
        input = new BufferedReader(new InputStreamReader(inStream));
        OutputStream outStream = socket.getOutputStream();
        output = new PrintWriter(new OutputStreamWriter(outStream));
    }

    void sendResponse(String message) throws IOException {
        output.print(message + "\n");
        output.flush();
    }

    String receiveRequest() throws IOException {
        return input.readLine();
    }

    public void close() throws IOException {
        socket.close();
    }
}
