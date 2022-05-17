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
    void testServerSocketOpensConnectionToPort() throws IOException {
        var echoServer = mockServerLoggerAndEchoServer();
        final int mockPort = 5678;

        echoServer.openServerSocketConnection(mockPort);
        ServerSocket serverSocketNew = echoServer.getServerSocket();

        assertNotNull(serverSocketNew);
    }
    @Test
    void testServerSocketFailsToConnectToInvalidPort() throws IOException {
        var echoServer = mockServerLoggerAndEchoServer();
        assertThrows(IllegalArgumentException.class, () -> echoServer.openServerSocketConnection(-1));
    }
    @Test
    void testClientSocketConnectsToServerSocket() throws IOException {
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
    void testClientSocketFailedToConnectToServerSocket() throws IOException {
        StdOutServerLogger mockServerLogger = mock(StdOutServerLogger.class);
        var ioSocketHandler = new IOSocketHandler();
        var echoServer = new EchoServer(mockServerLogger, ioSocketHandler);
        ServerSocket mockServerSocket = mock(ServerSocket.class);
        when(mockServerSocket.accept()).thenThrow(IOException.class);
        echoServer.connectClientSocket(mockServerSocket, mockServerLogger);

        verify(mockServerLogger, times(1)).failedConnection();
    }
    @Test
    void testClientSocketDisconnectedWhenClientSentExitMessage() throws IOException {
        StdOutServerLogger serverLogger = new StdOutServerLogger();
        var ioSocketHandler = new IOSocketHandler();
        var echoServer = new EchoServer(serverLogger, ioSocketHandler);
        ServerSocket mockServerSocket = mock(ServerSocket.class);
        Socket mockClientSocket = mock(Socket.class);
        when(mockServerSocket.accept()).thenReturn(mockClientSocket);

        echoServer.connectClientSocket(mockServerSocket, serverLogger);
        BufferedReader mockClientInput = mock(BufferedReader.class);
        PrintWriter mockServerOutput = mock(PrintWriter.class);
        when(mockClientInput.readLine()).thenReturn("bye");
        ioSocketHandler.clientInputOutputLoop(mockClientInput, mockServerOutput,mockClientSocket);

        verify(mockServerOutput, times(1)).close();
        verify(mockClientInput, times(1)).close();
        verify(mockClientSocket, times(1)).close();
    }
    EchoServer mockServerLoggerAndEchoServer() {
        StdOutServerLogger serverLogger = new StdOutServerLogger();
        var ioSocketHandler = new IOSocketHandler();
        var echoServer = new EchoServer(serverLogger, ioSocketHandler);
        return echoServer;
    }
}
