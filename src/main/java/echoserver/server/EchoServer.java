package echoserver.server;

import echoserver.iostream.IOSocketHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class EchoServer {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private final ServerLogger serverLogger;
    private final int PORT = 1234;
    private IOSocketHandler ioSocketHandler;

    public EchoServer(ServerLogger serverLogger, IOSocketHandler ioSocketHandler){
        this.serverLogger = serverLogger;
        this.ioSocketHandler = ioSocketHandler;
    }

    public void start() throws IOException {
        StdOutServerLogger serverLogger = new StdOutServerLogger();

        openServerSocketConnection(PORT);

        while(!serverSocket.isClosed()){
            connectClientSocket(serverSocket, serverLogger);
            ioSocketHandler.createClientSocketInputOutputStream(clientSocket, serverLogger);
        }
    }
    public void openServerSocketConnection(int PORT) {
        try {
            serverSocket = new ServerSocket(PORT);
            serverLogger.listeningForConnection(PORT);
        } catch(IOException ie){
            serverLogger.cannotListenForConnection(PORT);
            System.exit(1);
        }
    }
    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void connectClientSocket(ServerSocket serverSocket, ServerLogger serverLogger) {
            try {
                clientSocket = serverSocket.accept();
                serverLogger.successfulConnection();
            } catch (IOException ie) {
                serverLogger.failedConnection();
            }
    }
}
