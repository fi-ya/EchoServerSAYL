package echoserver.server;

import echoserver.iostream.IOSocketHandler;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
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
    private EchoServer createEchoServer() {
        StdOutServerLogger serverLogger = new StdOutServerLogger();
        var ioSocketHandler = new IOSocketHandler();
        return new EchoServer(serverLogger, ioSocketHandler);
    }
    @Test
    public void testMultipleClientsAbleToConnectToServer() throws IOException {
        StdOutServerLogger serverLogger = new StdOutServerLogger();
        var ioSocketHandler = new IOSocketHandler();
        ServerSocket mockServerSocket = MultipleClientsMock.mockTwoClients(ioSocketHandler);

        verify(mockServerSocket, times(2)).accept();
    }
    @Test
    public void testMultipleClientsAbleToConnectAndClose() throws IOException {
        var ioSocketHandler = new IOSocketHandler();
        MultipleClientsMock.mockTwoClients(ioSocketHandler);

        var serverLogger = MultipleClientsMock.mockServerLogger();
        Socket mockClientSocketOne = MultipleClientsMock.mockClientSocketOne;
        ioSocketHandler.handleClientSocket(mockClientSocketOne, serverLogger);

        BufferedReader mockClientInput = mock(BufferedReader.class);
        PrintWriter mockClientOutput = mock(PrintWriter.class);

        when(mockClientInput.readLine()).thenReturn("bye");
        ioSocketHandler.clientInputOutputLoop(mockClientInput, mockClientOutput,mockClientSocketOne);

        assertEquals(1, ioSocketHandler.clientConnectionCounter);
    }
}
