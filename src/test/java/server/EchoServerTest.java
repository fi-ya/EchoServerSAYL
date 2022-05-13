package server;

import java.io.*;
import java.net.*;
import java.util.List;

import iostream.IOSocketHandler;
import org.junit.jupiter.api.Test;
import org.mockito.internal.stubbing.answers.ReturnsElementsOf;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EchoServerTest {

    @Test
    public void testServerSocketOpensConnectionToPort() throws IOException {
        var echoServer = new EchoServer();
        final int mockPort = 5678;
        echoServer.openServerSocketConnection(mockPort);
        ServerSocket serverSocketNew = echoServer.getServerSocket();

        assertNotNull(serverSocketNew);
    }

    @Test
    public void testClientSocketConnectsToServerSocket() throws IOException {
        var echoServer = new EchoServer();
        ServerSocket mockServerSocket = mock(ServerSocket.class);
        Socket mockClientSocket = mock(Socket.class);

        when(mockServerSocket.accept()).thenReturn(mockClientSocket);

        echoServer.connectClientSocket(mockServerSocket);
        Socket result = echoServer.getClientSocket();
        assertEquals(mockClientSocket, result);
    }

    @Test
    public  void testServerDisconnectedWhenClientSentExitMessage() throws IOException {
        var echoServer = new EchoServer();
        ServerSocket mockServerSocket = mock(ServerSocket.class);
        Socket mockClientSocket = mock(Socket.class);
        when(mockServerSocket.accept()).thenReturn(mockClientSocket);

        echoServer.connectClientSocket(mockServerSocket);

        BufferedReader mockClientInput = mock(BufferedReader.class);
        PrintWriter mockServerOutput = mock(PrintWriter.class);
        when(mockClientInput.readLine()).thenAnswer(new ReturnsElementsOf(List.of("bye")));

        IOSocketHandler.clientInputOutputLoop(mockClientInput, mockServerOutput);
//        mockServerOutput.close();
////      mockClientInput.close();
//        mockServerOutput = null;
//        mockClientInput = null;
        verify(mockServerOutput, times(1)).close();
        verify(mockClientInput, times(1)).close();
//        verify(mockClientSocket, times(1)).close();
//        verify(mockServerSocket, times(1)).close();
//        verify(mockServerSocket).isClosed();
//        verify(mockClientSocket).isClosed();
//
//        assertNull(mockServerSocket);
//        assertNull(mockClientSocket);
    }
}
