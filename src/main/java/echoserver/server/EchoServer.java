package echoserver.server;

import echoserver.iostream.IOSocketHandler;
import echoserver.message.Connection;
import echoserver.message.Listening;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class EchoServer {
    private ServerSocket serverSocket;
    private Socket clientSocket;

    public void start() throws IOException {
        int PORT = 1234;
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

    public void connectClientSocket(ServerSocket serverSocket) throws IOException {
        try {
            clientSocket = serverSocket.accept();
            System.out.println(Connection.successfulConnection());
        } catch(IOException ie) {
            System.out.println(Connection.failedConnection());
            System.exit(1);
        }
    }
    public Socket getClientSocket() {
        return clientSocket;
    }
}
