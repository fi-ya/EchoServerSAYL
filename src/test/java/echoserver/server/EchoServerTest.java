package echoserver.server;

import echoserver.iostream.IOSocketHandler;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class EchoServerTest {
    @Test
    void serverSocketOpensConnectionToPort() throws IOException {
        var echoServer = createEchoServer();
        final int mockPort = 5678;

        echoServer.openServerSocketConnection(mockPort);
        ServerSocket serverSocketNew = echoServer.getServerSocket();

        assertNotNull(serverSocketNew);
    }
    @Test
    void serverSocketFailsToConnectToInvalidPort() throws IOException {
        var echoServer = createEchoServer();
        assertThrows(IllegalArgumentException.class, () -> echoServer.openServerSocketConnection(-1));
    }
    @Test
    void clientSocketConnectsToServerSocket() throws IOException {
        StdOutServerLogger serverLogger = new StdOutServerLogger();
        var ioSocketHandler = new IOSocketHandler();
        var echoServer = new EchoServer(serverLogger, ioSocketHandler);
        ServerSocket mockServerSocket = mock(ServerSocket.class);
        Socket mockClientSocket = mock(Socket.class);

        when(mockServerSocket.accept()).thenReturn(mockClientSocket);
        echoServer.connectClientSocket(mockServerSocket, serverLogger);
        verify(mockServerSocket, times(1)).accept();
    }
    @Test
    void clientSocketFailedToConnectToServerSocket() throws IOException {
        StdOutServerLogger mockServerLogger = mock(StdOutServerLogger.class);
        var ioSocketHandler = new IOSocketHandler();
        var echoServer = new EchoServer(mockServerLogger, ioSocketHandler);
        ServerSocket mockServerSocket = mock(ServerSocket.class);
        when(mockServerSocket.accept()).thenThrow(IOException.class);
        echoServer.connectClientSocket(mockServerSocket, mockServerLogger);

        verify(mockServerLogger, times(1)).failedConnection();
    }
    EchoServer createEchoServer() {
        StdOutServerLogger serverLogger = new StdOutServerLogger();
        var ioSocketHandler = new IOSocketHandler();
        var echoServer = new EchoServer(serverLogger, ioSocketHandler);
        return echoServer;
    }
}
