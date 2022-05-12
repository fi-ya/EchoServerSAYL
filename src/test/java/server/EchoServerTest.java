package server;

import java.io.*;
import java.net.*;

import iostream.IOSocketHandler;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EchoServerTest {

    @Test
    public void testServerSocketOpensConnectionToPort() throws IOException {
        final int mockPort = 5678;
        ServerSocket mockServerSocket = EchoServer.openServerSocketConnection(mockPort);
        assertEquals(mockServerSocket.getLocalPort(), mockPort);
    }

    @Test
    public void testClientSocketConnectsToServerSocket() throws IOException {
        ServerSocket mockServerSocket = mock(ServerSocket.class);
        when(mockServerSocket.accept()).thenReturn(new Socket());

        assertNotNull(EchoServer.connectClientSocket(mockServerSocket));
    }
}
