package iostream;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class IOSocketHandlerTest {

//    @Mock
//    InputStream inputStream;
//
//    @Mock
//    OutputStream outputStream;

    @Test
    public void testClientSocketInputCreated() throws IOException {
        ServerSocket mockServerSocket = mock(ServerSocket.class);
        when(mockServerSocket.accept()).thenReturn(clientSocket = new Socket());

        when(clientSocket.getInputStream()).thenReturn(inputStream);

        assertNotNull(IOSocketHandler.createClientInputReader(clientSocket));
    }
//    @Test
//    public void testClientSocketOutputCreated() throws IOException {
//        when(clientSocket.getOutputStream()).thenReturn(outputStream);
//
//        assertNotNull(IOSocketHandler.createClientOutputWriter(clientSocket));
//    }
}