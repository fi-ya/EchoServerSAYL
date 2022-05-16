package echoserver.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.PrintWriter;

import echoserver.server.EchoServer;
import echoserver.iostream.IOSocketHandler;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class EchoServerTest {
    @Test
    public void testServerSocketOpensConnectionToPort() throws IOException {
        StdOutServerLogger serverLogger = new StdOutServerLogger();
        var echoServer = new EchoServer(serverLogger);
        final int mockPort = 5678;

        echoServer.openServerSocketConnection(mockPort);
        ServerSocket serverSocketNew = echoServer.getServerSocket();

        assertNotNull(serverSocketNew);
    }
    @Test
    public void testServerSocketFailsToConnectToInvalidPort() throws IOException {
        StdOutServerLogger serverLogger = new StdOutServerLogger();
        var echoServer = new EchoServer(serverLogger);
        assertThrows(IllegalArgumentException.class, () -> echoServer.openServerSocketConnection(-1));
    }
    @Test
    public void testClientSocketConnectsToServerSocket() throws IOException {
        StdOutServerLogger serverLogger = new StdOutServerLogger();
        var echoServer = new EchoServer(serverLogger);
        ServerSocket mockServerSocket = mock(ServerSocket.class);
        Socket mockClientSocket = mock(Socket.class);

        when(mockServerSocket.accept()).thenReturn(mockClientSocket);
        echoServer.connectClientSocket(mockServerSocket);
        Socket result = echoServer.getClientSocket();

        assertEquals(mockClientSocket, result);
    }
    @Test
    public void testClientSocketFailedToConnectToServerSocket() throws IOException {
        StdOutServerLogger serverLogger = new StdOutServerLogger();
        var echoServer = new EchoServer(serverLogger);
        ServerSocket mockServerSocket = mock(ServerSocket.class);
        mockServerSocket.close();
        echoServer.connectClientSocket(mockServerSocket);

        assertNotNull(IllegalArgumentException.class);
    }
    @Test
    public  void testServerDisconnectedWhenClientSentExitMessage() throws IOException {
        StdOutServerLogger serverLogger = new StdOutServerLogger();
        var echoServer = new EchoServer(serverLogger);
        ServerSocket mockServerSocket = mock(ServerSocket.class);
        Socket mockClientSocket = mock(Socket.class);
        when(mockServerSocket.accept()).thenReturn(mockClientSocket);

        echoServer.connectClientSocket(mockServerSocket);
        BufferedReader mockClientInput = mock(BufferedReader.class);
        PrintWriter mockServerOutput = mock(PrintWriter.class);
        when(mockClientInput.readLine()).thenReturn("bye");
        IOSocketHandler.clientInputOutputLoop(mockClientInput, mockServerOutput);
        mockClientSocket.close();
        mockServerSocket.close();

        verify(mockServerOutput, times(1)).close();
        verify(mockClientInput, times(1)).close();
        verify(mockClientSocket, times(1)).close();
        verify(mockServerSocket, times(1)).close();
    }
}
