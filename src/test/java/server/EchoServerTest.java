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

    @Test
    public void testClientSocketInputOutputStreamCreated() throws IOException {
        ServerSocket mockServerSocket = mock(ServerSocket.class);
        Socket mockClientSocket = mock(Socket.class);
//        when(mockServerSocket.accept()).thenReturn(new Socket());

        assertEquals(IOSocketHandler.createClientSocketInputOutputStream(mockClientSocket), true);
    }
//    @Test
//    public void testOutputStreamPrintsConnectionSuccessfulMessage() throws IOException{
//        ServerSocket mockServerSocket = mock(ServerSocket.class);
//        when(mockServerSocket.accept()).thenReturn(new Socket());
//        EchoServer.connectClientSocket(mockServerSocket);
//
//        ByteArrayOutputStream mockOutput = new ByteArrayOutputStream();
//
//        assertArrayEquals("[+] Connection successful".getBytes(), mockOutput.toByteArray());
//    }
}
