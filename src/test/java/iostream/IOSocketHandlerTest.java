package iostream;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.*;
import java.io.IOException;
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
        when(clientSocket.getInputStream()).thenReturn(inputStream);

        assertNotNull(IOSocketHandler.createClientInputReader(clientSocket));
    }
    @Test
    public void testClientSocketOutputCreated() throws IOException {
        when(clientSocket.getOutputStream()).thenReturn(outputStream);

        assertNotNull(IOSocketHandler.createClientOutputWriter(clientSocket));
    }
}