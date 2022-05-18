package echoserver.server;

import echoserver.iostream.IOSocketHandler;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;

import static org.mockito.Mockito.*;


public class MultipleClientsMock {
    public static Socket mockTwoClients() throws IOException{
        var serverLogger = mockServerLogger();
        var ioSocketHandler = new IOSocketHandler();
        var echoServer = new EchoServer(serverLogger, ioSocketHandler);
        ServerSocket mockServerSocket = mock(ServerSocket.class);

        Socket mockClientSocketOne = mock(Socket.class);
        when(mockServerSocket.accept()).thenReturn(mockClientSocketOne);
        echoServer.connectClientSocket(mockServerSocket, serverLogger);
        ioSocketHandler.handleClientSocket(mockClientSocketOne, serverLogger);
        new Thread(ioSocketHandler).start();

        Socket mockClientSocketTwo = mock(Socket.class);
        when(mockServerSocket.accept()).thenReturn(mockClientSocketTwo);
        echoServer.connectClientSocket(mockServerSocket, serverLogger);
        ioSocketHandler.handleClientSocket(mockClientSocketTwo, serverLogger);
        new Thread(ioSocketHandler).start();

        return mockClientSocketOne;
    }
    public static ServerLogger mockServerLogger() {
        StdOutServerLogger serverLogger = new StdOutServerLogger();
        return serverLogger;
    }
}
