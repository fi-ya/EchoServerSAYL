package iostream;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import server.EchoServer;

import java.io.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class IOSocketHandlerTest {

    @Mock
    Socket clientSocket;

    @Mock
    InputStream inputStream;

    @Mock
    OutputStream outputStream;

    @Test
    public void testClientSocketInputCreated() throws IOException {
//        final int mockPort = 5678;
//        ServerSocket mockServerSocket = EchoServer.openServerSocketConnection(mockPort);
//        Socket mockClientSocket = mockServerSocket.accept();
////        when(mockClientSocket.getInputStream()).thenReturn(inputStream);
//        assertNotNull(IOSocketHandler.createClientInputReader(mockClientSocket));

        when(clientSocket.getInputStream()).thenReturn(inputStream);
        assertNotNull(IOSocketHandler.createClientInputReader(clientSocket));
    }
    @Test
    public void testClientSocketOutputCreated() throws IOException {
        final int mockPort = 5678;
        ServerSocket mockServerSocket = EchoServer.openServerSocketConnection(mockPort);
        Socket mockClientSocket = mockServerSocket.accept();
//        when(clientSocket.getOutputStream()).thenReturn(outputStream);

        assertNotNull(IOSocketHandler.createClientOutputWriter(mockClientSocket));
    }

    @Test
    public void testListeningForConnectionMessagePrinted() throws IOException{
        
    }
}
