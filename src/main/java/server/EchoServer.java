package server;

import iostream.IOSocketHandler;
import message.Connection;
import message.Listening;

import java.net.*;
import java.io.*;


public class EchoServer {
    public static ServerSocket serverSocket;
    public static Socket clientSocket;
    public static final int PORT = 1234;
    public void start() throws IOException {

        openServerSocketConnection(PORT);
        connectClientSocket(serverSocket);
        IOSocketHandler.createClientSocketInputOutputStream(clientSocket);

        clientSocket.close();
        serverSocket.close();
    }

    public void openServerSocketConnection(int PORT) throws IOException {
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println(Listening.listeningForConnection(Integer.toString(PORT)));
        } catch(IOException ie){
            System.out.println(Listening.cannotListenForConnection(Integer.toString(PORT)));
            System.exit(1);
        }
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }
    public Socket getClientSocket() {
        return clientSocket;
    }

    public void connectClientSocket(ServerSocket serverSocket) throws IOException {
        try {
            clientSocket = serverSocket.accept();
            System.out.println(Connection.successfulConnection());
        } catch(IOException ie) {
            System.out.println(Connection.failedConnection());
            System.exit(1);
        }
    }
}
