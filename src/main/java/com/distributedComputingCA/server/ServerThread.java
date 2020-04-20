package com.distributedComputingCA.server;

import com.distributedComputingCA.clientGUI.DisplayProtocolsGUI;
import com.distributedComputingCA.protocol.*;

import javax.swing.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

/**
 * This module is to be used with a concurrent Echo server.
 * Its run method carries out the logic of a client session.
 *
 * @author M. L. Liu
 */

public class ServerThread implements Runnable {
    private ServerStreamSocket myDataSocket;
    private boolean isSessionRunning = true;

    ServerThread(ServerStreamSocket myDataSocket) {
        this.myDataSocket = myDataSocket;
    }

    public void run() {
        String message;
        try {
            while(isSessionRunning) {
                message = myDataSocket.receiveRequest();
                determineMessageType(message);
                System.out.println("message received: " + message);
            }
        } catch (Exception ex) {
            System.out.println("Exception caught in thread: " + ex);
        }
    }

    private static boolean credentialsAreValid(String username, String password) {
        try (BufferedReader br = new BufferedReader(new FileReader("Users.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (username.equals(line.substring(0, line.indexOf(':'))) && password.equals(line.substring(line.indexOf(':') + 2))) {
                    Server.loggedInUsers.add(username);
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean isUserAlreadyLoggedIn(String username) {
        if(Server.loggedInUsers.contains(username)) {
            return true;
        }
        return false;
    }

    private void loginServerSide(List<String> messageType) {
        String username = messageType.get(2);
        String password = messageType.get(3);
        if(!isUserAlreadyLoggedIn(username)) {
            if (credentialsAreValid(username, password)) {
                try {
                    myDataSocket.sendResponse("502: " + ServerProtocol.LOGIN_SUCCESS);                    
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    myDataSocket.sendResponse("503: " + ServerProtocol.LOGIN_FAILURE);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else {
            try {
                myDataSocket.sendResponse("504: " + ServerProtocol.USER_ALREADY_LOGGED_IN);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean doesUserNameExist(String username) {
        try (BufferedReader br = new BufferedReader(new FileReader("Users.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (username.equals(line.substring(0, line.indexOf(':')))) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void appendUserToUsersFile(String username, String password) {
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("Users.txt", true), StandardCharsets.UTF_8));
            JOptionPane.showMessageDialog(null, username + ": " + password);
            writer.newLine();
            writer.append(username).append(": ").append(password);
            writer.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void addUserFileToUserMessagesDirectory(String username) {
        try {
            String fileName = username + ".txt";
            File directory = new File("UserMessages");
            if(!directory.exists()) {
                directory.mkdir();
            }
            File file = new File("UserMessages/" + fileName);
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.close();
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    private void createUserServerSide(List<String> messageType) {
        String username = messageType.get(2);
        String password = messageType.get(3);
        if(doesUserNameExist(username)) {
            try {
                myDataSocket.sendResponse("603: " + ServerProtocol.CREATE_USER_FAILURE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            appendUserToUsersFile(username, password);
            addUserFileToUserMessagesDirectory(username);
            try {
                myDataSocket.sendResponse("602: " + ServerProtocol.CREATE_USER_SUCCESS);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void downloadMessagesServerSide(List<String> messageType) {
        File file = new File("UserMessages/" + messageType.get(2) + ".txt");
        if(file.exists()) {
            try {
                myDataSocket.sendResponse("702: " + ServerProtocol.DOWNLOAD_SUCCESS);
                ProcessBuilder pb = new ProcessBuilder("Notepad.exe", "UserMessages/" + messageType.get(2) + ".txt");
                try {
                    pb.start();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
                
            }
        }else {
            try {
                myDataSocket.sendResponse("703: " + ServerProtocol.DOWNLOAD_FAILURE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void writeMessageToFile(String username, String message) {
        int indexOfCurrentUser = Server.loggedInUsers.indexOf(username);
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("UserMessages/" +
                Server.loggedInUsers.get(indexOfCurrentUser) + ".txt", true), StandardCharsets.UTF_8))) {
            bw.append("=====================================================================================");
            bw.newLine();
            final int MESSAGE_LENGTH = 188;
            if(message.length() > MESSAGE_LENGTH) {
                bw.append(message.substring(0, MESSAGE_LENGTH));
            }else {
                bw.append(message);
            }
            bw.newLine();
            bw.append("=====================================================================================");
            bw.newLine();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void uploadMessagesServerSide(List<String> messageType) {
        String username = messageType.get(2);
        String message = messageType.get(3);
        File file = new File("UserMessages/" + username + ".txt");
        if(file.exists()) {
            try {
                writeMessageToFile(username, message);
                myDataSocket.sendResponse("802: " + ServerProtocol.UPLOAD_SUCCESS);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            try {
                myDataSocket.sendResponse("803: " + ServerProtocol.UPLOAD_FAILURE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void logOffServerSide(List<String> messageType) throws IOException {
        String username = messageType.get(2);
        Server.loggedInUsers.remove(username);

        try {
            myDataSocket.sendResponse("902: " + ServerProtocol.LOGOFF_SUCCESS);
            myDataSocket.close();
        } catch (IOException e) {
            myDataSocket.sendResponse("903: " + ServerProtocol.LOGOFF_FAILURE);
        }
        isSessionRunning = false;
    }

    private void determineMessageType(String message) {
        List<String> messageType = Arrays.asList(message.split(";"));
        switch (messageType.get(1)) {
            case "CONNECT":
                return;
            case "LOGIN":
                loginServerSide(messageType);
                return;
            case "CREATE_USER":
                createUserServerSide(messageType);
                return;
            case "DOWNLOAD":
                downloadMessagesServerSide(messageType);
                return;
            case "UPLOAD":
                uploadMessagesServerSide(messageType);
                return;
            case "LOGOFF":
                try {
                    logOffServerSide(messageType);
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
}
