package iostream;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class IOSocketHandlerTest {

    @Mock
    Socket clientSocket;

    @Mock
    InputStream inputStream;

    @Mock
    OutputStream outputStream;

    @Test
    public void testClientSocketInputInitialized() throws IOException {
        when(clientSocket.getInputStream()).thenReturn(inputStream);

    }
}