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
        echoServer.connectClientSocket(mockServerSocket, serverLogger);
        Socket result = echoServer.getClientSocket();

        assertEquals(mockClientSocket, result);
    }
    @Test
    public void testClientSocketFailedToConnectToServerSocket() throws IOException {
        StdOutServerLogger serverLogger = new StdOutServerLogger();
        var echoServer = new EchoServer(serverLogger);
        ServerSocket mockServerSocket = mock(ServerSocket.class);
        mockServerSocket.close();
        echoServer.connectClientSocket(mockServerSocket, serverLogger);

        assertNotNull(IllegalArgumentException.class);
    }
    @Test
    public  void testClientSocketDisconnectedWhenClientSentExitMessage() throws IOException {
        StdOutServerLogger serverLogger = new StdOutServerLogger();
        var echoServer = new EchoServer(serverLogger);
        ServerSocket mockServerSocket = mock(ServerSocket.class);
        Socket mockClientSocket = mock(Socket.class);
        when(mockServerSocket.accept()).thenReturn(mockClientSocket);

        echoServer.connectClientSocket(mockServerSocket, serverLogger);
        BufferedReader mockClientInput = mock(BufferedReader.class);
        PrintWriter mockServerOutput = mock(PrintWriter.class);
        when(mockClientInput.readLine()).thenReturn("bye");
        IOSocketHandler.clientInputOutputLoop(mockClientInput, mockServerOutput, mockClientSocket, serverLogger);

        verify(mockServerOutput, times(1)).close();
        verify(mockClientInput, times(1)).close();
        verify(mockClientSocket, times(1)).close();
    }
    @Test
    public void testMultipleClientsAbleToConnectToServer() throws IOException {
        MultipleClientsMock.mockTwoClients();

        assertEquals(IOSocketHandler.clientConnectionCounter, 2);
    }
    @Test
    public void testMultipleClientsAbleToConnectAndClose() throws IOException {
        var mockClientSocketOne = MultipleClientsMock.mockTwoClients();
        var serverLogger = MultipleClientsMock.mockServerLogger();

        BufferedReader mockClientInput = mock(BufferedReader.class);
        PrintWriter mockServerOutput = mock(PrintWriter.class);
        when(mockClientInput.readLine()).thenReturn("bye");
        IOSocketHandler.clientInputOutputLoop(mockClientInput, mockServerOutput,mockClientSocketOne,serverLogger);

        verify(mockClientSocketOne, times(1)).close();
        assertEquals(IOSocketHandler.clientConnectionCounter, 1);
    }
}
