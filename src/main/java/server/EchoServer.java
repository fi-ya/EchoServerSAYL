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

        openServerSocketConnection(PORT);
        connectClientSocket(serverSocket);

        PrintWriter serverOutput = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader clientInput = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        ioStream(serverOutput, clientInput);

        clientSocket.close();
        serverSocket.close();
    }

    public static ServerSocket openServerSocketConnection(int PORT) throws IOException {
        int port = PORT;

        try {
            serverSocket = new ServerSocket(port);
            System.out.println(Listening.listeningForConnection(Integer.toString(port)));
        } catch(IOException ie){
            System.out.println(Listening.cannotListenForConnection(Integer.toString(port)));
            System.exit(1);
        }
        return serverSocket;
    }

    public static Socket connectClientSocket(ServerSocket serverSocket) throws IOException {
        try {
            clientSocket = serverSocket.accept();
            System.out.println(Connection.successfulConnection());
        } catch(IOException ie) {
            System.out.println(Connection.failedConnection());
            System.exit(1);
        }
        return clientSocket;
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
