package echoserver.server;

import echoserver.iostream.IOSocketHandler;

import java.io.IOException;
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
        connectClientSocket(serverSocket);
        IOSocketHandler.createClientSocketInputOutputStream(clientSocket, serverLogger);

        clientSocket.close();
        serverSocket.close();
    }
    public void openServerSocketConnection(int PORT) throws IOException {
        try {
            serverSocket = new ServerSocket(PORT);
            serverLogger.listeningForConnection(Integer.toString(PORT));
//            System.out.println(Message.listeningForConnection(Integer.toString(PORT)));
        } catch(IOException ie){
            serverLogger.cannotListenForConnection(Integer.toString(PORT));
//            System.out.println(Message.cannotListenForConnection(Integer.toString(PORT)));
            System.exit(1);
        }
    }
    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void connectClientSocket(ServerSocket serverSocket) throws IOException {
        try {
            clientSocket = serverSocket.accept();
            serverLogger.successfulConnection();
//            System.out.println(Message.successfulConnection());
        } catch(IOException ie) {
            serverLogger.failedConnection();
//            System.out.println(Message.failedConnection());
            System.exit(1);
        }
    }
    public Socket getClientSocket() {
        return clientSocket;
    }
}
