package server;

import message.Connection;
import message.Listening;

import java.net.*;
import java.io.*;


public class EchoServer {
    public static ServerSocket serverSocket = null;
    public static Socket clientSocket = null;
    public static final int PORT = 1234;
    public static void start() throws IOException {

        openConnection();
        connectClientSocket();

        PrintWriter serverOutput = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader clientInput = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        ioStream(serverOutput, clientInput);

        clientSocket.close();
        serverSocket.close();
    }

    public static void openConnection() throws IOException {

        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println(Listening.listeningForConnection(Integer.toString(PORT)));
        } catch(IOException ie){
            System.out.println(Listening.cannotListenForConnection(Integer.toString(PORT)));
            System.exit(1);
        }
    }

    public static void connectClientSocket() throws IOException {
        try {
            clientSocket = serverSocket.accept();
            System.out.println(Connection.successfulConnection());
        } catch(IOException ie) {
            System.out.println(Connection.failedConnection());
            System.exit(1);
        }
    }

    public static void ioStream(PrintWriter serverOutput, BufferedReader clientInput) throws IOException {
        System.out.println(Listening.listeningForClientInput());

        String clientInputLine;

        while((clientInputLine = clientInput.readLine()) != null) {
            System.out.println("Server will echo this back to the client: " + clientInputLine);
            serverOutput.println(clientInputLine);

            if (clientInputLine.equals(("bye"))) {
                break;
            }
        }

        serverOutput.close();
        clientInput.close();
    }
    }

