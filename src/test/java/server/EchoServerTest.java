package server;

import java.io.*;
import java.net.*;

import org.junit.jupiter.api.Test;

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
}
