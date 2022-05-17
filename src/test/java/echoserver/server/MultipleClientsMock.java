package echoserver.server;

import echoserver.iostream.IOSocketHandler;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;

import static org.mockito.Mockito.*;


public class MultipleClientsMock {
    public static Socket mockTwoClients() throws IOException{
        var serverLogger = mockServerLogger();
        var echoServer = new EchoServer(serverLogger);
        ServerSocket mockServerSocket = mock(ServerSocket.class);

        Socket mockClientSocketOne = mock(Socket.class);
        when(mockServerSocket.accept()).thenReturn(mockClientSocketOne);
        echoServer.connectClientSocket(mockServerSocket, serverLogger);
        IOSocketHandler clientIOStreamOne = new IOSocketHandler(mockClientSocketOne, serverLogger);
        new Thread(clientIOStreamOne).start();

        Socket mockClientSocketTwo = mock(Socket.class);
        when(mockServerSocket.accept()).thenReturn(mockClientSocketTwo);
        echoServer.connectClientSocket(mockServerSocket, serverLogger);
        IOSocketHandler clientIOStreamTwo = new IOSocketHandler(mockClientSocketTwo, serverLogger);
        new Thread(clientIOStreamTwo).start();

        return mockClientSocketOne;
    }
    public static ServerLogger mockServerLogger() {
        StdOutServerLogger serverLogger = new StdOutServerLogger();

        return serverLogger;
    }
}
