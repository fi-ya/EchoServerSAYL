package echoserver.server;

import echoserver.iostream.IOSocketHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class EchoServer {
    private ServerSocket serverSocket;
    private final ServerLogger serverLogger;
    private final int PORT = 1234;
    private final IOSocketHandler ioSocketHandler;
    private static boolean clientSocketStatus = false;

    public EchoServer(ServerLogger serverLogger, IOSocketHandler ioSocketHandler){
        this.serverLogger = serverLogger;
        this.ioSocketHandler = ioSocketHandler;
    }

    public void start() {
        openServerSocketConnection(PORT);

        while(!serverSocket.isClosed()){
            Socket clientSocket = connectClientSocket(serverSocket, serverLogger);
            serverLogger.successfulConnection(clientSocket.getPort());
            ioSocketHandler.handleClientSocket(clientSocket, serverLogger);
            ExecutorService es = Executors.newSingleThreadExecutor();
            es.execute(ioSocketHandler);
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

    public Socket connectClientSocket(ServerSocket serverSocket, ServerLogger serverLogger) {
        Socket clientSocket = null;
        try {
            clientSocket = serverSocket.accept();
            if (clientSocketStatus) {
                clientSocketStatus = false;
                ExecutorService es = Executors.newSingleThreadExecutor();
                es.shutdownNow();
            };
        } catch (IOException ie) {
            serverLogger.failedConnection();
        }
        return clientSocket;
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }
    public static boolean handleClientSocketStatus(boolean status) {
        return clientSocketStatus = true;
    }
}