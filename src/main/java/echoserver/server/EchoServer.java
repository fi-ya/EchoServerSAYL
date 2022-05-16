package echoserver.server;

import echoserver.iostream.IOSocketHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;


public class EchoServer {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private final ServerLogger serverLogger;

    public EchoServer(ServerLogger serverLogger){
        this.serverLogger = serverLogger;
    }

    public void start() throws IOException {
        int PORT = 1234;
        StdOutServerLogger serverLogger = new StdOutServerLogger();

        openServerSocketConnection(PORT);

        while(!serverSocket.isClosed()){
            connectClientSocket(serverSocket, serverLogger);
            IOSocketHandler.createClientSocketInputOutputStream(clientSocket, serverLogger);
        }
    }
    public void openServerSocketConnection(int PORT) throws IOException {
        try {
            serverSocket = new ServerSocket(PORT);
            serverLogger.listeningForConnection(Integer.toString(PORT));
        } catch(IOException ie){
            serverLogger.cannotListenForConnection(Integer.toString(PORT));
            System.exit(1);
        }
    }
    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void connectClientSocket(ServerSocket serverSocket, ServerLogger serverLogger) throws IOException {
            try {
                clientSocket = serverSocket.accept();
                serverLogger.successfulConnection();
            } catch (IOException ie) {
                serverLogger.failedConnection();
            }
    }
    public Socket getClientSocket() {
        return clientSocket;
    }
}
